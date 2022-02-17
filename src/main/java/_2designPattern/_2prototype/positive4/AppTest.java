package _2designPattern._2prototype.positive4;
import java.io.*;
import java.util.Date;

/**
 * @author Willam_xh
 * @create 2021-06-06 10:18
 */

/*

*/

class WeekReport implements Cloneable, Serializable { //表示一种能力 里面没有方法
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
        try {

            // OutputStream outputStream=new FileOutputStream("src/main/java/_2designPattern/_2prototype/positive3/1.txt");
            //在内存空间进行序列化
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(outputStream);
            oos.writeObject(this);//序列化时 对象所有属性的成绩关系会被序列化自动处理
            oos.close();


            //从内存中取出数据
            byte[] bytes = outputStream.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream ois=new ObjectInputStream(in);
            Object clone=ois.readObject();
            ois.close();
            //内容一样
            System.out.println(clone);
            System.out.println(this);
            //这样读出来的对象和克隆的对象就不是一个对象了
            System.out.println("this==clone"+this==clone);

            return clone;
        } catch (Exception e) {
            throw new RuntimeException();
        }
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

        //问题：这样的话，存盘的地址会受到win和linux的系统不同影响

        // 解决办法 写到内存中




    }
}
