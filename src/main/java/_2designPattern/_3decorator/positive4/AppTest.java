package _2designPattern._3decorator.positive4;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * 至此，我们已经学习完了“装饰器模式”。
 * 其实，我们以前学习的jdk中的流，就是-种装饰模式的体现。
 *
 */

public class AppTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        //现在只能读字节流或者字节数组
        //查看类层级关系 control+h
        //查看继承视图 command+shit+u
        //包裹 option + command +t
        //可以去看看那个装饰器就叫什么什么filter
        InputStream in=new FileInputStream("src/main/java/_2designPattern/_3decorator/positive4/1.txt");
        //那我现在要加缓冲区
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        //我还想加字符流
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream,"gbk");


        TimeUnit.SECONDS.sleep(1);


        inputStreamReader.close();
    }
}
