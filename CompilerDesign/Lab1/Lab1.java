/*
input txt file, code cleaning(space,whitespace,new line,double newline,tab,comment),
identify newline,double newline,identifier,number,keyword)
*/

package CompilerDesign.Lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class rwFile {
//    private String inputFilePath, outputFilePath;

    static String readFile(String inputFilePath) throws FileNotFoundException {
        StringBuilder s = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(inputFilePath))) {
            while (scanner.hasNextLine()) {
                s.append(scanner.nextLine());
                s.append(System.lineSeparator()); // Preserves original line breaks
            }
        }
        return s.toString();
    }

    static void writeFile(String outputFilePath, String content) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(outputFilePath)) {
            writer.print(content);
        }
    }

}

public class Lab1 {
    private String inputSourceCode, outputSourceCode;

    public Lab1(String s) {
        setInputSourceCode(s);
        setOutputSourceCode(s);
        process();
    }

    private void process() {
        removeComment();
        cleanWhitespace();
    }

    private void removeComment() {
        outputSourceCode = outputSourceCode.replaceAll("//.*", "");
        outputSourceCode = outputSourceCode.replaceAll("(?s)/\\*.*?\\*/", "");
    }

    private void cleanWhitespace() {
        outputSourceCode = outputSourceCode.replace("\t", " ");
        outputSourceCode = outputSourceCode.replaceAll(" [ ]+", " ");
        outputSourceCode = outputSourceCode.replaceAll("(?m)[ \t]+$", "");
        outputSourceCode = outputSourceCode.replaceAll("(?m)^[ \t]*\r?\n", "");
    }

    public String getInputSourceCode() {
        return inputSourceCode;
    }

    public void setInputSourceCode(String inputSourceCode) {
        this.inputSourceCode = inputSourceCode;
    }

    public String getOutputSourceCode() {
        return outputSourceCode;
    }

    public void setOutputSourceCode(String outputSourceCode) {
        this.outputSourceCode = outputSourceCode;
    }

    public static void main() throws FileNotFoundException {
        String input = rwFile.readFile("CompilerDesign/Lab1/input.c");
        Lab1 lexicalAnalyzer = new Lab1(input);
        System.out.println(lexicalAnalyzer.getOutputSourceCode());
        rwFile.writeFile("CompilerDesign/Lab1/output.c", lexicalAnalyzer.getOutputSourceCode());
    }
}

class test {
//    public static void main() throws FileNotFoundException {
//        String input = rwFile.readFile("CompilerDesign/Lab1/input.c");
//        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(input);
//        System.out.println(lexicalAnalyzer.getOutputSourceCode());
//        rwFile.writeFile("CompilerDesign/Lab1/output.c", lexicalAnalyzer.getOutputSourceCode());
//    }
}
