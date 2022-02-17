package _1designPatternPrinciple._2openClose.negative;

/**
 * @author Willam_xh
 * @create 2021-06-02 10:26
 */
public class Car {
    private String brand;
    private String color;
    private boolean louyou;
    private double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isLouyou() {
        return louyou;
    }

    public void setLouyou(boolean louyou) {
        this.louyou = louyou;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        //直接乘 0.8 的话就违反了开闭原则
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", louyou=" + louyou +
                ", price=" + price +
                '}';
    }
}
