package _2designPattern._8composite.e;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*
为了解诀d包中的问题:
1.客户需要自己写递归代码
重构代码如下:
让客户端只需调用hasNext和next即可。
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

    public CompositeIterator iterator() {
        throw new UnsupportedOperationException();
    }
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

    public CompositeIterator iterator(){
        return new CompositeIterator(list.iterator());
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

class CompositeIterator implements Iterator<MenuComponent> {
    private Stack<Iterator<MenuComponent>> s = new Stack();

    public CompositeIterator(Iterator<MenuComponent> it) {
        this.s.push(it);
    }

    public boolean hasNext() {
        if (this.s.isEmpty()) {
            return false;
        } else {
            Iterator<MenuComponent> it = (Iterator)this.s.peek();
            if (!it.hasNext()) {
                this.s.pop();
                return this.hasNext();
            } else {
                return true;
            }
        }
    }

    public MenuComponent next() {
        Iterator<MenuComponent> it = (Iterator)this.s.peek();
        MenuComponent mc = (MenuComponent)it.next();
        if (mc instanceof Menu) {
            this.s.push(((Menu)mc).getList().iterator());
        }

        return mc;
    }
}



//==========================

public class AppTest {
    public static void main(String[] args) {
        MenuComponent menu = new Menu("蜗牛餐厅菜单", "陕西人爱吃的菜");
        MenuComponent menu1=new Menu("陕西菜","ssss");
        MenuComponent menu2=new Menu("川菜","cccc");
        MenuComponent menu3=new Menu("鲁菜","llll");

        MenuComponent item1_1=new MenuItem("胡辣汤","aaaa",false,36);
        MenuComponent item1_2=new MenuItem("凉皮","aaaa",true,36);
        MenuComponent item1_3=new MenuItem("比昂比昂面","aaaa",false,36);

        MenuComponent item2_1=new MenuItem("剁椒鱼头","加肉加价",false,36);
        MenuComponent item2_2=new MenuItem("干煸豆角","加肉加价",true,36);
        MenuComponent item2_3=new MenuItem("火锅","加肉加价",false,36);

        MenuComponent item3_1=new MenuItem("牛肉拉面","加肉加价",false,36);
        MenuComponent item3_2=new MenuItem("豆角拉面","加肉加价",true,36);
        MenuComponent item3_3=new MenuItem("牛肉拉面","加肉加价",false,36);

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

        CompositeIterator it = menu.iterator();

        while(it.hasNext()) {
            MenuComponent mc = it.next();

            try {
                if (mc.isVegetarian()) {
                    System.out.println(mc.getName() + ", " + mc.getDescription());
                }
            } catch (Exception var19) {
            }
        }

        // //
        // menu.print("");



        // printV(menu);


    }

    // private static void printV(MenuComponent menu) {
    //     Iterator<MenuComponent> iterator = menu.getList().iterator();
    //     while (iterator.hasNext()) {
    //         MenuComponent mc = iterator.next();
    //         try {
    //             if(mc.isVegetarian()){
    //                 mc.print("");
    //             }
    //         } catch (Exception e) {
    //             printV(mc);
    //         }
    //     }
    // }
}

