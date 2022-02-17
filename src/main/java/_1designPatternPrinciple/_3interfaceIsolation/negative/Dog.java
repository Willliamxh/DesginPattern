package _1designPatternPrinciple._3interfaceIsolation.negative;

/**
 * @author Willam_xh
 * @create 2021-06-02 16:17
 */
public class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("啃骨头");
    }

    @Override
    public void swim() {
        System.out.println("狗刨");
    }

    @Override
    public void fly() {
        System.out.println("你行你上");
    }
}
