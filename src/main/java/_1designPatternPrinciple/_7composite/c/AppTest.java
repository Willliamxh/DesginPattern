package _1designPatternPrinciple._7composite.c;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Willam_xh
 * @create 2021-06-05 10:37
 */

//需求：制作一个集合，要求该集合能记录 曾经加过多少个元素 （不是统计某一时刻集合中有多少元素）
//针对b包的问题，MySet必须依赖于这样一个事实，AddAll必须回调add 但是jdk未来的版本不会保证我们的
//    addAll一定会进行回调
//修改代码如下：我们亲自重写addAll，这次重写addaLL,
// 不再让count累加c.size了，而是保证addAll一定会调用add



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
        boolean bb=false;
        for (Object o : c) {
            if (add(o)) {
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
亲自再重写addAll方法，确保addAll方法一定能调用到add方法，也就能够对 count进行增加操作。

但是，问题还是有的：
缺陷：

1.如果未来，HashSet新增了一个addSome方法进行元素的添加，那就白给了。我们的Myset根本没去重写这个方法。
这样，再新版本中，我们的Myset也继承了AddSome方法，当使用addSome方法添加元素的时候，根本不会去统计元素数量

2.重写了addAll、add这两个方法，如果JDK中其他类的某些方法依赖于HashMap中的这两个方法，
我们没头脑的取修改了这些方法
那么JDK中其他类依赖于HashMap中的这两个方法的某些方法就会有出错、崩溃等风险。

* */
