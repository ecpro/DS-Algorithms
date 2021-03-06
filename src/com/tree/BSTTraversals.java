package com.tree;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTTraversals {

    public static void preOrderTraversal(BST.Node rootNode) {
        if(rootNode == null) return;
        System.out.print(rootNode.getKey() + " ");
        preOrderTraversal(rootNode.getLeft());
        preOrderTraversal(rootNode.getRight());
    }

    public static void preOrderIterative(BST.Node root) {
        if(root == null) return;
        Stack<BST.Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()) {
            BST.Node x = stack.pop();
            if(x.getRight() != null) stack.push(x.getRight());
            if(x.getLeft() != null) stack.push(x.getLeft());
        }
    }

    public static void postOrderTraversal(BST.Node rootNode) {
        if(rootNode == null) return;
        postOrderTraversal(rootNode.getLeft());
        postOrderTraversal(rootNode.getRight());
        System.out.print(rootNode.getKey() + " ");
    }

    public static void postOrderIterative(BST.Node root) {

    }

    public static void inOrderTraversal(BST.Node rootNode) {
        if(rootNode == null) return;
        inOrderTraversal(rootNode.getLeft());
        System.out.print(rootNode.getKey() + " ");
        inOrderTraversal(rootNode.getRight());
    }

    public static void inOrderIterative(BST.Node root) {
        Stack<BST.Node> stack = new Stack<>();
        BST.Node x = root;
        while(!stack.isEmpty() || x != null) {
            if(x != null) {
                stack.push(x);
                x = x.getLeft();
            }
            else {
                x = stack.pop();
                System.out.print(x.getKey() + " ");
                x = x.getRight();
            }
        }
    }

    public static void levelOrderTraversal(BST.Node root) {
        BST.Node x = root;
        Queue<BST.Node> queue = new LinkedList<>();
        queue.add(x);
        while(!queue.isEmpty()) {
            x = queue.remove();
            System.out.print(x.getKey() + " ");
            if(x.getLeft() != null) queue.add(x.getLeft());
            if(x.getRight() != null) queue.add(x.getRight());
        }
    }

    public static int sizeOfBinaryTree(BST.Node root) {
        BST.Node x = root;
        Queue<BST.Node> queue = new LinkedList<>();
        queue.add(x);
        int count = 0;
        while(!queue.isEmpty()) {
            x = queue.remove();
            count++;
            if(x.getLeft() != null) queue.add(x.getLeft());
            if(x.getRight() != null) queue.add(x.getRight());
        }
        return count;
    }

    public static int sizeOfBinaryTreeRecursive(BST.Node root) {
        if(root == null) return 0;
        return 1 + sizeOfBinaryTreeRecursive(root.getLeft()) + sizeOfBinaryTreeRecursive(root.getRight());
    }

    public static boolean isSameTree(BST.Node x, BST.Node y) {
        if(x == null && y == null) return true;
        if(x == null || y == null) return false;
        return x.getKey().compareTo(y.getKey()) == 0 && isSameTree(x.getLeft(), y.getLeft()) && isSameTree(x.getRight(), y.getRight());
    }

    public static int heightBinaryTree(BST.Node root) {
        if(root.getLeft() == null && root.getRight() == null) return 0;
        if(root.getLeft() == null && root.getRight() != null) {
            return 1 + heightBinaryTree(root.getRight());
        }
        if(root.getLeft() != null && root.getRight() == null) {
            return 1 + heightBinaryTree(root.getLeft());
        }
        return 1 + Math.max(heightBinaryTree(root.getLeft()), heightBinaryTree(root.getRight()));
    }

    public static boolean rootLeafSum(int sum, BST.Node root, Queue<BST.Node> path) {
        if(root == null) return false;
        int key = (Integer) root.getKey();
        int remSum = sum - key;
        if(root.getLeft() == null && root.getRight() == null && remSum == 0) {
            path.add(root);
            return true;
        }
        boolean flag = false;
        flag = rootLeafSum(remSum, root.getLeft(), path) || rootLeafSum(remSum, root.getRight(), path);
        if(flag) {
            path.add(root);
        }
        return flag;
    }

    public static void iterativePostOrderTraveral(BST.Node root) {
        if(root == null) throw new IllegalArgumentException("root cannot be " + null);
        Stack<BST.Node> stack = new Stack<>();
        BST.Node curr = root;
        stack.push(root);
        curr = curr.getLeft();
        BST.Node lastPopped = null;
        while(!stack.isEmpty()) {
            // go the bottommost leaf node on the left child
            if(curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            else {
                // leaf node condition
                if(stack.peek().getRight() == null) {
                 lastPopped = stack.pop();
                 System.out.print(lastPopped.getKey() + " ");
                }
                else if(stack.peek().getRight() != null && stack.peek().getRight() != lastPopped) {
                    curr = stack.peek().getRight();
                }
                else {
                    if(lastPopped != stack.peek().getLeft()) {
                        lastPopped = stack.pop();
                        System.out.print(lastPopped.getKey() + " ");
                    }
                }
            }
        }
    }

    public static void main(String args []) {
        BST<Integer, Object> bst = new BST<>();
        final Object x = new Object();
        bst.put(20, x);
        bst.put(12, x);
        bst.put(22, x);
        bst.put(18, x);
        bst.put(14, x);
        bst.put(8, x);
        bst.put(6, x);
        bst.put(24, x);
        bst.put(23, x);
        bst.put(26, x);

        System.out.println("---- PreOrderTraversal Recursive ----");
        preOrderTraversal(bst.getRoot());
        System.out.println("\n---- PreOrderTraversal Iterative ----");
        preOrderTraversal(bst.getRoot());
        System.out.println("\n---- InOrderTraversal Recursive ----");
        inOrderTraversal(bst.getRoot());
        System.out.println("\n---- InOrderTraversal Iterative ----");
        inOrderIterative(bst.getRoot());
        System.out.println("\n---- PostOrderTraversal Recursive ----");
        postOrderTraversal(bst.getRoot());
        System.out.println("\n---- LevelOrderTraversal ----");
        levelOrderTraversal(bst.getRoot());
        System.out.println("\n---- Same Tree Or Not ----");
        System.out.println(isSameTree(bst.getRoot(), bst.getRoot()));

        BST<Integer, Object> bst2 = new BST<>();
        bst2.put(20, x);
        bst2.put(12, x);
        bst2.put(22, x);
        bst2.put(18, x);
        bst2.put(14, x);
        bst2.put(8, x);
        bst2.put(6, x);
        bst2.put(24, x);
        bst2.put(23, x);
        bst2.put(26, x);

        System.out.println("\n---- Same Tree Or Not ----");
        System.out.println(isSameTree(bst.getRoot(), bst2.getRoot()));

        System.out.println("\n---- Size of Binary Tree Iterative ----");
        System.out.println(sizeOfBinaryTree(bst.getRoot()));

        System.out.println("\n---- Size of Binary Tree Recursive ----");
        System.out.println(sizeOfBinaryTreeRecursive(bst.getRoot()));

        System.out.println("\n---- Height of Binary Tree Recursive ----");
        System.out.println(heightBinaryTree(bst.getRoot()));

        System.out.println("\n---- Iterative post order traversal ----");
        iterativePostOrderTraveral(bst.getRoot());

    }

}
