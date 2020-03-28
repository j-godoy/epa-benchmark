/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.filterlistiteration;

// version commons 3.2.1 (https://archive.apache.org/dist/commons/collections/source/commons-collections-3.2.1-src.zip)
//https://archive.apache.org/dist/commons/collections/source/
//https://issues.apache.org/jira/browse/COLLECTIONS-360

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;


/** 
 * Decorates another {@link ListIterator} using a predicate to filter elements.
 * <p>
 * This iterator decorates the underlying iterator, only allowing through
 * those elements that match the specified {@link Predicate Predicate}.
 *
 * @since Commons Collections 2.0
 * @version $Revision: 646777 $ $Date: 2008-04-10 13:33:15 +0100 (Thu, 10 Apr 2008) $
 * 
 * @author Rodney Waldhoff
 */
public class FilterListIterator implements ListIterator {

    /** The iterator being used */
    private ListIterator iterator;
    
    /** The predicate being used */
    private Predicate predicate;

    /** 
     * The value of the next (matching) object, when 
     * {@link #nextObjectSet} is true. 
     */
    private Object nextObject;

    /** 
     * Whether or not the {@link #nextObject} has been set
     * (possibly to <code>null</code>). 
     */
    private boolean nextObjectSet = false;   

    /** 
     * The value of the previous (matching) object, when 
     * {@link #previousObjectSet} is true. 
     */
    private Object previousObject;

    /** 
     * Whether or not the {@link #previousObject} has been set
     * (possibly to <code>null</code>). 
     */
    private boolean previousObjectSet = false;   

    /** 
     * The index of the element that would be returned by {@link #next}.
     */
    private int nextIndex = 0;
    
    //-----------------------------------------------------------------------
    /**
     * Constructs a new <code>FilterListIterator</code> that will not function
     * until {@link #setListIterator(ListIterator) setListIterator}
     * and {@link #setPredicate(Predicate) setPredicate} are invoked.
     */
    @EpaAction(name = "FilterListIterator")
    public FilterListIterator() {
        super();
    }

    /**
     * Constructs a new <code>FilterListIterator</code> that will not 
     * function until {@link #setPredicate(Predicate) setPredicate} is invoked.
     *
     * @param iterator  the iterator to use
     */
    @EpaAction(name = "FilterListIterator(ListIterator)")
    public FilterListIterator(ListIterator iterator ) {
        super();
        this.iterator = iterator;
    }

    /**
     * Constructs a new <code>FilterListIterator</code>.
     *
     * @param iterator  the iterator to use
     * @param predicate  the predicate to use
     */
    @EpaAction(name = "FilterListIterator(ListIterator,Predicate)")
    public FilterListIterator(ListIterator iterator, Predicate predicate) {
        super();
        this.iterator = iterator;
        this.predicate = predicate;
    }

    /**
     * Constructs a new <code>FilterListIterator</code> that will not function
     * until {@link #setListIterator(ListIterator) setListIterator} is invoked.
     *
     * @param predicate  the predicate to use.
     */
    @EpaAction(name = "FilterListIterator(Predicate)")
    public FilterListIterator(Predicate predicate) {
        super();
        this.predicate = predicate;
    }

    //-----------------------------------------------------------------------
    /** Not supported. */
    @EpaAction(name = "add")
    public void add(Object o) {
        throw new UnsupportedOperationException("FilterListIterator.add(Object) is not supported.");
    }

    @EpaAction(name = "hasNext")
    public boolean hasNext() {
        if(nextObjectSet) {
            return true;
        } else {
            return setNextObject();
        }
    }

    @EpaAction(name = "hasPrevious")
    public boolean hasPrevious() {
        if(previousObjectSet) {
            return true;
        } else {
            return setPreviousObject();
        }
    }

    @EpaAction(name = "next")
    public Object next() {
        if(!nextObjectSet) {
            if(!setNextObject()) {
                throw new NoSuchElementException();
            }
        }
        nextIndex++;
        Object temp = nextObject;
        clearNextObject();
        return temp;
    }

    @EpaAction(name = "nextIndex")
    public int nextIndex() {
        return nextIndex;
    }

    @EpaAction(name = "previous")
    public Object previous() {
        if(!previousObjectSet) {
            if(!setPreviousObject()) {
                throw new NoSuchElementException();
            }
        }
        nextIndex--;
        Object temp = previousObject;
        clearPreviousObject();
        return temp;
    }

    @EpaAction(name = "previousIndex")
    public int previousIndex() {
        return (nextIndex-1);
    }

    /** Not supported. */
    @EpaAction(name = "remove")
    public void remove() {
        throw new UnsupportedOperationException("FilterListIterator.remove() is not supported.");
    }

    /** Not supported. */
    @EpaAction(name = "set")
    public void set(Object o) {
        throw new UnsupportedOperationException("FilterListIterator.set(Object) is not supported.");
    }

    //-----------------------------------------------------------------------
    /** 
     * Gets the iterator this iterator is using.
     * 
     * @return the iterator.
     */
    @EpaAction(name = "getListIterator")
    public ListIterator getListIterator() {
        return iterator;
    }

    /** 
     * Sets the iterator for this iterator to use.
     * If iteration has started, this effectively resets the iterator.
     * 
     * @param iterator  the iterator to use
     */
    @EpaAction(name = "setListIterator")
    public void setListIterator(ListIterator iterator) {
        this.iterator = iterator;
    }

    //-----------------------------------------------------------------------
    /** 
     * Gets the predicate this iterator is using.
     * 
     * @return the predicate.
     */
    @EpaAction(name = "getPredicate")
    public Predicate getPredicate() {
        return predicate;
    }

    /** 
     * Sets the predicate this the iterator to use.
     * 
     * @param predicate  the transformer to use
     */
    @EpaAction(name = "setPredicate")
    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    //-----------------------------------------------------------------------
    private void clearNextObject() {
        nextObject = null;
        nextObjectSet = false;
    }

    //BUGGY
    /*private boolean setNextObject() {
        // if previousObjectSet,
        // then we've walked back one step in the 
        // underlying list (due to a hasPrevious() call)
        // so skip ahead one matching object
        if(previousObjectSet) {
            clearPreviousObject();
            if(!setNextObject()) {
                return false;
            } else {
                clearNextObject();
            }
        }

        while(iterator.hasNext()) {
            Object object = iterator.next();
            if(predicate.evaluate(object)) {
                nextObject = object;
                nextObjectSet = true;
                return true;
            }
        }
        return false;
    }*/

    //FIX
    private boolean setNextObject() {
        if(iterator != null) {

            // if previousObjectSet,
            // then we've walked back one step in the
            // underlying list (due to a hasPrevious() call)
            // so skip ahead one matching object
            if(previousObjectSet) {
                clearPreviousObject();
                if(!setNextObject()) {
                    return false;
                } else {
                    clearNextObject();
                }
            }
            while(iterator.hasNext()) {
                Object object = iterator.next();
                if(predicate.evaluate(object)) {
                    nextObject = object;
                    nextObjectSet = true;
                    return true;
                }
            }
            return false;
        }
        else
            return false;
    }

    private void clearPreviousObject() {
        previousObject = null;
        previousObjectSet = false;
    }

    //BUGGY
    /*private boolean setPreviousObject() {
        // if nextObjectSet,
        // then we've walked back one step in the 
        // underlying list (due to a hasNext() call)
        // so skip ahead one matching object
        if(nextObjectSet) {
            clearNextObject();
            if(!setPreviousObject()) {
                return false;
            } else {
                clearPreviousObject();
            }
        }

        while(iterator.hasPrevious()) {
            Object object = iterator.previous();
            if(predicate.evaluate(object)) {
                previousObject = object;
                previousObjectSet = true;
                return true;
            }
        }
        return false;
    }*/

    //FIX
    private boolean setPreviousObject() {
        if (iterator != null) {

            // if nextObjectSet,
            // then we've walked back one step in the
            // underlying list (due to a hasNext() call)
            // so skip ahead one matching object
            if(nextObjectSet) {

                clearNextObject();
                if(!setPreviousObject()) {
                    return false;
                } else {
                    clearPreviousObject();
                }
            }
            while(iterator.hasPrevious()) {
                Object object = iterator.previous();
                if(predicate.evaluate(object)) {
                    previousObject = object;
                    previousObjectSet = true;
                    return true;
                }
            }
            return false;
        } else
            return false;
    }

    /*-------------------------------------------------------
     * EPA Precondition Methods
     */

    @EpaActionPrecondition(name = "add")
    private boolean isaddEnabled() {
        return false;
    }

    @EpaActionPrecondition(name = "hasNext")
    private boolean ishasNextEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "hasPrevious")
    private boolean ishasPreviousEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "next")
    private boolean isnextEnabled() {
        return nextObjectSet || setNextObject();
    }

    @EpaActionPrecondition(name = "nextIndex")
    private boolean isnextIndexEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "previous")
    private boolean ispreviousEnabled() {
        return previousObjectSet || setPreviousObject();
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

    @EpaActionPrecondition(name = "getListIterator")
    private boolean isgetListIteratorEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "setListIterator")
    private boolean issetListIteratorEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "getPredicate")
    private boolean isgetPredicateEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "setPredicate")
    private boolean issetPredicateEnabled() {
        return true;
    }

    /*public static void main(String[] args) {
        java.lang.Integer var6 = new java.lang.Integer(0);
        List<String> var7 = new ArrayList<>();
        Predicate var9 = PredicateUtils.anyPredicate((java.util.Collection)var7);
        FilterListIterator var13 = new FilterListIterator(var9);
        //this line throws exception!
        var13.hasNext();
    }*/

}
