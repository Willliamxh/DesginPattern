package _1designPatternPrinciple._1singleResponsibility.negative;


//需求3：统计一个文本文件中有多少个单词
//超级多的空格！！

import java.io.*;

public class AppTest3 {
    public static void main(String[] args) throws IOException {
        //统计一个文本文件中有多少个字符
        //Reader 默认查询的码表是与操作系统一致的码表，我们的操作系统是中文的，所以Reader就会使用GBK码表
        //GBK一个汉字占2个字节 且每个汉字的两个字节 都是以1 开头的  1开头就一次读俩
        //读到记事本中到数字 45489----》gbk-------》北 ------》unicode------》21271
        //读取文本中的字符码值（数字） --> 使用gbk解码 --> 汉字或字符 --> 使用unicode码表找到该字符对应的码值

        Reader in =new FileReader("src/main/java/designPatternPrinciple/singleResponsibility/3.txt");

        File file;
        BufferedReader br=new BufferedReader(in);

        String line=null;
        StringBuilder sb=new StringBuilder("");

        while ((line=br.readLine())!=null){
            // System.out.println(line+"!!!");
            sb.append(line);
            sb.append(" ");
        }
        // System.out.println(sb);
        String words[]=sb.toString().split(" +");//正则表达式
        for (int i = 0; i < words.length; i++) {
            System.out.println(i);
            System.out.println(words[i]);
        }

        br.close();

    }
}
