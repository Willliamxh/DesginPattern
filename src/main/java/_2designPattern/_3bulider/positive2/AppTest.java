package _2designPattern._3bulider.positive2;

/**
 * 针对b的问题，修改代码如下
 *针对不同的需求，我们需要创建不同的建造者，来分别生产不同配置的产品
 *
 */
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

//高级电脑建造者
class HighComputerBuilder{
    private Computer computer=new Computer();

    public Computer build(){
        computer.setCpu("inter");
        computer.setGpu("1080");
        computer.setMemory("16g");
        computer.setHardDisk("1T");

        return computer;
    }
}

//中级电脑建造者
class MidComputerBuilder{
    private Computer computer=new Computer();

    public Computer build(){
        computer.setCpu("inter");
        computer.setGpu("980");
        computer.setMemory("16g");
        computer.setHardDisk("512T");

        return computer;
    }
}

//高级电脑建造者
class LowComputerBuilder{
    private Computer computer=new Computer();

    public Computer build(){
        computer.setCpu("inter");
        computer.setGpu("950");
        computer.setMemory("8g");
        computer.setHardDisk("1T");

        return computer;
    }
}


//======================================================

public class AppTest {
    public static void main(String[] args) {
        HighComputerBuilder cb=new HighComputerBuilder();
        //玩游戏
        Computer build = cb.build();
        System.out.println(build);

        //办公娱乐
        Computer midComputer=new MidComputerBuilder().build();
        System.out.println(midComputer);

        //开发
        Computer lowComputer=new LowComputerBuilder().build();
        System.out.println(lowComputer);



    }
}

/**
 * 目前还不是建造者模式，现在的优点：
 * 1。可以根据客户端的不同需求，使用不同的建造者来生产产品。
 * 2。
 *
 * 目前的缺点：
 *1。我们发现，多个不同的建造者代码在重复。既然出现了重复代码，那就有来坏味道
 * 2。建造过程不稳定，如果在某个建造者创建产品的过程中，漏掉了某个部分，那也不会报错
 * （相当于KFC的某个汉堡少了个步骤，因为没有了标准）
 *
 *
 *
 */