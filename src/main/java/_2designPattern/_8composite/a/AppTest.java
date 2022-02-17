package _2designPattern._8composite.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
菜单
    |--陕菜
        |--胡辣汤
             - -肉丸胡辣汤
            --河南胡辣汤
        |--羊肉泡
            优质
            --普通
            --双份优质
        |--三秦套餐
    1--川菜
        :火锅
        .辣椒就朝天椒
        伤心惊粉
     粤菜
        --鱼丸
        --牛丸
        --虾丸
 */
class Menu{
    private String name;
    private String description;
    private List<MenuItem> list=new ArrayList<>();

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void print(String prefix){
        System.out.println("菜单名称<"+name+">菜单描述"+description);
        Iterator<MenuItem> itemIterator=list.iterator();
        while (itemIterator.hasNext()){
            MenuItem next = itemIterator.next();
            next.print("\t"+prefix);
        }
    }
    public void add (MenuItem item){
        list.add(item);
    }


}

class MenuItem{
    private String name;
    private String description;


    public MenuItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void print(String prefix){
        System.out.println(prefix+"菜单项的"+name+"描述为："+description);
    }



}

public class AppTest {
    public static void main(String[] args) {
        Menu menu = new Menu("陕西菜", "陕西人爱吃的菜");
        MenuItem item=new MenuItem("胡辣汤","很糊很辣很烫");
        MenuItem item2=new MenuItem("羊肉泡","羊肉汤");
        MenuItem item2_1=new MenuItem("优质羊肉泡","加肉加价");
        MenuItem item2_2=new MenuItem("普通羊肉泡","普通");

        MenuItem item3=new MenuItem("蚂蚁上树","粉条肉沫");

        menu.add(item);
        menu.add(item2);
        menu.add(item3);

        //问题来了，item2.add(item2_1) 这个操作无法通过编译
        //因为菜单项，没有add方法，用于添加其他菜单项
        //所以这样做，不能满足需求

        menu.print("");
    }
}
