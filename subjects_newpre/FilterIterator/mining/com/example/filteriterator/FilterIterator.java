/*
 *  Copyright 1999-2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.filteriterator;

// fix in version commons 3.2. Bug in 3.1
// Bug in https://issues.apache.org/jira/browse/COLLECTIONS-61

import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;

import java.util.*;

/**
 * Decorates an iterator such that only elements matching a predicate filter
 * are returned.
 *
 * @author James Strachan
 * @author Jan Sorensen
 * @author Ralph Wagner
 * @author Stephen Colebourne
 * @version $Revision: 1.8 $ $Date: 2004/02/18 00:59:50 $
 * @since Commons Collections 1.0
 */
public class FilterIterator implements Iterator {

    /**
     * The iterator being used
     */
    private Iterator iterator;
    /**
     * The predicate being used
     */
    private Predicate predicate;
    /**
     * The next object in the iteration
     */
    private Object nextObject;
    /**
     * Whether the next object has been calculated yet
     */
    private boolean nextObjectSet = false;

    //-----------------------------------------------------------------------

    /**
     * Constructs a new <code>FilterIterator</code> that will not function
     * until {@link #setIterator(Iterator) setIterator} is invoked.
     */
    @EpaAction(name = "FilterIterator")
    public FilterIterator() {
        super();
    }

    /**
     * Constructs a new <code>FilterIterator</code> that will not function
     * until {@link #setPredicate(Predicate) setPredicate} is invoked.
     *
     * @param iterator the iterator to use
     */
    @EpaAction(name = "FilterIterator(Iterator)")
    public FilterIterator(Iterator iterator) {
        super();
        this.iterator = iterator;
    }

    /**
     * Constructs a new <code>FilterIterator</code> that will use the
     * given iterator and predicate.
     *
     * @param iterator  the iterator to use
     * @param predicate the predicate to use
     */
    @EpaAction(name = "FilterIterator(Iterator,Predicate)")
    public FilterIterator(Iterator iterator, Predicate predicate) {
        super();
        this.iterator = iterator;
        this.predicate = predicate;
    }

    //-----------------------------------------------------------------------

    /**
     * Returns true if the underlying iterator contains an object that
     * matches the predicate.
     *
     * @return true if there is another object that matches the predicate
     */
    @EpaAction(name = "hasNext")
    public boolean hasNext() {
        if (nextObjectSet) {
            return true;
        } else {
            return setNextObject();
        }
    }

    /**
     * Returns the next object that matches the predicate.
     *
     * @return the next object which matches the given predicate
     * @throws NoSuchElementException if there are no more elements that
     *                                match the predicate
     */
    @EpaAction(name = "next")
    public Object next() {
        if (!nextObjectSet) {
            if (!setNextObject()) {
                throw new NoSuchElementException();
            }
        }
        nextObjectSet = false;
        return nextObject;
    }

    /**
     * Removes from the underlying collection of the base iterator the last
     * element returned by this iterator.
     * This method can only be called
     * if <code>next()</code> was called, but not after
     * <code>hasNext()</code>, because the <code>hasNext()</code> call
     * changes the base iterator.
     *
     * @throws IllegalStateException if <code>hasNext()</code> has already
     *                               been called.
     */
    @EpaAction(name = "remove")
    public void remove() {
        if (nextObjectSet) {
            throw new IllegalStateException("remove() cannot be called");
        }
        iterator.remove();
    }

    //-----------------------------------------------------------------------

    /**
     * Gets the iterator this iterator is using.
     *
     * @return the iterator.
     */
    @EpaAction(name = "getIterator")
    public Iterator getIterator() {
        return iterator;
    }

    /**
     * Sets the iterator for this iterator to use.
     * If iteration has started, this effectively resets the iterator.
     *
     * @param iterator the iterator to use
     */
    @EpaAction(name = "setIterator")
    public void setIterator(Iterator iterator) {
        this.iterator = iterator;
        nextObject = null; // FIX
        nextObjectSet = false; // FIX
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
     * @param predicate the transformer to use
     */
    @EpaAction(name = "setPredicate")
    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
        nextObject = null; // FIX
        nextObjectSet = false; //FIX
    }

    //-----------------------------------------------------------------------

    /**
     * Set nextObject to the next object. If there are no more
     * objects then return false. Otherwise, return true.
     */
    private boolean setNextObject() {
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (predicate.evaluate(object)) {
                nextObject = object;
                nextObjectSet = true;
                return true;
            }
        }
        return false;
    }


    /*-------------------------------------------------------
     * EPA Precondition Methods
     */

    @EpaActionPrecondition(name = "hasNext")
    private boolean ishasNextEnabled() {
        return nextObjectSet || iterator != null;
    }

    @EpaActionPrecondition(name = "next")
    private boolean isnextEnabled() {
        return nextObjectSet || iterator != null;
    }

    @EpaActionPrecondition(name = "remove")
    private boolean isremoveEnabled() {
        return !nextObjectSet && iterator != null;
    }

    @EpaActionPrecondition(name = "getIterator")
    private boolean isgetIteratorEnabled() {
        return true;
    }

    @EpaActionPrecondition(name = "setIterator")
    private boolean issetIteratorEnabled() {
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
        Iterator iter1 = Collections.singleton(new Object()).iterator();
        Iterator iter2 = Collections.EMPTY_LIST.iterator();
        FilterIterator filterIterator = new FilterIterator(iter1);
        filterIterator.setPredicate(TruePredicate.getInstance());
        assertTrue("filterIterator should have an element", filterIterator.hasNext());
        filterIterator.setIterator(iter2);
        assertFalse("filterIterator should not have an element", filterIterator.hasNext());

//        Iterator iter = Collections.singleton(null).iterator();
//        Iterator iter = Arrays.asList(1).iterator();
//        FilterIterator filterIterator = new FilterIterator(iter);
//        filterIterator.setPredicate(TruePredicate.getInstance());
//        assertTrue("filterIterator should have an element", filterIterator.hasNext());
//        filterIterator.setPredicate(NotNullPredicate.getInstance());
//        assertTrue("filterIterator should not have an element", !filterIterator.hasNext());
    }*/
}
