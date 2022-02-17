package _2designPattern._1factoryInterface._1simpleFactory.negative;

/**
 * @author Willam_xh
 * @create 2021-05-31 20:19
 */

//抽象产品
interface Food{
    void eat();
}

//具体产品
class Hamburger implements Food{

    @Override
    public void eat() {
        System.out.println("吃汉堡包");
    }
}

//========================上面是作者 下面是用户==============================
//这个时候 服务器和客户端是耦合的 就是一个改了 另外一个也得改
//这时候，产品来改需求来了，“哥，你先把刀放下。咱们现在这 Noodles改名了，得改个特牛逼的名字Spaghetti，让用户记住咱们这是西餐意大利面。”
//
//这时候，因为你原有设计是上面的反例，你得能从修改服务端的源代码开始，再修改客户端源代码。以后再有改名这类事，你还要把刀拿出来放桌上给产品看。
//
//这种设计过于脆弱，因为这样服务端源代码和客户端源代码是耦合的，改变会牵一发而动全身。

public class Negtive {
    public static void main(String[] args) {
        Food f = new Hamburger();//向上转型发生时候 调用的方法之和new的对象有关
        f.eat();

    }
}
