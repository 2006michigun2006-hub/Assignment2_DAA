package metrics;

import algorithms.InsertionSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        int size = args.length > 0 ? Integer.parseInt(args[0]) : 1000000;
        String dist = args.length > 1 ? args[1] : "random";
        int runs = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        try (FileWriter fw = new FileWriter("benchmark.csv", true)) {
            for (int r = 0; r < runs; r++) {
                int[] arr = generateArray(size, dist);
                InsertionSort sorter = new InsertionSort();
                sorter.sort(arr);
                PerformanceTracker tracker = sorter.getTracker();

                fw.write(String.format(
                        "InsertionSort,%d,%s,%d,%d,%d,%d,%d\n",
                        size, dist, r + 1,
                        tracker.elapsedNs(),
                        tracker.getComparisons(),
                        tracker.getSwaps(),
                        tracker.getArrayAccesses()
                ));
            }
        }
    }

    private static int[] generateArray(int n, String dist) {
        Random rand = new Random();
        int[] arr = new int[n];
        switch (dist) {
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "nearly":
                for (int i = 0; i < n; i++) arr[i] = i;
                // 1% random swaps
                for (int i = 0; i < n / 100; i++) {
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n);
                    int tmp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = tmp;
                }
                break;
            default: // random
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n * 10);
        }
        return arr;
    }
}