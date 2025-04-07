package Algorithms.PracticeMakesAManPerfect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class KnapsackGreedy {
    static class Instance {
        int price, weight;
        double pw;

        public Instance(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }
    }

    static ArrayList<Instance> instances = new ArrayList<>();

    static void takeInputFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Scanner scanner1 = new Scanner(s);
            int a = scanner1.nextInt();
            int b = scanner1.nextInt();
            if (a == b && a == 0) return;
            instances.add(new Instance(a, b));
        }
    }

    private static double knapsack(int maxNumItem, int maxWeight) {
        for (int i = 0; i < instances.size(); i++) {
            instances.get(i).pw = (double) instances.get(i).price / (double) instances.get(i).weight;
        }
        instances.sort(Comparator.comparingDouble(instance -> instance.pw));
        Collections.reverse(instances);

        double profit = 0.0;
        ArrayList<Double> x = new ArrayList<>(Collections.nCopies(instances.size(), 0.0));

        double U = maxWeight;

        int i = 0;
        for (i = 0; i < x.size(); i++) {
            if (instances.get(i).weight < U) break;
            else {
                x.set(i, 1.0);
                maxNumItem -= instances.get(i).weight;
            }
        }
        double frac = (double) U / (double) instances.get(i).weight;
        if (i <= maxNumItem) x.set(i, frac);

        // calculate total profit
        for (i = 0; i < x.size(); i++) {
            System.out.println(x.get(i));
            profit += x.get(i) * instances.get(i).price;
        }
        return profit;
    }

    public static void main(String[] args) throws FileNotFoundException {
        takeInputFromFile("./Algorithms/Greedy/Knapsack.txt");
        double maxProfit = knapsack(3, 20);
        System.out.println("Max Profit = " + maxProfit);
    }
} // DONE
