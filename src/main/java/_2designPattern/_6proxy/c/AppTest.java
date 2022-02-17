package _2designPattern._6proxy.c;
/*
为了解决b包的问题
解决方法如下
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


public class AppTest {
    public static void main(String[] args) {
        ICalc c=new CalcImpl();
        System.out.println(c.add(1,2));
        System.out.println(c.sub(1,2));
        System.out.println(c.mul(1,2));
        System.out.println(c.div(1,2));
    }
}

/*
目前，这个写法，符合了开闭原则，毕竟我们没有修改作者的代码
这样写是有很大的缺点的:
1.工作量太大，我们要为每个方法添加日志功能!
2.需求如果再次变化呢?比如，客户要求我们使用英文写日志!过了一段时间，还是中文好! !星期1,3,5中文， 2,4,6英文。
*/