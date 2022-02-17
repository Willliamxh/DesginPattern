package _2designPattern._3bulider.positive4;

/**
 * 针对d的问题，修改代码如下
 *
 *建造者模式，终于进化出来了。
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


class Director{
    public Computer build(ComputerBuilder cb){
        cb.setCpu();
        cb.setGpu();
        cb.setHardDisk();
        cb.setMemory();
        Computer build = cb.build();
        return build;
    }
}

//======================================================
//自己定一个中高配置
class HMComputerBuilder implements ComputerBuilder{
    private Computer computer=new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("inter");
    }

    @Override
    public void setGpu() {
        computer.setGpu("970");
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


public class AppTest {
    public static void main(String[] args) {
        HighComputerBuilder hb=new HighComputerBuilder();
        MidComputerBuilder mb=new MidComputerBuilder();
        LowComputerBuilder lb=new LowComputerBuilder();

        //玩游戏的电脑
        Director director=new Director();
        Computer build=director.build(hb);
        System.out.println(build);

        //办公用的电脑
        Computer build1 = new Director().build(mb);
        System.out.println(build1);

        HMComputerBuilder hmComputerBuilder = new HMComputerBuilder();
        Computer build2 = new Director().build(hmComputerBuilder);
        System.out.println(build2);


    }
}

/**
 *
 *这就是建造者模式
 *
 *
 * 优点：
 *1。创建对象的过程是稳定不变的（因为有ComputerBuilder接口来稳定过程）
 *2。创建对象的过程只写了一次，没有重复代码（指挥者完成）
 * 3。当需要扩展指挥者的时候，不用修改之前的代码，这符合来开闭原则 我客户端可以自己定义想要的电脑类型
 *
 * 建造者和工厂模式的区别是什么？
 * 1。工厂模式只需要一个简单的new 。new 出产品即可
 * 2。建造者模式更加注重new出产品之后，为产品赋值的过程
 *
 */