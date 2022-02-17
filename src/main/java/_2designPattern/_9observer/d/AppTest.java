package _2designPattern._9observer.d;

/*
为了解决c包中的问题 我们将role和对应的观察者组合起来
*/

import java.util.ArrayList;
import java.util.List;

class Role{
    private String name;
    private Integer hp;
    private Integer mp;
    private List<Observer> observers=new ArrayList<>();

    public void addObserver(Observer obj){
        observers.add(obj);
    }

    public void removeObserver(Observer obj){
        observers.remove(obj);
    }

    public void notifyObservers(){
        for(Observer obj:observers){
            obj.update();
            // System.out.println("通知"+obj);
        }
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;

        //每每当hp发生变化 就通知所有观察者
        notifyObservers();
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", mp=" + mp +
                '}';
    }
}

interface Observer{
    //需要一个方法，来接受主体发来的新数据
    public void update();
}

class Panel implements Observer{
    private Role r;

    public Panel(Role r) {
        this.r = r;
    }

    @Override
    public void update() {
        System.out.println("在左上角的面板中更新数据"+r);
    }
    //需要一个方法，来接受主体发来的新数据

}

class BallPanel implements Observer{
    private Role r;

    public BallPanel(Role r) {
        this.r = r;
    }
    @Override
    public void update() {//所有观察者 只能观察role了
        System.out.println("在球形面板中 更新数据"+r);
    }
    //需要一个方法，来接受主体发来的新数据

}

class HeadPanel implements Observer{
    private Role r;

    public HeadPanel(Role r) {
        this.r = r;
    }
    @Override
    public void update() {
        System.out.println("在头上更新数据"+r);
    }
    //需要一个方法，来接受主体发来的新数据
}

// /=======================================
class Monster{
    public void attack(Role role){
        role.setHp(role.getHp()-10);
    }
}

class RactPanel implements Observer{
    private Role r;

    public RactPanel(Role r) {
        this.r = r;
    }
    @Override
    public void update() {
        System.out.println("更新右下角的面板,数据为"+r);
    }
}

public class AppTest {
    public static void main(String[] args) {
        Role r=new Role();
        r.setName("haha");

        r.setHp(100);
        r.setMp(100);



        Panel panel = new Panel(r);
        BallPanel ballPanel = new BallPanel(r);
        HeadPanel headPanel = new HeadPanel(r);
        RactPanel ractPanel = new RactPanel(r);


        r.addObserver(panel);
        r.addObserver(ballPanel);
        r.addObserver(headPanel);
        r.addObserver(ractPanel);

        Monster monster = new Monster();
        monster.attack(r);
        // monster.attack(r);
        // monster.attack(r);
        // monster.attack(r);



    }
}

//目前没什么问题了