package _2designPattern._8composite.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 为了解决a包中的问题:
1.菜单只能添加菜单项， 不能再添加其他菜单。
2.菜单项不能再添加其他菜单项，不能出现嵌套的菜单形式。

重构代码如下，要达到的目的:
1.菜单可以添加菜单和菜单项
2.菜单项不能再添加菜单项。
就可以出现嵌套的菜单形式。
 */

//菜单组建 提出了两者的共性
abstract class MenuComponent{
    private String name;
    private String description;
    // private List<MenuItem> list=new ArrayList<>();

    public MenuComponent(String name, String description) {
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

    public abstract void print(String prefix);


    //分析 属于菜单的方法

    //属于菜单项的方法
}

class Menu extends MenuComponent{
    public Menu(String name, String description) {
        super(name,description);
    }

    //list只写在菜单里面 因为菜单项不需要去加东西
    private List<MenuComponent> list=new ArrayList<>();

    public void print(String prefix){
        System.out.println(prefix+"菜单名称<"+getName()+">菜单描述"+getDescription());
        Iterator<MenuComponent> itemIterator=list.iterator();
        while (itemIterator.hasNext()){
            MenuComponent next = itemIterator.next();
            //向上转型 是菜单 就用菜单的那个 是菜单项 就用菜单项的那个
            if(next instanceof Menu){
                next.print('\t'+prefix);
            }else {
                next.print('\t'+prefix);
            }

        }
    }

    //此时，add方法，就可以同时添加Menu和MenuItem了。
    public void add (MenuComponent item){
        list.add(item);
    }


}

class MenuItem extends MenuComponent{
    private boolean vegetarian;
    private double price;

    public MenuItem(String name, String description,boolean vegetarian,double price) {
        super(name, description);
        this.vegetarian=vegetarian;
        this.price=price;
    }

    public void print(String prefix){
        String str=vegetarian ? "素食":"";
        System.out.println(prefix+"菜单项的"+getName()+str+"描述为："+getDescription());
    }



}

public class AppTest {
    public static void main(String[] args) {
        Menu menu = new Menu("陕西菜", "陕西人爱吃的菜");
        MenuItem item=new MenuItem("胡辣汤","很糊很辣很烫",false,6);
        Menu menu2=new Menu("羊肉泡","羊肉汤");
        MenuItem item2_1=new MenuItem("优质羊肉泡","加肉加价",false,36);
        MenuItem item2_2=new MenuItem("普通羊肉泡","普通",false,26);
        MenuItem item3=new MenuItem("蚂蚁上树","粉条肉沫",false,16);


        menu.add(item);

        menu2.add(item2_1);
        menu2.add(item2_2);

        menu.add(menu2);
        menu.add(item3);


        //
        menu.print("");
    }
}

/*
* 现在变化来了，用户需要把素食给全部提取出来
* 那我们就在菜单项那边加一个boolean 顺便加个价格选项
*
* 但是，如果有了各种要求
* 比如说 价格 要求 口味 颜色 分量
* 那我不能去加if 因为涉及到了开闭原则
* */
