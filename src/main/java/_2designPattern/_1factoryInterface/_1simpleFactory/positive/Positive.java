package _2designPattern._1factoryInterface._1simpleFactory.positive;

/**
 * @author Willam_xh
 * @create 2021-06-01 18:19
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


class FoodFactory{
    public Food getFood(int num){
        Food food =null;
        switch (num){
            case 1 :
                food = new Hamburger();
                break;
            case 2:
                food =new RiceNoodle();
                break;
        }
        return food;
    }
}

//========================上面是作者 下面是用户==============================
//通过这样一个正例，把创建对象的代码全交给服务端处理，将服务端代码和客户端代码进行了解耦。以后产品再找你聊天是不是可以暂时把刀收起来了？
//
//这样做的好处，不只是服务端开发人员受益，当服务端代码修改时，客户端也不知道，也不需要知道。
//
//这样的设计模式并不是十全十美的，任何一种设计模式都不会是十全十美的。只是根据业务逻辑在各方面进行取舍。
//
//优点 1.把具体产品类型 从客户端代码中解耦出来 2.服务器端，如果修改了具体的产品类名，客户端也知道 这便符合了“面向接口变成的思想”
//简单工厂模式的缺点（要在变化中体现出来）：
//客户必须记住工厂中常量和具体产品的映射关系。
//一旦产品品种体量增大到一定程度，工厂类将变得非常臃肿。
//最致命的缺陷，增加产品时，就要修改工厂类。违反开闭原则。
public class Positive {
    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory();
        Food food01 = foodFactory.getFood(1);
        food01.eat();

        Food food02 = foodFactory.getFood(2);
        food02.eat();
    }
}