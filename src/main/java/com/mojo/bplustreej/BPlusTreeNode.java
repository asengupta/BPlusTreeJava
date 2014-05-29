package com.mojo.bplustreej;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BPlusTreeNode<T extends Comparable> implements IBPlusTreeNode<T> {
  private List<BTreeValue> values;
  private int order;

  public BPlusTreeNode(int order) {
    this.order = order;
    values = new ArrayList<>();
  }

  @Override public BTreeValue[] valuesAsArray() {
    return values.toArray(new BTreeValue[0]);
  }

  @Override public List<BTreeValue> values() {
    if (values == null) values = new ArrayList<>();
    return values;
  }

  @Override public BTreeValue addInternal(BTreeValue value) {
    if (value == null) return null;
    IndexPartition indexPartition = new IndexPartitionBuilder(this, value).build();
    BTreeValue predecessor = indexPartition.predecessor();
    BTreeValue successor = indexPartition.successor();
    values.add(value);
    Collections.sort(values);
    predecessor.setGreaterThan(value.getLessThan());
    successor.setLessThan(value.getGreaterThan());

    if (!hasOverflowed()) {
      return null;
    }

    EqualSplitIndexPartition equalSplitIndexPartition = new EqualSplitIndexPartition(this);
    IBPlusTreeNode lessThanNode = equalSplitIndexPartition.lessThan();
    IBPlusTreeNode greaterThanNode = equalSplitIndexPartition.greaterThan();
    BTreeValue middleValue = equalSplitIndexPartition.getMiddleValue();
    BTreeValue newRoot = middleValue.copy();
    newRoot.setLessThan(lessThanNode);
    newRoot.setGreaterThan(greaterThanNode);
    return newRoot;
  }

  @Override public IBPlusTreeNode<T> find(BTreeValue b) {
    if (values.indexOf(b) != -1) return this;
    IndexPartition<T> partition = new IndexPartitionBuilder(this, b).build();
    BTreeValue predecessor = partition.predecessor();
    IBPlusTreeNode greaterThanSlotOfPredecessor = predecessor.getGreaterThan();
    return greaterThanSlotOfPredecessor.find(b);
  }

  @Override public BTreeValue add(BTreeValue b) {
    IndexPartition<T> partition = new IndexPartitionBuilder(this, b).build();
    BTreeValue predecessor = partition.predecessor();
    IBPlusTreeNode greaterThanSlotOfPredecessor = predecessor.getGreaterThan();
    if (greaterThanSlotOfPredecessor instanceof NullBPlusTreeNode) {
      return addInternal(b);
    }
    BTreeValue possibleKey = greaterThanSlotOfPredecessor.add(b);
    return addInternal(possibleKey);
  }

  @Override public int getOrder() {
    return order;
  }

  @Override public boolean hasOverflowed() {
    return values().size() > order;
  }
}