package controllers.requests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.*;

public class  Bingo {

    public static void main(String[] args) {
        Bingo cl = new Bingo();
        int[] result = cl
                .countLOC("D:\\visitationbook\\visitationbook\\app\\controllers\\HomeController.scala");
        out.println("The program CountLOC has " + result[0]
                + " lines of code and " + result[1] + " total lines");
    }

    public int[] countLOC(String txt) {
        int lineCount = 0;
        int codeLineCount = 0;
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(txt))) {
                String line = reader.readLine();
                while (line != null) {
                    lineCount++;
                    if (!isCommentLine(line)) {
                        codeLineCount++;
                    }
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            out.println(e);
        }

        return new int[] { codeLineCount, lineCount };
    }

    public boolean isCommentLine(String line) {
        line = line.trim();
        return line.isEmpty() || line.startsWith("//") || line.startsWith("/*")
                || line.startsWith("*") || line.startsWith("*/");
    }

}