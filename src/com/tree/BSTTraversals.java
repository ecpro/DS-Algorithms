package com.tree;

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
    }

}
