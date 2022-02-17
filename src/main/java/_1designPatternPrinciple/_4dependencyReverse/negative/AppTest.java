package _1designPatternPrinciple._4dependencyReverse.negative;

/**
 * @author Willam_xh
 * @create 2021-06-02 20:10
 */
class Person{
    //现在是人依赖狗  这会导致每出现一个新的动物 人都要依赖这个新动物
    public void feed(Dog dog){
        System.out.println("Person start feed");
        dog.eat();
    }
}


class Dog{
    public void eat(){
        System.out.println("Dog start eat");
    }
}




//==========================================================================

//变化来了 客户端不仅仅需要喂狗 还需要喂猫

//客户端自己定义一个猫类   为了让人喂猫 那还得改代码 这就违反了开闭原则 这变成了上层依赖下层

class Cat{
    public void eat(){
        System.out.println("猫吃鱼");
    }
}

public class AppTest {
    public static void main(String[] args) {
        Person person = new Person();
        Dog d= new Dog();
        person.feed(d);

        Cat cat = new Cat();

    }
}
//此时 这种代码违反了依赖倒置 因为 每当下层变动的时候 上层都要跟着一起变动
//我们希望的是 当下层新增一个动物的时候 上层应该不知道 上层代码不需要改动