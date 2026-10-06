/*
First and Follow Detector
Reads CFG from a text file and prints First and Follow table.
'#' or 'e' or 'epsilon' or 'ε' can denote epsilon.
*/

package CompilerDesign.Lab3;

import java.io.*;
import java.util.*;

public class FirstAndFollow {

    static class rwFile {
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

    public static final String EPSILON = "#";
    public static final String DOLLAR = "$";

    private String inputGrammarText;
    private String startSymbol;
    private final List<String> nonTerminals = new ArrayList<>();
    private final Set<String> nonTerminalSet = new LinkedHashSet<>();
    private final Set<String> terminals = new LinkedHashSet<>();
    // Non-terminal -> list of production RHS alternatives (each alternative is a list of token symbols)
    private final Map<String, List<List<String>>> productions = new LinkedHashMap<>();

    private final Map<String, Set<String>> firstSets = new LinkedHashMap<>();
    private final Map<String, Set<String>> followSets = new LinkedHashMap<>();

    public FirstAndFollow(String grammarText) {
        this.inputGrammarText = grammarText;
        prepare();
        computeFirst();
        computeFollow();
    }

    public String getInputGrammarText() {
        return inputGrammarText;
    }

    public void setInputGrammarText(String inputGrammarText) {
        this.inputGrammarText = inputGrammarText;
    }

    public Map<String, Set<String>> getFirstSets() {
        return firstSets;
    }

    public Map<String, Set<String>> getFollowSets() {
        return followSets;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    private void prepare() {
        String[] lines = inputGrammarText.split("\\r?\\n");

        // Pass 1: Identify all LHS non-terminals and establish start symbol
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("//")) {
                continue;
            }

            String[] sides = line.split("->|=");
            if (sides.length < 2) {
                continue;
            }

            String lhs = sides[0].trim();
            if (!nonTerminalSet.contains(lhs)) {
                nonTerminalSet.add(lhs);
                nonTerminals.add(lhs);
                productions.put(lhs, new ArrayList<>());
                firstSets.put(lhs, new LinkedHashSet<>());
                followSets.put(lhs, new LinkedHashSet<>());
            }

            if (startSymbol == null) {
                startSymbol = lhs;
            }
        }

        // Pass 2: Parse productions and tokenize RHS alternatives
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("//")) {
                continue;
            }

            String[] sides = line.split("->|=");
            if (sides.length < 2) {
                continue;
            }

            String lhs = sides[0].trim();
            String rhsStr = sides[1].trim();
            String[] alternatives = rhsStr.split("\\|");
            for (String alt : alternatives) {
                List<String> altTokens = tokenize(alt.trim());
                if (altTokens.isEmpty()) {
                    altTokens.add(EPSILON);
                }
                productions.get(lhs).add(altTokens);
            }
        }

        // Pass 3: Identify terminals
        for (Map.Entry<String, List<List<String>>> entry : productions.entrySet()) {
            for (List<String> alt : entry.getValue()) {
                for (String sym : alt) {
                    if (!sym.equals(EPSILON) && !nonTerminalSet.contains(sym)) {
                        terminals.add(sym);
                    }
                }
            }
        }
    }

    private List<String> tokenize(String str) {
        List<String> tokens = new ArrayList<>();
        if (str.isEmpty()) {
            return tokens;
        }

        // Check if string contains spaces separating tokens
        if (str.contains(" ")) {
            String[] rawTokens = str.split("\\s+");
            for (String token : rawTokens) {
                token = token.trim();
                if (!token.isEmpty()) {
                    tokens.add(normalizeSymbol(token));
                }
            }
        } else {
            // Tokenize contiguous string: match longest known non-terminal or primed symbol/identifier/character
            int i = 0;
            while (i < str.length()) {
                if (Character.isWhitespace(str.charAt(i))) {
                    i++;
                    continue;
                }

                // Check if remaining prefix matches any known non-terminal
                String matchedNT = null;
                for (String nt : nonTerminals) {
                    if (str.startsWith(nt, i)) {
                        if (matchedNT == null || nt.length() > matchedNT.length()) {
                            matchedNT = nt;
                        }
                    }
                }

                if (matchedNT != null) {
                    tokens.add(normalizeSymbol(matchedNT));
                    i += matchedNT.length();
                } else if (Character.isLetter(str.charAt(i))) {
                    // Collect alphanumeric identifier / word token (e.g. "id", "num")
                    int start = i;
                    while (i < str.length() && (Character.isLetterOrDigit(str.charAt(i)) || str.charAt(i) == '\'')) {
                        i++;
                    }
                    tokens.add(normalizeSymbol(str.substring(start, i)));
                } else {
                    tokens.add(normalizeSymbol(String.valueOf(str.charAt(i))));
                    i++;
                }
            }
        }
        return tokens;
    }

    private String normalizeSymbol(String s) {
        if (s.equals("e") || s.equals("epsilon") || s.equals("EPSILON") || s.equals("ε") || s.equals("#")) {
            return EPSILON;
        }
        return s;
    }

    private void computeFirst() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String nt : nonTerminals) {
                Set<String> ntFirst = firstSets.get(nt);
                int beforeSize = ntFirst.size();

                List<List<String>> rhsList = productions.get(nt);
                for (List<String> rhs : rhsList) {
                    Set<String> rhsFirst = computeFirstOfSequence(rhs);
                    ntFirst.addAll(rhsFirst);
                }

                if (ntFirst.size() > beforeSize) {
                    changed = true;
                }
            }
        }
    }

    public Set<String> computeFirstOfSequence(List<String> symbols) {
        Set<String> result = new LinkedHashSet<>();
        if (symbols == null || symbols.isEmpty()) {
            result.add(EPSILON);
            return result;
        }

        boolean allDeriveEpsilon = true;
        for (String sym : symbols) {
            if (sym.equals(EPSILON)) {
                result.add(EPSILON);
                break;
            } else if (!nonTerminalSet.contains(sym)) {
                // Terminal symbol
                result.add(sym);
                allDeriveEpsilon = false;
                break;
            } else {
                // Non-terminal symbol
                Set<String> symFirst = firstSets.get(sym);
                for (String f : symFirst) {
                    if (!f.equals(EPSILON)) {
                        result.add(f);
                    }
                }
                if (!symFirst.contains(EPSILON)) {
                    allDeriveEpsilon = false;
                    break;
                }
            }
        }

        if (allDeriveEpsilon) {
            result.add(EPSILON);
        }

        return result;
    }

    private void computeFollow() {
        if (startSymbol != null) {
            followSets.get(startSymbol).add(DOLLAR);
        }

        boolean changed = true;
        while (changed) {
            changed = false;
            for (String lhs : nonTerminals) {
                List<List<String>> rhsList = productions.get(lhs);

                for (List<String> rhs : rhsList) {
                    for (int i = 0; i < rhs.size(); i++) {
                        String B = rhs.get(i);

                        if (nonTerminalSet.contains(B)) {
                            Set<String> bFollow = followSets.get(B);
                            int beforeSize = bFollow.size();

                            // Subsequence beta after B
                            List<String> beta = rhs.subList(i + 1, rhs.size());
                            Set<String> firstBeta = computeFirstOfSequence(beta);

                            for (String sym : firstBeta) {
                                if (!sym.equals(EPSILON)) {
                                    bFollow.add(sym);
                                }
                            }

                            if (firstBeta.contains(EPSILON)) {
                                bFollow.addAll(followSets.get(lhs));
                            }

                            if (bFollow.size() > beforeSize) {
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private String formatSet(Set<String> set) {
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        StringBuilder sb = new StringBuilder("{ ");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" }");
        return sb.toString();
    }

    public void printTable() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-18s | %-28s | %-28s\n", "Non-Terminal", "FIRST", "FOLLOW");
        System.out.println("--------------------------------------------------------------------------------");
        for (String nt : nonTerminals) {
            String firstStr = formatSet(firstSets.get(nt));
            String followStr = formatSet(followSets.get(nt));
            System.out.printf("%-18s | %-28s | %-28s\n", nt, firstStr, followStr);
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void main(String[] args) throws FileNotFoundException {
        String inputPath = "CompilerDesign/Lab3/input.txt";
        if (args.length > 0) {
            inputPath = args[0];
        }

        String input = rwFile.readFile(inputPath);
        System.out.println("Input Grammar:");
        System.out.println(input);

        FirstAndFollow detector = new FirstAndFollow(input);
        System.out.println("First and Follow Table:");
        detector.printTable();
    }
}
