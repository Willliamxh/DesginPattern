package _2designPattern._6proxy.supplement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Person{
    public void eat(){
        System.out.println("我吃");
    }

    public void eat(String a){
        System.out.println("我吃"+a);
    }
}

class Cat{
    public void eat(){
        System.out.println("猫吃饭");
    }
}

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        //    利用反射机制调用类的方法

        //    1，获取类的字节码,字节码是根据源代码生成的，所以源代码中的信息，字节码也有。
        Class<?> aClass = Class.forName("_2designPattern._6proxy.supplement.Person");
        System.out.println(aClass);

        //   2。利用反射机制创建一个对象,以下API就是利用反射机制，调用类的无参构造器来实例化对象的。
        Object o = aClass.newInstance();
        System.out.println(o);

        //   3。反射出字节码中的某个方法
        Method eat = aClass.getDeclaredMethod("eat");

        Method eat2 = aClass.getDeclaredMethod("eat",String.class);

        //    4.利用反射机制调用方法
        //把eat所代表的方法 当作o对象的方法来调用。
        eat.invoke(o);

        eat2.invoke(o,"拉面");

        Cat cat=new Cat();
        eat.invoke(cat);
    //object is not an instance of declaring class
    }
}
