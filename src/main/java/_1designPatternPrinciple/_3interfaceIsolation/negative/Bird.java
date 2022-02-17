package _1designPatternPrinciple._3interfaceIsolation.negative;

/**
 * @author Willam_xh
 * @create 2021-06-02 16:18
 */
public class Bird implements Animal {
    @Override
    public void eat() {

    }

    @Override
    public void swim() {
        throw new RuntimeException("you can you up");
    }

    @Override
    public void fly() {
        System.out.println("fly");
    }
}
