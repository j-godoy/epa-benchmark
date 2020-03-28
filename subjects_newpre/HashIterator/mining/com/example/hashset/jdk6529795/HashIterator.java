package com.example.hashset.jdk6529795;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;

public class HashIterator<E> implements Iterator<E> {
    private HashMap.Entry<?,?> next;	// next entry to return
    private  int expectedModCount;	// For fast-fail
    private int index;		// current slot
    private  HashMap. Entry<?,?> current;	// current entry
    private HashMap<?,?> internalMap;

    @EpaAction(name = "HashIterator(HashMap)")
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

    @EpaAction(name = "hasNext")
    public final boolean hasNext() {
        return next != null;
    }

    @Override
    @EpaAction(name = "next")
    public E next() {
        throw new UnsupportedOperationException();
    }

    @EpaAction(name = "nextEntry")
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

    @EpaAction(name = "remove")
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


    /*-------------------------------------------------------
     * EPA Precondition Methods
     */

    @EpaActionPrecondition(name = "hasNext")
    private boolean ishasNextEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "next")
    private boolean isnextEnabled() {
        return false;
    }

    @EpaActionPrecondition(name = "nextEntry")
    private boolean isnextEntryEnabled() {
        return internalMap.getModCount() == expectedModCount && next != null;
    }

    @EpaActionPrecondition(name = "remove")
    private boolean isremoveEnabled() {
        return current != null && internalMap.getModCount() == expectedModCount;
    }

}
