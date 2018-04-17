package com.greedy;

/**
 Write a method getProductsOfAllIntsExceptAtIndex() that takes an array of integers and returns an array of the products.
 For example, given: [1, 7, 3, 4]
 your method would return: [84, 12, 28, 21]
 Note : Do not use division. Time complexity must be O(n).

 Greedy approach solution below :

 [84,12,4,1] next sum
 [1, 1, 7, 21] prev sum
 [84,12, 28, 21] complete sum result

 */
public class ProductOfOthers {

    public static void getProductsOfAllIntsExceptAtIndex(int [] input) {
        int [] result = new int [input.length];
        int tempProduct = 1;

        // product of all next elements
        for(int i = input.length - 1; i >= 0; i--) {
            if(i == input.length - 1) {
                result[i] = 1;
            }
            else {
                tempProduct *= input[i+1];
                result[i] = tempProduct;
            }
        }
        tempProduct = 1;
        // product of all prev elements
        for(int i = 0; i < input.length; i++) {
            if(i == 0) {
                result[i] *= tempProduct;
            }
            else {
                tempProduct *= input[i - 1];
                result[i] *= tempProduct;
            }
        }

        for(int x : result) {
            System.out.println(x);
        }
    }

    public static void main(String args[]) {
        int input [] = {1,7,3,4};
        getProductsOfAllIntsExceptAtIndex(input);
    }
}
