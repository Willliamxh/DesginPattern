package _2designPattern._6proxy.j;

/**
 *
 * 代理模式:为其他对象提供一种代理以控制对这个对象的访问。
 * 在某些情况下，一个对象不适合或者不能直接引用另-一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用。
 * 不一定非要使用jdk动态代理。大家不要 把jdk动态代理等价于代理模式
 * 代理模式，是一个设计模式，代码是不固定的!我们不适用动态代理,也能写出代理模式的例子! !下午再讲吧! !
 */

public class AppTest {
}
/*
我们学习过:适配器模式，现在也学习了 代理模式，有么有感觉它们很相似? ?
        毕竟它们是不同的设计模式，有什么区别? ?
        1.代理模式中，代理对象和它所包裹的目标对象，必须实现相同的接口;适配器模式中，适配器和它所包裹的对象不用实现相同的接口
        2.代理模式中，代理对象可以控制它所包裹的目标对象的方法是否执行!适配器模式中，适配器总是会调用目标对象的方法!无法控制

 */