package com.example.observablelist;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;



//from: http://hg.openjdk.java.net/openjfx/10-dev/rt/rev/572a70fabb47

// Iterator to traverse the list of selected indices in both directions.
public class SelectionListIterator<E> implements ListIterator<E> {
    private int pos;
    private final List<E> list;


    public SelectionListIterator(List<E> list) {
        this(list, 0);
    }


    public SelectionListIterator(List<E> list, int pos) {
        this.list = list;
        this.pos = pos;
    }

    @Override

    public boolean hasNext() {
        return pos < list.size();
    }

    @Override

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list.get(pos++);
    }

    @Override

    public boolean hasPrevious() {
        return pos > 0;
    }

    @Override

    public E previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return list.get(--pos);
    }

    @Override

    public int nextIndex() {
        return pos;
    }

    @Override

    public int previousIndex() {
        return pos - 1;
    }

    @Override

    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override

    public void set(E e) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override

    public void add(E e) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
