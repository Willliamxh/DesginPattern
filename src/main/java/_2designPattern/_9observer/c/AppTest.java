package _2designPattern._9observer.c;

/*
为了解决b包的问题
我们直接给观察者传role
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
            obj.update(this);
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
    public void update(Role r);
}

class Panel implements Observer{
    @Override
    public void update(Role r) {
        System.out.println("在左上角的面板中更新数据"+r);
    }
    //需要一个方法，来接受主体发来的新数据

}

class BallPanel implements Observer{
    @Override
    public void update(Role r) {//所有观察者 只能观察role了
        System.out.println("在球形面板中 更新数据"+r);
    }
    //需要一个方法，来接受主体发来的新数据

}

class HeadPanel implements Observer{
    @Override
    public void update(Role r) {
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

    @Override
    public void update(Role r) {
        System.out.println("更新右下角的面板,数据为"+r);
    }
}

public class AppTest {
    public static void main(String[] args) {
        Role r=new Role();
        r.setName("haha");

        r.setHp(100);
        r.setMp(100);



        Panel panel = new Panel();
        BallPanel ballPanel = new BallPanel();
        HeadPanel headPanel = new HeadPanel();
        RactPanel ractPanel = new RactPanel();


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

/*
优点,
1.目前每当主体的状态发生变化，都会把主体整个对象囫囵地广播给所有观察者，就算主体有很多个属性也不会影响代码!
缺点:
2.你有没有发现，作为-一个接口，Observer接口中的update方法，居然出现了具体类名!如此，Observer就只能观察Role这个类，观察不了别的类的对象了!
*/