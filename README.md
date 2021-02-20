# MyJVM
私人开发一个demo版本的jvm,可正确进行基础代码的运行，但由于还有很多native方法未实现，
因此一些工具包或者高级一些的代码暂时不支持（eg:Arrays.toString(new String[]{"abc","bcd"})）,
后续更新会陆续完善这部分代码

hotspot与MyJVM对比：

|  是否支持| 类加载器  | 类分析器 | 解释器  |  即时编译器  |   垃圾回收器  | 逃逸分析 | 反汇编  | JVM工具包 |
| ------- | -------  | ------   | ------ |  ------     |------        | ------  |  ------ | ------   |
| HotSpot |    是    |   是     |   是    |  是         |   是         | 是      | 是      |   是      |
| MyJvm   |    是    |   是     |   是    |  否         |   否         | 否      | 是      |   否      |

MyJVM运行效果演示图：

![image](https://github.com/SBXSYZL/MyJVM/blob/master/images/1.png)
![image](https://github.com/SBXSYZL/MyJVM/blob/master/images/2.png)
![image](https://github.com/SBXSYZL/MyJVM/blob/master/images/3.png)

参考资料：

    1. Oracle JDK 官方文档
    2. 深入理解JVM字节码
    3. 深入理解Java虚拟机
    4. 自己动手实现java虚拟机
    5. Java核心技术系列：Java虚拟机规范（JavaSE 8版） 