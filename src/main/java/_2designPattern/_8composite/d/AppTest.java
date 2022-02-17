package _2designPattern._8composite.d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 为了解决b包中的问题:

为了解决c包中的问题:
1.客户依赖Menu和MenuItem
重构代码如下:
组合模式，将对象组合成树形结构以表示“部分-整体”的层次结构，组合模式使得用户对单个对象和组合对象的使用具有一致性。
掌握组合模式的重点是要理解清楚“部分/整体”还有”单个对象“与"组合对象”的含义。

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
    //属于菜单的方法: add remove getChild
    //这些方法对于菜单而言，是有意义的，但是对于菜单项而言没有意义,为什么还非要定义的这个父类中呢?
    //为的就是:组合模式使得用户对单个对象和组合对象的使用具有一致性。
    //增加
    public void add(MenuComponent item){
        throw new UnsupportedOperationException();
    }
    //删除
    public void remove(MenuComponent item){
        throw new UnsupportedOperationException();
    }
    //获取菜单项
    public MenuComponent getChild(int i){
        throw new UnsupportedOperationException();
    }

    //属于菜单项的方法
    //这些方法对于菜单项而言，是有意义的，但是对于菜单而言没有意义,为什么还非要定义的这个父类中呢?
    //为的就是:组合模式使得用户对单个对象和组合对象的使用具有一致性。
    //getPrice isVegetarian
    public double getPrice(){
        throw new UnsupportedOperationException();
    }
    public boolean isVegetarian(){
        throw new UnsupportedOperationException();
    }

    public  List getList(){
        throw new UnsupportedOperationException();
    };
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

    @Override
    public void remove(MenuComponent item) {
        list.remove(item);
    }

    @Override
    public MenuComponent getChild(int i) {
        return list.get(i);
    }

    public List<MenuComponent> getList() {
        return list;
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

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

//==========================

public class AppTest {
    public static void main(String[] args) {
        Menu menu = new Menu("蜗牛餐厅菜单", "陕西人爱吃的菜");
        Menu menu1=new Menu("陕西菜","ssss");
        Menu menu2=new Menu("川菜","cccc");
        Menu menu3=new Menu("鲁菜","llll");

        MenuItem item1_1=new MenuItem("胡辣汤","aaaa",false,36);
        MenuItem item1_2=new MenuItem("凉皮","aaaa",true,36);
        MenuItem item1_3=new MenuItem("比昂比昂面","aaaa",false,36);

        MenuItem item2_1=new MenuItem("剁椒鱼头","加肉加价",false,36);
        MenuItem item2_2=new MenuItem("干煸豆角","加肉加价",true,36);
        MenuItem item2_3=new MenuItem("火锅","加肉加价",false,36);

        MenuItem item3_1=new MenuItem("牛肉拉面","加肉加价",false,36);
        MenuItem item3_2=new MenuItem("豆角拉面","加肉加价",true,36);
        MenuItem item3_3=new MenuItem("牛肉拉面","加肉加价",false,36);

        menu1.add(item1_1);
        menu1.add(item1_2);
        menu1.add(item1_3);

        menu2.add(item2_1);
        menu2.add(item2_2);
        menu2.add(item2_3);

        menu3.add(item3_1);
        menu3.add(item3_2);
        menu3.add(item3_3);

        menu.add(menu1);
        menu.add(menu2);
        menu.add(menu3);

        // //
        menu.print("");

        printV(menu);


    }

    private static void printV(MenuComponent menu) {
        Iterator<MenuComponent> iterator = menu.getList().iterator();
        while (iterator.hasNext()) {
            MenuComponent mc = iterator.next();
            try {
                if(mc.isVegetarian()){
                    mc.print("");
                }
            } catch (Exception e) {
                printV(mc);
            }
        }
    }
}

//此时，客户端只依赖于MenuComponent,而不再知道Menu和MenuItem的存在了，这样符合最后知道原则。
//此时，仍然需要客户端自己写递归代码，这样对客户端不友好，最理想的情况是: hasNext，next.
