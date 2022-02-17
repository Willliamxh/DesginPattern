package _2designPattern._7strategy.b;

/*
游戏公司的老总们开会，得出一个提高本公司游戏竞争力的方案:要 求让游戏中的鸭子能飞起来!
把其他竞争者远远甩在身后!
程序猿就会想，是时候展现我们面向对象程序猿的威力了!我只需在父类Duck中， 添加一-个fly方法，那么所有
Duck的子类，也都具备了fly方法。

此时，问题看似解决了，但实际上出现了更麻烦的问题:所有Duck的子类鸭子，统统都会飞了! 要知道，父类中的方法，并不是所有
子类都能通用的! !比如:橡皮鸭! 橡皮鸭是没有生命的，不能飞。结果因为继承了Duck,搞得橡皮鸭也能飞了!这样程序猿就会
背锅，被老总批评.. .
*/

abstract class Duck{
    public void quack(){
        System.out.println("嘎嘎～");
    }

    public void swim(){
        System.out.println("游泳");
    }

    public void fly(){
        System.out.println("fly!");
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

class RubDuck extends Duck{
    @Override
    public void display() {
        System.out.println("外观是橡皮鸭子");
    }

    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }

    //因为橡皮鸭不会飞。却又继承了Duck中的f1y方法，所以我们可以像
  //重写quack方法那样，去重写f1y方法。
    @Override
    public void fly() {
        System.out.println("你行你上 我飞失败了");
    }
}

public class AppTest {
    public static void main(String[] args) {
        Duck a=new RedDuck();
        a.quack();
        a.swim();
        a.fly();
        a.display();
    }
}

/*
 看起来，问题好像解决了，但是并没有，问题是，变化不断的出现，-会加个木头鸭子，一 会加个鸭子超人，-会加个怪鸭伯爵
程序猿就要在每次添加新的鸭子角色时，都会判断，新的鸭子角色不会不叫，会不会飞， 针对于不同鸭子要有不同的处理方法。
这样也很麻烦，只不过是从一个噩梦跳入了另一个噩梦。
 */
