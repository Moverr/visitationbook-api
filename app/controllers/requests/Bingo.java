package controllers.requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class  Bingo {

    public static void main(String[] args) {
        Bingo cl = new Bingo();
        int[] result = cl
                .countLOC("D:\\visitationbook\\visitationbook\\app\\controllers\\HomeController.scala");
        System.out.println("The program CountLOC has " + result[0]
                + " lines of code and " + result[1] + " total lines");
    }

    public int[] countLOC(String txt) {
        int lineCount = 0;
        int codeLineCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(txt));
            String line = reader.readLine();
            while (line != null) {
                lineCount++;
                if (!isCommentLine(line)) {
                    codeLineCount++;
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new int[] { codeLineCount, lineCount };
    }

    public boolean isCommentLine(String line) {
        line = line.trim();
        return line.isEmpty() || line.startsWith("//") || line.startsWith("/*")
                || line.startsWith("*") || line.startsWith("*/");
    }

}