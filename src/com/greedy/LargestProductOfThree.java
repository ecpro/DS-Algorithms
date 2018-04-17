package com.greedy;

/**
 * Created by eccspro on 26/03/18.
 * Given an array of integers, find the highest product you can get from three of the integers.
 * Gotchas
 Does your method work with negative numbers? If arrayOfInts is [-10, -10, 1, 3, 2][−10,−10,1,3,2] we should return 300 (which we get by taking -10 * -10 * 3).
 {1, 10, -5, 1, -100} should give 5000
 Breakdown To brute force ↴ an answer we could iterate through arrayOfInts and multiply each integer by each other integer, and then multiply that product by each other other integer.
 This would probably involve nesting 3 loops. But that would be an O(n^3)O(n3) runtime! We can definitely do better than that.
 Because any integer in the array could potentially be part of the greatest product of three integers, we must at least look at each integer. So we're doomed to spend at least O(n)O(n) time.
 Sorting the array would let us grab the highest numbers quickly, so it might be a good first step. Sorting takes O(n\lg{n})O(nlgn) time. That's better than the O(n^3)O(3) time our brute force approach required, but we can still do better.
 */
public class LargestProductOfThree {

}
