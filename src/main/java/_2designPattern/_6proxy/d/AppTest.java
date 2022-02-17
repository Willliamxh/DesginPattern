package _2designPattern._6proxy.d;

import _1designPatternPrinciple._6liskovPrinciple.positive.Test;

import javax.swing.*;
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


public class AppTest {
    public static void main(String[] args) {

        //真实对象 目标对象 被代理对象
        CalcImpl calc = new CalcImpl();


        //创建一个代理对象。创建这个代理对象的时候，需要传入3个参数。

       /*
        第一个参数:类加载器
        地球人都知道，要实例化一个对象，就必须调用类的构造器!在构造器调用之前，jvm会加载该类的字节码!
        jvm恰恰就是使用“类加载器”来加载类的字节码的!这-步是jvm自动完成的!
        以下代码，是动态创建一个代理对象的代码，可以说是一种不太正常的创建对象的方式，就算它不正常，它毕竟
        也是要创建对象的嘛!但凡创建对象,势必要先加载字节码，势必就要使用到类加载器，和构造器实例化不同的是:
        使用构造器实例化对象时，jvm会自动找到类加载器，而以下代码，必须我们手动传入类加载器!
        */
        //因为这个类加载器把他们都加载了，所以通过谁都能获得同一个类加载器
        // System.out.println(CalcImpl.class.getClassLoader()==Test.class.getClassLoader());
        ClassLoader cl= CalcImpl.class.getClassLoader();

        /*
             第二个人参数:接口的字节码数组
             地球人都知道，要创建-一个类的对象，必须要先加载类的字节码，那么动态代理也不例外，所以我们才传入第-个参数:类加载器!
             问题是:这个传入的类加载器，加载的是哪个类的字节码? ?
             对比，使用构造器创建对象时，加载的字节码很明确:
             new String();
             jvm就会加载String.class
             new Date(); .
             jvm就会加载Date.class
             new ArrayList(); jvm就会 加载ArrayL ist. class
         而使用动态代理api创建对象时，加载哪个字节码呢?加载的字节码就会在运行期动态生成的字节码,这个动态生成的字节码是不需要
         源代码的! !
         还有问题是:字节码确实可以自动生成，那么动态代理api动态生成的字节码的内容，是根据什么生成的呢?
         这就是由第二个参数生成的！！！动态生成代理，会生成一个实现了目标接口的类的字节码，
         在本例中，就是生成类一个实现了Icalc接口的类的字节码。
         以实现的这个接口为依据来生成字节码。
         */
        Class[] interfaces={ICalc.class};

        /*
        第三个参数:调用处理器，InvocationHandler
        我们已经知道,动态代理会加载自己动态生成的字节码，且这个字节码是根据某个接口生成的，在本例中就是根据ICalc接口生成的
        生成的是实现了ICalc接口的类的字节码。
        问题是:实现一个接口，就要实现其中的抽象方法，那么动态代理生成的字节码,实现了ICalc接口，势必就要实现其中的add. sub. mul. div方法!
        这些方法被实现的方法体都是什么内容呢? ? ?
        这恰恰就是由第三个参数决定的。
        MyHandler类的invoke方法就是方法体的内容。

        class 我是动态生成的哪个类 implements ICalc{
            add(){
                new MyHandler().invoke();
            }
            sub(){
                new MyHandler().invoke();
            }

        }
        */

        //生成的字节码 实现了ICalc这个接口
        ICalc proxy = (ICalc) Proxy.newProxyInstance(cl, interfaces, new MyHandler(calc));
        //总之 就记住 对代理对象对调用，都统统会进入这个调用处理器中。
        int add = proxy.add(1, 2);
        int sub = proxy.sub(1, 2);
        int div = proxy.div(1, 2);
        int mul = proxy.mul(1, 2);




    }
}

/*|
这样写已经克服了之前的缺点了:
1.工作量一点都不打，很随意! !
2.需求如果再次变化呢?比如,客户要求我们使用英文写日志!我们只需要该一个地方即可!
3.如果需求是这样呢?星期1,3,5中文，2,4,6英文。

就目前这个写法还是有缺点的：太复杂了！我们最好把这个代码封装一下，对使用者更加友好。
*/