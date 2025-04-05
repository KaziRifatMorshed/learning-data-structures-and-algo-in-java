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
        for (Instance k : instances) {
            k.pw = (double) k.price / (double) k.weight;
        }
        instances.sort(Comparator.comparingDouble(instance -> instance.pw));
        Collections.reverse(instances);

        ArrayList<Double> x = new ArrayList<>(Collections.nCopies(maxNumItem, 0.0));

        int U = maxWeight;
        int i = 0;
        for (i = 0; i < maxNumItem; i++) {
            if (instances.get(i).weight <= U) {
                x.set(i, 1.0);
                U -= instances.get(i).weight;
            } else {
                double frac = (double) U / (double) instances.get(i).weight;
                x.set(i, frac);
            }
        }

        double totalProfit = 0.0;
        for (i = 0; i < x.size(); i++) {
            System.out.println(x.get(i));
            totalProfit += x.get(i) * instances.get(i).price;
        }
        return totalProfit;
    }

    public static void main(String[] args) throws FileNotFoundException {
        takeInputFromFile("./Algorithms/Greedy/Knapsack.txt");
        double maxProfit = knapsack(3, 20);
        System.out.println("Max Profit = " + maxProfit);
    }
} // DONE
