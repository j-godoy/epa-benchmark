package com.example.hashset.jdk6529795;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class HashIterator<E> implements Iterator<E> {
    private HashMap.Entry<?,?> next;	// next entry to return
    private  int expectedModCount;	// For fast-fail
    private int index;		// current slot
    private  HashMap. Entry<?,?> current;	// current entry
    private HashMap<?,?> internalMap;


    HashIterator(HashMap<?,?> map) {
        try {
            ctorHashIter(map);
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    private void ctorHashIter(HashMap<?,?> map)
    {
        this.internalMap = map;
        expectedModCount = internalMap.getModCount();
        if (internalMap.size() > 0) { // advance to first entry
            HashMap.Entry<?,?>[] t = internalMap.getTable();
            while (index < t.length && (next = t[index++]) == null)
                ;
        }
    }


    public final boolean hasNext() {
        return next != null;
    }

    @Override

    public E next() {
        throw new UnsupportedOperationException();
    }


    public final HashMap.Entry<?,?> nextEntry() {
        if (internalMap.getModCount() != expectedModCount)
            throw new ConcurrentModificationException();
        HashMap.Entry<?,?> e = next;
        //current = e; // BUGGY
        if (e == null)
            throw new NoSuchElementException();

        if ((next = e.next) == null) {
            HashMap.Entry<?,?>[] t = internalMap.getTable();
            while (index < t.length && (next = t[index++]) == null)
                ;
        }
        current = e; //FIX
        return e;
    }


    public void remove() {
        if (current == null)
            throw new IllegalStateException();
        if (internalMap.getModCount() != expectedModCount)
            throw new ConcurrentModificationException();
        Object k = current.key;
        current = null;
        internalMap.removeEntryForKey(k);
        expectedModCount = internalMap.getModCount();
    }

}
