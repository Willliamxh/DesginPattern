package _1designPatternPrinciple._1singleResponsibility.negative.origin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author Willam_xh
 * @create 2021-06-01 21:19
 */

//这次统计句子

public class negative2 {
    public static void main(String[] args) throws IOException {
        //reader 默认查询的码表是与操作系统一致的码表 我们的操作系统是中文的，所以 Reader就会使用GBK码表
        //GBK码表 一个汉字占用2个字节 且汉字的两个字节 都是1开头的 1开头就是一次读两个
        //记事本读取到数字45489--->查gbk--->找到北--->然后去找Unicode---->21271
        Reader in = new FileReader("F:/WorkLearning/DesignPatternLearning/codeSrc/1_principle/src/_1SingleResponsibility/otherExp/1.txt");
        BufferedReader br =new BufferedReader(in);

        String line=null;

        StringBuilder stringBuilder = new StringBuilder();
        while((line= br.readLine())!=null)
        {
//            System.out.println(line);
            stringBuilder.append(line);
            stringBuilder.append(' ');//把换行编程空格
        }
        System.out.println(stringBuilder);
        String[] s = stringBuilder.toString().split("[.!?。]+");
        for (String s1 : s) {

            System.out.println("!!!"+s1+"!!!");
        }
        System.out.println(s.length);


        br.close();
        in.close();




////        int n=in.read();//这边是unicode中的数字
////        System.out.println((char) n);
//        int n=0;
//        int count=0;
//        while ((n=in.read())!=-1)//我这边用的字符流 所以能直接查到  如果是字节流 一个汉字是两个字节
//        {
//            count++;
//        }
//        System.out.println(count);
//
//        in.close();




//        //编码  字符  码表 数字
//        String s="精";
//        byte[] gbks = s.getBytes("gbk");
//        System.out.println(Arrays.toString(gbks));
//
//        //解码 数字 码表 字符
//        byte[] gbks2 = s.getBytes("unicode");//java 用的是Unicode码表 每个字符都是两个字节
//        //开头永远是 -2 和 -1
//        System.out.println(Arrays.toString(gbks2));



//        InputStream in =new FileInputStream("F:/WorkLearning/DesignPatternLearning/codeSrc/1_principle/src/_1SingleResponsibility/otherExp/1.txt")
//
//        int n=in.read();
//        System.out.println(n);//一个字节最大是 127
//
//        in.close();


    }
}
