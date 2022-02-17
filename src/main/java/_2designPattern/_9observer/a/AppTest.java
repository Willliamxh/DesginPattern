package _2designPattern._9observer.a;

/*
有这样一个业务场景。
一个游戏中，有一个角色，角色有hp、mp。在游戏窗口中， 有一些面板来展示该游戏角色的hp、mp.
*/

class Role{
    private String name;
    private Integer hp;
    private Integer mp;

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
        //就是在这个地方 当hp发生变化当时候 一定要通知三个地方
        //1.血条 2 球 3 main 版
        System.out.println("血条更新为："+hp);

        System.out.println("球形更新为："+hp);

        System.out.println("面板更新为："+hp);


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

class Monster{
    public void attack(Role role){
        role.setHp(role.getHp()-10);
    }
}

public class AppTest {
    public static void main(String[] args) {
        Role role=new Role();
        role.setName("haha");

        role.setHp(100);
        role.setMp(100);

        Monster monster = new Monster();
        monster.attack(role);

    }
}
/*
变化来了:如果在游戏面板中,突然多了一个组件也要显示角色的hp和mp,那么就要在以前的代码中，继续添加新的代码，这样不好，不应该这样!
1.违反开闭原则。
2.违反单一职责。

 */