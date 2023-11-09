package com.yanhao.leetcode;

import java.util.LinkedList;
import java.util.Queue;

// 定义二叉搜索树结构
public class BST {
    public Node root;

    // 这个函数是private 的， 递归调用，插入节点  递归插入:RecursionInsert
    private Node RecursionInsert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.data) {
            node.left = RecursionInsert(node.left, data);
            node.left.parent = node;
        } else if (data > node.data) {
            node.right = RecursionInsert(node.right, data);
            node.right.parent = node;
        }
        return node;
    }

    // 对外开放的 插入数据  接口
    public void Insert(int data) {
        if (root == null) {
            root = RecursionInsert(root, data);
        } else {
            RecursionInsert(root, data);
        }
    }

    // 层次遍历 二叉树
    public void LevelOrderTraversal() {
        // java 中创建队列的 对象
        Queue<Node> q = new LinkedList<Node>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node currNode = q.poll();

            if (currNode.left != null) {
                q.offer(currNode.left);
            }

            if (currNode.right != null) {
                q.offer(currNode.right);
            }
//            // 括号里面是父节点的值
            String msg = String.format("%s,(%s).....", currNode.data, currNode.parent != null ? currNode.parent.data : "null");

            System.out.println("==========" + msg);
        }

    }

}
