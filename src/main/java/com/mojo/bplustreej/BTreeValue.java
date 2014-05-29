package com.mojo.bplustreej;

public class BTreeValue<T extends Comparable<T>> implements Comparable<BTreeValue> {
  private T v;
  private IBPlusTreeNode lessThan;
  private IBPlusTreeNode greaterThan;

  public IBPlusTreeNode getGreaterThan() {
    return greaterThan;
  }

  public IBPlusTreeNode getLessThan() {
    return lessThan;
  }

  public static BTreeValue nullValue() {
    BTreeValue bTreeValue = new BTreeValue(null);
    bTreeValue.setLessThan(new NullBPlusTreeNode());
    bTreeValue.setGreaterThan(new NullBPlusTreeNode());
    return bTreeValue;
  }

  public BTreeValue(T v) {
    this.v = v;
    setLessThan(new NullBPlusTreeNode());
    setGreaterThan(new NullBPlusTreeNode());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BTreeValue that = (BTreeValue) o;

    if (v != that.v) return false;

    return true;
  }

  @Override public String toString() {
    return v.toString();
  }

  @Override public int compareTo(BTreeValue other) {
    if (this.equals(other)) return 0;
    return this.v.compareTo((T) other.v);
  }

  public void setLessThan(IBPlusTreeNode lessThan) {
    this.lessThan = lessThan;
  }

  public void setGreaterThan(IBPlusTreeNode greaterThan) {
    this.greaterThan = greaterThan;
  }

  public IBPlusTreeNode lesser() {
    return lessThan;
  }

  public IBPlusTreeNode greater() {
    return greaterThan;
  }

  public BTreeValue copy() {
    return new BTreeValue(v);
  }
}
