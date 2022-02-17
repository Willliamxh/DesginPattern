package _2designPattern._2prototype.negative;

import java.util.Date;

/**
 * @author Willam_xh
 * @create 2021-06-06 10:18
 */

class WeekReport{
    private int id;
    private String emp;
    private String summary;
    private String plan;
    private String suggestion;
    private Date time;

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
}


public class AppTest {
    public static void main(String[] args) {
//        第一周周报
        WeekReport weekReport = new WeekReport();
        weekReport.setEmp("张珊珊");
        weekReport.setSummary("讲解完了七大原则");
        weekReport.setPlan("讲解完了设计模式");
        weekReport.setSuggestion("无");
        weekReport.setTime(new Date());

        //入库
        System.out.println(weekReport);

//        第二周周报 问题是景观第二周周报大部分内容和第一周周报内容一致，但是仍然要重复设置
//        等同于在表单中重复填写和上一周一样的内容
        WeekReport weekReport2 = new WeekReport();
        weekReport2.setEmp("张珊珊");
        weekReport2.setSummary("讲解完HTMl");
        weekReport2.setPlan("讲解完了CSS");
        weekReport2.setSuggestion("无");
        weekReport2.setTime(new Date());

        //我们希望是，不变的，就不填写了，只设置变化的部分


    }
}
