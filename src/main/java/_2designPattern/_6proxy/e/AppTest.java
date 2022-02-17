package _2designPattern._6proxy.e;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
为了解决c包的问题,我们必须学习jdk中的一个api 动态代理

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
//====================================================================
class MyHandler implements InvocationHandler{

    private Object target;

    public MyHandler(Object target) {
        this.target = target;
    }

    //proxy     .add            (1,2);
    //proxy.    method      Object[] args;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName()+"方法开始，参数是"+ Arrays.toString(args));
        // 利用反射机制调用方法，
        //把method所代表都方法，当作c对象都方法都效用，参数是args
        //
        Object res = method.invoke(target, args);
        System.out.println("结果为："+res);
        return res;
    }
}

class MyProxy{
    //作者，在封装功能的时候，无法预测未来的用户，将会传入什么样的目标对象!
    //封装:对外隐藏复杂的实现细节，暴露出简单的使用方法!
    public Object getProxy(Object target){

        ClassLoader cl= CalcImpl.class.getClassLoader();
        //获取target类 所实现的所有的接口
        Class[] interfaces=target.getClass().getInterfaces();
        Object proxy = Proxy.newProxyInstance(cl, interfaces, new MyHandler(target));
        return proxy;
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




public class AppTest {
    public static void main(String[] args) {
        // ICalc calc = new CalcImpl();
        // ICalc proxy = (ICalc) new MyProxy().getProxy(calc);
        // proxy.add(1,2);

        Foo foo=new Foo();
        A proxy1 = (A) new MyProxy().getProxy(foo);
        proxy1.f2();


    }
}

/*
目前看起来挺好的，但是仍然有问题:
1.目前我们创建的代理对象，只能在真实对象的真实方法调用前后，加上日志，无法加其他功能!
比如，用户不想加日志功能，而是想加缓存功能!或者权限控制，或者延迟加载..... whatever...
*/

