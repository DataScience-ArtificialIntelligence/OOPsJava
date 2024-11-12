public class ComparableHelper {
    /**
     * Compares two Comparable objects and returns the result of the comparison.
     *
     * @param <T> The type of the Comparable objects.
     * @param a   The first Comparable object.
     * @param b   The second Comparable object.
     * @return A negative integer if a is less than b, zero if a is equal to b, and a positive integer if a is greater than b.
     */
    public static <T extends Comparable<? super T>> int compare(T a, T b) {
        return a.compareTo(b);
    }

    /**
     * Checks if the first Comparable object is less than the second Comparable object.
     *
     * @param <T> The type of the Comparable objects.
     * @param a   The first Comparable object.
     * @param b   The second Comparable object.
     * @return True if a is less than b, false otherwise.
     */
    public static <T extends Comparable<? super T>> boolean isLess(T a, T b) {
        return compare(a, b) < 0;
    }

    /**
     * Checks if the first Comparable object is greater than the second Comparable object.
     *
     * @param <T> The type of the Comparable objects.
     * @param a   The first Comparable object.
     * @param b   The second Comparable object.
     * @return True if a is greater than b, false otherwise.
     */
    public static <T extends Comparable<? super T>> boolean isGreater(T a, T b) {
        return compare(a, b) > 0;
    }

    /**
     * Checks if the first Comparable object is equal to the second Comparable object.
     *
     * @param <T> The type of the Comparable objects.
     * @param a   The first Comparable object.
     * @param b   The second Comparable object.
     * @return True if a is equal to b, false otherwise.
     */
    public static <T extends Comparable<? super T>> boolean isEqual(T a, T b) {
        return compare(a, b) == 0;
    }
}
