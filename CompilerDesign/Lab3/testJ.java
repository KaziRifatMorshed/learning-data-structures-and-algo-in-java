package CompilerDesign.Lab3;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class testJ {

    static class rwFile {
        static String readFile(String inputFilePath) throws FileNotFoundException {
            StringBuilder s = new StringBuilder();
            try (Scanner scanner = new Scanner(new File(inputFilePath))) {
                while (scanner.hasNextLine()) {
                    s.append(scanner.nextLine());
                    s.append(System.lineSeparator());
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
    private final Set<String> nonTerminals = new LinkedHashSet<>();
    private final Set<String> terminals = new LinkedHashSet<>();
    private final Map<String, List<List<String>>> productions = new LinkedHashMap<>();

    private final Map<String, Set<String>> firstSets = new LinkedHashMap<>();
    private final Map<String, Set<String>> followSets = new LinkedHashMap<>();

    public testJ(String grammarText) {
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

    public Set<String> getNonTerminals() {
        return nonTerminals;
    }

    private void prepare() {
        String[] lines = inputGrammarText.split("\\r?\\n");

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
            if (nonTerminals.add(lhs)) {
                productions.put(lhs, new ArrayList<>());
                firstSets.put(lhs, new LinkedHashSet<>());
                followSets.put(lhs, new LinkedHashSet<>());
            }

            if (startSymbol == null) {
                startSymbol = lhs;
            }
        }

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
                if (altTokens.isEmpty()) { // in case " A -> " but, emon amra usually kori na
                    altTokens.add(EPSILON);
                }
                productions.get(lhs).add(altTokens);
            }
        }

        for (Map.Entry<String, List<List<String>>> entry : productions.entrySet()) {
            for (List<String> alt : entry.getValue()) {
                for (String sym : alt) {
                    if (!sym.equals(EPSILON) && !nonTerminals.contains(sym)) {
                        terminals.add(sym);
                    }
                }
            }
        }
    }


    private List<String> tokenize(String str) {
        if (str.trim().isEmpty()) return Collections.emptyList();

        return Arrays.stream(str.trim().split("\\s+"))
                .map(this::normalizeSymbol)
                .collect(Collectors.toList());
    }

    private List<String> tokenizeManual(String str) {
        List<String> tokens = new ArrayList<>();
        if (str.isEmpty()) {
            return tokens;
        }

        if (str.contains(" ")) {
            String[] rawTokens = str.split("\\s+");
            for (String token : rawTokens) {
                token = token.trim();
                if (!token.isEmpty()) {
                    tokens.add(normalizeSymbol(token));
                }
            }
        } else {
            int i = 0;
            while (i < str.length()) {
                if (Character.isWhitespace(str.charAt(i))) {
                    i++;
                    continue;
                }

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
                int beforeSize = firstSets.get(nt).size();
                computeFirst(nt);
                if (beforeSize < firstSets.get(nt).size()) {
                    changed = true;
                }
            }
        }
    }

    public Set<String> computeFirst(String symbol) {
        return computeFirst(symbol, new HashSet<>());
    }


    private Set<String> computeFirst(String symbol, Set<String> visiting) {
        if (symbol.equals(EPSILON)) {
            Set<String> res = new LinkedHashSet<>();
            res.add(EPSILON);
            return res;
        }

        if (!nonTerminals.contains(symbol)) {
            Set<String> res = new LinkedHashSet<>();
            res.add(symbol);
            return res;
        }

        if (visiting.contains(symbol)) {
            return firstSets.getOrDefault(symbol, Collections.emptySet());
        }

        visiting.add(symbol);
        Set<String> result = firstSets.get(symbol);

        List<List<String>> rhsList = productions.get(symbol);
        if (rhsList != null) {
            for (List<String> rhs : rhsList) {
                Set<String> rhsFirst = computeFirstOfSequence(rhs, visiting);
                result.addAll(rhsFirst);
            }
        }

        visiting.remove(symbol);
        return result;
    }


    public Set<String> computeFirstOfSequence(List<String> symbols) {
        return computeFirstOfSequence(symbols, new HashSet<>());
    }

    private Set<String> computeFirstOfSequence(List<String> symbols, Set<String> visiting) {
        Set<String> result = new LinkedHashSet<>();

        if (symbols == null || symbols.isEmpty()) {
            result.add(EPSILON);
            return result;
        }

        String firstSymbol = symbols.get(0);
        Set<String> firstOfFirstSymbol = computeFirst(firstSymbol, visiting);

        for (String sym : firstOfFirstSymbol) {
            if (!sym.equals(EPSILON)) {
                result.add(sym);
            }
        }

        if (firstOfFirstSymbol.contains(EPSILON)) {
            List<String> rest = symbols.subList(1, symbols.size());
            Set<String> firstOfRest = computeFirstOfSequence(rest, visiting);
            result.addAll(firstOfRest);
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
            for (String nt : nonTerminals) {
                int beforeSize = followSets.get(nt).size();
                computeFollow(nt);
                if (followSets.get(nt).size() > beforeSize) {
                    changed = true;
                }
            }
        }
    }

    public Set<String> computeFollow(String symbol) {
        return computeFollow(symbol, new HashSet<>());
    }

   private Set<String> computeFollow(String symbol, Set<String> visiting) {
        Set<String> follow = followSets.get(symbol);
        if (follow == null) {
            return Collections.emptySet();
        }

        if (symbol.equals(startSymbol)) {
            follow.add(DOLLAR);
        }

        if (visiting.contains(symbol)) {
            return follow;
        }

        visiting.add(symbol);

        for (Map.Entry<String, List<List<String>>> entry : productions.entrySet()) {
            String lhs = entry.getKey();
            List<List<String>> rhsList = entry.getValue();

            for (List<String> rhs : rhsList) {
                int i = rhs.indexOf(symbol);
                while (i != -1) {
                    List<String> beta = rhs.subList(i + 1, rhs.size());

                    Set<String> firstBeta = computeFirstOfSequence(beta);

                    for (String bSym : firstBeta) {
                        if (!bSym.equals(EPSILON)) {
                            follow.add(bSym);
                        }
                    }

                    if (firstBeta.contains(EPSILON)) {
                        if (!lhs.equals(symbol)) {
                            Set<String> lhsFollow = computeFollow(lhs, visiting);
                            follow.addAll(lhsFollow);
                        }
                    }

                    int next = rhs.subList(i + 1, rhs.size()).indexOf(symbol);
                    i = (next != -1) ? (i + 1 + next) : -1;
                }
            }
        }

        visiting.remove(symbol);
        return follow;
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
        System.out.printf("%-18s | %-28s | %-28s\n", "Non-Terminal", "FIRST", "FOLLOW");
        for (String nt : nonTerminals) {
            String firstStr = formatSet(firstSets.get(nt));
            String followStr = formatSet(followSets.get(nt));
            System.out.printf("%-18s | %-28s | %-28s\n", nt, firstStr, followStr);
        }
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
