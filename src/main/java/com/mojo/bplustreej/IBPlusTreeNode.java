package com.mojo.bplustreej;

import java.util.List;

public interface IBPlusTreeNode<T extends Comparable> {
  BTreeValue[] valuesAsArray();
  List<BTreeValue> values();
  BTreeValue addInternal(BTreeValue value);
  IBPlusTreeNode<T> find(BTreeValue b);
  BTreeValue add(BTreeValue b);
  int getOrder();
  boolean hasOverflowed();
}
