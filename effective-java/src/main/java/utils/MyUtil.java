package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MyUtil {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\codeLab\\math-reasoning\\mathematics-cas\\src\\main\\java\\edu\\uestc\\auto\\reasoning\\tool");
        SearchAtName(file);

    }


    public static void SearchAtName(File file) throws IOException {
        if (file.isFile()) {
            if (file.getName().endsWith(".txt") || file.getName().endsWith(".java")) {
                String fileAbsolutePath = file.getAbsolutePath();//reasoning.tools.tool
                autoReplace(fileAbsolutePath, "edu.uestc.auto.reasoning.tool.s", "edu.uestc.auto.reasoning.tools");
            }
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                SearchAtName(f);
            }
        }

    }

    /**
     * 文件修改
     *
     * @param filePath 文件名称
     * @param oldstr   要修改的字符串
     * @param newStr   新的字符串
     */
    private static void autoReplace(String filePath, String oldstr, String newStr) {
        //创建文件
        File file = new File(filePath);
        //记录文件长度
        Long fileLength = file.length();
        //记录读出来的文件的内容
        byte[] fileContext = new byte[fileLength.intValue()];
        FileInputStream in = null;
        PrintWriter out = null;
        try {
            in = new FileInputStream(filePath);
            //读出文件全部内容(内容和文件中的格式一致,含换行)
            in.read(fileContext);
            // 避免出现中文乱码
            String str = new String(fileContext, "utf-8");
            //修改对应字符串内容
            str = str.replace(oldstr, newStr);
            //再把新的内容写入文件
            out = new PrintWriter(filePath);
            out.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}