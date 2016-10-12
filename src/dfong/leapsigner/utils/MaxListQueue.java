package dfong.leapsigner.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.EvictingQueue;
import com.leapmotion.leap.Hand;
import com.sun.jmx.remote.internal.ArrayQueue;

public class MaxListQueue<E> implements List<E> {

  final int capacity;
  final List<E> list;
  
  public MaxListQueue(int capacity) {
    // TODO Auto-generated constructor stub
    this.capacity = capacity;
    this.list = new ArrayList<E>(capacity);
    
    
    List<Hand> hands = new ArrayQueue<Hand>(100);
    
    EvictingQueue<Hand> eq = EvictingQueue.create(100);
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return list.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return list.toArray(a);
  }

  @Override
  public boolean add(E e) {
    if (list.size() > capacity) {
      list.remove(capacity);
    }
    return list.add(e);
    //return false;
  }

  @Override
  public boolean remove(Object o) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public E get(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public E set(int index, E element) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void add(int index, E element) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public E remove(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int indexOf(Object o) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ListIterator<E> listIterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    // TODO Auto-generated method stub
    return null;
  }

}
