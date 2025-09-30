package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long startNs;
    private long endNs;

    public void reset() {
        comparisons = swaps = arrayAccesses = 0;
    }

    public void startTimer() {
        startNs = System.nanoTime();
    }

    public void stopTimer() {
        endNs = System.nanoTime();
    }

    public long elapsedNs() {
        return endNs - startNs;
    }

    public void incComparisons() {
        comparisons++;
    }

    public void incSwaps() {
        swaps++;
    }

    public void addArrayAccess(int count) {
        arrayAccesses += count;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }
    
    public long getArrayAccesses() {
        return arrayAccesses;
    }
}