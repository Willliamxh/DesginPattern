package _1designPatternPrinciple._7composite.a;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Willam_xh
 * @create 2021-06-05 10:37
 */

//需求：制作一个集合，要求该集合能记录 曾经加过多少个元素 （不是统计某一时刻集合中有多少元素）
//


class MySet extends HashSet {
    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public boolean add(Object o) {
        count++;
        return super.add(o);
    }

    @Override
    public boolean addAll(Collection c) {//其实这边也调用了add 子类的add 循环调用了三次add 所以是6
        count+=c.size();
        return super.addAll(c);
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
        System.out.println(mySet.getCount());
    }
}
//问题是：在执行了addAll之后，count不是3，而是6，为什么呢？因为addAll回调了add方法//父类调用add 那边首先用的是子类的add。每调用一次add
//就会使得count++
//所以这并没有解决需求



