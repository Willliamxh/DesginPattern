package _2designPattern._3decorator.positive4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.ref.SoftReference;

/**
 * 至此，我们已经学完了"装饰器模式"
 *其实，我们以前学习的jdk中的liu
 */

class MyBufferedReader extends Reader{
    private Reader in;

    public MyBufferedReader(Reader fileReader) {
        in=fileReader;
    }

    public String ReadLine() throws IOException {
        StringBuilder sb=new StringBuilder();
        while (true){
            int n=in.read();

            if(n=='\r'){
                continue;
            }
            if(n=='\n'||n==-1){
                break;
            }

            sb.append((char)n);
        }

        if(sb.toString().length()==0){
            return null;
        }else {
            return sb.toString();
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        //不实现 空方法体

        return 0;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}

public class AppTest2 {
    public static void main(String[] args) throws IOException {
        Reader fileReader = new FileReader("/Users/program/designPattern/code/src/main/java/_2designPattern/_3decorator/positive4/1.txt");
        MyBufferedReader myBufferedReader = new MyBufferedReader(fileReader);

        String line=myBufferedReader.ReadLine();
        System.out.println(line);

        String line2=myBufferedReader.ReadLine();
        System.out.println(line2);



    }
}
