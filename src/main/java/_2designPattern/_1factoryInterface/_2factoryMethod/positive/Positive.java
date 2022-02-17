package _2designPattern._1factoryInterface._2factoryMethod.positive;

/**
 * @author Willam_xh
 * @create 2021-06-01 18:19
 * 针对于简单工厂的问题：使用工厂方法
 */

//抽象产品
interface Food{
    void eat();
}

//具体产品
class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡包");
    }
}


class RiceNoodle implements Food {

    @Override
    public void eat() {
        System.out.println("吃过桥米线");
    }
}


interface FoodFactory{
    public Food getFood();
}


class HamburgerFactory implements FoodFactory{
    @Override
    public Food getFood() {
        return new Hamburger();
    }
}

class RiceNoodleFactory implements FoodFactory{
    @Override
    public Food getFood() {
        return new RiceNoodle();
    }
}



//===============================================================================================

class LP implements Food{

    @Override
    public void eat() {
        System.out.println("从小吃凉皮");
    }
}
//现在我想让工厂去生产lp 这样就能随意扩展 不用去修改作者的代码了

class LPFactory implements FoodFactory{
    @Override
    public Food getFood() {
        return new LP();
    }
}

class Bussiness{
    public void taste(FoodFactory ff){
        Food food = ff.getFood();
        System.out.println("评委1：品尝");
        food.eat();

        Food food2 = ff.getFood();
        System.out.println("评委2：品尝");
        food2.eat();

        Food food3 = ff.getFood();
        System.out.println("评委3：品尝");
        food3.eat();
    }
}

class Bussiness2{
    public void taste(Food ff){
//        Food food = ff.getFood();
        System.out.println("评委1：品尝");
        ff.eat();

//        Food food2 = ff.getFood();
        System.out.println("评委2：品尝");
        ff.eat();

//        Food food3 = ff.getFood();
        System.out.println("评委3：品尝");
        ff.eat();
    }
}


//工厂方法模式的优点：
// 仍然具备简单工厂的优点：
// 1.服务器修改了具体产品的类名之后，客户端不知道。客户端只要new 自己的就行了
//2.当客户端需要扩展一个新的产品的时候 不需要修改作者原来的代码 只是需要扩展一个新的工厂而已

//缺点：
// 1.简单工程和工厂方法都有一个优点，就是，服务器端的具体产品类名变化之后，客户端不知道
// 但是 反观我们现在的代码 客户端仍然依赖于具体的工厂的类名呀！  此时 如果服务器修改了具体的工厂的类名，那么客户端也要随之一起修改！
// 也就是说 我们工厂本身就是耦合的，感觉折腾了一圈 又回到了原点
//
// 解释：作为工厂，工厂的名字，是视为接口的，作者有责任 有义务保证工厂的名字是稳定的。 也就是说，虽然客户端依赖于工厂的具体类名，可是再IT业内
// ，所有的工厂都是趋向于稳定的  至少工厂类的名字哦，要比具体产品类的名字更加稳定。
//
// 2. 既然产品是我们自己客户端扩展出来的，那为什么不直接自己实例化呢？ 毕竟这个扩展站出来的LP产品。我们自己就是作者，
// 我们想怎么改类名，我们都能自己把控，为什么还要为自己建造工厂呢？

//解释：作者在开发这个功能的时候，不仅仅 只是会开发一些抽象产品、 具体产品、 对应工厂，还会配套地搭建一些框架 比如我们这边会有人去品尝我们的food
// 这时候 我们新建的lp 能够直接接入到这个功能中  提高了我们的扩展性

// 现在制作出的lpFactory 是为了能把LpFactory 传入给 bussiness.teste方法，所以 必须定义这个LpFactory。
//那为什么不从一开始 就让 Bussiness.taste 直接接受 food 参数呢？ 而不是现在的FoodFactory作为参数
// 那又回到了最开始的问题 我服务器端名字变了 我这边客户端还是要变

//核心缺点： 如果有多个产品等级，工厂类就会爆炸多  当业务需要的类型变多，
// 目前只有食物，当产生饮料，日用品等类别时，我们又要创建新的工厂来实现，造成代码重复臃肿


public class Positive {
    public static void main(String[] args) {
//        3.又回到了最初的起点 服务器变了 我这边也要变
//        new Bussiness2().taste(new LP());
//        new Bussiness2().taste(new RiceNoodle());



//        2.配套设施
//        FoodFactory foodFactory = new LPFactory();//接口直接发生向上转型
//
//        new Bussiness().taste(foodFactory);


//        Food food01 = foodFactory.getFood();
//        food01.eat();
//
//        FoodFactory foodFactory2 = new RiceNoodleFactory();//接口直接发生向上转型
//        Food food02 = foodFactory2.getFood();
//        food02.eat();
//
//
//        FoodFactory factory3=new LPFactory();
//        Food food = factory3.getFood();
//        food.eat();

    }
}