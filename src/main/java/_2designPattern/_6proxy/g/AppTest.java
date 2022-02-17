package _2designPattern._6proxy.g;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
为了解决f包的问题,重构代码如下

只要把方法 和 方法参数作为变量传进去就行了

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

interface A{
    void f1();
    void f2();
}

class Foo implements A{

    @Override
    public void f1() {
        System.out.println("执行 f1");
    }

    @Override
    public void f2() {

    }
}


//用户也能自己定义拦截器
class LoginTest implements Interceptor{

    @Override
    public void before(Method m,Object[] args) {
        System.out.println("前置日志");
        System.out.println(m.getName()+"方法开始");
        System.out.println("参数为"+ Arrays.toString(args));
    }

    @Override
    public void after(Method m,Object res) {
        System.out.println("后置日志");
        System.out.println(m.getName()+"方法结束");
        System.out.println("结果为"+res);
    }
}

public class AppTest {
    public static void main(String[] args) {
        ICalc calc = new CalcImpl();
        Interceptor myMethond=new LoginTest();
        ICalc proxy = (ICalc) new MyProxy().getProxy(calc,myMethond);
        proxy.add(1,2);

        // Foo foo=new Foo();
        // A proxy1 = (A) new MyProxy().getProxy(foo);
        // proxy1.f2();


    }
}

/*
目前，有用能够自己决定目标方法执行前后，前置通知和后置通知了，针对于日志功能，也能细化了!
新需求来了:
针对于ICalc这个接口的日志功能，add方法使用英文日志，sub方法使用中文日志，mul方法不要日志，div日文日志.
在log中加个判断即可 "add".equals(method.getName())

但是程序中有ifelse 的话 就会比较怪怪的 违反了单一职责了

针对于这个需求，像当前包这样解决，是不好的，这违反了单一职责。

 */


