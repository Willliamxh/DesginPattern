package _2designPattern._3bulider.positive;

/**
 * 针对a的问题 修改代码如下
 *
 * 作者专门创建一个电脑建造者类 ComputerBuilder
 * 这个类专门负责封装组装电脑的过程
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

//电脑建造者类，建造者类必须关联产品
class ComputerBuilder{
    private Computer computer=new Computer();

    public Computer build(){
        computer.setCpu("inter");
        computer.setGpu("1080");
        computer.setMemory("16g");
        computer.setHardDisk("1T");

        return computer;
    }

}


//======================================================

public class AppTest {
    public static void main(String[] args) {
        ComputerBuilder cb=new ComputerBuilder();
        //玩游戏
        Computer build = cb.build();
        System.out.println(build);

        //办公娱乐

        //开发



    }
}

/**
 * 目前还不是建造者模式，现在的优点：
 * 1。客户端程序员需要一个产品的时候，直接向建造者申请即可，建造者封装了创建电脑的"复杂"过程
 *
 *
 * 目前的缺点：
 *1。封装的太狠了，什么都用最高的配置。相当于去配电脑，无论是什么需求，商家都会配置最贵的电脑
 *
 *
 *
 *
 */