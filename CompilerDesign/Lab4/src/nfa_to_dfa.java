package CompilerDesign.Lab4.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class nfa_to_dfa {

    static class NFA_edge {
        public int from, to;
        public String w;

        public NFA_edge(int from, int to, String terminal) {
            this.from = from;
            this.to = to;
            this.w = terminal;
        }
    }

    static class DFA_state {
        //        public String DFA_State;
        public String a, b;
        Set<Integer> NFA_state;

        public DFA_state(Set<Integer> NFA_state) {
            this.NFA_state = NFA_state;
        }
    }

    private Map<String, DFA_state> DFA_table;
    private ArrayList<NFA_edge> NFA_edges;
    public int startingNfaState;
    Character DFA_state_tracker = 'A';

    public nfa_to_dfa() {
        DFA_table = new LinkedHashMap<>();
        NFA_edges = new ArrayList<>();
    }

    public void constructDFA() {
        // we need to build the first DFA state A
        Set<Integer> t = new LinkedHashSet<>();
        t.add(startingNfaState);
        // first tar epsilon closure ta new state A hobe
        Set<Integer> firstEpsilonClosure = epsilonClosure(t);
        putInDFA_table(firstEpsilonClosure);

        // a and b hunting + necessary new state add
        char ch = 'A';
        while (true) {
            if (DFA_table.get(Character.toString(ch)) == null) break;
            Set<Integer> epsilonClosures = epsilonClosure(t);
            putInDFA_table(epsilonClosures);

            Set<Integer> move1_result = move(Character.toString(ch), "a");
            Set<Integer> move1_epClosure = epsilonClosure(move1_result);
            DFA_table.get(Character.toString(ch)).a = putInDFA_table(move1_epClosure);

            Set<Integer> move2_result = move(Character.toString(ch), "b");
            Set<Integer> move2_epClosure = epsilonClosure(move2_result);
            DFA_table.get(Character.toString(ch)).b = putInDFA_table(move2_epClosure);
            ch++;
            if (ch == DFA_state_tracker) break;
        }
    }

    public String putInDFA_table(Set<Integer> t) { // will return je kon state e rakha holo
        if (t.isEmpty()) return "-";

        // jodi age theke kono state thake tahole oi state er nam return korbe
        for (char ch = 'A'; ch < DFA_state_tracker; ch++) {
            if (DFA_table.get(Character.toString(ch)).NFA_state.containsAll(t)) {
                return ch + "";
            }
        }
        // jodi kono state na thake tobe new state create kore oi state tar nam return korbe
        DFA_table.put(Character.toString(DFA_state_tracker), new DFA_state(t));
        DFA_state_tracker++;
        return Character.toString(DFA_state_tracker - 1);
    }

    public void printDfaTable() {
        System.out.println("Printing DFA Table: ");
//        System.out.println("NFA States" + "\t\t\t|\t" + "DFA State" + "\t\t|\t" + "a" + "\t|\t" + "b" + "\t|\t");
        for (char ch = 'A'; ch < DFA_state_tracker; ch++) {
            String c = Character.toString(ch);
            System.out.println("DFA State: " + c + ", NFA States: " + DFA_table.get(c).NFA_state + ", a=" + DFA_table.get(c).a + ", b=" + DFA_table.get(c).b);
//            System.out.println(DFA_table.get(c).NFA_state + "\t|\t" + c + "\t|\t" + DFA_table.get(c).a + DFA_table.get(c).b);
        }
    }

    public Set<Integer> move(String dfaState, String terminal) {
        Set<Integer> output = new LinkedHashSet<>();
        Set<Integer> NfaStatesOfThisDfaState = this.DFA_table.get(dfaState).NFA_state;

        boolean iterate = true;
        while (iterate) {
            int prevSize = output.size();
            for (NFA_edge edge : NFA_edges) {
                if (NfaStatesOfThisDfaState.contains(edge.from) && edge.w.equals(terminal)) {
                    output.add(edge.to);
                }
            }
            if (prevSize == output.size()) {
                iterate = false;
            }
        }
        return output;
    } // kaj kora ucit

    public Set<Integer> epsilonClosure(Set<Integer> nfaStates) {
        Set<Integer> epsilonClosures = new LinkedHashSet<>();
        epsilonClosures.addAll(nfaStates); // epsilon closure e nijer gulo o add hoy

        // epsilon closure e je je add ache tader theke epsilon diye kon kon NFA state e jaoa jay
        boolean iterate = true;
        while (iterate) {
            int prevSize = epsilonClosures.size();
            for (NFA_edge edge : NFA_edges) {
                if (epsilonClosures.contains(edge.from) // je edge gulor starting ta epsilonClosure e ache
                        && edge.w.equals("~")) { // sei edge ta jodi epsilon hoy
                    epsilonClosures.add(edge.to);
                }
            }
            if (prevSize == epsilonClosures.size()) { // jodi notun kono NFA state epsilon closure e add na hoy
                iterate = false;
            }
        }
        return epsilonClosures;
    } // done

    public void printEpsilonClosures(Set<Integer> inp) {
        Set<Integer> output = epsilonClosure(inp);
        System.out.print("Epsilon Closures of ");
        System.out.print(inp.toString());
        System.out.print(" are: ");
        System.out.println(output.toString());
        System.out.println("----------------");
    }

    public void tester() {
        Set<Integer> t = new LinkedHashSet<>();
        t.add(startingNfaState);
        printEpsilonClosures(t);
    }

    private void readFile() throws FileNotFoundException {
        String inputString = "";
        Scanner scanner = new Scanner(new File("CompilerDesign/Lab4/src/nfaInput.txt"));
//        System.out.println("NFA Edge list:");
        while (scanner.hasNext()) {
            inputString = scanner.nextLine();
            Scanner scanner1 = new Scanner(inputString);
            int a = scanner1.nextInt();
            int b = scanner1.nextInt();
            String ch = scanner1.next();
            this.NFA_edges.add(new NFA_edge(a, b, ch));
//            System.out.println(a + " " + b + " " + ch);
        }
//        System.out.println("----------------");

    }

    static void main() throws FileNotFoundException {
        nfa_to_dfa nfaToDfa = new nfa_to_dfa();
        nfaToDfa.readFile();
        nfaToDfa.startingNfaState = 6;
//        nfaToDfa.tester();
        nfaToDfa.constructDFA();
        nfaToDfa.printDfaTable();
    }
}
/*
/usr/lib/jvm/java-1.25.0-openjdk-amd64/bin/java -javaagent:/home/kazirifat-ugreen/.local/share/JetBrains/Toolbox/apps/intellij-idea-ultimate/lib/idea_rt.jar=42035 -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /home/kazirifat-ugreen/IdeaProjects/CompilerLab/out/production/CompilerLab nfa_to_dfa
NFA Edge list:
0 1 a
1 5 ~
2 3 b
3 5 ~
4 0 ~
4 2 ~
5 7 ~
5 4 ~
6 4 ~
6 7 ~
7 8 ~
8 9 a
9 10 ~
10 11 b
11 12 ~
12 13 b
----------------
Epsilon Closures of [6] are: [6, 4, 7, 8, 0, 2]
----------------
Printing DFA Table:
DFA State: A, NFA States: [6, 4, 7, 8, 0, 2], a=B, b=C
DFA State: B, NFA States: [1, 9, 5, 7, 4, 8, 10, 0, 2], a=B, b=D
DFA State: C, NFA States: [3, 5, 7, 4, 8, 0, 2], a=B, b=C
DFA State: D, NFA States: [3, 11, 5, 7, 4, 8, 12, 0, 2], a=B, b=E
DFA State: E, NFA States: [3, 13, 5, 7, 4, 8, 0, 2], a=B, b=C

Process finished with exit code 0
 */