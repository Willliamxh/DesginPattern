package _1designPatternPrinciple._1singleResponsibility.positive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author Willam_xh
 * @create 2021-06-01 21:20
 */
public class positive {
    public static StringBuilder loadFile(String fileLocation) throws IOException {

        //读取文件的内容
        Reader in = new FileReader("src/main/java/designPatternPrinciple/singleResponsibility/1.txt");
        BufferedReader bufferedReader = new BufferedReader(in);

        String line = null;
        StringBuilder sb = new StringBuilder("");

        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
            sb.append(" ");
        }

        bufferedReader.close();
        return sb;
    }

    public static String[] getWords(String regex, StringBuilder sb){
        //对内容进行分割
        return  sb.toString().split(regex);
    }

    public static void main(String[] args) throws IOException {

        //读取文件的内容
        StringBuilder sb = loadFile("E:\\1.txt");

        //对内容进行分割
        String[] words = getWords("[^a-zA-Z]+", sb);

        System.out.println(words.length);
    }
}

//代码的重用性提高了
//代码可读性提高了 此时的代码 就像一个大纲一样
