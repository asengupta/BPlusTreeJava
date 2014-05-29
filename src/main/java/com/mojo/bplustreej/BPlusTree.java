package com.mojo.bplustreej;

public class BPlusTree {
  private int order;
  private BPlusTreeNode root;

  public BPlusTree(int order) {
    this.order = order;
    root = new BPlusTreeNode(order);
  }

  public void insert(BTreeValue value) {
    BTreeValue newRoot = root.add(value);
    if (newRoot == null) return;
    root = new BPlusTreeNode(order);
    root.values().add(newRoot);
  }

  public BPlusTreeNode getRoot() {
    return root;
  }

  public IBPlusTreeNode find(BTreeValue b) {
    return root.find(b);
  }
}





