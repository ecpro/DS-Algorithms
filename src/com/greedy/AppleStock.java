package com.greedy;

/**
 * Question: Suppose we could access yesterday's stock prices as an array, where:

 The indices are the time in minutes past trade opening time, which was 9:30am local time.
 The values are the price in dollars of Apple stock at that time.
 So if the stock cost $500 at 10:30am, stockPricesYesterday[60] = 500.

 Write an efficient method that takes stockPricesYesterday and returns the best profit
 I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.
 *
 * For example:
 int[] stockPricesYesterday = new int[] {10, 7, 5, 8, 11, 9};
 getMaxProfit(stockPricesYesterday);
 // returns 6 (buying for $5 and selling for $11)
 If we're going to do better than O(n^2), we're probably going to do it in either O(n log(n))or O(n)
 Since we're trying to loop through the array once, let's use a greedy.
 */
public class AppleStock {

    public static int getMaxProfit(int [] stockPricesYesterday) {
        if (stockPricesYesterday.length < 2) {
            throw new IllegalArgumentException("Getting a profit requires at least 2 prices");
        }
        int prevMin = stockPricesYesterday[0];
        int maxProfit = Integer.MIN_VALUE;
        for(int i = 1; i < stockPricesYesterday.length; i++) {
            int currStockPrice = stockPricesYesterday[i];
            int currProfit = currStockPrice - prevMin;
            prevMin = Math.min(currStockPrice, prevMin);
            maxProfit = Math.max(currProfit, maxProfit);
        }
        return maxProfit;
        // We’re finding the max profit with one pass and constant space!
    }

    public static void main(String [] args) {
        //int[] stockPricesYesterday = new int[] {10, 7, 5, 8, 11, 9};
        int[] stockPricesYesterday = new int[] {19, 17, 15, 12, 2};
        int maxProfit = AppleStock.getMaxProfit(stockPricesYesterday);
        System.out.println(maxProfit);
    }
}
