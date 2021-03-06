package com.eomcs.lms.util;

import java.util.Arrays;

public class ArrayList<E> {
  final int DEFAULT_CAPACITY = 10;
  private Object[] list;
  private int size = 0;
  
  public ArrayList() {
    list = new Object[DEFAULT_CAPACITY];
  }
  
  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY) 
      list = new Object[initialCapacity];
    else
      list = new Object[DEFAULT_CAPACITY];
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if(arr.length < size)
      return (E[])Arrays.copyOf(list, size, arr.getClass());
    
    System.arraycopy(list, 0, arr, 0, size);
    
    if(arr.length > size)
      arr[size] = null;
    
    return arr;
  }
  
  public void add(E item) {
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }
    
    list[size++] = item;
  }
  
  public int size() {
    return this.size;
  }
}
