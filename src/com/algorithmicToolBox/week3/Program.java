package com.algorithmicToolBox.week3;

import java.util.LinkedList;
import java.util.Queue;

class Program {
    public static int helper(BinaryTree tree) {
        if(tree == null) return 0;
        if(tree.left == null && tree.right == null)  return tree.value;
        int LMax = helper(tree.left);
        int RMax = helper(tree.right);
        int LMaxPlus = LMax + tree.value;
        int RMaxPlus = RMax = tree.value;
        return Math.max(tree.value, Math.max(LMaxPlus, RMaxPlus));
    }

    public static int helper2(BinaryTree root) {
        int leftmax = helper(root.left);
        int rightmax = helper(root.right);
        int LeftMaxPlus = leftmax + root.value;
        int RightMaxPlues = rightmax + root.value;
        int TMax = root.value + leftmax + rightmax;
        return Math.max(Math.max(Math.max(LeftMaxPlus, RightMaxPlues), TMax), root.value);
    }

    public static int maxPathSum(BinaryTree tree) {
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(tree);
        int maxSum = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            BinaryTree curr = queue.remove();
            if(curr.left != null) queue.add(curr.left);
            if(curr.right != null) queue.add(curr.right);
            maxSum = Math.max(helper2(curr), maxSum);
        }
        return maxSum;
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}





