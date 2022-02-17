package _1designPatternPrinciple._6liskovPrinciple.negative;

/**
 * @author Willam_xh
 * @create 2021-06-05 9:40
 */

/*
* 继承的作用
* 1，提高代码的重用性
* 2.多态的前提
*
*
* 两个类能不能发送继承关系的依据是什么？有没有 is a 关系
*   a汽车和猫没有继承关系
*   b。在两个类有了is a关系之后 要考虑子类对象再替换了父类对象之后 业务逻辑是否变化
*       如果变化 则不能发生继承关系
* 正方系和长方形有“ is a” 关系 。那么 我们能不能让正方系类 直接就去继承长方形类呢？现在不能了
* 为什么呢？因为还要考虑业务场景，看看在特定的业务场景下，正方形能替换了长方形以后，业务逻辑是否变化！
*
*
*
*
* */

/**
 * 变形 宽一直累加 直到 宽比长 要长
 * 让宽的长度 大于 长 的长度
 */
class Utils{
    public static void transform(Rectangular r){
        while (r.getWidth()<=r.getLength())//如果是正方系 宽变了 长也会变了 这时候长宽是一个值
        {
            r.setWidth(r.getWidth()+1);
            System.out.println("长："+r.getLength()+" : " +
                    "宽："+r.getWidth());
        }
    }
}

/**
 * 长方形类
 */
class Rectangular {
    private double width;
    private double length;


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Rectangular{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }
}

/**
 * 正方形类
 */
class Square extends Rectangular{
    private double sideLength;

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public void setWidth(double width) {
        this.sideLength=width;
    }

    @Override
    public double getLength() {
        return sideLength;
    }

    @Override
    public void setLength(double length) {
        this.sideLength=length;
    }
}

public class AppTest2 {
    public static void main(String[] args) {
        Rectangular r = new Rectangular();
        //这里如果替换会了正方形 你宽一设置
        // 长也会接着变 这时候 业务场景换了就没法用子类替换父类了 因为会不停的死循环
        r.setWidth(12);
        r.setLength(20);

        System.out.println(r);

        Utils.transform(r);
        System.out.println(r);

    }
}
