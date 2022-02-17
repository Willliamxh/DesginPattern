package _1designPatternPrinciple._2openClose.positive;

/**
 * @author Willam_xh
 * @create 2021-06-02 10:27
 */


public class AppTest {
    public static void main(String[] args) {
        Car car = new DiscountCar();
        car.setBrand("Benz");
        car.setColor("black");
        car.setLouyou(true);
        car.setPrice(666666);
        System.out.println(car.getPrice());


    }
}

//变化来了 现在所有汽车都打折
//违反开闭原则的做法 是 直接打开getPrice
//要始终保持开闭原则的话 那就是 保证car的源代码不被修改
//我们创建一个子类 重写Car的getPrice方法