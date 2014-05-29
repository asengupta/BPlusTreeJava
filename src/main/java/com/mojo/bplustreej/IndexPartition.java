package com.mojo.bplustreej;

public interface IndexPartition<T extends Comparable> {
  IBPlusTreeNode greaterThan();
  IBPlusTreeNode lessThan();
  BTreeValue predecessor();
  BTreeValue successor();
}
