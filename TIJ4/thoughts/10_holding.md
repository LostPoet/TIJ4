# Holding Your Objects

<img src="https://raw.githubusercontent.com/LostPoet/Images/master/TIJ4/Collection.png" title="容器简明逻辑图" width="100%">

1. 这里说的对象其实是指对象的引用
2. 数组提供了一种受编译器支持的容纳对象的方式, 这种方式优点是效率高, 不用担心类型转换, 并且支持基本类型, 缺点是长度固定, 因此要引入java.util包中的容器类(collection classes):List, Set, Queue, Map

## <span id="h2"> 泛型与类型安全的容器 </span>

1. 在Java SE5前的时代, 编译器会允许向容器中插入错误的类型(因为所有类都从Object类隐含继承), 这种错误往往要到使用了相应对象时才会以运行时异常的形式被发现(比如类型转换时向下downcast出错)
2. 泛型为容器提供了编译时的一层类型保障, 主要有以下要点:
   * 放入容器的对象要正确
   * 取出对象时自动的类型转换
   * upcast对泛型同样有效

## <span id="h3"> 容器基本概念 </span>

1. 分两大类, 它们都以interface的形式存在, 下面展示接口源码:
   * Collection: 继承Iterable, 子interface包括List, Set, Queue

    ```java
    public interface Collection<E> extends Iterable<E> {
        // Query Operations
        int size();
        boolean isEmpty();
        boolean contains(Object o);
        Iterator<E> iterator();
        Object[] toArray();
        <T> T[] toArray(T[] a);
        default <T> T[] toArray(IntFunction<T[]> generator) {
            return toArray(generator.apply(0));
        }

        // Modification Operations
        boolean add(E e);
        boolean remove(Object o);

        // Bulk Operations
        boolean containsAll(Collection<?> c);
        boolean addAll(Collection<? extends E> c);
        boolean removeAll(Collection<?> c);
        default boolean removeIf(Predicate<? super E> filter) {
            Objects.requireNonNull(filter);
            boolean removed = false;
            final Iterator<E> each = iterator();
            while (each.hasNext()) {
                if (filter.test(each.next())) {
                each.remove();
                removed = true;
                }
            }
            return removed;
        }
        boolean retainAll(Collection<?> c);
        void clear();

        // Comparison and hashing
        boolean equals(Object o);
        int hashCode();
        @Override
        default Spliterator<E> spliterator() {
            return Spliterators.spliterator(this, 0);
        }
        default Stream<E> stream() {
            return StreamSupport.stream(spliterator(), false);
        }
        default Stream<E> parallelStream() {
            return StreamSupport.stream(spliterator(), true);
        }
    }
    ```

   * List

    ```java
    public interface List<E> extends Collection<E> {
        // Query Operations
        int size();
        boolean isEmpty();
        boolean contains(Object o);
        Iterator<E> iterator();
        Object[] toArray();
        <T> T[] toArray(T[] a);

        // Modification Operations
        boolean add(E e);
        boolean remove(Object o);

        // Bulk Modification Operations
        boolean containsAll(Collection<?> c);
        boolean addAll(Collection<? extends E> c);
        boolean addAll(int index, Collection<? extends E> c);
        boolean removeAll(Collection<?> c);
        boolean retainAll(Collection<?> c);
        default void replaceAll(UnaryOperator<E> operator) {
            Objects.requireNonNull(operator);
            final ListIterator<E> li = this.listIterator();
            while (li.hasNext()) {
                li.set(operator.apply(li.next()));
            }
        }
        @SuppressWarnings({"unchecked", "rawtypes"})
        default void sort(Comparator<? super E> c) {
            Object[] a = this.toArray();
            Arrays.sort(a, (Comparator) c);
            ListIterator<E> i = this.listIterator();
            for (Object e : a) {
                i.next();
                i.set((E) e);
            }
        }
        void clear();

        // Comparison and hashing
        boolean equals(Object o);
        int hashCode();

        // Positional Access Operations
        E get(int index);
        E set(int index, E element);
        void add(int index, E element);
        E remove(int index);

        // Search Operations
        int indexOf(Object o);
        int lastIndexOf(Object o);

        // List Iterators
        ListIterator<E> listIterator();
        ListIterator<E> listIterator(int index);

        // View (Some methods has been omitted)
        List<E> subList(int fromIndex, int toIndex);
    }
    ```

   * Set

    ```java
    public interface Set<E> extends Collection<E> {
        // Query Operations
        int size();
        boolean isEmpty();
        boolean contains(Object o);
        Iterator<E> iterator();
        Object[] toArray();
        <T> T[] toArray(T[] a);

        // Modification Operations
        boolean add(E e);
        boolean remove(Object o);

        // Bulk Operations
        boolean containsAll(Collection<?> c);
        boolean addAll(Collection<? extends E> c);
        boolean retainAll(Collection<?> c);
        boolean removeAll(Collection<?> c);
        void clear();

        // Comparison and hashing (Some methods has been omitted)
        boolean equals(Object o);
        int hashCode();
    }
    ```

   * Queue

    ```java
    public interface Queue<E> extends Collection<E> {
        boolean add(E e);
        boolean offer(E e);
        E remove();
        E poll();
        E element();
        E peek();
    }
    ```

   * Map

    ```java
    public interface Map<K, V> {
        // Query Operations
        int size();
        boolean isEmpty();
        boolean containsKey(Object key);
        boolean containsValue(Object value);
        V get(Object key);

        // Modification Operations
        V put(K key, V value);
        V remove(Object key);

        // Bulk Operations
        void putAll(Map<? extends K, ? extends V> m);
        void clear();

        // Views
        Set<K> keySet();
        Collection<V> values();
        Set<Map.Entry<K, V>> entrySet();
        interface Entry<K, V> {
            K getKey();
            V getValue();
            V setValue(V value);
            boolean equals(Object o);
            int hashCode();
            public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K, V>> comparingByKey() {
                return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getKey().compareTo(c2.getKey());
            }
            public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K, V>> comparingByValue() {
                return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getValue().compareTo(c2.getValue());
            }
            public static <K, V> Comparator<Map.Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
                Objects.requireNonNull(cmp);
                return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
            }
            public static <K, V> Comparator<Map.Entry<K, V>> comparingByValue(Comparator<? super V> cmp) {
                Objects.requireNonNull(cmp);
                return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
            }
        }

        // Comparison and hashing
        boolean equals(Object o);
        int hashCode();

        // Default methods omitted here
    }
    ```

2. 在定义时确定容器的实际实现类型, 使用接口类型的引用完成后续操作, 改变实现时, 一般在定义处改变就行, 然而对一些特殊的实现类型, 比如LinkedList和TreeMap, 如需使用它们特定的方法, 则不能upcast到接口类型, 只能使用准确的类型

## 添加元素

1. Arrays.asList()使用数组或由多个参数指定的对象创建List对象, 两个注意点:
   * 此方法使用数组进行实现, 因此如果直接被拿来赋为List接口类型, 则无法进行插入和删除等操作
   * 只能识别出直接父类, 如果要识别更远的父类, 需要在方法名之前进行显式声明:

    ```java
        List<Type> list = Arrays.<Type>asList(/* args */);
    ```

2. Collections.addAll()的第一个参数指明待添加的Collection类型对象, 向其中添加数组或是多个参数, 此方法执行速度快, 通常创建一个空容器, 用此方法添加对象, 这种做法也能规避掉Array.asList()方法仅识别直接父类的问题
3. Collection类型的构造函数可以使用Collection类型的参数并据此初始化
4. Collection类型自带addAll()方法, 但此方法仅接受Collection类型的参数, 不接受变长参数

## <span id="h5"> 打印容器 </span>

1. 对数组而言, Arrays.toString()能够产出打印结果, 而容器通过toString()函数自带打印效果
2. LinkedList比ArrayList的方法更多
3. HashSet查找元素的速度最快用哈希函数实现, TreeSet依比较顺序对元素进行排序用红黑树实现, LinkedHashSet保持元素的插入顺序用哈希函数提高查找速度, Map相应类型同理

## <span id="h6"> List </span>

1. List继承自Collection, 最重要的区别是添加了获取位置并根据位置来进行增删查改的方法
2. equals()决定了许多方法的行为, 如contains(), remove(), indexOf(), retainAll()等, 当List容纳自定义的对象时, 行为符合常识, 但当List容纳String类型或是基本类型的wrapper类型时, equals()根据内容来判别是否相同, 此时可能会产生意想不到的行为, 另外, 在使用下标时不需要考虑equals()的问题
3. 注意List的两种实现方式ArrayList和LinkedList, 在适当的情形下进行选取, 这实际上是一个性能优化问题
4. subList()所产生的子表是基于原表的, 因此改变一个, 另一个也会发生相应改变
5. 对List有一个重载的addAll()方法, 可以指定插入位置
6. Collection类型自带toArray()方法, 该方法有两种重载形式, 第一种不带参数的返回Object类型数组, 第二种给定一种数组, toArray()将返回这种类型的数组, 如果给定的数组空间不足, 该方法会重建一个大小合适的数组

## <span id="h7"> Iterator </span>

1. *Iterator Pattern*是为容器编写通用方法的有力借助点, iterator常被称作容器的统一接入
2. Iterator属于轻量级对象, 有四个基本点:
   * Collection类型自带iterator()方法, 该方法返回一个iterator, 此iterator准备但尚未返回第一个元素
   * 返回序列中的下一对象next()
   * 检查序列中是否还有对象hasNext()
   * 删除上一个被返回的对象remove(), 该方法并非必须实现, 但Java库中的容器类型都进行了实现
3. 如果不对序列进行修改, 那么forEach语法会更加简洁

### <span id="h8"> ListIterator </span>

1. ListIterator只能由List类型来产生, 相较于Iterator有四个不同:
   * 双向的, 多了hasPrevious(), 和previous()方法, 注意previous()返回cursor所指的前一元素, 并将cursor前移(cursor的初始位置在第一个元素之前)
   * nextIndex(), previousIndex()用于获取前后的下标, 这里previousIndex()获取的实际上就是当前下标, nextIndex()获取下一个下标
   * 可以修改最后返回的对象, 使用set(), 这里可以看出ListIterator还能改变元素, 这是Iterator做不到的
   * 重载的构造函数, 除了默认的, 还有一个ListIterator(n), 制定下标为n的地方为起始点

## <span id="h9"> LinkedList </span>

1. 梳理一下方法结构, ==表示等价, ≈≈表示相似, 其中相似的区别是左边的返回异常NoSuchElementException, 右边的返回null:
   * 取: getFirst() == element() ≈≈ peek()
   * 头部删除: removeFirst() == remove() ≈≈ poll()
   * 尾部增加: addLast() == add() ≈≈ offer()
   * 尾部删除: removeLast()
   * 头部增加: addFirst()
2. 可以注意一下Queue的接口方法, 和LinkedList特有方法有重合, 因此用LinkedList实现起来比较方便

## <span id="h10"> Stark </span>

1. LinkedList可以直接当做Stark来用, 但意义不明, net.mindview.util包里的Stark类展示了用泛型定义类的基本方法, 使用LinkedList实现了一个可用的Stark类
2. java.util.Stark类使用了继承实现栈, 被证明是相当错误的做法
3. 在import的时候精确导入net.mindview.util.Stark类可以不必输入全名

## <span id="h11"> Set </span>

1. Set一般用于查找, 测试某对象是否属于某一集合, 常用的一个实现HashSet可以提高查找效率
2. Set的接口方法基本和Collection一致, 属于典型的继承和多态用法, 表达不同的行为
3. net.mindview.TextFile类能够打开并读取一个文件到Set之中, 继承自List\<String>, 其构造函数的第一个参数指示文件名, 第二个参数用正则表达式指示规则, 这个类对象能直接赋给TreeSet的构造函数
4. String.CASE_INSENSITIVE_ORDER返回一个comparator, 可用作TreeSet构造函数的参数

## <span id="h12"> Map </span>

1. get(key), put(key, value)是Map的基本方法, 当不存在对应键值时get()将返回null
2. containsKey(), containsValue()用于检测键值或普通值是否在Map中
3. Map可以通过泛型参数拓展到多个维度
4. Map可以产生键值的Set, 通过KeySet(), 可以产生普通值的Collection, 通过values(), 也可以产生键值对的Set, 通过entrySet()

## <span id="h13"> Queue </span>

1. 通常被用作缓冲区域
2. Queue接口方法总结:
   * offer()是针对于Queue的方法, 尾部插入元素, 失败返回false
   * peek()和element()都仅返回头部元素, 失败时, 前者返回null, 后者抛出NoSuchElementException
   * poll(), remove()删除并返回头部元素, 失败时的情形同上
3. Queue具有一套独立且完整的功能体系, 不依赖于父类接口Collection的方法

## <span id="h14"> PriorityQueue </span>

1. FIFO可以说是最典型的排序准则, 在优先级队列中, 排序依据预先设定的优先级, 这个优先级要么是自然序*natural order*, 要么是自行提供的comparator指示的顺序
2. 在队列中顺序可能在两种时刻被决定, 一种是放入时, 一种是取出时, 具体何时取决于优先级是否会变化
3. 优先级队列中允许重复, 空格被视为是字符, 优先级排在字母之前, Collections.reverseOrder()产生一个逆自然序的comparator
4. 对自定义的类对象, 需要添加新功能来产生自然序, 或者提供一个comparator, 否则无法放入优先级队列

## <span id="h15"> 关于Collection和Iterator的讨论 </span>

1. Collection接口用来描述序列容器间的一致性, java.util.AbstractCollection类提供了对该接口的默认实现, 因此用户可以选择继承此抽象类来减少不必要的代码重复
2. interface的一大功用便是用来编写通用性更高的方法, 对容器来说, 可以以Collection类型做参数编写通用方法, 也可以实现iterator()方法来完成通用操作, 后者是C++中的做法, 因为C++中没有Collection这个公共接口, 但这两种方法并非平行关系, Collection实现了Iterable接口类型, 因此实现Collection就意味着实现了iterator()
3. 对适用于Collection对象的方法来说, 这两种方式可以被认为是等价的, 但考虑另一种情况, 当你有了通用方法, 并且想要编写一个适用于该方法的新类, 这时如果可以选择实现Collection接口, 这显然相当复杂, 或者继承AbstractCollection类, 但这同样会带来冗余的size()方法(该方法支持AbstractCollection中的其他方法, 但很有可能用户并用不到), 所以直接实现iterator()是最为方便的做法

## <span id="h16"> Foreach的实质 </span>

1. 目前数组和Collection类型的对象都可以使用foreach进行遍历
2. Java SE5引入了一个接口Iterable, 该接口唯一的抽象方法是iterator()方法, foreach的实质便是使用了该接口来进行遍历
3. 因此Iterable或是数组都会导致foreach生效, 但是Iterable与数组之间并无联系, 数组类型不会自动转为Iterable, 同时显然Iterable不是数组类型
4. System.getenv()能够返回一个包含环境变量的Map

## <span id="h17"> "Adapter Method" </span>

1. 当你实现了Iterable, 但是却想要另外一种实现, 并且不影响原有的实现, 这时显然不能override已有的方法, 而是用到"适配方法", 编写一个接口类型, 同时在该接口类型中添加新的方法返回一个Iterator
2. Collections.shuffle()方法可对Collection容器进行随机洗牌, 这时可以重新建立一个引用容器(比如在外部包裹ArrayList), 对这个容器洗牌将不影响原有的容器顺序

## 练习题

| 题号  |小节| 描述                                                              |
| :---: | --|----------------------------------------------------------------- |
|   1   |[泛型与类型安全的容器](#h2)| ArrayList类型, 最基本的容器用法, 放和取                           |
|   2   |[容器基本概念](#h3)| holding/SimpleCollection.java的Set类型版本, 放和取                |
|   3   |[容器基本概念](#h3)|innerclasses/Sequence.java的容器实现, 不限长序列                  |
|   4   |[打印容器](#h5)|net/mindview/util/Generator\<T>接口的一个实现, 无限顺序产生字符串 |
|5|[List](#h6)|holding/ListFeature.java中的类型由Pet换为Integer, equals()不同引发的不同效果, 注意重载方法的参数类型|
|6|[List](#h6)|类型转为String, 其他同上|
|7|[List](#h6)|asList()和subList()分别以参数提供的数组和List作为实现基础, 因此返回的结果改变会引起原数组或List发生改变, 另外要注意removeAll()这类方法会引起的异常|
|8|[Iterator](#h7)|对本章第1题的类型容器使用Iterator进行遍历|
|9|[Iterator](#h7)|innerclasses/Sequence.java相当于自己编写的容器类, 对这个可以自行实现iterator()方法|
|10|[Iterator](#h7)|对*Polymorphism*章节第9题的已有继承体系使用Iterator进行遍历, 并且产生多态效果|
|11|[Iterator](#h7)|对Collection类型编写通用方法, 使用Iterator, 借用了本章第4题的生成器|
|12|[ListIterator](#h8)|ListIterator的基本用法, 进行一个List的原地反转|
|13|[LinkedList](#h9)|将innerclasses/controller/Controller.java中的ArrayList换为LinkedList并用Iterator进行遍历|
|14|[LinkedList](#h9)|总是在一个LinkedList的中间进行插入, 注意如何实现的|
|15|[Stark](#h10)|用Stark为表达式求值, 本例没有错误处理机制|
|16|[Set](#h11)|应用Set不重复的特性和查找的功能|
|17|[Map](#h12)|联系本章第1题, Map基本用法|
|18|[Map](#h12)|用到了net.mindview.util.Countries类, HashMap和LinkedHashMap容器中元素的顺序的不同之处, Arrays.sort(), Collection类型的toArray()值得注意|
|19|[Map](#h12)|把Map换为Set, 其余同上, 本题应当是关于Set的|
|20|[Map](#h12)|这里用Map类型记录单词中和文件里每个元音的个数, 而不是简单的int类型|
|21|[Map](#h12)|Map的基本用法, 记录文件中单词的频度, 用到了Collections.sort()方法以及比较器String.CASE_INSENSITIVE_ORDER|
|22|[Map](#h12)|重复前一题的功能, 对自定义类使用自定的equals()和comparator完成对象间的比较|
|23|[Map](#h12)|用histoUnit实现Comparable接口中的compareTo()方法, 能够使用Collections.sort()排序|
|24|[Map](#h12)|第18题的LinkedHashMap版本|
|25|[Map](#h12)|统计文件中每个单词出现的位置, Map对象对容器的一个范例|
|26|[Map](#h12)|根据文件中每个单词出现的位置, 复现每个单词, 使用了倒置的Map|
|27|[Queue](#h13)|Queue的基本应用|
|28|[PriorityQueue](#h14)|double类型在PriorityQueue有自然序|
|29|[PriorityQueue](#h14)|PriorityQueue中无法插入未定义比较顺序的类对象|
|30|[关于Collection和Iterator的讨论](#h15)|重写holding/CollectionSequence.java, 体会直接实现Collection的复杂, 注意System.arrayCopy()方法|
|31|[Foreach的实质](#h16)|重写polymorphism/shape/RandomShapeGenerator.java, 使其实现Iterable接口, 并能够使用foreach语法进行遍历|
|32|["Adapter Method"](#h17)|"Adapter Method"最基本的应用, 为一个实现Iterable的类添加不同的遍历方式|
