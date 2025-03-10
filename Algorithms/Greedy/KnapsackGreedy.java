package Algorithms.Greedy;

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
        Scanner fs = new Scanner(new File(filePath));
        while (fs.hasNext()) {
            String s = fs.nextLine();
            Scanner scanner = new Scanner(s);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a == b && b == 0) return;
            Instance k = new Instance(a, b);
            instances.add(k);
        }
    } // WORKS

    static double knapsack(int maxNumItem, int maxWeight) {
        //fill all p/w
        for (Instance i : instances) {
            i.pw = i.price / i.weight;
        }
        instances.sort(Comparator.comparingDouble(instance -> instance.pw));
//        instances.reversed(); ----------- THIS DOES NOT WORK
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
//            if (instances.get(i).weight > U) break;
//            else {
//                x.set(i, 1.0);
//                U -= instances.get(i).weight;
//            }
//        }
//        if (i <= maxWeight) {
//            x.set(i, (double) ((double)U / (double)instances.get(i).weight));
        }

        // calculate total profit
        double totalProfit = 0;
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
