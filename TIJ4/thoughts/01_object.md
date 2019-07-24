# Everything Is An Object

## 对象与引用

1. 引用可以单独声明,独立于对象存在
2. 创建时就初始化引用是一个好习惯

## 存储

1. 引用和基本类型存在于栈中
2. 用new创建的对象都在堆中
3. 对象可以转化到非RAM介质中存储,如流式对象可用于网络传输,常驻对象可以存储在磁盘上

## 基本类型

1. 值存在于变量之中,也即引用与对象合一
2. 没有unsigned类型
3. 注意wrapper classes和auto boxing(包含基本类型和封装类型的相互转换)

## 数组

1. 不存在越界问题
2. 数组元素一定被初始化

## 范围

1. 在每个范围内,(基本类型和引用的)变量名不允许重复
2. 引用在范围末端失效,但对象由垃圾收集器负责回收
3. 重名的域和本地变量不存在命名冲突,需要注意避免

## 默认值

1. 域能够保证初始化,在定义时被分配默认值
2. 本地变量不保证初始化,因此不在定义时进行初始化会报错

## static声明

1. static与non-static交叉组合有四种访问方式,彼此可以互通
2. static域可通过类和对象访问,但由类所独有
3. 对于static方法,类和对象对其的调用方式完全一致(相对于域来说,类和对象都可以使用,而不是仅类独有),但在定义程序入口点时就显出必要性(必须要有不依赖对象的方法来创建对象),从另一角度看,static方法的一大优势就是不用创建对象即可调用

## 常识

1. java.lang库被自动包含

---

## 练习题

| 题号  | 描述                            |
| :---: | ------------------------------- |
|  10   | 介绍了System.err和System.exit() |