# Type Information

1. RTTI(*Runtime Type Information*)指在运行时发现与使用类型信息, 它有几个注意点:
   * 解放编译时的类型限制, 编制更加强大的程序
   * 涉及面向对象概念中一些十分有趣的问题
   * 引出了如何组织Java程序这一基本问题
2. 传统的RTTI意味着所有类型在编译时已知, 而反射机制则是完全在运行时发现与使用类型信息

## 为何需要RTTI

1. 对于经典的Shape继承树, 从放入和取出`List<Shape>`容器都发生了什么?
   * 放入Shape的子类对象: upcast, 子类丢失了它们的特殊信息, 而被认为是Shape类型
   * 取出子类对象: 在容器实际上存放的是Object类型的前提下, 自动将容器内对象转成Shape类型
   * 取出时不进一步转为子类的原因: 取出时的已知信息仅为容器中包含Shape类型对象, Java泛型在编译时保证了这点, RTTI则在运行时将Object转为Shape, 而这是RTTI一个最简单的应用范例
2. 多态作为一种设计目标, 是使用尽可能少的信息来产生正确的行为(通过*late binding*), 这对于解决通用问题比较有效, 但对于需要特定类型信息的问题, 多态就无能为力了, 以Shape继承树为例, 当想要选取特定几种形状时, 必须获得进一步的类型信息

## Class对象

1. Class对象用于在运行时表示类型信息, 并且能够创建普通对象, 并且包含了实际类的静态域, RTTI的操作也通过Class对象来完成, 例如cast操作
2. 编译过程将源码变成.class文件, 与每个类对应, 但要创建对象的话, 需要类装载器, 而这是JVM的一个子系统
3. 类装载器这个子系统实际山包含了一个装载链, JVM中包含了初始装载器, 这个装载器会装载所有**被信任类**(包括Java API类), 这种类一般存放在硬盘中, 如果有特殊需求(如跨网络加载类)这时也可通过钩子机制添加额外的链
4. 要注意装载过程实际上是一个动态过程, 只有在引用了静态成员才会触发装载, 同时注意构造函数的隐式静态, 这种机制决定了Java程序在开始执行时并没有被完全装载, 这一点和静态装载的语言(如C++)完全不同
5. Class类中的一些重要方法:

    ```java
    // 根据类名创建Class对象并返回引用, 无需相应实际对象
    // 如果该Class对象没有被装载, 则装载之
    // 在有实际对象的情况下, 可用Object类方法getClass()得到Class对象引用
    public static Class forName(String) throws ClassNotFoundException

    // 返回名称
    public String getName()
    public String getPackageName()
    public String getSimpleName()
    public String getTypeName()
    public String getCanonicalName()

    // 接口信息
    public native boolean isInterface()
    public Class[] getInterfaces()
    public Type[] getGenericInterfaces()
    public AnnotatedType[] getAnnotatedInterfaces()

    // 调用默认构造函数生成对象
    // 想调用其他构造函数要用到反射
    public Object newInstance() throws InstantiationException, IllegalAccessException
    ```

### 类字面常量

1. 类字面常量能够返回Class对象的引用, 并且会在编译时完成类型检查, 不会抛出异常, 不会对实际对象进行初始化, 适用于普通类, 接口, 数组以及基本类型, 同时注意基本类型中的TYPE常量会产生相同效果
2. 在为一个普通类分配堆空间之前要经历哪些步骤?
   * 装载(*loading*): 由装载器完成, 寻找字节码, 并且通过字节码创建Class对象
   * 连接(*linking*): 主要分三步, 首先确认字节码的完整性和安全性, 之后为实际对象的静态区域分配存储空间, 最后解析实际对象的引用(这一步涉及JVM原理, 不深究)
   * 初始化(*initialization*): 如果有父类, 先对父类进行静态初始化, 然后对当前实际对象进行静态初始化
   * 要注意第三部初始化尽可能延后, 直到第一次引用实际类的静态域
3. 静态域中, 被声明为static和final的常量可能是编译常量, 不会触发初始化, 但要注意不是所有static和final的量都是编译常量, 同时仅有static的量一定触发初始化

### 泛型引用

1. Class引用指向Class对象, Java SE5之后, 泛型提供了一种限制引用所指向特定类别的Class对象的机制, 这种机制在编译时完成类型检查
2. 对于存在继承关系的父类和子类, 它们所对应的Class对象并不存在继承关系, 因此想要放松泛型的类型限制, 使用父类不是一个可行的办法
3. 针对上述问题, 通配符'?'派上了用场, 注意虽然通配符泛型参数和无参数效果相同, 但后者会产生一个编译警告, 因为这代表没有为Class引用添加限制, extends关键字可以和'?'搭配来增加限制
4. `newInstance()`方法会返回由泛型参数限制的类型引用, 但要注意当使用`getSuperclass()`时仅允许`Class<? extends Type>`的类型, 因此用此引用仅能生成Object类型的引用

### 新的cast语法

1. cast()方法能够将传入的对象转为当前Class对象对应的实际类型

## cast前的检查

1. 截至目前有两种RTTI的应用形式:
   * cast: RTTI保证类型转换的正确性, 如不正确将抛出ClassCastException异常
   * 使用Class对象表示实际对象的类型, 可以询问Class对象类型信息
2. C++中没有RTTI, 因此对于编译器来说, 仅仅需要按照类型转换的字面意思来进行转换, 不执行检查, 而在Java中, upcast省略了类型转换的语法, 因为编译器能够看到父类, downcast则必须显式说明, 同时编译器也会检查downcast的合理性, 因此不能downcast到一个不是子类的类中去(但是当downcast到非自身的子类还是会引发异常)
3. RTTI的第三种形式: `reference instanceof Type`检查引用所指的对象是不是特定类型(及其子类), 提供了在downcast之前进行检查的一种途径, 否则可能导致ClassCastException异常, 最常见的用法是对所有子类都使用一遍instanceof语句, 因为无法直接与子类的数组比较, 这种方法的写法比较冗长
4. 本节示例程序中的细节:
   * typeinfo/pets/PetCreator.java用到了*Template Method*设计模式
   * typeinfo/pets/ForNameCreator.java中static语句块不能用注解, 因此套在了方法体内

### 使用类字面常量

1. 对比typeinfo/pets/ForNameCreator.java和typeinfo/pets/LiteralPetCreator.java容易发现, 使用类字面常量来实现PetCreator更加简洁, 且不会抛出异常

### 动态instanceof关键字

1. `Class.isInstance()`方法可用来动态测试对象的类型, 消除了前面方法的冗余
2. net/mindview/util/MapData.java接受Iterable和常量作为参数, 返回一个LinkedHashMap的子类`MapData<K, V>`
3. typeinfo/PetCount3.java相较于typeinfo/PetCount.java的优越之处一方面在于count()方法使用了动态instanceof, 语句简洁, 另一方面在于当添加新类时count()方法体不用修改, 仅修改类字面常量的数组即可

### 递归计数

1. typeinfo/PetCount3.java虽然有不小进步, 但是需要预先加载包含全部Pet种类的Map, 否则就只能对继承树叶子类别进行计数, net/mindview/util/TypeCounter.java使用了`Class.isAssignableFrom()`方法实现了一个通用的类型计数器, 该方法能够验证对象是否存在于继承体系中, 注意`countClass()`方法是递归方法

### 注册工厂

1. 如果向Pet的继承体系中添加了新类, 那么就必须向typeinfo/pets/LiteralPetCreator.java中的List添加相应的Class对象, 一个解决方案是在根类中通过static语句块中完成表的构造, 这样每次添加新类后, 比较容易记得在根类里面添加类对象(也可以自己写个源码分析器, 不用手动向表中添加元素)
2. 另一点是PetCreator是直接创建实际对象的, typeinfo/RegisteredFactories.java则使用了Factory Method设计模式, 将对象的创建交给了公开的嵌套内部类, 根类中的代码仅仅需要处理相应的工厂容器即可

### instanceof 对比 ==与equals()

1. instanceof关键字检查对象是否是某一类型或其子类, 而==与equals()则用于判断两个Class对象的类型是否完全一致, 不考虑继承