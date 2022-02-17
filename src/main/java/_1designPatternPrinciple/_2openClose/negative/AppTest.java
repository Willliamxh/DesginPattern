package _1designPatternPrinciple._2openClose.negative;

/**
 * @author Willam_xh
 * @create 2021-06-02 10:27
 */



public class AppTest {
    public static void main(String[] args) {
        Car car = new Car();
        car.setBrand("Benz");
        car.setColor("black");
        car.setLouyou(true);
        car.setPrice(666666);
        System.out.println(car);


    }
}
//变化来了 现在所有汽车都打折
//违反开闭原则的做法 是 直接打开getPrice