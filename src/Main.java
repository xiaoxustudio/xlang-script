// Generated from xlang.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.*;

import java.io.*;

import org.antlr.runtime.ANTLRInputStream;

public class Main {
    public static void main(String[] args)  {
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\17835\\Desktop\\编程练习\\FlexAndBison\\a.xlang");
            InputStreamReader  reader = new InputStreamReader(file);
            // 创建缓冲区
            BufferedReader bf = new BufferedReader(reader);
            String str = "";
            //读取缓冲
            String str1;
            while ((str1 = bf.readLine()) != null) {
                str += str1;
            }
            reader.close();

            // 解析
            exprLexer lexer = new exprLexer(CharStreams.fromString(str));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            exprParser parser = new exprParser(tokens);
            parser.setBuildParseTree(true);
            exprParser.ModelContext root = parser.model();
            // 执行
            NameSpace ns = new NameSpace();
            TextVisitor tv = new TextVisitor(ns);
            BlockE res = tv.visit(root);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}