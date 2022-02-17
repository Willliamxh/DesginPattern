package _1designPatternPrinciple._4dependencyReverse.positive;

/**
 * @author Willam_xh
 * @create 2021-06-02 20:10
 */

interface Animal{
    public void eat();
}

class Person{
    //现在是人依赖狗  这会导致每出现一个新的动物 人都要依赖这个新动物
    public void feed(Animal dog){
        System.out.println("Person start feed");
        dog.eat();
    }
}


class Dog implements Animal{
    public void eat(){
        System.out.println("Dog start eat");
    }
}




//==========================================================================

//变化来了 客户端不仅仅需要喂狗 还需要喂猫

//客户端自己定义一个猫类   为了让人喂猫 那还得改代码 这就违反了开闭原则 这变成了上层依赖下层

class Cat implements Animal{
    public void eat(){
        System.out.println("猫吃鱼");
    }
}

public class AppTest {
    public static void main(String[] args) {
        Person person = new Person();
        Dog d= new Dog();
        person.feed(d);

        Cat cat = new Cat();//多态 向上转型
        person.feed(cat);

    }
}
//这时候，Person内部的feed方法不在依赖于依赖于Dog或者Cat，
// 而是不管是Person，还是Dog或者Cat，他们都依赖与Animal这一抽象类，都依赖于抽象类。
//这时候，不管是曾经的上层代码，还是曾经的下层代码，都不会因为需求而改变。
//依赖倒转原则就是指：代码要依赖于抽象的类，而不要依赖于具体的类；要针对接口或抽象类编程，而不是针对具体类编程。
// 通过面向接口编程，抽象不应该依赖于细节，细节应该依赖于抽象。

