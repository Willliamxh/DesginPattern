package _2designPattern._3decorator.positive3;


/**
 *
 * 业务场景:星巴克卖咖啡，-开始，只有4种咖啡:
 * Decaf Espresso DrakRoast HouseBlend
 * 因为所有咖啡都有共性，所有开发人员，把它们的共性上提到一个父类中: Beverage
 *
 * 针对于c包的问题，我们，要使用“装饰器设计模式"来解救我们!
 * 装饰器模式：一边关联 一边继承
 */

/**
 * 父类没有无参构造器，所以子类只能调用这个有参构造
 */
abstract class Beverage{
    private String description;

    private boolean milk,soy,mocha,bubble;

    public Beverage(String description){
        this.description=description;
    }
    public abstract double cost();

    public String getDescription() {
        return this.description;
    }

}



class Decaf extends Beverage{

    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public double cost() {
        //咖啡本身价格+调料价格
        return 1;
    }
}


class Espresso extends Beverage{

    public Espresso() {
        super("浓缩咖啡");
    }

    @Override
    public double cost() {
        //咖啡本身价格+调料价格
        return 1.5;
    }
}

class DrakRoast extends Beverage{

    public DrakRoast() {
        super("椒炒咖啡");
    }

    @Override
    public double cost() {
        //咖啡本身价格+调料价格
        return 2;
    }
}


class HouseBlend extends Beverage{

    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 3;
    }
}


/**
 * 判断两个类之间能不能有继承关系，主要看这两个类之间有没有“is a”关系。 并且还要符合里氏替换原则.
 * 以上只是原则，不是语法强制的!也就是说， 在特定情况下，可以范围这个规则，必须在装饰器模式中就是这样:
 * 尽管调料不是饮料,但是为了制作出装饰器模式，我们也只能让调料去继承饮料。
 *
 */
abstract class Condiment extends Beverage{

    //让调料类，关联饮料类
    protected Beverage beverage;

    public Condiment(Beverage beverage) {
        super("调料");
        this.beverage=beverage;
    }


}

class Milk extends Condiment{
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        //饮料的价格+牛奶的价格 返回
        return beverage.cost()+0.2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+" 牛奶";
    }
}

class Soy extends Condiment{
    public Soy(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        //饮料的价格+soy的价格 返回
        return beverage.cost()+0.3;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+" 豆浆";
    }
}


class Mocha extends Condiment{
    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        //饮料的价格+soy的价格 返回
        return beverage.cost()+0.4;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+" 摩卡";
    }
}

class Bubble extends Condiment{
    public Bubble(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        //饮料的价格+soy的价格 返回
        return beverage.cost()+0.5;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+" 气泡";
    }
}

//===================================================================

class Gouqi extends Condiment{
    public Gouqi(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return 0.9+beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+"加了枸杞";
    }
}


public class AppTest {
    public static void main(String[] args) {
        Beverage b=new DrakRoast();
        System.out.println(b.getDescription()+":"+b.cost());

        //加牛奶
        Beverage beverageWithMilk = new Milk(b);
        System.out.println(beverageWithMilk.getDescription()+":"+beverageWithMilk.cost());

        //加摩卡
        Mocha beverageWithMilkAndMocha = new Mocha(beverageWithMilk);
        System.out.println(beverageWithMilkAndMocha.getDescription()+":"+beverageWithMilkAndMocha.cost());

        //加豆浆
        Beverage beverageWithMilkAndMochaAndSoy = new Soy(beverageWithMilkAndMocha);
        System.out.println(beverageWithMilkAndMochaAndSoy.getDescription()+":"+beverageWithMilkAndMochaAndSoy.cost());

        // 第一份加枸杞
        Beverage b2=new Gouqi(b);
        System.out.println(b2.getDescription()+":"+b2.cost());


    }
}

/**
 *优点:
 * 加一个新的饮料或者一个新的调料都不影响开闭原则。
 *
 * 缺点：
 *1。类还是有点多，但是我们已经尽力了。
 *
 */
