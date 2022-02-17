package _1designPatternPrinciple._7composite.b;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Willam_xh
 * @create 2021-06-05 10:37
 */

//需求：制作一个集合，要求该集合能记录 曾经加过多少个元素 （不是统计某一时刻集合中有多少元素）
//针对于a包的问题，addAll会回调add 方法，我们修改代码如下：把addAll删除掉，
// 不重写HashSet的AddAll了 反正父类的addall会回调add（子类的add 先从子类找 然后找不到了再去父类）



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

//    @Override
//    public boolean addAll(Collection c) {//其实这边也调用了add 子类的add 循环调用了三次add 所以是6
//        count+=c.size();
//        return super.addAll(c);
//    }
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
//此时这个代码 看似很完美 但是还是存在问题
//问题是 目前这个代码 必须依赖于这样一个事实：
//hashSet的addAll必须去回调 add方法。
//万一 addAll 方法 不再调用add 就出问题了
/*
    万一在将来的JDK版本中，HashSet的AddAll实现代码，突然不回调add方法了，则在将来的jdk版本中
    我们定义的这个MySet就被撼动了。

    比如：HashMap 在jdk 1.6 。17 1.8 中分别换了三次
* */

