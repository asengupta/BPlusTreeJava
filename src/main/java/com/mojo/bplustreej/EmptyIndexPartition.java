package com.mojo.bplustreej;

public class EmptyIndexPartition<T extends Comparable> implements IndexPartition {
  private final IBPlusTreeNode originalNode;
  private final BTreeValue partitioningValue;

  public EmptyIndexPartition(IBPlusTreeNode originalNode,
                             BTreeValue partitioningValue) {this.originalNode = originalNode;
    this.partitioningValue = partitioningValue;
  }

  @Override public IBPlusTreeNode greaterThan() {
    return new NullBPlusTreeNode<T>();
  }

  @Override public IBPlusTreeNode lessThan() {
    return new NullBPlusTreeNode<T>();
  }

  @Override public BTreeValue predecessor() {
    return BTreeValue.nullValue();
  }

  @Override public BTreeValue successor() {
    return BTreeValue.nullValue();
  }
}
