package _2designPattern._4template.positive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 测试ArrayList和linkedList的增加效率 查询效率
 *
 * 使用模版方法模式来解决a包中的问题
 */

abstract class Template{
    public void template(){
        System.out.println("开始");
        long start =System.currentTimeMillis();
        code();
        long end =System.currentTimeMillis();
        System.out.println("结束："+(end-start));
    }

    public abstract void code();
}
//=====================================================

class A extends Template{

    @Override
    public void code() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(0,1);
        }
    }
}

class TestArrayList extends Template{
    @Override
    public void code() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(0,1);
        }
    }
}


public class AppTest {
    public static void main(String[] args) {
        Template a = new A();
        a.template();

    }

}

/**
 * 此时，每次要测试的代码发生变化时，都势必要修改原有的代码，
 * 如果修改之后，又需要测试之前的代码，那么又要把之前的代码写回来!
 */