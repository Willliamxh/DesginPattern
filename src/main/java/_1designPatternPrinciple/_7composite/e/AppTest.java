package _1designPatternPrinciple._7composite.e;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Willam_xh
 * @create 2021-06-05 10:37
 */

//需求：制作一个集合，要求该集合能记录 曾经加过多少个元素 （不是统计某一时刻集合中有多少元素）
//针对d包的问题
    /*
    * 修改代码如下：
     1.我们的Myset别去继承HashSet了
     2.取而代之，我们去用组合关系
    *
    * */


class MySet {

    private HashSet set= new HashSet();
    private int count = 0;

    public int getCount() {
        return count;
    }

    public boolean add(Object o) {
        count++;
        return set.add(o);//没有重写 他调用的是自己的add 里面没有count++
    }


    public boolean addAll(Collection c) {//其实这边也调用了add 子类的add 循环调用了三次add 所以是6
        count+=c.size();
       return set.addAll(c);//没有重写 他调用的是自己的addall 里面没有count+ 不会去累加size

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
        mySet.add("1213");
        System.out.println(mySet.getCount());
    }
}
/*
此时 完美的解决了这个需求

组合的优点，我们已经体会到了，

问题是：
1.难道以后都不用继承了嘛？
2.难道以后都不能进行方法重写了嘛？

只有一个原则！
如果，父类作者和子类的作者不是同一个人，就别继承。
那么父类作者不知道未来的子类会重写自己的哪一个方法。
那么子类的作者，也不知道，未来的父类会加入什么新方法

如果父类作者和子类作者就是同一个人，那就可以放开手脚去使用继承了。
自己当然知道每一个方法是什么作用，作者可以同时控制父类和子类。

 我们自己写代码，继承，重写，随便使用。
 如果我们仅仅是为了复用代码而重写别人的类，那难免出现沟通上的问题，

* */
