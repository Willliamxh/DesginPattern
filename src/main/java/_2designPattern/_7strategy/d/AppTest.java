package _2designPattern._7strategy.d;

/*
/*
针对于c包中的问题

是时候，把飞行和叫方法，从鸭子类中分离出来了! (注意，这里的分离，与C包中分离不一-样)
 */




/*
	有一个设计原则： 封装变化原则
	找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起

	这样的概念很简单，几乎是每个设计模式背后的精神所在。所有的模式都提供了一套方法，
	让“系统中的某部分改变不会影响其他部分”。

	好，该是把鸭子的行为从Duck类中取出的时候了!

	我们知道Duck类内的fly()和quack()会随着鸭子的不同而改变。
	为了要把这两个行为从Duck类中分开，我们将把它们从Duck类中取出来，建立一组新类来代表每个行为。(见图1)

	在“针对接口编程，而不是针对实现编程”的原则指导下， （注意这里所说的接口指的是超类或实际的接口）
	我们定义两个接口: FlyBehavior，QuackBehavior，并且让所有的行为类都实现这两个接口中的其中之一(见图2)

*/

interface FlyBehavior {
    void fly();
}

class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!!");
    }
}

class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}

class FlyRockedPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}

interface QuackBehavior {
    void quack();
}

class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}

class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}

class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}

abstract class Duck {
    //鸭子组合就行了 不用去实现对应的飞方法
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void swim() {
        System.out.println("All ducks float, even wooden duck!");
    }

    public abstract void display();

    //有了get set 那就能在运行时 变化飞行状态了
    public void setFlyBehavior(FlyBehavior fb) {
        this.flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        this.quackBehavior = qb;
    }

}

class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}

class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();	// 一开始，鸭模型是不会飞的
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }

}




// ==============================================================

public class AppTest {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();

        mallard.performQuack();
        mallard.performFly();

        System.out.println("=====================");
        Duck model = new ModelDuck();
        model.performFly();
        model.performQuack();
        //通过getset 直接在程序运行过程中变化飞行状态  运行时 吃了个什么道具 我就会飞了
        model.setFlyBehavior(new FlyRockedPowered());
        model.performFly();
    }
}


// ==============================================

class Tang extends Duck {

    public Tang() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("外观是唐老鸭");
    }

}


//此时，针对于48种鸭子，有12种飞行方法而言，每种飞行方法，写1次!
// 策略模式:
// 定义了算法族，分别封装起来，让它们之间可以互相替换，此模式让算法的变化独立于使用算法的客户。