package _2designPattern._2prototype.positive2;
import java.lang.ref.WeakReference;
import java.util.Date;

/**
 * @author Willam_xh
 * @create 2021-06-06 10:18
 */

/*
    针对于A包中的问题，解决办法如下：

*
    使用"原型模式"来解决这个问题：
    1.必须让目标类实现Cloneable接口，该接口中没有任何抽象方法。这样的接口仅仅是一个"标记接口"，作用是
    告诉jvm，任何实现了该Cloneable接口的类的对象，都可以被克隆。
    2。必须充血java。lang。Object的clone方法，一定要把该方法改成public

*/

class WeekReport implements Cloneable{ //表示一种能力 里面没有方法
    private int id;
    private String emp;
    private String summary;
    private String plan;
    private String suggestion;
    private Date time;

    public WeekReport() {
        System.out.println("aaa");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WeekReport{" +
                "id=" + id +
                ", emp='" + emp + '\'' +
                ", summary='" + summary + '\'' +
                ", plan='" + plan + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", time=" + time +
                '}';
    }

    //深拷贝
    @Override
    public Object clone() throws CloneNotSupportedException {
        WeekReport clone = (WeekReport) super.clone();
        Date cloneDate=(Date) clone.getTime().clone();
        clone.setTime(cloneDate);
        return clone;
    }
}


public class AppTest {
    public static void main(String[] args) throws CloneNotSupportedException {
//        第一周周报
        WeekReport weekReport = new WeekReport();
        weekReport.setEmp("张珊珊");
        weekReport.setSummary("讲解完了七大原则");
        weekReport.setPlan("讲解完了设计模式");
        weekReport.setSuggestion("无");
        weekReport.setTime(new Date());

        //入库
        // System.out.println(weekReport);

//        第二周周报 问题是景观第二周周报大部分内容和第一周周报内容一致，但是仍然要重复设置
//        等同于在表单中重复填写和上一周一样的内容

        // 思考1：克隆方法，会不会引起构造方法的调用（不会）
        //思考2：如何实现clone对象的效果呢？  clone方法是直接复制内存中的二进制
        //思考3：既然 克隆方法没有引起构造器的调用，那么克隆出的对象和原先的对象地址是否一致
        //不一致 地址不一样 最终是不同空间中的对象 两个都在堆里



        WeekReport weekReport2 = (WeekReport) weekReport.clone();
        // weekReport2.setSummary("讲解完HTMl");//只需要改变化的就行了
        // weekReport2.setPlan("讲解完了CSS");
        // System.out.println(weekReport2);


        System.out.println(weekReport.hashCode());
        System.out.println(weekReport2.hashCode());

        //现在用的是深拷贝了
        weekReport2.setSummary("aaaabbbccc");
        weekReport2.getTime().setTime(0);//这样能够直接去修改对应的对象值

        System.out.println(weekReport);
        System.out.println(weekReport2);

        //目前以及达到了深拷贝的目的，也就是修改副本对象的任何属性，都对原有副本没有任何影响

        //问题是：如果对象深度比较深，则深拷贝的实现起来很麻烦！！




    }
}
