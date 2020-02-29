# Strings

## String类型的不变性

1. String类型具有不变性, 如果查阅JDK文档, 容易发现库方法如果改变了String对象的内容则返回一个全新的String对象引用, 如果没有改变String内容, 那么将返回原对象的引用, 这样节省了存储空间并且减少了开销

## 重载的'+'对比StringBuilder

1. 再次强调Java中的重载运算符只有'+'和'+=', 并且这种做法使得运算符重载的使用反而比C++中更为方便
2. '+'的实现使用了隐含的StringBuilder(编译器优化), 这样减少了String对象的创建, 使得开销降低
3. 但当存在循环时, 编译器并不能正确处理'+', 在每次循环中均会创建新的StringBuilder对象, 这时需要自行使用StringBuilder完成字符串的构建
4. 如不确定是否创建了过多的StringBuilder, 运行javap
5. 在Java SE5之前, StringBuffer常用于构建字符串, 但此类确保了线程安全因此开销较大, StringBuilder更快速, 其常用方法包括`insert()`, `replace()`, `substring()`, `reverse()`, `append()`, `toString()`及`delete()`

## 无意的递归 

1. 下面覆写的`toString()`方法会无限递归, 最终导致栈溢出异常, 原因就是重载的'+'隐含地自动调用`toString()`方法本身, 要解决此问题, 通过`super.toString()`调用Object中的同名方法即可

    ```java
    String toString() {
        return " InfiniteRecursion address: " + this + "\n";
    }
    ```

## String类型的操作

1. [String类的JDK文档](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/String.html)
2. 书p511

## 格式化输出

1. Java SE5添加了格式化输出的新特性, 对PrintStream和PrintWriter对象可以使用`printf()`或`format()`方法完成这一功能

### Formatter类

1. 存在于java.util包中, 功能是将格式字符串和数据转换成想要的结果, 可以在其构造函数参数中传递输出目的地, 常用的目的类有PrintStream, OutputStream及File
2. [Formatter类的JDK文档](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/Formatter.html)

### 格式声明

1. 格式声明的一般语法:
    > %[参数下标$][标志][宽度][.精度]转换类型
    > 宽度: 指定该区域的最小长度, 不足则用空格补足, 默认右对齐, 对任意类型这里表示的含义相同
    > 精度: 对不同转换类型有着不同含义, 对字符串, 表示可打印的最大字符个数, 对浮点数, 表示小数点后的位数(默认为6), 越界则舍入, 不足则补零, 对整型数, 精度没有意义, 因此如果使用会得到异常
    > 标志: 覆写对齐方式, '-'表示左对齐

2. 常用的转换类型表:
    | 转换字符 | 描述                   |
    | :------: | ---------------------- |
    |    d     | 十进制整型数           |
    |    c     | Unicode字符            |
    |    b     | 布尔值                 |
    |    s     | 字符串                 |
    |    f     | 十进制浮点数           |
    |    e     | 科学计数法表示的浮点数 |
    |    x     | 十六进制整型数         |
    |    h     | 十六进制哈希值         |
    |    %     | '%'符号本身            |

3. strings/Conversion.java展示了不同类型值放入不同转换类型时的表现
4. 任意类型都能转换为"%b"进行输出, 对于非布尔类型, 只要该类型存在对象, 输出便为true, 否则为false, 注意和0等一些容易混淆的值区分
5. 更多玩法参见Formatter类的JDK文档

### String.format()

1. 同C语言中的sprintf(), 返回值为String类型, 底层使用Formatter实现

### 十六进制产生工具

1. net/mindview/util/Hex.java能够将byte数组转换为十六进制输出, main()函数测试时用到了net.mindview.util.BinaryFile

## 正则表达式

1. 之前, Java中处理字符串的类主要是String, StringBuffer及StringTokenizer, 功能相当受限

### 基础

1. 基本语法:
   * ?: 如-?表示可能有'-'也可能没有'-'
   * \\\\type: 在其他语言中, 单斜杠即表示插入正则表达式, 而Java中'\\\\'表示插入了正则表达式, 后面所跟的字符将具有特殊含义, 或者也可用于将正则表达式的内置符号转为字面意思, 但注意类似于'\\n\\t'这类转义字符, 单斜杠便已足够
   * *: 表示不存在或多个
   * +: 表示一个或多个
   * |: 表示或
   * (): 打包表达式
2. String类中的`split(String regex))`可按正则表达式分割字符串, 表达式本身代表的部分将不包含在分割后的结果中, `replaceFirst()`及`replaceAll()`可替换第一处或所有正则匹配出现的地方

### 建立正则表达式

1. [java.util.regex.Pattern](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/regex/Pattern.html)

### 量词

1. 量词描述了模式如何匹配文本:
   * Greedy: 默认
   * Reluctant: 最小匹配
   * Possessive; 舍弃了中间状态, 因此无法回溯, 能提高匹配效率

### CharSequence

1. [官方定义](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/CharSequence.html)
2. 实现此interface的类包括: CharBuffer, Segment, String, StringBuffer, StringBuilder

## Pattern和Matcher

1. 参阅Pattern文档Pattern和Matcher使用上的描述, 大概分三个层次:
   * 编译: 根据正则表达式, 构建Pattern
   * 匹配: 提供待匹配字符串, 寻找match
   * 操作: 围绕匹配处进行一些操作
2. Pattern值得注意的方法:

    ```java
    // 编译正则表达式, flags为bit mask形式
    public static Pattern compile(String regex)
    public static Pattern compile(String regex, int flags)

    // 生成Matcher对象
    public Matcher matcher(CharSequence input)

    // "一次性正则表达式"匹配
    public static boolean matches(String regex, CharSequence input)

    // limit == (进行匹配的次数+1)
    public String[] split(CharSequence input, int limit)

    // 相当于limit == 0
    public String[] split(CharSequence input)
    ```

3. Matcher的三种基本操作:

    ```java
    // 匹配整个输入的字符串
    public boolean matches()

    // 从开头匹配整个字符串, 可以不完整
    public boolean lookingAt()

    // 返回下一匹配位置, start指示起始点
    public boolean find()
    public boolean find(int start)
    ```

### Groups

1. Group 0表示整个表达式所匹配的内容, 后面依次是表达式中每个括号所划定的子式所匹配的内容
2. Matcher类中关于Groups的一些方法:

    ```java
    // 返回在Pattern中包含几个Group, Group 0不计算在内
    public int groupCount()

    // 返回Group的内容
    public String group()
    public String group(int group)

    // 返回上次成功匹配的下标, end()返回最后一个匹配字符下标加1
    public int start(int group)
    public int end(int group)
    ```

3. 关于零宽断言(*zero-width positive/negative lookahead/lookbehind*):
   * 零宽意味着位置, 即字符之间的空隙
   * *positive/negative*控制语句的真假, *lookahead/lookbehind*控制语句判断的方向
   * 断言的意思是这个语句为真时整个正则表达式继续进行匹配, 否则匹配失败

### start()和end()

1. 注意end()返回的是匹配区域下一位置, 如果没有进行过成功的匹配, 那么这两个方法会抛出异常

### Pattern标志

1. 最常用的三个标志是: CASE_INSENSITIVE, COMMENTS和MULTILINE
2. flags要么在编译时传入, 要么以(?X)形式加入到正则表达式之前

### split()

1. 关键在于理解limit参数, 建议参阅官方文档

### Matcher中的替换操作

1. 主要相关方法:

    ```java
    // 用给定字符串替换第一个匹配项, 或将匹配处送方程, 方程处理结果作为替换内容
    // 注意此方法先重置Matcher匹配位置
    public String replaceFirst(String)
    public String replaceFirst(Function)

    // 用给定字符串替换所有匹配项, 或将匹配处送方程, 方程处理结果作为替换内容
    // 注意此方法先重置Matcher匹配位置
    public String replaceAll(String)
    public String replaceAll(Function)

    // 填充当前匹配处及之前内容
    // 此方法允许分解匹配操作, 因此可实现更灵活的替换
    public Matcher appendReplacement(StringBuilder,String)
    public Matcher appendReplacement(StringBuffer,String)

    // 填充匹配处之后剩余内容
    public StringBuilder appendTail(StringBuilder)
    public StringBuffer appendTail(StringBuffer)
    ```

### reset()

1. 传入String时此方法可以替换Matcher对象中的待匹配字符串, 不带参数时可以重置当前匹配位置

## 正则表达式结合Java I/O

1. 正则表达式的实际应用, 处理文本文件, strings/JGrep.java实现了Unix中*grep*的基本用法, 在一个文件中搜寻正则表达式匹配之处

## 扫描输入

1. 在java.io库中, StringReader将String转为可读流, BufferedReader包含一个`readLine()`方法, 读取一行作为String
2. 使用parse()系列方法读取输入流十分痛苦, 要先将数据准确分割成String
3. Java SE5之后Scanner类大大简化了对输入的读取, Scanner的构造函数可接受File, InputStream, String或是Readable类型的参数, Readable是一个包含`read()`方法的interface, 而BufferedReader实现了这个接口
4. Scanner的next()系列方法能够读取下一个数据, 包括BigDecimal和BigInteger, 此方法具有阻塞特性, 仅在Scanner对象包含下一个匹配的数据时才会返回, hasNext()系列方法用于检验是否匹配
5. Scanner吞掉了IOException, 因此不用再额外添加异常检测代码, 但`ioException()`方法返回最近一次的异常, 因此在想要检查异常时也能做到

### Scanner的分界符

1. 默认分界符是空白符, 但通过`useDelimiter()`方法能够用正则表达式来表示新的分界符, `delimiter()`方法返回当前分界符的Pattern

### 正则表达式在Scanner中的应用

1. hasNext()和next()系列方法均接受Pattern类型的输入, next()的作用是进行下一次匹配, MatchResult类型用于存储`match()`方法返回的匹配结果

## StringTokenizer

1. 已废弃, 正则表达式和Scanner可以完美替代

## 练习题

| 题号  | 小节                                | 描述                                                                                      |
| :---: | ----------------------------------- | ----------------------------------------------------------------------------------------- |
|   1   | [重载的'+' 对比 StringBuilder](#重载的'+'对比stringBuilder) | 反编译了reusing/SprinklerSystem.class, 确认不涉及循环时重载'+'只创建一个StringBuilder对象 |
|   2   | [无意的递归](#无意的递归)              | 解决`toString()`造成无限递归的一例                                                      |
|10|[Pattern和Matcher](#pattern和matcher)|Pattern和Matcher的基本用法|
|11| [Pattern和Matcher](#pattern和matcher)                       |Pattern和Matcher的基本用法|
|12|[Groups](#groups)|用到了零宽断言这一特性|
|13|[start()和end()](#start()和end())|有一个模式用到了最小匹配|
|14|[split()](#split())|Pattern的split()方法实质上调用了String的split()|
|15|[正则表达式结合Java I/O](#正则表达式结合java-i/o)|strings/JGrep.java添加识别正则表达式的flag功能|
|16|[正则表达式结合Java I/O](#正则表达式结合java-i/o)|strings/JGrep.java添加路径识别功能, 并能够处理同一路径下所有文本文件|
