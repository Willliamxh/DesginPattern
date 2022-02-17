package _1designPatternPrinciple._7composite.d;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Willam_xh
 * @create 2021-06-05 10:37
 */

//需求：制作一个集合，要求该集合能记录 曾经加过多少个元素 （不是统计某一时刻集合中有多少元素）
//针对c包的问题
    /*
    * 修改代码如下：
    * 1.我们不再重写这两个方法了，因为会撼动架构
    * 2.我们额外制作两个代替add和addall的方法，add2 和 addAll2
    * 然后 还要在类API文档中 说明每当使用add和addAll的时候，都去调用add2和AddAll2
    *
    *
    *
    * */


class MySet extends HashSet {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public boolean add2(Object o) {
        count++;
        return super.add(o);
    }


    public boolean addAll2(Collection c) {//其实这边也调用了add 子类的add 循环调用了三次add 所以是6
        boolean bb=false;
        for (Object o : c) {
            if (add2(o)) {
                bb=true;
            }
        }
        return bb;
    }
}


public class AppTest {
    public static void main(String[] args) {
        MySet mySet = new MySet();
//        mySet.add("a");
//        mySet.add("b");
//        mySet.add("c");

        Set set2 = new HashSet<>();
        set2.add("葵花宝典");
        set2.add("辟邪剑谱");
        set2.add("乾坤大挪移");

        mySet.addAll(set2);
//        看似解决了需求，add 方法可以成功将count进行自加，
//        addAll方法通过方法内调用add，可以成功将count进行增加操作。
        set2.add("1213");
        System.out.println(mySet.getCount());
    }
}
/*
问题是：
这个代码看上去很勉强，但是也满足需求了

1.这种情况对用户要求有点过分了，用户必须看类的api文档，看完了还要怪怪地使用add2和addAll2，还
不能写错
2.更要命的问题是：就是那么寸，在jdk新版本中，HashSet多了一个api all2和addAll2
重名了 诶 就是玩

继承 卒
* */
