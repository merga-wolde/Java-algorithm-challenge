import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2. The fundamental theorem of arithmetic states that every natural number
 * greater than 1 can be written as a unique product of prime
 * numbers. So, for instance, 6936=2*2*2*3*17*17. Write a method named
 * encodeNumber what will encode a number n as an array that
 * contains the prime numbers that, when multipled together, will equal n. So
 * encodeNumber(6936) would return the array {2, 2, 2, 3,
 * 17, 17}. If the number is <= 1 the function should return null;
 * If you are programming in Java or C#, the function signature is
 * int[ ] encodeNumber(int n)
 * If you are programming in C or C++, the function signature is
 * int *encodeNumber(int n) and the last element of the returned array is 0.
 * Note that if you are programming in Java or C#, the returned array should be
 * big enough to contain the prime factors and no bigger.
 * If you are programming in C or C++ you will need one additional element to
 * contain the terminating zero.
 * Hint: proceed as follows:
 * 1. Compute the total number of prime factors including duplicates.
 * 2. Allocate an array to hold the prime factors. Do not hard-code the size of
 * the returned array!!
 * 3. Populate the allocated array with the prime factors. The elements of the
 * array when multiplied together should equal the number.
 */

public class EncodeNumber {
    public static int[] encodeNumber(int n) {
        boolean flag;
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        if (n < 2) {
            return null;
        }
        for (int i = 2; i <= n / 2; i++) {
            flag = true;
            if (n % i == 0) {
                for (int j = 2; j <= i / 2; j++) {
                    if (i % j == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    tempArray.add(i); // 2, 3, 17
                }
            }
        }
        // Incase the number has no prime factors at all, we will return null.
        if (tempArray.size() == 0) {
            return null;
        }

        int encodedArraySize = 0; // 2, 3, 17
        ArrayList<Integer> reprArray = new ArrayList<Integer>();
        for (int i = tempArray.size() - 1; n > 1; i--) {
            if (n % tempArray.get(i) == 0) {
                reprArray.add(tempArray.get(i)); // 17, 3, 2, 17, 2, 2
                encodedArraySize++; // 3, 4, 5, 6
                n /= tempArray.get(i);
                if (i == 0) {
                    i = tempArray.size() - 1;
                }
            } else {
                if (i == 0) {
                    i = tempArray.size();
                }
                continue;
            }
        }
        int[] encodedArray = new int[encodedArraySize];

        for (int i = 0; i < encodedArraySize; i++) {
            encodedArray[i] = reprArray.get(i);
        }
        Arrays.sort(encodedArray);
        return encodedArray;
    }

    /*
     * This printArray method doesn't do anything on solving the problem.
     * It is just used to verify the output by printing the array using the format.
     */

    public static void printArray(int[] a) {
        if (a == null) {
            System.out.println("null");
            return;
        }
        System.out.print("{");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
        System.out.println();
    }

    public static void main(String[] args) {
        printArray(encodeNumber(6936)); // {2, 2, 2, 3, 17, 17}
        printArray(encodeNumber(10)); // {2, 5}
        printArray(encodeNumber(134)); // {2, 67}
        printArray(encodeNumber(15765)); // {3, 5, 1051}
        printArray(encodeNumber(13457)); // null
    }
}