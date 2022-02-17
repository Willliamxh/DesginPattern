package _2designPattern._9observer.b;

/*
为了解决a包的问题 我们将面板的公共属性 提出来
抽象成observer接口
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
            obj.update(hp);
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
}

interface Observer{
    //需要一个方法，来接受主体发来的新数据
    public void update(int hp);
}

class Panel implements Observer{
    @Override
    public void update(int hp) {
        System.out.println("在左上角的面板中更新数据"+hp);
    }
    //需要一个方法，来接受主体发来的新数据

}

class BallPanel implements Observer{
    @Override
    public void update(int hp) {
        System.out.println("在球形面板中 更新数据"+hp);
    }
    //需要一个方法，来接受主体发来的新数据

}
class HeadPanel implements Observer{
    @Override
    public void update(int hp) {
        System.out.println("在头上更新数据"+hp);
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
    public void update(int hp) {
        System.out.println("更新右下角的面板,数据为"+hp);
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
优点，
1.当我们添加一个新的面板要显示数据时，不会违反开闭原则。
2.因为每个面板的算法，都被隔离在不同的类中了，也就符合了单一-职责! !|
缺点:
1.目前主体只会把自己的hp广播给所有的观察者，那么如果想也把mp-起广播呢?势必要违反开闭原则! !| T
2.而且游戏业务，经常变化，经常加入新的玩法，导致Role类的属性，越来越多，难道每次多一 个属性，都要修改Observer的update方法吗?


*/