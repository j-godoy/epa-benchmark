/*
 * This file was automatically generated by EvoSuite
 * Sat Aug 11 00:16:36 GMT 2018
 */

package com.example.listitr;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.listitr.ListItr;
import com.example.listitr.MyArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Test_Find_Mutants_default {

  @Test(timeout = 1300)
  public void test00()  throws Throwable  {
      ListItr listItr0 = null;
      try {
        listItr0 = new ListItr((MyArrayList) null, 0);
        fail("Expecting exception: NullPointerException");

      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test01()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Integer integer0 = new Integer(0);
      myArrayList0.add((Object) integer0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      listItr0.remove();
      assertFalse(listItr0.hasPrevious());
  }

  @Test(timeout = 1300)
  public void test02()  throws Throwable  {
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
  public void test03()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      MyArrayList myArrayList0 = new MyArrayList();
      myArrayList0.add((Object) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      // Undeclared exception!
      try {
        listItr0.set(linkedList0);
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test04()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Integer integer0 = new Integer(0);
      myArrayList0.add((Object) integer0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      assertTrue(listItr0.hasPrevious());

      listItr0.previous();//<-
      boolean boolean0 = listItr0.hasNext();
      assertTrue(boolean0);
  }

  @Test(timeout = 1300)
  public void test05()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      MyArrayList myArrayList0 = new MyArrayList();
      myArrayList0.add((Object) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      boolean boolean0 = listItr0.hasPrevious();
      assertTrue(boolean0);
      assertEquals(0, listItr0.previousIndex());
  }

  @Test(timeout = 1300)
  public void test06()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = null;
      try {
        listItr0 = new ListItr(myArrayList0, 1851);
        fail("Expecting exception: IndexOutOfBoundsException");

      } catch(IndexOutOfBoundsException e) {
         //
         // Index: 1851
         //
      }
  }

  @Test(timeout = 1300)
  public void test07()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = null;
      try {
        listItr0 = new ListItr(myArrayList0, (-1));
        fail("Expecting exception: IndexOutOfBoundsException");

      } catch(IndexOutOfBoundsException e) {
         //
         // Index: -1
         //
      }
  }

  @Test(timeout = 1300)
  public void test08()  throws Throwable  {
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
  public void test09()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      boolean boolean0 = listItr0.hasPrevious();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test10()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      int int0 = listItr0.previousIndex();
      assertEquals((-1), int0);
  }

  @Test(timeout = 1300)
  public void test11()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList(4523);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      int int0 = listItr0.nextIndex();
      assertEquals(0, int0);
  }

  @Test(timeout = 1300)
  public void test12()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      boolean boolean0 = listItr0.hasNext();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test13()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      myArrayList0.add((Object) null);
      myArrayList0.sort((Comparator<? super Object>) null);
      assertEquals(1, myArrayList0.size());
  }

  @Test(timeout = 1300)
  public void test14()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList(4523);
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
  public void test15()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Integer integer0 = new Integer(0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.add(integer0);
      assertEquals(0, listItr0.previousIndex());
      assertFalse(listItr0.hasNext());
  }
}