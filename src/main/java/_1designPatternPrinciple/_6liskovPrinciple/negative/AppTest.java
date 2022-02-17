package _1designPatternPrinciple._6liskovPrinciple.negative;

/**
 * @author Willam_xh
 * @create 2021-06-05 9:15
 */
/*
* 方法重写：子类和父类中，出现了返回类型相同、方法名相同、方法参数相同的方法时，构成方法重写
*
* 方法重写的两个限制
* 1.子类重写父类的方法时，子类方法的访问修饰符不能比父类更严格，更严格了就是作死
* 2.子类重写父类的方法时，子类方法的不能抛出更多的异常
*
* 为什么要有以上这两个限制？
* 为了保证在子类对象替换父类对象后，语法不会报错
* 从这 语法就给你dang住了
*
* */

class Father{
    public void f1(){};
}


class Son extends Father{
    public void f1() //throws Exception
    {};
}


public class AppTest {
    public static void main(String[] args) {
        Father father = new Son();
        father.f1();
        //本来是父类对象的时候 不抛异常 然后 如果(实际上编译不给过)我抛了异常 这就得try catch
//        此时此刻 替换掉了以后 程序发送了改变  这就违背了里氏替换原则
    }
}
