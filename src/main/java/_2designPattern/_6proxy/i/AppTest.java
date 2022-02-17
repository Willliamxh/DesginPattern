package _2designPattern._6proxy.i;

import javax.xml.transform.Source;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
为了解决g包的问题,重构代码如下


*/
interface ICalc{
    int add(int a,int b);
    int sub(int a,int b);
    int mul(int a,int b);
    int div(int a,int b);
}

class CalcImpl implements ICalc{

    @Override
    public int add(int a, int b) {
        int res=a+b;
        System.out.println("add");
        return res;
    }

    @Override
    public int sub(int a, int b) {
        int res=a-b;
        return res;
    }

    @Override
    public int mul(int a, int b) {
        int res=a*b;
        return res;
    }

    @Override
    public int div(int a, int b) {
        int res=a/b;
        return res;
    }
}

//===========================================
class MyCalcImpl extends CalcImpl{
    @Override
    public int add(int a, int b) {
        System.out.println("开始，参数是："+a+","+b);
        int res=super.add(a, b);
        System.out.println("结果是"+res);
        return res;
    }

    @Override
    public int sub(int a, int b) {
        System.out.println("开始，参数是："+a+","+b);
        int res=super.sub(a, b);
        System.out.println("结果是"+res);
        return super.sub(a, b);
    }

    @Override
    public int mul(int a, int b) {
        System.out.println("开始，参数是："+a+","+b);
        int res=super.mul(a, b);
        System.out.println("结果是"+res);
        return super.mul(a, b);
    }

    @Override
    public int div(int a, int b) {
        System.out.println("开始，参数是："+a+","+b);
        int res=super.div(a, b);
        System.out.println("结果是"+res);
        return super.div(a, b);
    }
}

interface Interceptor{
    void before(Method m,Object[] args);// 其实这个就是SpringMVC里面的JointPoint封装的东西
    void after(Method m,Object res);
}


//====================================================================
class MyHandler implements InvocationHandler{

    private Object target;
    private Interceptor interceptor;

    public MyHandler(Object target,Interceptor interceptor) {
        this.target = target;
        this.interceptor=interceptor;
    }

    //proxy     .add            (1,2);
    //proxy.    method      Object[] args;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

       // 前置通知 决定权交给用户
        interceptor.before(method,args);

        Object res = method.invoke(target, args);

        // 后置通知 决定权交给用户
        interceptor.after(method,res);


        return res;
    }
}

class MyProxy{
    //作者，在封装功能的时候，无法预测未来的用户，将会传入什么样的目标对象!
    //封装:对外隐藏复杂的实现细节，暴露出简单的使用方法!
    public Object getProxy(Object target,Interceptor interceptor){

        ClassLoader cl= CalcImpl.class.getClassLoader();
        //获取target类 所实现的所有的接口
        Class[] interfaces=target.getClass().getInterfaces();
        Object proxy = Proxy.newProxyInstance(cl, interfaces, new MyHandler(target,interceptor));
        return proxy;
    }

    public Object getProxy2(Object target,List<Interceptor> interceptors){

        for (int i = interceptors.size()-1; i >=0 ; i--) {
            Interceptor interceptor = interceptors.get(i);
            target=(ICalc)new MyProxy().getProxy(target,interceptor);
        }

        return target;
    }

    //用配置文件去读
    public Object getProxy3(Object target) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //拦截器的集合是通过配置文件得到的。
        Properties properties=new Properties();
        //注意点1：只能写相对路径 http://www.hellojava.com/a/91419.html
        //注意点2 我这个是
        String name="myconfig2.properties";
        InputStream in = AppTest.class.getClassLoader().getResourceAsStream(name);
        // 使用properties对象加载输入流
        properties.load(in);
        //获取key对应的value值
        System.out.println(properties.getProperty("interceptors"));

        String ss = properties.getProperty("interceptors");
        String[] split = ss.split("，");

        List<Interceptor> interceptors=new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            interceptors.add((Interceptor)Class.forName(split[i]).newInstance());
        }

        for (int i = interceptors.size()-1; i >=0 ; i--) {
            Interceptor interceptor = interceptors.get(i);
            target=(ICalc)new MyProxy().getProxy(target,interceptor);
        }

        return target;
    }

}

class MyInterceptor implements Interceptor{

    @Override
    public void before(Method m,Object[] args) {
        System.out.println("AAAAAA");
    }

    @Override
    public void after(Method m,Object res) {
        System.out.println("bbbbbbb");
    }
}

//===============================================
class AddInterceptor implements Interceptor{

    @Override
    public void before(Method m, Object[] args) {
        if(m.getName().equalsIgnoreCase("add")){
            System.out.println("add开始");
        }
    }

    @Override
    public void after(Method m, Object res) {
        if(m.getName().equalsIgnoreCase("add")){
            System.out.println("add结束");
        }
    }
}

class SubInterceptor implements Interceptor{

    @Override
    public void before(Method m, Object[] args) {
        if(m.getName().equalsIgnoreCase("Sub")){
            System.out.println("Sub开始");
        }
    }

    @Override
    public void after(Method m, Object res) {
        if(m.getName().equalsIgnoreCase("Sub")){
            System.out.println("Sub结束");
        }
    }
}


public class AppTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //calc是目标对象
        ICalc calc = new CalcImpl();
        //
        // //开发中，不会让你开发拦截器的，直接让你使用springmvc就行了
        // List<Interceptor> interceptors=new ArrayList<>();
        // interceptors.add(new AddInterceptor());
        // interceptors.add( new SubInterceptor());
        //
        ICalc c = (ICalc) new MyProxy().getProxy3(new CalcImpl());

        calc.add(1,2);

        // Properties pros =  new Properties();
        // InputStream in = AppTest.class.getResourceAsStream("/Users/program/designPattern/code/src/main/java/_2designPattern/_6proxy/i/myconfigRE.properties");
        // System.out.println(in);
        // pros.load(in);

        // String fileRoot="/Users/program/designPattern/code/src/main/java/_2designPattern/_6proxy/i/myconfigRE.properties";
        // File file = new File("/Users/program/designPattern/code/src/main/java/_2designPattern/_6proxy/i/myconfigRE.properties");
        // System.out.println(file.exists());

        // Properties properties = new Properties();
        // // 使用InPutStream流读取properties文件
        // BufferedReader bufferedReader = new BufferedReader(new FileReader(fileRoot));
        // properties.load(bufferedReader);
        // System.out.println(properties.getProperty("ssa"));

        // Properties properties = new Properties();
        // // 使用ClassLoader加载properties配置文件生成对应的输入流
        // String name="myconfig2.properties";
        // InputStream in = AppTest.class.getClassLoader().getResourceAsStream(name);
        // // 使用properties对象加载输入流
        // properties.load(in);
        // //获取key对应的value值
        // System.out.println(properties.getProperty("interceptors"));




        // //根据目标对象 动态生成一个代理对象
        // ICalc proxy = (ICalc) new MyProxy().getProxy(calc,new AddInterceptor());
        //
        // //我们把proxy这个代理对象再当成一个新的目标对象。 层层拦截 套娃
        // ICalc proxy2= (ICalc) new MyProxy().getProxy(proxy,new SubInterceptor());
        //
        // proxy2.add(1,2);
        // proxy2.sub(1,2);

    }
}



/*
倒叙解决方法调用的问题。 但是还是有问题

代码变复杂了。所以再次封装。

但是还有问题。
目前代码的问题是:以后，用户要添加拦截器，删除拦截器,势必要修改应用程序代码，以修改List<Interceptor>
所以 用配置文件去设置拦截器
我这边拦截器要放在resource里面

*/


