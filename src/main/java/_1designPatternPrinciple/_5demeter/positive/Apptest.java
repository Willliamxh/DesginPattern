package _1designPatternPrinciple._5demeter.positive;

/**
 * @author Willam_xh
 * @create 2021-06-03 20:41
 */

class Computer{
    private void saveData(){
        System.out.println("保存数据");
    }

    private void killProcess(){
        System.out.println("关闭程序");
    }

    private  void  closeScreen(){
        System.out.println("关闭屏幕");
    }
    private  void  powerOff(){
        System.out.println("断电");
    }

    public void turnOff(){
        this.saveData();
        this.killProcess();
        this.closeScreen();
        this.powerOff();
    }
}


class Person{
    private Computer c= new Computer();

    //此时这个person对于computer的细节知道的太多了
//    对于Person 只需要知道 关机按钮在哪里就可以了 不需要知道如何保存数据 如何关闭进程 如何断电这些细节。。
//    这样的代码复杂度就提升了
//    万一用户使用不当 直接gg
    public void shutComputer(){
        c.turnOff();
    }

}


public class Apptest {
}
