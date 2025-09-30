package algorithms;

import metrics.PerformanceTracker;

public class InsertionSort {

    private final PerformanceTracker tracker = new PerformanceTracker();

    public int[] sort(int[] array) {
        tracker.reset();
        tracker.startTimer();

        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            tracker.addArrayAccess(1); // read key
            int j = i - 1;

            // Binary search for insertion position (оптимизация)
            int pos = binarySearch(array, key, 0, j);
            tracker.incComparisons();

            // Shift elements right
            int len = i - pos;
            if (len > 0) {
                System.arraycopy(array, pos, array, pos + 1, len);
                tracker.addArrayAccess(2 * len); // read + write
            }

            array[pos] = key;
            tracker.addArrayAccess(1); // write key
        }

        tracker.stopTimer();
        return array;
    }

    private int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            tracker.incComparisons();
            tracker.addArrayAccess(1);
            int mid = (low + high) >>> 1;
            if (arr[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public PerformanceTracker getTracker() {
        return tracker;
    }
}