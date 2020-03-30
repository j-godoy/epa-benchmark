package com.example.observablelist;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;

//from: http://hg.openjdk.java.net/openjfx/10-dev/rt/rev/572a70fabb47

// Iterator to traverse the list of selected indices in both directions.
public class SelectionListIterator<E> implements ListIterator<E> {
    private int pos;
    private final List<E> list;

    @EpaAction(name="SelectionListIterator(List)")
    public SelectionListIterator(List<E> list) {
        this(list, 0);
    }

    @EpaAction(name="SelectionListIterator(List, int)")
    public SelectionListIterator(List<E> list, int pos) {
        this.list = list;
        this.pos = pos;
    }

    @Override
    @EpaAction(name="hasNext")
    public boolean hasNext() {
        return pos < list.size();
    }

    @Override
    @EpaAction(name="next")
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list.get(pos++);
    }

    @Override
    @EpaAction(name="hasPrevious")
    public boolean hasPrevious() {
        return pos > 0;
    }

    @Override
    @EpaAction(name="previous")
    public E previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return list.get(--pos);
    }

    @Override
    @EpaAction(name="nextIndex")
    public int nextIndex() {
        return pos;
    }

    @Override
    @EpaAction(name="previousIndex")
    public int previousIndex() {
        return pos - 1;
    }

    @Override
    @EpaAction(name="remove")
    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    @EpaAction(name="set")
    public void set(E e) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    @EpaAction(name="add")
    public void add(E e) {
        throw new UnsupportedOperationException("Not supported.");
    }

    /*
    EPA Methods Preconditions
     */

    @EpaActionPrecondition(name = "hasNext")
    private boolean ishasNextEnabled() {
        return list != null;
    }

    @EpaActionPrecondition(name = "next")
    private boolean isnextEnabled() {
        return hasNext();
    }

    @EpaActionPrecondition(name = "hasPrevious")
    private boolean ishasPreviousEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "previous")
    private boolean ispreviousEnabled() {
        return hasPrevious();
    }

    @EpaActionPrecondition(name = "nextIndex")
    private boolean isnextIndexEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "previousIndex")
    private boolean ispreviousIndexEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "remove")
    private boolean isremoveEnabled() {
        return false;
    }

    @EpaActionPrecondition(name = "set")
    private boolean issetEnabled() {
        return false;
    }

    @EpaActionPrecondition(name = "add")
    private boolean isaddEnabled() {
        return false;
    }
}
