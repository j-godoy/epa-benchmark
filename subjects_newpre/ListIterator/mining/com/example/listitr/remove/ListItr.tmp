package com.example.listitr.remove;

//https://bugs.openjdk.java.net/browse/JDK-4308549

import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;


public class ListItr implements ListIterator {
    private LinkedList.Entry lastReturned;
    private LinkedList.Entry next;
    private int nextIndex;
    private int expectedModCount;
    private LinkedList _linkedlist;

    @EpaAction(name = "ListItr")
    public ListItr(LinkedList linkedist, int index) {
        try {
            ctorListItr(linkedist, index);
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    private void ctorListItr(LinkedList linkedist, int index) {
        if (index < 0 || index > linkedist.size())
            throw new IndexOutOfBoundsException("Index: " + index +
                    ", Size: " + linkedist.size());
        this._linkedlist = linkedist;
        if (index < linkedist.size() / 2) {
            next = linkedist.getHeader().next;
            for (nextIndex = 0; nextIndex < index; nextIndex++)
                next = next.next;
        } else {
            next = linkedist.getHeader();
            for (nextIndex = linkedist.size(); nextIndex > index; nextIndex--)
                next = next.previous;
        }
        lastReturned = linkedist.getHeader();
        expectedModCount = linkedist.getModCount();
    }

    @EpaAction(name = "hasNext")
    public boolean hasNext() {
        return nextIndex != _linkedlist.size();
    }

    @EpaAction(name = "next")
    public Object next() {
        checkForComodification();
        if (nextIndex == _linkedlist.size())
            throw new NoSuchElementException();

        lastReturned = next;
        next = next.next;
        nextIndex++;
        return lastReturned.element;
    }

    @EpaAction(name = "hasPrevious")
    public boolean hasPrevious() {
        return nextIndex != 0;
    }

    @EpaAction(name = "previous")
    public Object previous() {
        if (nextIndex == 0)
            throw new NoSuchElementException();

        lastReturned = next = next.previous;
        nextIndex--;
        checkForComodification();
        return lastReturned.element;
    }

    @EpaAction(name = "nextIndex")
    public int nextIndex() {
        return nextIndex;
    }

    @EpaAction(name = "previousIndex")
    public int previousIndex() {
        return nextIndex - 1;
    }

    @EpaAction(name = "remove")
    public void remove() {
        checkForComodification(); // FIX
        _linkedlist.remove((LinkedList.Entry) lastReturned);
        if (next == lastReturned)
            next = lastReturned.next;
        else
            nextIndex--;
        lastReturned = _linkedlist.getHeader();
        expectedModCount++;
    }

    @EpaAction(name = "set")
    public void set(Object o) {
        if (lastReturned == _linkedlist.getHeader())
            throw new IllegalStateException();
        checkForComodification();
        lastReturned.element = o;
    }

    @EpaAction(name = "add")
    public void add(Object o) {
        checkForComodification();
        lastReturned = _linkedlist.getHeader();
        _linkedlist.addBefore(o, next);
        nextIndex++;
        expectedModCount++;
    }

    private final void checkForComodification() {
        if (_linkedlist.getModCount() != expectedModCount)
            throw new ConcurrentModificationException();
    }

    /*-------------------------------------------------------
     * EPA Precondition Methods
     */

    @EpaActionPrecondition(name = "hasNext")
    private boolean isHasNextEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "next")
    private boolean isNextEnabled() {
        return this._linkedlist.getModCount() == expectedModCount && nextIndex != this._linkedlist.size() && next != null;
    }

    @EpaActionPrecondition(name = "hasPrevious")
    private boolean isHasPreviousEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "previous")
    private boolean isPreviousEnabled() {
        return nextIndex != 0 && next != null && this._linkedlist.getModCount() == expectedModCount && next.previous != null;
    }

    @EpaActionPrecondition(name = "nextIndex")
    private boolean isNextIndexEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "previousIndex")
    private boolean isPreviousIndexEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "remove")
    private boolean isRemoveEnabled() {
        return this._linkedlist.getModCount() == expectedModCount && lastReturned != _linkedlist.getHeader() && lastReturned != null;
    }

    @EpaActionPrecondition(name = "set")
    private boolean isSetEnabled() {
        return lastReturned != _linkedlist.getHeader() && this._linkedlist.getModCount() == expectedModCount && lastReturned != null;
    }

    @EpaActionPrecondition(name = "add")
    private boolean isAddEnabled() {
        return this._linkedlist.getModCount() == expectedModCount && next != null;
    }

    /*public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("hola");
        linkedList.add("chau");
        System.out.println(linkedList.size());
        ListIterator iter = new ListItr(linkedList, 0);
        iter.next();
        linkedList.add("chauchau");
        iter.remove(); // this must throw a ConcurrentModificationException
        System.out.println(linkedList.size());
    }*/
}