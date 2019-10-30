/*
 * This file was automatically generated by EvoSuite
 * Sat Aug 11 00:00:45 GMT 2018
 */

package com.example.listitr;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.listitr.ListItr;
import com.example.listitr.MyArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Test_Find_Mutants_Line_Branch {

  @Test(timeout = 1300)
  public void test00()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      listItr0.remove();
      assertFalse(listItr0.hasNext());
  }

  @Test(timeout = 1300)
  public void test01()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      // Undeclared exception!
      try {
        listItr0.next();
        fail("Expecting exception: NoSuchElementException");

      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test02()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      boolean boolean0 = listItr0.hasPrevious();
      assertEquals(0, listItr0.previousIndex());
      assertTrue(boolean0);
  }

  @Test(timeout = 1300)
  public void test03()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = null;
      try {
        listItr0 = new ListItr(myArrayList0, 2295);
        fail("Expecting exception: IndexOutOfBoundsException");

      } catch(IndexOutOfBoundsException e) {
         //
         // Index: 2295
         //
      }
  }

  @Test(timeout = 1300)
  public void test04()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = null;
      try {
        listItr0 = new ListItr(myArrayList0, (-2607));
        fail("Expecting exception: IndexOutOfBoundsException");

      } catch(IndexOutOfBoundsException e) {
         //
         // Index: -2607
         //
      }
  }

  @Test(timeout = 1300)
  public void test05()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      // Undeclared exception!
      try {
        listItr0.remove();
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test06()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      boolean boolean0 = listItr0.hasPrevious();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test07()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      myArrayList0.add((Object) null);
      myArrayList0.sort((Comparator<? super Object>) null);
      assertEquals(1, myArrayList0.getModCount());
  }

  @Test(timeout = 1300)
  public void test08()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      int int0 = listItr0.previousIndex();
      assertEquals((-1), int0);
  }

  @Test(timeout = 1300)
  public void test09()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList(0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      int int0 = listItr0.nextIndex();
      assertEquals(0, int0);
  }

  @Test(timeout = 1300)
  public void test10()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      boolean boolean0 = listItr0.hasNext();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test11()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      // Undeclared exception!
      try {
        listItr0.set(myArrayList0);
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test12()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      // Undeclared exception!
      try {
        listItr0.previous();
        fail("Expecting exception: NoSuchElementException");

      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test13()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.add(myArrayList0);
      assertEquals(0, listItr0.previousIndex());
      assertFalse(listItr0.hasNext());

      listItr0.previous();
      boolean boolean0 = listItr0.hasNext();
      assertTrue(boolean0);
  }
}