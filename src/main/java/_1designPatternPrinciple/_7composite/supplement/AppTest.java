package _1designPatternPrinciple._7composite.supplement;

import java.util.Stack;

/**
 * @author Willam_xh
 * @create 2021-06-05 15:56
 */
public class AppTest {
    public static void main(String[] args) {
        Stack<String> s=new Stack<>();
        s.add("啤酒");
        s.add("白酒");
        s.add("红酒");
        s.add("黄酒");


        s.remove("白酒");//这就不单纯是一个堆栈了
//        其实stack是个很不好 因为直接继承了vector 有了很多没用的 负面功能


        System.out.println(s);


    }
}
