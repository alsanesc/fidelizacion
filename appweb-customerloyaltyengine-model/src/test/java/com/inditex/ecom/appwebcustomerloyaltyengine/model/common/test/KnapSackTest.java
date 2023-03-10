package com.inditex.ecom.appwebcustomerloyaltyengine.model.common.test;

public class KnapSackTest {
    public static void main(String[] args) {
        int[] price = { 1, 1, 1, 1, 1, 1 };
        int[] weights = { 20, 11, 10, 7, 5, 3 };

        int sackCapacity = 20;
        boolean[][] keep = getItemsToPick(price, weights, sackCapacity);
        printSelectedItems(keep, sackCapacity, price, weights);
    }

    private static boolean[][] getItemsToPick(int[] price, int[] weights, int sackCapacity) {
        int nItems = price.length;
        // dp[i][w] - the maximum value of sub problem with i items and with w sack capacity.
        // no need to initialize with zeros as in java, the defalt values are 0 for int premitive type.
        int[][] dpTable = new int[nItems + 1][sackCapacity + 1];
        boolean[][] keep = new boolean[nItems][sackCapacity + 1];

        // iterate through all of the items
        for (int i = 1; i <= nItems; i++) {
            // calculate sub problem for all weights
            for (int w = 1; w <= sackCapacity; w++) {
                if (weights[i - 1] > w) {
                    // we cannt take this weight as it exceeds sub problem with weight w and i items
                    dpTable[i][w] = dpTable[i - 1][w];
                } else {
                    // Price if we include item i
                    int pYes = price[i - 1] + dpTable[i - 1][w - weights[i - 1]];
                    // Price if we include item i
                    int pNo = dpTable[i - 1][w];
                    if (pYes > pNo) {
                        // this item MAY go into sack
                        keep[i - 1][w] = true;
                        dpTable[i][w] = pYes;
                    } else {
                        dpTable[i][w] = pNo;
                    }
                }
            }
        }
        // printing dpTable
        for (int[] rows : dpTable) {
            for (int col : rows) {
                System.out.format("%5d", col);
            }
            System.out.println();
        }

        // System.out.println(Arrays.deepToString(dpTable));
        return keep;
    }

    public static void printSelectedItems(boolean[][] keep, int sackCapacity, int[] price, int[] weights) {
        // printing what items we picked
        System.out.println("Selected items:");
        int K = sackCapacity;
        int n = price.length;
        int wsel = 0;
        int vsel = 0;
        for (int i = n - 1; i >= 0; i--) { // need to go in the reverse order
            if (keep[i][K] == true) {
                System.out.println(i + "\tv=" + price[i] + "\tw=" + weights[i]);
                wsel += weights[i];
                vsel += price[i];
                K = K - weights[i];
            }
        }
        System.out.println("The overall value of selected items is " + vsel + " and weight " + wsel);
        System.out.println("Maximum weight was " + sackCapacity);
    }
}
