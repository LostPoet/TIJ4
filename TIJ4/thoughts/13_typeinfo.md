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

## 注册工厂

1. 如果向Pet的继承体系中添加了新类, 那么就必须向typeinfo/pets/LiteralPetCreator.java中的List添加相应的Class对象, 一个解决方案是在根类中通过static语句块中完成表的构造, 这样每次添加新类后, 比较容易记得在根类里面添加类对象(也可以自己写个源码分析器, 不用手动向表中添加元素)
2. 另一点是PetCreator是直接创建实际对象的, typeinfo/RegisteredFactories.java则使用了Factory Method设计模式, 将对象的创建交给了公开的嵌套内部类, 根类中的代码仅仅需要处理相应的工厂容器即可

## instanceof对比Class等价判断

1. instanceof关键字检查对象是否是某一类型或其子类, 而==与equals()则用于判断两个Class对象的类型是否完全一致, 不考虑继承

## 反射:运行时类型信息

1. RTTI的一大限制是所用的类型必须对编译器可见, 程序无法获知未被编译的类型信息

2. 运行时获知类型信息的应用场景:
   - GUI编程
   - RMI(*Remote Method Invocation*), 分布式计算场景
   
3. Class类支持反射的概念, 对应的库是java.lang.reflect, 一些常用的类如Field, Method和Constructor由JVM负责在运行时创建, 用于表示未知类的信息, 这些类都是Member接口的实现类, 下表展示了这些类相关的常用方法

   | 作用                                                | 方法名                                       |
   | --------------------------------------------------- | -------------------------------------------- |
   | 使用Class对象创建Field, Method或Constructor\<T>对象 | getFields(), getMethods(), getConstructors() |
   | 操作Field对象                                       | get(), set()                                 |
   | 操作Method对象                                      | invoke()                                     |
   | 操作Constructor对象                                 | newInstance()                                |

4. 反射机制并不神秘, 和RTTI一样, 它同样需要JVM装载对应的.class文件, 但它们的本质区别是RTTI在编译时打开和检查.class文件, 而反射通过运行时环境打开和检查.class文件

### 一个类方法提取器

1. 反射一般被应用在支持一些Java特性如JavaBeans或对象序列化上, 但可被用于动态提取类信息
2. typeinfo/ShowMethods.java能够提取类中所有方法, 包括那些继承而来的, 或是自动生成的方法, 如默认构造函数, 注意在此程序中, 待提取类由args[0]指定, 编译时并不确定

## 动态代理

1. **代理**(*Proxy*)是一种基本的设计模式, 意即使用一个对象替代原本的"真实"对象, 这个对象会进行一些额外的或不同的操作, 同时这个对象能够和"真实"对象进行通信, 扮演着"中间人"的角色

2. typeinfo/SimpleProxyDemo.java展示了最简单的**代理**实现, RealObject和SimpleProxy都继承了Interface接口, 这使得它们都实现了Interface包含的方法, 能够进行相同的方法调用, 另一方面, SimpleProxy类内部使用了**委托**(*Delegation*)的设计模式来完成与RealObject之间的通信, 这个例子说明代理的目的是对一些操作进行封装, 以便于进行切换

3. **动态代理**(*Dynamic Proxy*)是**代理**的升级版, 动态二字体现在两点, 一是动态地创建代理对象, 一是动态地处理代理对象的方法调用, 一旦有调用发生, 这个调用就会被重定向到一个统一的**调用处理器**(*Invocation Handler*)中, 调用处理器的工作主要是接收这个调用, 并安排一些操作, 同时调用处理器还负责与"真实"对象的通信工作, 这一方面也通过**委托**来进行实现

4. 剖析一下**代理**与**动态代理**的区别: 在**代理**当中, 代理对象的创建是"静态"的, 该代理对象所实现的接口已经预先设定好了, 另一方面, 代理对象所代理的"真实"对象和它所进行的操作都是绑定在一起的, 无法被更改, 而在**动态代理**当中, 创建的是动态代理对象, 这些对象预先会实现哪种接口是不确定的, 要自行设定, 另外, 动态代理对象可以和不同的**调用处理器**进行绑定, 而调用处理器还可以接收不同的"真实"对象, 也就是说操作也完全是动态的

5. typeinfo/SimpleDynamicProxy.java是SimpleProxyDemo.java的动态代理版本, 这里主要关注以下两方面:

   * 动态创建代理对象: 

     ```java
     // 代理对象需要三个参数, 并在最后强制转型为接口类型
     // 参数1: 这个ClassLoader定义了代理对象的类, 一般传待转型类型的ClassLoader即可
     // 参数2: 传代理对象要实现的接口所对应的类对象
     // 参数3: 待绑定的调用处理器
     Interface proxy = (Interface)Proxy.newProxyInstance(
         Interface.class.getClassLoader(),
         new Class[]{ Interface.class },
         new DynamicProxyHandler(real));
     ```

   * 调用处理器: 

     ```java
     class DynamicProxyHandler implements InvocationHandler {
         // 使用委托完成与"真实"对象的通信
         private Object proxied;
         public DynamicProxyHandler(Object proxied) {
             this.proxied = proxied;
         }
         // 调用处理器截获了对代理对象的调用, 这个调用最终重定向到了这里
         // 注意: 在invoke()内部对代理对象进行调用, 仍会重定向, 要十分小心
         // 参数1: 代理对象本身
         // 参数2: Method对象, 表示代理对象所调用的方法
         // 参数3: 上述方法的参数列表
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
             // 进行一些操作
             return method.invoke(proxied, args);
         }
     }	
     ```

6.  以上仅仅是动态代理的一个简单介绍, 动态代理一般可用来针对接口里的不同方法进行一些不同的处理, 比如typeinfo/SelectingMethods.java里展示的一种简单情况, 当接到interesting()方法调用时, 打印出一条额外的信息

## 空对象

1. **null**指示对象的缺失, 多数情况下我们仅能对其进行测试这种操作, 如果执行一些具体的操作则会造成NullPointerException, 为了不引发异常, 频繁测试**null**的代码可能会造成冗余, 这时需要一种空对象(*Null Object*)来表示对应类型对象的缺失, 这种对象能够像正常对象一样回应操作, 不需要编写检查代码来避免异常

2. 要注意空对象并不适用于所有情况, 一些情况下检查**null**是可容忍的, 或者能够确定引用不可能为**null**, 甚至有时可能需要捕获NullPointerException, 空对象的应用场景是与数据本身相关度较高的

3. 即使使用了空对象, 测试缺失的需求还是存在的, 一般来说可定义Null接口来辅助判断(使用`instanceof`), 让所有具体的空对象实现此接口, 这样一来, 便无需在类中定义类似于`isNull()`这类方法

   ```java
   public interface Null {}
   ```

4. 一般情况下, 可使用单例设计模式(*Singleton*)来创建空对象, 这样便无需每次创建不同的对象, 这里顺便讨论一下不变性(*immutable*), String类型是具有不变性的, 每次改变String的内容时, 并不能将对象里的字符进行修改, 只能创建新的String对象, 并且赋给原来的引用, 在typeinfo/Person.java中的Person类也具有不变性, 所以替换Person类型的空对象的唯一途径就是新建一个有意义的Person对象, 并且替换掉空对象, 最后, 如果采取了这种方式创建空对象, 那么测试缺失的途径便多了`== `和`equals()`

5. typeinfo/Person.java处理的是普通类, 定义空对象时用到了嵌套内部类, 在处理接口时, 情况有所改变, 因为一个接口有着不同的实现, 因此要对每个实现都创建一种空对象, 这时显然无法采取嵌套内部类的单独定义方式, 而要用动态代理, 可以灵活地创建不同类型的空对象, 并减少代码的重复, typeinfo/NullRobot.java展示了此方法

### 模拟对象与桩

1. 模拟对象(*Mock Object*)与桩(*Stub*)在逻辑上和空对象很相似, 都是用来代替"真实"对象, 所不同的是, 它们用来传递实际的信息, 而不是像空对象一样仅表达缺失, 模拟对象常被用来做某一具体项目的测试用例, 而桩存有大量数据, 可在不同项目间复用

## 接口与类型信息

1. 接口的一大功用是分离组件, 降低耦合, 但是作为引用类型的接口并不能掩盖其实现对象的类型信息, 有许多方法都能够绕过接口, 去获取对象的实际类型, 甚至进一步访问对象中非接口的部分

2. typeinfo/InterfaceViolation.java中实现类B位于同一包内, 此时绕过类型信息的方法如下:

   ```java
   // 类B实现接口A, B中包含了额外方法g()
   A a = new B();
   // 使用getClass()方法获取实现类的Class对象, 进而获知其名称
   System.out.println(a.getClass().getName());
   // 利用RTTI的典型方法构造了B类型的引用
   if(a instanceof B) {
       B b = (B)a;
       b.g();
   }
   ```

3.  当不在同一包内时, 上述方法中的B类型不可见, 也就无法构造出B类型的引用, 此时要使用反射机制才能够绕过接口的限制, 调用到实现类型中的方法, typeinfo/HiddenImplementation.java中详细展示了这一过程, 这种做法的关键点在于获知实现类中的方法名称, 如果没有`.java`源码, 可以通过如下命令进行获取:

   > javap -private SimpleClassName

4. typeinfo/InnerImplementation.java以及typeinfo/AnonymousImplementation.java说明了把实现类做成私有内部类或者匿名内部类都不能阻止反射获取到实现类的类型信息, typeinfo/ModifyingPrivateFields.java证明对于域来说, 访问限定符同样不能阻止反射, 然而对于声明为`final`的域, 反射仅仅获取其信息, 而不能够改变其值, 更确切地, java运行时能够接受改变该域的方法调用而不报错, 但是并没有任何实际效果 

## 总结

1. RTTI能够揭示父类引用掩盖下的实际对象的类型信息, 如果事先不了解多态的概念, 就容易被误用, 比如想实现多态方法, 也可使用RTTI确定子类类型并结合switch语句来调用具体的方法, 但这种做法显然不如多态来的简洁有效, 因此记住面向对象的一句箴言: 能用多态便用多态, 仅在需要时使用RTTI
2. 如果使用其他人提供的多态, 父类中包含的方法可能不够用, 这时无法直接修改源码, 但可以直接继承父类, 自己进行改写, 并在用到自己编写的特性时进行向下转型, 这种做法并不会影响到原有的继承体系和可拓展性
3.  如果仅仅处于方便某一子类的目的便在父类中添加新特性, 那就意味着对其他类来说这个新特性(方法)是无意义的, 这时可以对该子类添加针对性的方法, 并在使用时使用RTTI, 但是有些时候在父类中添加更通用的特性(子类特性的超集)也是一种解决办法
4. RTTI还可能解决一些效率问题, 对于那些多态表现不好的类, 可以编写特定方法解决
5. 反射的本质是把静态变为动态, 把编译检查变为运行时的异常处理, 这并不会导致代码的出错率提高, 这种动态的机制反而是将Java和静态语言如C++区别开来的重要标志

## 练习题

|      |      |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |

