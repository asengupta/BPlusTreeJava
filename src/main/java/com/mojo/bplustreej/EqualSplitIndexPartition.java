package com.mojo.bplustreej;

import java.util.List;

public class EqualSplitIndexPartition extends NonEmptyIndexPartition {

  public EqualSplitIndexPartition(IBPlusTreeNode originalNode) {
    super(originalNode.getOrder()/2, originalNode.getOrder()/2 + 1, originalNode);
  }

  public BTreeValue getMiddleValue() {
    List<BTreeValue> values = originalNode.values();
    return values.get(values.size()/2);
  }
}
