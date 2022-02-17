package _2designPattern._5adapter._1simpleEx;

/*
适配器
一个类的接口转换成客户希望的另一个接口。适配器模式让那些接口不兼容的类可以一起工作

通俗一点，就是根据已有接口，生成想要的接口。
*/

class Calc{
    public int add(int a,int b){
        return a+b;
    }
}

//================================================================

//变化来了 客户端希望计算三个数的和 而calc的add方法只能接受两个参数

class CalcAdapter extends Calc{
    public int add(int a,int b,int c){
        return add(a,add(b,c));
    }
}

//但是 组合优于继承 所以用组合更好
class CalcAdapter2 {
    private Calc calc;

    public CalcAdapter2(Calc c) {
        this.calc = c;
    }

    public int add(int a, int b, int c){
        return calc.add(a,calc.add(b,c));
    }
}


public class AppTest {
    public static void main(String[] args) {
        // CalcAdapter calcAdapter = new CalcAdapter();
        // int r=calcAdapter.add(1,2,3);
        // System.out.println(r);

        Calc c=new Calc();
        CalcAdapter2 calcAdapter2 = new CalcAdapter2(c);
        int add = calcAdapter2.add(1, 2, 3);
        System.out.println(add);

    }
}
