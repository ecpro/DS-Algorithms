package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by eccspro on 24/02/18.
 */
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

        preOrderTraversal(bst.getRoot());
        System.out.println();
        preOrderTraversal(bst.getRoot());
        System.out.println();
        inOrderTraversal(bst.getRoot());
        System.out.println();
        inOrderIterative(bst.getRoot());
        System.out.println();
        postOrderTraversal(bst.getRoot());
        System.out.println();
        levelOrderTraversal(bst.getRoot());
    }

}
