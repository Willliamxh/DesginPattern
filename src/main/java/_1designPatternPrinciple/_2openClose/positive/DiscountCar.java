package _1designPatternPrinciple._2openClose.positive;

/**
 * @author Willam_xh
 * @create 2021-06-02 15:58
 */
public class DiscountCar extends Car {
    @Override
    public double getPrice() {
        return super.getPrice()*0.8;
    }
}
