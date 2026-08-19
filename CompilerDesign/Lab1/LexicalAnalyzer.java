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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static class Identifier<A> {
        String name, type;
        A value;

        Identifier(String a, String b, A v) {
            name = a;
            type = b;
            value = v;
        }

        @Override
        public String toString() {
            return "id name = " + name + ", type= " + type + ", value = " + value;
        }
    }

    private String inputSourceCode, outputSourceCode;
    ArrayList<String> keywordList = new ArrayList<>();
    ArrayList<String> usedKeywordList = new ArrayList<>();
    ArrayList<String> builtinFunctionList = new ArrayList<>();
    ArrayList<String> usedBuiltinFunctionList = new ArrayList<>();
    ArrayList<Identifier> symbolTable = new ArrayList<>();

    public LexicalAnalyzer(String s) {
        setInputSourceCode(s);
        setOutputSourceCode(s);
        prepare();
        process();
    }

    private void prepare() {
        keywordList.add("int");
        keywordList.add("void");
        keywordList.add("float");
        keywordList.add("double");
        builtinFunctionList.add("main");
        builtinFunctionList.add("printf");
        builtinFunctionList.add("scanf");
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

    public void printKeywords() {
        for (String c : keywordList) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public void printUsedKeywords() {
        for (String c : usedKeywordList) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public void printBuiltinFunctions() {
        for (String c : builtinFunctionList) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public void printUsedFunctions() {
        for (String c : usedBuiltinFunctionList) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public String getKeywords() {
        StringBuilder s = new StringBuilder();
        for (String c : keywordList) {
            s.append(c).append(" ");
        }
        return s.toString();
    }

    public String getUsedKeywords() {
        StringBuilder s = new StringBuilder();
        for (String c : usedKeywordList) {
            s.append(c).append(" ");
        }
        return s.toString();
    }

    public String getBuiltinFunctions() {
        StringBuilder s = new StringBuilder();
        for (String c : builtinFunctionList) {
            s.append(c).append(" ");
        }
        return s.toString();
    }

    public String getUsedFunctions() {
        StringBuilder s = new StringBuilder();
        for (String c : usedBuiltinFunctionList) {
            s.append(c).append(" ");
        }
        return s.toString();
    }


    private void process() {
        removeComment();
        cleanWhitespace();
        identify();
    }

    private boolean checkKeyword(String s) {
        for (String keyword : keywordList) {
            if (keyword.equals(s)) return true;
        }
        return false;
    }

    private boolean checkIdentifierValidity(String s) {
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

    private void identify() {
        ArrayList<String> list = new ArrayList<>();
        String s = "", regex = "\\b\\w+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(outputSourceCode);

        while (matcher.find()) {
            s = matcher.group();
//            System.out.println(s);
            if (s.isEmpty()){
                System.out.println("ERROR LA:");
                continue;
            }
            if (!checkIdentifierValidity(s)) {
                System.out.println("ERROR LA: INVALID IDENTIFIER NAME. (identifier must start with a character or an underscore.)");
                continue;
            }
            if (keywordList.contains(s)) {
                usedKeywordList.add(s);
            } else if (builtinFunctionList.contains(s)) {
                usedBuiltinFunctionList.add(s);
            }
        }
    }

    private void removeComment() {
        outputSourceCode = outputSourceCode.replaceAll("//.*\n", ""); // single line comment
        outputSourceCode = outputSourceCode.replaceAll("(?s)/\\*.*\\*/", ""); // multi line comment
    }

    private void cleanWhitespace() {
        outputSourceCode = outputSourceCode.replace("\t", " "); // tab
        outputSourceCode = outputSourceCode.replaceAll(" [ ]*", " "); // multiple whitespace
        outputSourceCode = outputSourceCode.replaceAll("\n[ ]*\n", "\n"); // blank line
    }

    public static void main(String[] args) throws FileNotFoundException {
        String input = rwFile.readFile("CompilerDesign/Lab1/input.c");
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(input);
//        System.out.println(lexicalAnalyzer.getOutputSourceCode());
        rwFile.writeFile("CompilerDesign/Lab1/output.c", lexicalAnalyzer.getOutputSourceCode());

//        System.out.println(lexicalAnalyzer.checkIdentifierValidity("?name"));
        lexicalAnalyzer.printUsedKeywords();
        lexicalAnalyzer.printUsedFunctions();
    }
}
