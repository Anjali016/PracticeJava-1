package data.structures.arrays.sliding.window;

import java.util.Arrays;

public class SlidingWindowExamples {

    /**
     * 138. Find maximum average subarray of k length
     * Given an array with positive and negative numbers, find the maximum average subarray of given length.

     Example:
     Input:  arr[] = {1, 12, -5, -6, 50, 3}, k = 4
     Output: Maximum average subarray of length 4 begins
     at index 1.
     */
    int maxAverage(int[] array, int k) {
        int currMax = Arrays.stream(array).limit(k).sum();
        int max = 0; int index = 0;
        int windowL;
        int windowR = k - 1;
        for (windowL = 0; windowL <= array.length - k; windowL++) {
            currMax -= array[windowL];
            currMax += array[++windowR];
            if (currMax > max) {
                max = currMax;
                index = windowL;
            }
        }
        return index;
    }

    /**
     * 141. Given a binary array and an integer m, find the position of zeroes flipping which creates maximum
     * number of consecutive 1s in array.
     */

    /**
     * 143. Find minimum average subarray of k length.
     */

}
