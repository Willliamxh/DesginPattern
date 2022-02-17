package _2designPattern._7strategy.a;

/*
有一家游戏恭喜，制作-款鸭子游戏:在这个鸭子游戏中，角色都是鸭子，不同的鸭子之间，有共性，所以为了提高代码的重用性,
开发人员，就制作了一个鸭子的父类: Duck,把这些鸭子的共性上提到父类中:
*/

abstract class Duck{
    public void quack(){
        System.out.println("嘎嘎～");
    }

    public void swim(){
        System.out.println("游泳");
    }
    public abstract void display();

}

class MallarDuck extends Duck{
    @Override
    public void display() {
        System.out.println("外观是野鸭");
    }
}

class RedDuck extends Duck{
    @Override
    public void display() {
        System.out.println("外观是红头鸭");
    }
}


public class AppTest {
    public static void main(String[] args) {
        Duck a=new RedDuck();
        a.quack();
        a.swim();
        a.display();
    }
}

/*
就目前而言，没有什么问题!请看下一个包!
*/
