#SPI 全称为 Service Provider Interface，是一种服务发现机制。

`SPI 的本质是将接口实现类的全限定名配置在文件中，并由服务加载器读取配置文件，加载实现类。这样可以在运行时，动态为接口替换实现类。
正因此特性，我们可以很容易的通过 SPI 机制为我们的程序提供拓展功能。SPI 机制在第三方框架中也有所应用，比如 Dubbo 就是通过 SPI
机制加载所有的组件。`

#Java SPI 示例
前面简单介绍了 SPI 机制的原理，本节通过一个示例演示 Java SPI 的使用方法。首先，我们定义一个接口，名称为 Search。

~~~public interface Search {
List<String> search(String keyword);
}
~~~
**接下来定义两个实现类，分别为 DatabaseSearch 和 Search。**
~~~~
public class DatabaseSearch implements Search {
    @Override
    public List<String> search(String keyword) {
        System.out.println("now use database search. keyword:" + keyword);
        return null;
    }
}

public class FileSearch implements Search {
    @Override
    public List<String> search(String keyword) {
        System.out.println("now use file system search. keyword:" + keyword);
        return null;
    }
}

~~~~
接下来 META-INF/services 文件夹下创建文件夹名字为Seach的全限定名com.north.spilat.service.Search，并创建 DatabaseSearch 的全限定名 DatabaseSearch
和FileSearch的全限定名文件com.north.spilat.service.impl.FileSearch。

`或者创建com.north.spilat.service.Search文件内容是 DatabaseSearch 的全限定名 DatabaseSearch
和FileSearch的全限定名文件com.north.spilat.service.impl.FileSearch
文件内容为实现类的全限定的类名`

目录结构为
~~~
    src
    --main
    ----resources
    ------META-INF
    --------services
    ----------DatabaseSearch
    ----------FileSearch
~~~ 
做好所需的准备工作，接下来编写代码进行测试。
~~~
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> searchList = s.iterator();
        while (searchList.hasNext()) {
            Search curSearch = searchList.next();
            curSearch.search("test");
        }
    }
}
~~~
执行main方法控制台输出结果为
~~~
"D:\Program Files\Java\jdk1.8.0_202\bin\java.exe" "-javaagent:E:\Program Files\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=58658:E:\Program Files\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java\jdk1.8.0_202\jre\lib\charsets.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\deploy.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\cldrdata.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\dnsns.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\jaccess.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\jfxrt.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\localedata.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\nashorn.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunec.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunpkcs11.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\zipfs.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\javaws.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\jce.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\jfr.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\jfxswt.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\jsse.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\management-agent.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\plugin.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\resources.jar;D:\Program Files\Java\jdk1.8.0_202\jre\lib\rt.jar;E:\code\java\javaspidemo\target\classes" Main
Hello World!
now use database search. keyword:test
now use file system search. keyword:test
~~~

**第二种实现**

我们定义一个接口，名称为 Robot。
~~~
public interface Robot {
void sayHello();
~~~
接下来定义两个实现类，分别为 OptimusPrime 和 Bumblebee。
~~~
public class OptimusPrime implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Optimus Prime.");
    }
}

public class Bumblebee implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
~~~
接下来 META-INF/services 文件夹下创建一个文件，名称为 Robot 的全限定名 org.apache.spi.Robot。文件内容为实现类的全限定的类名，如下：
~~~
org.apache.spi.OptimusPrime
org.apache.spi.Bumblebee
~~~
做好所需的准备工作，接下来编写代码进行测试。
~~~
public class JavaSPITest {

    @Test
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}  
~~~
最后来看一下测试结果，如下：
~~~
Java SPI
Hello, I am Bumblebee.
Hello, I am Optimus Prime.
~~~