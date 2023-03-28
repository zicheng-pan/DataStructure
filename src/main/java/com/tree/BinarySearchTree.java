package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BinarySearchTree<E extends Comparable> {

    private class Node {
        public E value;
        public Node left;
        public Node right;

        public Node(E value) {
            this.value = value;
        }
    }

    private Node root;

    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(E value) {
        if (root == null) {
            size++;
            root = new Node(value);
        } else {
            add(root, value);
        }
    }

    public Node addwithoutNull(Node node, E value) {
        if (node == null) {
            size++;
            return new Node(value);
        }
        if (value.compareTo(node.value) < 0) {
            node.left = addwithoutNull(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = addwithoutNull(node.right, value);
        }
        return node;
    }

    public boolean contains(Node node, E value) {
        if (node == null)
            return false;
        if (value.compareTo(node.value) == 0)
            return true;
        if (value.compareTo(node.value) < 0)
            return contains(node.left, value);
        else
            return contains(node.right, value);
    }

    public void add(Node node, E value) {

        if (value.compareTo(node.value) == 0)
            return;
        else if (value.compareTo(node.value) < 0 && node.left == null) {
            node.left = new Node(value);
            size++;
            return;
        } else if (value.compareTo(node.value) > 0 && node.right == null) {
            node.right = new Node(value);
            size++;
            return;
        }

        if (value.compareTo(node.value) < 0) {
            add(node.left, value);
        } else {
            add(node.right, value);
        }
    }


    public void print(Node node) {
        if (node == null)
            return;
        System.out.print(node.value + "  ");
        print(node.left);
        print(node.right);
    }


    public void printMid(Node node) {
        if (node == null)
            return;
        printMid(node.left);
        System.out.println(node.value);
        printMid(node.right);
    }

    public void printbylayer(Node node) {

        Queue queue1 = new LinkedList();
        Queue queue2 = new LinkedList();
        if (node == null)
            return;

        queue1.add(node);
        int i = 0;
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
//            System.out.println("layer_" + i);
            addLayer(queue1, queue2);
            System.out.println();
            i = i + 1;
//            System.out.println("layer_" + i);
            addLayer(queue2, queue1);
            System.out.println();
        }

    }


    public void addLayer(Queue<Node> queue1, Queue<Node> queue2) {
        while (!queue1.isEmpty()) {
            Node temp = (Node) queue1.poll();
            if (temp == null) {
                System.out.print("null" + " ");
                continue;
            }
            System.out.print(temp.value + " ");

            queue2.add(temp.left);
            queue2.add(temp.right);
        }
    }

    public E getMin() {
        if (root == null) {
            return null;
        }
        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    public Node getMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null)
            return node;
        return getMin(node.left);
    }


    public Node getMax() {
        if (root == null) {
            return null;
        }
        return getMax(root);
    }

    private Node getMax(Node node) {
        if (node.right == null)
            return node;

        return getMax(node.right);
    }

    public Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            size--;
            node.left = null;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }


    public Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            size--;
            node.right = null;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public Node removeElement(Node node, E value) {

        if (node == null) {
            return null;
        }

        if (value.compareTo(node.value) == 0) {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            } else if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            } else {
                Node leftMax = getMin(node.right);
                leftMax.right = removeMin(node.right);
                leftMax.left = node.left;
                node.left = null;
                node.right = null;
                return leftMax;
            }
        }


        if (value.compareTo(node.value) < 0) {
            node.left = removeElement(node.left, value);
        } else {
            node.right = removeElement(node.right, value);
        }
        return node;

    }


    /*
        查找节点的前驱节点
     */
    public Node predecessor(Node node, E value) {
        if (node == null)
            return null;

        if ((node.left != null && value.compareTo(node.left.value) == 0) ||
                (node.right != null && value.compareTo(node.right.value) == 0)) {
            return node;
        } else if (node.left != null && value.compareTo(node.value) < 0) {
            return successor(node.left, value);
        } else if (node.right != null && value.compareTo(node.value) > 0) {
            return successor(node.right, value);
        } else {
            throw new RuntimeException("not found " + value + " node item");
        }
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int value = random.nextInt(100);
//            System.out.println("add value:" + value);
            binarySearchTree.add(value);
        }
        binarySearchTree.add(9);
        binarySearchTree.add(6);
//        for (int i = 0; i < 10; i++) {
//            int value = binarySearchTree.getMin();
//            System.out.println("remove:" + value);
//            binarySearchTree.root = binarySearchTree.removeElement(binarySearchTree.root, value);
////            binarySearchTree.print(binarySearchTree.root);
//            binarySearchTree.printbylayer(binarySearchTree.root);
//        }

        binarySearchTree.printbylayer(binarySearchTree.root);
//        BinarySearchTree<Integer>.Node node = binarySearchTree.successor(binarySearchTree.root, -1);
//        System.out.println(node.value);

    }
}
