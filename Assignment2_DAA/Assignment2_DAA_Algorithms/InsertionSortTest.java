package algorithms;
public class InsertionSortTest {
    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();
        check(sorter.sort(new int[]{}), new int[]{});
        check(sorter.sort(new int[]{42}), new int[]{42});
        check(sorter.sort(new int[]{1, 2, 3, 4, 5}), new int[]{1, 2, 3, 4, 5});
        check(sorter.sort(new int[]{5, 4, 3, 2, 1}), new int[]{1, 2, 3, 4, 5});
        check(sorter.sort(new int[]{2, 2, 1, 3, 1}), new int[]{1, 1, 2, 2, 3});
        System.out.println("✅ Yeah! You did it man, nice job!");
    }
    private static void check(int[] actual, int[] expected) {
        if (actual.length != expected.length) {
            throw new AssertionError("The size of array doesn't compare. Do change them!");
        }
        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != expected[i]) {
                throw new AssertionError("You did " + arrToStr(expected) +
                        " however you needed " + arrToStr(actual));
            }
        }
    }
    private static String arrToStr(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
