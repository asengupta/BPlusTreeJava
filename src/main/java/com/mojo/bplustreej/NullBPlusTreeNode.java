package com.mojo.bplustreej;

import java.util.ArrayList;
import java.util.List;

public class NullBPlusTreeNode<T extends Comparable> implements IBPlusTreeNode<T> {
  public NullBPlusTreeNode() {

  }

  @Override public BTreeValue[] valuesAsArray() {
    return new BTreeValue[0];
  }

  @Override public List<BTreeValue> values() {
    return new ArrayList<>();
  }

  @Override public BTreeValue addInternal(BTreeValue value) {
    return null;
  }

  @Override public IBPlusTreeNode<T> find(BTreeValue b) {
    return this;
  }

  @Override public BTreeValue add(BTreeValue b) {
    return null;
  }

  @Override public int getOrder() {
    return 0;
  }

  @Override public boolean hasOverflowed() {
    return false;
  }
}
