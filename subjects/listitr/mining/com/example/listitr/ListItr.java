package com.example.listitr;

import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;

/**
 * An optimized version of AbstractList.ListItr
 */
public class ListItr implements ListIterator<Object> {

	/**
	 * 
	 */
	private MyArrayList arrayList;
	private int cursor; // index of next element to return
	private int lastRet; // index of last element returned; -1 if no such
	private int expectedModCount;

	@EpaAction(name = "ListItr")
	public ListItr(MyArrayList arrayList, int index) {
		try {
			ctorListItr0(arrayList, index);
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private void ctorListItr0(MyArrayList arrayList, int index) {
		if (index < 0 || index > arrayList.size())
			throw new IndexOutOfBoundsException("Index: " + index);

		this.arrayList = new MyArrayList(arrayList);
		this.lastRet = -1;
		this.expectedModCount = this.arrayList.getModCount();
		this.cursor = index;
	}

	@EpaAction(name = "hasPrevious")
	public boolean hasPrevious() {
		try {
			return hasPrevious0();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private boolean hasPrevious0() {
		final boolean b = cursor != 0;
		return b;
	}

	@EpaAction(name = "nextIndex")
	public int nextIndex() {
		try {
			return nextIndex0();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private int nextIndex0() {
		return cursor;
	}

	@EpaAction(name = "previousIndex")
	public int previousIndex() {
		try {
			return previousIndex0();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private int previousIndex0() {
		final int i = cursor - 1;
		return i;
	}

	@EpaAction(name = "hasNext")
	public boolean hasNext() {
		try {
			return hasNext0();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private boolean hasNext0() {
		boolean superHasNext = super_hasNext();
		return superHasNext;
	}

	@EpaAction(name = "next")
	public Object next() {
		try {
			return next0();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private Object next0() {
		final Object superNext = super_next();
		return superNext;
	}

	@EpaAction(name = "remove")
	public void remove() {
		try {
			remove0();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	@EpaAction(name = "previous")
	public Object previous() {
		try {
			return previous0();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private Object previous0() {
		checkForComodification();
		int i = cursor - 1;
		if (i < 0)
			throw new NoSuchElementException();
		Object[] elementData = arrayList.getElementData();
		if (i >= elementData.length)
			throw new ConcurrentModificationException();
		cursor = i;

		Object object = (Object) elementData[lastRet = i];
		return object;
	}

	@EpaAction(name = "set")
	public void set(Object e) {
		try {
			set0(e);
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private void set0(Object e) {
		if (lastRet < 0)
			throw new IllegalStateException();
		checkForComodification();

		try {
			arrayList.set(lastRet, e);
		} catch (IndexOutOfBoundsException ex) {
			throw new ConcurrentModificationException();
		}
	}

	@EpaAction(name = "add")
	public void add(Object e) {
		try {
			add0(e);
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	private void add0(Object e) {
		checkForComodification();

		try {
			int i = cursor;
			arrayList.add(i, e);
			cursor = i + 1;
			lastRet = -1;
			expectedModCount = this.arrayList.getModCount();
		} catch (IndexOutOfBoundsException ex) {
			throw new ConcurrentModificationException();
		}
	}

	private boolean super_hasNext() {
		final boolean b = cursor != this.arrayList.size();
		return b;
	}

	private Object super_next() {
		checkForComodification();
		int i = cursor;
		if (i >= this.arrayList.size())
			throw new NoSuchElementException();
		Object[] elementData = arrayList.getElementData();
		if (i >= elementData.length)
			throw new ConcurrentModificationException();
		cursor = i + 1;
		final Object o = (Object) elementData[lastRet = i];
		return o;
	}

	private void remove0() {
		if (lastRet < 0)
			throw new IllegalStateException();
		checkForComodification();

		try {
			arrayList.remove(lastRet);
			cursor = lastRet;
			lastRet = -1;
			expectedModCount = this.arrayList.getModCount();
		} catch (IndexOutOfBoundsException ex) {
			throw new ConcurrentModificationException();
		}
	}

	private final void checkForComodification() {
		if (this.arrayList.getModCount() != expectedModCount)
			throw new ConcurrentModificationException();
	}

	/*-------------------------------------------------------
	 * EPA Precondition Methods
	 */

	@EpaActionPrecondition(name = "hasPrevious")
	private boolean isHasPreviousEnabled() {
		return true;
	}

	@EpaActionPrecondition(name = "nextIndex")
	private boolean isNextIndexEnabled() {
		return cursor < this.arrayList.size();
	}

	@EpaActionPrecondition(name = "previousIndex")
	private boolean isPreviousIndexEnabled() {
		return cursor - 1 >= 0;
	}

	@EpaActionPrecondition(name = "hasNext")
	private boolean isHasNextEnabled() {
		return true;
	}

	@EpaActionPrecondition(name = "next")
	private boolean isNextEnabled() {
		return cursor < this.arrayList.size();
	}

	@EpaActionPrecondition(name = "remove")
	private boolean isRemoveEnabled() {
		return lastRet >= 0;
	}

	@EpaActionPrecondition(name = "previous")
	private boolean isPreviousEnabled() {
		return cursor - 1 >= 0;
	}

	@EpaActionPrecondition(name = "set")
	private boolean isSetEnabled() {
		return lastRet >= 0;
	}

	@EpaActionPrecondition(name = "add")
	private boolean isAddEnabled() {
		// add is always enabled
		return true;
	}

}
