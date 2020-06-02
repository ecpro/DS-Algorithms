package com.algorithmicToolBox.week3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * capacity : 55
 * {60, 100, 120}
 * {20, 50, 30} -> {30,20,50}
 * {3, 2, 4 } - {4, 3, 2}
 * 30 -> 1
 * 20 -> 1
 * 5 -> 5 *
 */
public class FractionalKnapsack {

    public static float maximizeLoot(int capacity, Integer[] weights, int[] prices) {
        Map<Integer, Float> pricesPerUnit = new HashMap<>();
        for (int i = 0; i < prices.length; i++) {
            pricesPerUnit.put(weights[i], prices[i] / (float) weights[i]);
        }
        Arrays.sort(weights, (w1, w2) -> pricesPerUnit.get(w2).compareTo(pricesPerUnit.get(w1)));
        float profit = 0;
        int index = 0;
        while (capacity > 0 && index < weights.length) {
            if (capacity >= weights[index]) {
                profit += weights[index] * pricesPerUnit.get(weights[index]);
                capacity = capacity - weights[index];
            }
            else {
                index++;
            }
        }
        if(capacity != 0) {
            profit += capacity * pricesPerUnit.get(weights[index]);
        }
        return profit;

    }

    public static void main(String[] args) {
        //int [] prices = {60, 100, 120};
        //Integer [] weights = {20, 50, 30};
        Integer [] weights = {1, 2, 3, 4};
        int [] prices = {2, 3, 4, 5};
        int capacity = 59;
        float lootValue = maximizeLoot(capacity, weights, prices);
        System.out.println(lootValue);
    }
}
