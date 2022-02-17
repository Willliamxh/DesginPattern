package _2designPattern._3bulider.positive3;

import java.util.concurrent.CompletableFuture;

/**
 * 针对c的问题，修改代码如下
 *
 * 1。创建一个建造者接口，把制造产品的具体步骤给稳定下来
 * 2。我们让建造者类去实现建造者接口，接口中的方法步骤，类就必须都要实现，少
 * 实现一个抽象方法就会报错！
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

interface ComputerBuilder{
    void setCpu();
    void setGpu();
    void setMemory();
    void setHardDisk();
    Computer build();
}


//高级电脑建造者
class HighComputerBuilder implements ComputerBuilder{
    private Computer computer=new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("inter");
    }

    @Override
    public void setGpu() {
        computer.setGpu("1080");
    }

    @Override
    public void setMemory() {
        computer.setMemory("16g");
    }

    @Override
    public void setHardDisk() {
        computer.setHardDisk("1T");
    }

    public Computer build(){
        return computer;
    }
}

//中级电脑建造者
class MidComputerBuilder implements ComputerBuilder{
    private Computer computer=new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("inter");
    }

    @Override
    public void setGpu() {
        computer.setGpu("980");
    }

    @Override
    public void setMemory() {
        computer.setMemory("16g");
    }

    @Override
    public void setHardDisk() {
        computer.setHardDisk("512T");
    }

    public Computer build(){
        return computer;
    }
}

//高级电脑建造者
class LowComputerBuilder implements ComputerBuilder{
    private Computer computer=new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("inter");
    }

    @Override
    public void setGpu() {
        computer.setGpu("950");
    }

    @Override
    public void setMemory() {
        computer.setMemory("8g");
    }

    @Override
    public void setHardDisk() {
        computer.setHardDisk("1T");

    }

    public Computer build(){
        return computer;
    }
}


//======================================================

public class AppTest {
    public static void main(String[] args) {
        HighComputerBuilder cb=new HighComputerBuilder();
        //玩游戏
        cb.setCpu();
        cb.setGpu();
        cb.setHardDisk();
        cb.setMemory();
        Computer build = cb.build();
        System.out.println(build);

        //办公娱乐
        MidComputerBuilder midComputerBuilder = new MidComputerBuilder();
        midComputerBuilder.setCpu();
        midComputerBuilder.setGpu();
        midComputerBuilder.setHardDisk();
        midComputerBuilder.setMemory();
        Computer build1 = midComputerBuilder.build();
        System.out.println(build1);

        //开发 直接调用的话 只有null
        Computer lowComputer=new LowComputerBuilder().build();
        System.out.println(lowComputer);

    }
}

/**
 *
 * 现在还不是建造者模式
 * 优点：
 * 1。建造者类中的建造过程是稳定的，不会漏掉某一步，这样当客户端想扩展建造者时，也不会漏掉某一步
 *
 *
 *缺点：
 * 1。代码仍然有重复
 * 2。客户端现在又变成了自己配置电脑，又违反了迪米特法则。（这相当于去配电脑，虽然不用我亲自组装电脑，但是你必须指挥那个装机元，去做那个事情）
 *
 *
 */