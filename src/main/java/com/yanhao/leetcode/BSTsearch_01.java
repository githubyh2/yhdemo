package com.yanhao.leetcode;

// 创建一个二叉 搜索树
public class BSTsearch_01 {
    /*
    Let us create following BST
      50
   /     \
  30      70
 /  \    /  \
20   40  60   80
    */
    public static void main(String[] args) {
        BST bst = new BST();

        bst.Insert(50);
        bst.Insert(30);
        bst.Insert(20);
        bst.Insert(40);
        bst.Insert(70);
        bst.Insert(60);
        bst.Insert(80);


        bst.LevelOrderTraversal();
    }

}
