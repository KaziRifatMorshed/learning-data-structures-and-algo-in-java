/*
First and Follow Detector
Reads CFG from a text file and prints First and Follow table.
'#' or 'e' or 'epsilon' or 'ε' can denote epsilon.
*/

package CompilerDesign.Lab3;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    // Non-terminals in order of appearance (LinkedHashSet preserves insertion order & provides O(1) lookups)
    private final Set<String> nonTerminals = new LinkedHashSet<>();
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

    public Set<String> getNonTerminals() {
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
            if (nonTerminals.add(lhs)) {
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
                if (altTokens.isEmpty()) { // in case " A -> " but, emon amra usually kori na
                    altTokens.add(EPSILON);
                }
                productions.get(lhs).add(altTokens);
            }
        }

        // Pass 3: Identify terminals
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
        List<String> tokens = new ArrayList<>();
        if (str == null || str.trim().isEmpty()) return tokens;

        // 1. Sort nonTerminals by descending length so longer names match first (e.g., E' before E)
        String ntPattern = nonTerminals.stream()
                .sorted((a, b) -> Integer.compare(b.length(), a.length()))
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));

        // 2. Build regex: (non-terminals) | (identifiers like 'id', 'num') | (any non-whitespace single char)
        String regex = (ntPattern.isEmpty() ? "" : ntPattern + "|") + "[a-zA-Z][a-zA-Z0-9']*|\\S";
        Matcher matcher = Pattern.compile(regex).matcher(str);

        while (matcher.find()) {
            tokens.add(normalizeSymbol(matcher.group()));
        }
        return tokens;
    }

    private List<String> tokenizeManual(String str) {
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

    /**
     * Driver method that computes the FIRST sets for all non-terminals in the grammar.
     * Uses recursive computation and iterates until all sets stabilize (fixed-point convergence),
     * ensuring that recursive calls across mutual cycles are fully resolved.
     */
    private void computeFirst() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String nt : nonTerminals) {
                int beforeSize = firstSets.get(nt).size();
                computeFirst(nt);
                if (firstSets.get(nt).size() > beforeSize) {
                    changed = true;
                }
            }
        }
    }

    /**
     * Public helper to recursively compute the FIRST set of a grammar symbol.
     * 
     * @param symbol Terminal, epsilon, or non-terminal symbol.
     * @return Set of terminals and/or epsilon belonging to FIRST(symbol).
     */
    public Set<String> computeFirst(String symbol) {
        return computeFirst(symbol, new HashSet<>());
    }

    /**
     * Recursive implementation of FIRST(symbol).
     * 
     * Rules & Base Cases:
     * 1. Base Case (Epsilon): FIRST(#) = { # }.
     * 2. Base Case (Terminal): If symbol is a terminal, FIRST(symbol) = { symbol }.
     * 3. Base Case (Recursion Cycle): If the non-terminal is already in 'visiting' call stack,
     *    return current known elements to prevent infinite loops (e.g. left recursion).
     * 4. Recursive Step (Non-Terminal): If symbol is a non-terminal, look at every production
     *    alternative: symbol -> Y1 Y2 ... Yk.
     *    Recursively compute FIRST(Y1 Y2 ... Yk) and add all symbols to FIRST(symbol).
     */
    private Set<String> computeFirst(String symbol, Set<String> visiting) {
        // Base Case 1: Epsilon
        if (symbol.equals(EPSILON)) {
            Set<String> res = new LinkedHashSet<>();
            res.add(EPSILON);
            return res;
        }

        // Base Case 2: Terminal
        if (!nonTerminals.contains(symbol)) {
            Set<String> res = new LinkedHashSet<>();
            res.add(symbol);
            return res;
        }

        // Base Case 3: Cycle prevention (avoids infinite recursion on left-recursive grammars)
        if (visiting.contains(symbol)) {
            return firstSets.getOrDefault(symbol, Collections.emptySet());
        }

        visiting.add(symbol);
        Set<String> result = firstSets.get(symbol);

        // Recursive Step: evaluate all RHS alternatives for this non-terminal
        List<List<String>> rhsList = productions.get(symbol);
        if (rhsList != null) {
            for (List<String> rhs : rhsList) {
                // Recursively compute FIRST of the sequence: rhs
                Set<String> rhsFirst = computeFirstOfSequence(rhs, visiting);
                result.addAll(rhsFirst);
            }
        }

        visiting.remove(symbol);
        return result;
    }

    /**
     * Public helper to recursively compute FIRST for a sequence of symbols Y1 Y2 ... Yk.
     * 
     * @param symbols Sequence of grammar symbols (tokens).
     * @return Set of terminals and/or epsilon belonging to FIRST(Y1 Y2 ... Yk).
     */
    public Set<String> computeFirstOfSequence(List<String> symbols) {
        return computeFirstOfSequence(symbols, new HashSet<>());
    }

    /**
     * Recursive implementation of FIRST for a sequence of symbols: [Y1, Y2, ..., Yk].
     * 
     * Base cases:
     * 1. Empty sequence: returns { EPSILON }.
     * 
     * Recursive step:
     * 2. Compute FIRST(Y1) recursively.
     * 3. Add all non-epsilon symbols from FIRST(Y1) to the sequence's FIRST set.
     * 4. If FIRST(Y1) derives EPSILON:
     *    Recursively compute FIRST of the rest of the sequence [Y2, ..., Yk] and add to result.
     * 5. If FIRST(Y1) does NOT derive EPSILON:
     *    Stop (do not explore remaining symbols since Y1 does not vanish).
     */
    private Set<String> computeFirstOfSequence(List<String> symbols, Set<String> visiting) {
        Set<String> result = new LinkedHashSet<>();

        // Base case: empty sequence derives epsilon
        if (symbols == null || symbols.isEmpty()) {
            result.add(EPSILON);
            return result;
        }

        // Head symbol Y1
        String firstSymbol = symbols.get(0);

        // Recursively compute FIRST of the head symbol
        Set<String> firstOfFirstSymbol = computeFirst(firstSymbol, visiting);

        // Add all non-epsilon symbols from FIRST(Y1)
        for (String sym : firstOfFirstSymbol) {
            if (!sym.equals(EPSILON)) {
                result.add(sym);
            }
        }

        // If Y1 can derive epsilon, recursively compute FIRST of remainder [Y2 ... Yk]
        if (firstOfFirstSymbol.contains(EPSILON)) {
            List<String> rest = symbols.subList(1, symbols.size());
            Set<String> firstOfRest = computeFirstOfSequence(rest, visiting);
            result.addAll(firstOfRest);
        }

        return result;
    }

    /**
     * Driver method that computes the FOLLOW sets for all non-terminals in the grammar.
     * Uses recursive computation and iterates until all sets stabilize (fixed-point convergence),
     * ensuring full propagation through mutual circular dependencies.
     */
    private void computeFollow() {
        // Base initialization: Add '$' to the start symbol's FOLLOW set
        if (startSymbol != null) {
            followSets.get(startSymbol).add(DOLLAR);
        }

        // Multi-pass recursive convergence:
        // Repeatedly invoke recursive computeFollow until all FOLLOW sets stabilize
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

    /**
     * Public helper to recursively compute the FOLLOW set of a non-terminal.
     * 
     * @param symbol Non-terminal symbol.
     * @return Set of terminals and/or '$' belonging to FOLLOW(symbol).
     */
    public Set<String> computeFollow(String symbol) {
        return computeFollow(symbol, new HashSet<>());
    }

    /**
     * Recursive implementation of FOLLOW(symbol).
     * 
     * Rules & Base Cases:
     * 1. Base Case (Start Symbol): If symbol == startSymbol, '$' is in FOLLOW(symbol).
     * 2. Base Case (Recursion Cycle): If symbol is already in 'visiting', return the currently
     *    accumulated FOLLOW set to break mutual dependency recursion (e.g. A -> B and B -> A).
     * 3. Recursive Step (Production LHS -> alpha symbol beta):
     *    Search every production in the grammar where 'symbol' appears in the RHS:
     *    a. Subsequence beta follows 'symbol'.
     *       Recursively compute FIRST(beta) using computeFirstOfSequence(beta).
     *       Add all non-epsilon terminals from FIRST(beta) into FOLLOW(symbol).
     *    b. If beta derives epsilon (or beta is empty, meaning 'symbol' is the last symbol of RHS):
     *       Everything in FOLLOW(LHS) must be in FOLLOW(symbol).
     *       If LHS != symbol, recursively compute FOLLOW(LHS) and add its symbols into FOLLOW(symbol).
     */
    private Set<String> computeFollow(String symbol, Set<String> visiting) {
        Set<String> follow = followSets.get(symbol);
        if (follow == null) {
            return Collections.emptySet();
        }

        // Rule 1: Start symbol includes the end-marker '$'
        if (symbol.equals(startSymbol)) {
            follow.add(DOLLAR);
        }

        // Rule 2: Recursion cycle detection to guard against infinite loops
        if (visiting.contains(symbol)) {
            return follow;
        }

        visiting.add(symbol);

        // Rule 3: Search all productions where 'symbol' appears on the RHS
        for (Map.Entry<String, List<List<String>>> entry : productions.entrySet()) {
            String lhs = entry.getKey();
            List<List<String>> rhsList = entry.getValue();

            for (List<String> rhs : rhsList) {
                // Use indexOf() to find the index of 'symbol' in the production RHS
                int i = rhs.indexOf(symbol);
                while (i != -1) {
                    // 'symbol' is at index i in the RHS
                    // beta is the subsequence after 'symbol'
                    List<String> beta = rhs.subList(i + 1, rhs.size());

                    // Recursively compute FIRST of beta
                    Set<String> firstBeta = computeFirstOfSequence(beta);

                    // Rule 3a: Add FIRST(beta) \ { EPSILON } to FOLLOW(symbol)
                    for (String bSym : firstBeta) {
                        if (!bSym.equals(EPSILON)) {
                            follow.add(bSym);
                        }
                    }

                    // Rule 3b: If beta =>* epsilon (or beta is empty),
                    // everything in FOLLOW(lhs) is in FOLLOW(symbol)
                    if (firstBeta.contains(EPSILON)) {
                        if (!lhs.equals(symbol)) {
                            // Recursively compute FOLLOW(lhs) and add to FOLLOW(symbol)
                            Set<String> lhsFollow = computeFollow(lhs, visiting);
                            follow.addAll(lhsFollow);
                        }
                    }

                    // Find next occurrence of 'symbol' in the rest of rhs (if it appears more than once)
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
