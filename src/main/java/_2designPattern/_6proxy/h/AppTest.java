package _2designPattern._6proxy.h;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

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
    public static void main(String[] args) {
        //calc是目标对象
        ICalc calc = new CalcImpl();

        //根据目标对象 动态生成一个代理对象
        ICalc proxy = (ICalc) new MyProxy().getProxy(calc,new AddInterceptor());

        //我们把proxy这个代理对象再当成一个新的目标对象。 层层拦截 套娃
        ICalc proxy2= (ICalc) new MyProxy().getProxy(proxy,new SubInterceptor());

        proxy2.add(1,2);
        proxy2.sub(1,2);

    }
}



/*
目前代码的问题是:添加拦截器的顺序是逆向的，对用户不友好
*/


