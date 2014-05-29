package com.mojo.bplustreej;

public class IndexPartitionBuilder {
  private final IBPlusTreeNode originalNode;
  private final BTreeValue partitioningValue;

  public IndexPartitionBuilder(IBPlusTreeNode originalNode,
                               BTreeValue partitioningValue) {

    this.originalNode = originalNode;
    this.partitioningValue = partitioningValue;
  }

  public IndexPartition build() {
    if (originalNode.values().size() == 0) {
      return new EmptyIndexPartition(originalNode, partitioningValue);
    }
    return new NonEmptyIndexPartition(originalNode, partitioningValue);
  }
}