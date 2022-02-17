package _2designPattern._3decorator.positive2;


/**
 *
 * 业务场景:星巴克卖咖啡，-开始，只有4种咖啡:
 * Decaf Espresso DrakRoast HouseBlend
 * 因为所有咖啡都有共性，所有开发人员，把它们的共性上提到一个父类中: Beverage
 *
 *
 *e针对于b包的问题，我们何必为每一 种咖啡加每一 种调料都创建一 个类呢?这样做太2了，太笨了!
 * 1我们可以直接在父类Beverage中，添加4个boolean属性， 分别代表是否加了对应的4种调料啊!
 *
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
    public double cost(){
        double total=0;
        if(milk){
            total+=2;
        }if(soy){
            total+=3;
        }if(mocha){
            total+=4;
        }if(bubble){
            total+=5;
        }
        return total;
    }

    public String getDescription() {
        String str=description;
        if(milk){
            str=str+"牛奶";
        }if(soy){
            str=str+"豆浆";
        }if(mocha){
            str=str+"摩卡";
        }if(bubble){
            str=str+"泡沫";
        }
        return str;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isMocha() {
        return mocha;
    }

    public void setMocha(boolean mocha) {
        this.mocha = mocha;
    }

    public boolean isBubble() {
        return bubble;
    }

    public void setBubble(boolean bubble) {
        this.bubble = bubble;
    }
}

class Decaf extends Beverage{

    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public double cost() {
        //咖啡本身价格+调料价格
        return 1+super.cost();
    }
}


class Espresso extends Beverage{

    public Espresso() {
        super("浓缩咖啡");
    }

    @Override
    public double cost() {
        //咖啡本身价格+调料价格
        return 1.5+super.cost();
    }
}

class DrakRoast extends Beverage{

    public DrakRoast() {
        super("椒炒咖啡");
    }

    @Override
    public double cost() {
        //咖啡本身价格+调料价格
        return 2+super.cost();
    }
}


class HouseBlend extends Beverage{

    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 3+super.cost();
    }
}


//===================================================================

class tea extends Beverage{

    public tea() {
        super("tea");
    }

    public double cost(){
        return 2;
    }
}

public class AppTest {
    public static void main(String[] args) {
        Beverage b=new DrakRoast();
        b.setMilk(true);
        b.setBubble(true);

        System.out.println(b.getDescription()+":"+b.cost());

    }
}

/**
 *优点:
 * 1.类没有爆炸，没有出现各种各样的类!
 * 2.变化来了:星巴克的老板，又加入了一个新的饮料: 茶. 不会影响服务端的代码。是符合开闭幕式原则的。
 * 缺点:
 * 1.星巴克的老板，又加入了一种新的调料:枸杞，就要重新改写父类Berverage的cost方法和getDisecription方法，来把枸杞家进去!
 * 这势必会违反开闭原则! 要去boolean那边加入一个新的变量值
 *
 *
 *
 *
 *
 *
 */
