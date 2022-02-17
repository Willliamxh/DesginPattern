package _1designPatternPrinciple._5demeter.negative;

/**
 * @author Willam_xh
 * @create 2021-06-03 20:41
 */

class Computer{
    public void saveData(){
        System.out.println("保存数据");
    }

    public void killProcess(){
        System.out.println("关闭程序");
    }

    public  void  closeScreen(){
        System.out.println("关闭屏幕");
    }
    public  void  powerOff(){
        System.out.println("断电");
    }
}


class Person{
    private Computer c= new Computer();

    //此时这个person对于computer的细节知道的太多了
//    对于Person 只需要知道 关机按钮在哪里就可以了 不需要知道如何保存数据 如何关闭进程 如何断电这些细节。。
//    这样的代码复杂度就提升了
//    万一用户使用不当 直接gg
    public void shutComputer(){
        c.saveData();
        c.killProcess();
        c.closeScreen();
        c.powerOff();
    }

}


public class Apptest {
}
