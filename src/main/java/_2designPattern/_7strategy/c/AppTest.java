package _2designPattern._7strategy.c;

/*
/*
针对于b包中的问题: 程序猿需要判断每个鸭子子类，谁不会不会叫，谁会不会飞, 不会叫的，就重写quack方法，不会飞的就重写f1y方法。
这个工作量是很大的! !
我们希望那些不会飞<鸭子， 压根就没有f1y方法，不会叫的鸭子，压根就没有quack方法。
把这2个经常在子类中变化的方法，从父类中分出来，分成两个接口: Quackable, Flyable|
*/

abstract class Duck {
    public void swim() {
        System.out.println("游泳");
    }
    public abstract void display();
}

interface Flyable {
    public void fly();
}

interface Quackable {
    public void quack();
}
class MallardDuck extends Duck implements Flyable, Quackable {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
    @Override
    public void fly() {
        System.out.println("飞起来了");
    }
    @Override
    public void display() {
        System.out.println("外观是绿头鸭");
    }
}

class RedheadDuck extends Duck implements Flyable, Quackable  {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
    @Override
    public void fly() {
        System.out.println("飞起来了");
    }
    @Override
    public void display() {
        System.out.println("外观是红头鸭");
    }
}

class RubberDuck extends Duck implements Quackable {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
    @Override
    public void display() {
        System.out.println("外观是橡皮鸭");
    }
}

class WoodenDuck extends Duck {
    @Override
    public void display() {
        System.out.println("外观是木头假鸭");
    }
}

public class AppTest {
    public static void main(String[] args) {
        //向上转型时 只能调用父类和子类都有的方法
        RubberDuck a=new RubberDuck();
        a.quack();


    }
}

/*
思考，这样问题解决了吗?没有!
以前是:每加入一个新的鸭子角色，程序猿就要判断，这个新鸭子角色是否会飞，
是否会叫，不会飞的就重写飞方法，不会叫的就重写叫方法。

现在是:每加入一个新的鸭子角色，程序猿就要判断，这个新鸭子角色是否会飞，
是否会叫，不会飞的就不实现F1yable接口，不会叫的就不实现Quackab1接口

如此，程序猿仍然没有减少工作量啊?仍然要不断地判断新鸭子角色!
另外一个缺点是f1y和quack方法没有重用性可言，比如48种鸭子，有8种不会飞，那么飞方法就要在40个鸭子子类中-共重复40次!

杠：
此时，应有杠:老师，从jdk1 .8开始，接口中的方法就有默认实现!此时，48种鸭子， 有8种不会飞，那么~ 飞方法只需要在F1yable中定义- -个默认实现即可，只写1次。
解释:对于48种鸭子， 有12种飞行方法，又该如何? ?
飞行方法，仍然在子类中重复
因为接口默认实现只能有一种实现方式
*/
