package _2designPattern._1factoryInterface._3abstractFactory;

/**
 * @author Willam_xh
 * @create 2021-06-01 18:19
 * 针对于工厂方法的问题，当有多个产品等级的时候（食物，饮料，甜品）的时候，工厂类就会很多很多
 * 修改代码如下：抽象工厂设计模式
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


interface Drink{
    public void drink();//作为饮料都能被喝
}

class Cola implements Drink{

    @Override
    public void drink() {
        System.out.println("可口可乐 你值得拥有");
    }
}

class IcePeak implements Drink{
    @Override
    public void drink() {
        System.out.println("从小就喝冰峰");
    }
}


interface Factory{
    public Food getFood();
    public Drink getDrink();//我也生产饮料 然后抽象工厂
}


class KFCFactory implements Factory{
    @Override
    public Food getFood() {
        return new Hamburger();
    }

    @Override
    public Drink getDrink() {
        return new Cola();
    }
}

class SanQinFactory implements Factory{
    @Override
    public Food getFood() {
        return new RiceNoodle();
    }

    @Override
    public Drink getDrink() {
        return new IcePeak();
    }
}


//
//interface DrinkFactory {
//    public Drink getDrink();
//}
//
//class ColaFactory implements DrinkFactory{
//
//    @Override
//    public Drink getDrink() {
//        return new Cola();
//    }
//}
//
//class icePeakFactory implements DrinkFactory{
//
//    @Override
//    public Drink getDrink() {
//        return new IcePeak();
//    }
//}
//===============================================================================================
//
class LP implements Food{

    @Override
    public void eat() {
        System.out.println("从小吃凉皮");
    }
}

class Fenta implements Drink{

    @Override
    public void drink() {
        System.out.println("芬达你值得拥有");
    }
}

class BaoJiFactory implements Factory{

    @Override
    public Food getFood() {
        return new LP();
    }

    @Override
    public Drink getDrink() {
        return new Fenta();
    }
}
////现在我想让工厂去生产lp 这样就能随意扩展 不用去修改作者的代码了
//
//class LPFactory implements FoodFactory{
//    @Override
//    public Food getFood() {
//        return new LP();
//    }
//}

class Bussiness{
    public void taste(Factory ff){
        Food food = ff.getFood();
        Drink drink = ff.getDrink();
        System.out.println("评委1：品尝");
        food.eat();
        drink.drink();

        Food food2 = ff.getFood();
        Drink drink1 = ff.getDrink();
        System.out.println("评委2：品尝");
        food2.eat();
        drink1.drink();

        Food food3 = ff.getFood();
        Drink drink2 = ff.getDrink();
        System.out.println("评委3：品尝");
        food3.eat();
        drink2.drink();

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


public class Positive {
    public static void main(String[] args) {

        BaoJiFactory baoJiFactory = new BaoJiFactory();
        Food food = baoJiFactory.getFood();

        new Bussiness().taste(new BaoJiFactory());

    }
}

/*
*   抽象工厂的优点：
*   1.仍然有简单工厂（耦合性降低）和工厂方法（扩展性高）的优点
*   2.更重要的是，抽象工厂把工厂类的数量减少了！无论有多个产品等级，工厂就一套。
*   3.多一个产品簇的时候，直接就扩展
*
*   杠点
*   1.为什么三清工厂中，为什么必须是米线搭配冰峰呢？为什么不是米线搭配可乐？
*   解释：抽象工厂中，可以生产多个产品，这多个产品之间，必须要有内在联系。
*
*   抽象工厂的缺点：
*   1.当产品等级发生变化的时候，不论增加或者删除产品等级，都要引起所有以前工厂代码的修改
*   这就违反了 开闭原则
*
*   结论：
*   当产品等级比较固定的时候，可以考虑使用抽象工程
*   当产品等级经常变化的时候，不建议使用抽象工厂
*
*   Spring 动态工厂
*
*
*
*
*
*
* */