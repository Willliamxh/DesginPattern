package _2designPattern._3bulider.negative;


class Computer {
    private String cpu;
    private String gpu;
    private String memory;
    private String hardDisk;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", memory='" + memory + '\'' +
                ", hardDisk='" + hardDisk + '\'' +
                '}';
    }
}

//======================================================

public class AppTest {
    public static void main(String[] args) {
        Computer c=new Computer();
        c.setCpu("intel");
        c.setGpu("1080");
        c.setMemory("512g");
        c.setHardDisk("1T");

        System.out.println(c);

    }
}

/**
 * 这样做的缺点是什么？
 * 込祥做的缺点是.
 * 1.客戸端程序猿，在突例化好产品的対象之后，必須カ亥対象的毎一个属性賦値， 込祥対于客戸端程序員来説，太麻煩了!
 * 2.违反了迪米特法则。
 *
 *相当于去配电脑的时候，商家把零件都给你 你自己组装电脑
 *
 * 工厂模式和建造者模式都区别：
 * 工厂模式：直接实例化出一个类都对象即可。（直接new个凉皮 注重结果）
 * 建造者模式：是在实例化出类都对象之后，还要给该对象都属性赋值。（加特定调料的凉皮 注重过程）
 *
 *
 *
 *
 */