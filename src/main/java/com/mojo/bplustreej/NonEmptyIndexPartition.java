package com.mojo.bplustreej;

import java.util.List;

public class NonEmptyIndexPartition<T extends Comparable> implements IndexPartition<T> {

  private int lesserIndex;
  private int greaterIndex;
  protected final IBPlusTreeNode<T> originalNode;

  protected NonEmptyIndexPartition(int lesserIndex,
                                int greaterIndex,
                                IBPlusTreeNode<T> originalNode) {
    this.lesserIndex = lesserIndex;
    this.greaterIndex = greaterIndex;
    this.originalNode = originalNode;
  }

  public NonEmptyIndexPartition(IBPlusTreeNode<T> originalNode, BTreeValue value) {
    this.originalNode = originalNode;
    int i = 0;
    List<BTreeValue> values = originalNode.values();
    for (; i < values.size(); ++i) {
      if (values.get(i).compareTo(value) == 1) {
        lesserIndex = i - 1;
        greaterIndex = i;
        break;
      }
    }
    if (lesserIndex == 0 && greaterIndex == 0) {
      lesserIndex = originalNode.values().size() - 1;
      greaterIndex = lesserIndex + 1;
    }
  }

  @Override public IBPlusTreeNode greaterThan() {
    BPlusTreeNode<Comparable> greaterThanNode = new BPlusTreeNode<>(originalNode.getOrder());
    greaterThanNode.values().addAll(
        fromTo(originalNode.values(), greaterIndex, originalNode.values().size() - 1));
    return greaterThanNode;
  }

  @Override public IBPlusTreeNode lessThan() {
    BPlusTreeNode<Comparable> lessThanNode = new BPlusTreeNode<>(originalNode.getOrder());
    lessThanNode.values().addAll(fromTo(originalNode.values(), 0, lesserIndex));
    return lessThanNode;
  }

  private List<BTreeValue> fromTo(List<BTreeValue> values, int from, int to) {
    return values.subList(from, to + 1);
  }

  @Override public BTreeValue predecessor() {
    if (lesserIndex < 0 || lesserIndex >= originalNode.values().size()) {
      BTreeValue nullValue = BTreeValue.nullValue();
      nullValue.setGreaterThan(originalNode.values().get(lesserIndex + 1).getLessThan());
      return nullValue;
    }
    return originalNode.values().get(lesserIndex);
  }

  @Override public BTreeValue successor() {
    if (greaterIndex < 0 || greaterIndex >= originalNode.values().size()) {
      BTreeValue nullValue = BTreeValue.nullValue();
      nullValue.setLessThan(originalNode.values().get(greaterIndex - 1).getGreaterThan());
      return nullValue;
    }
    return originalNode.values().get(greaterIndex);
  }
}