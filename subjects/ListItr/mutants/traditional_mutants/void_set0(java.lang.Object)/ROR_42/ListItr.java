// This is a mutant program.
// Author : ysma

package com.example.listitr;


import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class ListItr implements java.util.ListIterator<Object>
{

    private com.example.listitr.MyArrayList arrayList;

    private int cursor;

    private int lastRet;

    private int expectedModCount;

    public ListItr( com.example.listitr.MyArrayList arrayList, int index )
    {
        try {
            ctorListItr0( arrayList, index );
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  void ctorListItr0( com.example.listitr.MyArrayList arrayList, int index )
    {
        if (index < 0 || index > arrayList.size()) {
            throw new java.lang.IndexOutOfBoundsException( "Index: " + index );
        }
        this.arrayList = new com.example.listitr.MyArrayList( arrayList );
        this.lastRet = -1;
        this.expectedModCount = this.arrayList.getModCount();
        this.cursor = index;
    }

    public  boolean hasPrevious()
    {
        try {
            return hasPrevious0();
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  boolean hasPrevious0()
    {
        final boolean b = cursor != 0;
        return b;
    }

    public  int nextIndex()
    {
        try {
            return nextIndex0();
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  int nextIndex0()
    {
        return cursor;
    }

    public  int previousIndex()
    {
        try {
            return previousIndex0();
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  int previousIndex0()
    {
        final int i = cursor - 1;
        return i;
    }

    public  boolean hasNext()
    {
        try {
            return hasNext0();
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  boolean hasNext0()
    {
        boolean superHasNext = super_hasNext();
        return superHasNext;
    }

    public  java.lang.Object next()
    {
        try {
            return next0();
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  java.lang.Object next0()
    {
        final java.lang.Object superNext = super_next();
        return superNext;
    }

    public  void remove()
    {
        try {
            remove0();
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    public  java.lang.Object previous()
    {
        try {
            return previous0();
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  java.lang.Object previous0()
    {
        checkForComodification();
        int i = cursor - 1;
        if (i < 0) {
            throw new java.util.NoSuchElementException();
        }
        java.lang.Object[] elementData = arrayList.getElementData();
        if (i >= elementData.length) {
            throw new java.util.ConcurrentModificationException();
        }
        cursor = i;
        java.lang.Object object = (java.lang.Object) elementData[lastRet = i];
        return object;
    }

    public  void set( java.lang.Object e )
    {
        try {
            set0( e );
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  void set0( java.lang.Object e )
    {
        if (false) {
            throw new java.lang.IllegalStateException();
        }
        checkForComodification();
        try {
            arrayList.set( lastRet, e );
        } catch ( java.lang.IndexOutOfBoundsException ex ) {
            throw new java.util.ConcurrentModificationException();
        }
    }

    public  void add( java.lang.Object e )
    {
        try {
            add0( e );
        } catch ( java.lang.RuntimeException ex ) {
            throw ex;
        }
    }

    private  void add0( java.lang.Object e )
    {
        checkForComodification();
        try {
            int i = cursor;
            arrayList.add( i, e );
            cursor = i + 1;
            lastRet = -1;
            expectedModCount = this.arrayList.getModCount();
        } catch ( java.lang.IndexOutOfBoundsException ex ) {
            throw new java.util.ConcurrentModificationException();
        }
    }

    private  boolean super_hasNext()
    {
        final boolean b = cursor != this.arrayList.size();
        return b;
    }

    private  java.lang.Object super_next()
    {
        checkForComodification();
        int i = cursor;
        if (i >= this.arrayList.size()) {
            throw new java.util.NoSuchElementException();
        }
        java.lang.Object[] elementData = arrayList.getElementData();
        if (i >= elementData.length) {
            throw new java.util.ConcurrentModificationException();
        }
        cursor = i + 1;
        final java.lang.Object o = (java.lang.Object) elementData[lastRet = i];
        return o;
    }

    private  void remove0()
    {
        if (lastRet < 0) {
            throw new java.lang.IllegalStateException();
        }
        checkForComodification();
        try {
            arrayList.remove( lastRet );
            cursor = lastRet;
            lastRet = -1;
            expectedModCount = this.arrayList.getModCount();
        } catch ( java.lang.IndexOutOfBoundsException ex ) {
            throw new java.util.ConcurrentModificationException();
        }
    }

    private final  void checkForComodification()
    {
        if (this.arrayList.getModCount() != expectedModCount) {
            throw new java.util.ConcurrentModificationException();
        }
    }

}
