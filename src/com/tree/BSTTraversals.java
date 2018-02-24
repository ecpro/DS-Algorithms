package com.tree;

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

    public static void postOrderTraversal(BST.Node rootNode) {
        if(rootNode == null) return;
        postOrderTraversal(rootNode.getLeft());
        postOrderTraversal(rootNode.getRight());
        System.out.print(rootNode.getKey() + " ");
    }

    public static void inOrderTraversal(BST.Node rootNode) {
        if(rootNode == null) return;
        inOrderTraversal(rootNode.getLeft());
        System.out.print(rootNode.getKey() + " ");
        inOrderTraversal(rootNode.getRight());
    }

    public static void levelOrderTraversal(BST.Node rootNode) {

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
        inOrderTraversal(bst.getRoot());
        System.out.println();
        postOrderTraversal(bst.getRoot());
    }

}
