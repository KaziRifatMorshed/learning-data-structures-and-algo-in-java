/*
input txt file, code cleaning(space,whitespace,new line,double newline,tab,comment),
identify newline,double newline,identifier,number,keyword)
*/

package CompilerDesign.Lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LexicalAnalyzer {

    static class rwFile {
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

    private String inputSourceCode, outputSourceCode;

    public LexicalAnalyzer(String s) {
        setInputSourceCode(s);
        setOutputSourceCode(s);
        process();
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

    private void process() {
        removeComment();
        cleanWhitespace();
    }

    private void removeComment() {
        outputSourceCode = outputSourceCode.replaceAll("//.*", ""); // single line comment
        outputSourceCode = outputSourceCode.replaceAll("(?s)/\\*.*?\\*/", ""); // multi line comment
    }

    private void cleanWhitespace() {
        outputSourceCode = outputSourceCode.replace("\t", " "); // tab
        outputSourceCode = outputSourceCode.replaceAll(" [ ]*?", " "); // multiple whitespace
        outputSourceCode = outputSourceCode.replaceAll("\n[ ]*?\n", "\n"); // blank line
    }


    private boolean checkKeyword(String s){
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.add("int");
        keywordList.add("void");

        for (String keyword: keywordList){
            if (keyword.equals(s)) return true;
        }
        return false;
    }

    private boolean checkIdentifierValidity(String s){
        char c = s.charAt(0);
        if (c == '_' // single underscore
                || ('a' <= c && c <= 'z')
                || ('A' <= c && c <= 'Z')
        ) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String input = rwFile.readFile("CompilerDesign/Lab1/input.c");
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(input);
//        System.out.println(lexicalAnalyzer.getOutputSourceCode());
        rwFile.writeFile("CompilerDesign/Lab1/output.c", lexicalAnalyzer.getOutputSourceCode());

//        System.out.println(lexicalAnalyzer.checkIdentifierValidity("?name"));
    }
}
