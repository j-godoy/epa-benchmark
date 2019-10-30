/*
 * This file was automatically generated by EvoSuite
 * Mon Apr 09 19:33:22 GMT 2018
 */

package com.example.listitr;

import static org.evosuite.runtime.EvoAssertions.verifyException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class TestListItr  {

  @Test
  public void test00()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      int int0 = listItr0.previousIndex();
      assertEquals((-1), int0);
  }

  @Test
  public void test01()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList(0);
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      int int0 = listItr0.nextIndex();
      assertEquals(0, int0);
  }

  @Test
  public void test02()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Integer integer0 = new Integer((-15));
      myArrayList0.add((Object) integer0);
      Object object0 = new Object();
      myArrayList0.add(object0);
      myArrayList0.add((Object) integer0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      listItr0.previous();
      listItr0.remove();
      listItr0.next();
      assertEquals(1, listItr0.nextIndex());
  }

  @Test
  public void test03()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList(0);
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      boolean boolean0 = listItr0.hasPrevious();
      assertFalse(boolean0);
  }

  @Test
  public void test04()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      boolean boolean0 = listItr0.hasNext();
      assertTrue(boolean0);
  }

  @Test
  public void test05()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      listItr0.previous();
      listItr0.next();
      listItr0.add((Object) null);
      listItr0.previous();
      listItr0.set((Object) null);
      assertEquals(0, listItr0.previousIndex());
  }

  @Test
  public void test06()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      listItr0.previous();
      listItr0.next();
      listItr0.add((Object) null);
      listItr0.previous();
      listItr0.remove();
      assertTrue(listItr0.hasPrevious());
  }

  @Test
  public void test07()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      Object object0 = new Object();
      listItr0.add(object0);
      listItr0.add(object0);
      listItr0.previous();
      int int0 = listItr0.previousIndex();
      assertEquals(1, listItr0.nextIndex());
      assertEquals(0, int0);
  }

  @Test
  public void test08()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      Object object0 = new Object();
      listItr0.add(object0);
      listItr0.add(object0);
      listItr0.previous();
      int int0 = listItr0.nextIndex();
      assertTrue(listItr0.hasPrevious());
      assertEquals(1, int0);
  }

  @Test
  public void test09()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      Object object0 = new Object();
      listItr0.add(object0);
      listItr0.add(object0);
      listItr0.add(myArrayList0);
      listItr0.previous();
      listItr0.previous();
      listItr0.next();
      assertEquals(2, listItr0.nextIndex());
  }

  @Test
  public void test10()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      Object object0 = new Object();
      listItr0.add(object0);
      listItr0.add(object0);
      listItr0.previous();
      boolean boolean0 = listItr0.hasPrevious();
      assertEquals(0, listItr0.previousIndex());
      assertTrue(boolean0);
  }

  @Test
  public void test11()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      Object object0 = new Object();
      listItr0.add(object0);
      listItr0.add(object0);
      listItr0.previous();
      boolean boolean0 = listItr0.hasNext();
      assertEquals(0, listItr0.previousIndex());
      assertTrue(boolean0);
  }

  @Test
  public void test12()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      assertEquals(-1, listItr0.previousIndex());
      
      listItr0.next();
      int int0 = listItr0.previousIndex();
      assertEquals(0, int0);
  }

  @Test
  public void test13()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      Object object0 = new Object();
      listItr0.add(object0);
      listItr0.add(object0);
      listItr0.previous();
      listItr0.previous();
      listItr0.next();
      listItr0.next();
      listItr0.previous();
      assertEquals(0, listItr0.previousIndex());
  }

  @Test
  public void test14()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      int int0 = listItr0.nextIndex();
      assertEquals(1, int0);
  }

  @Test
  public void test15()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      listItr0.previous();
      listItr0.set(linkedList0);
      assertFalse(listItr0.hasPrevious());
  }

  @Test
  public void test16()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      Object object1 = listItr0.previous();
      listItr0.add(object1);
      listItr0.previous();
      listItr0.remove();
      assertEquals(0, listItr0.nextIndex());
  }

  @Test
  public void test17()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      listItr0.previous();
      listItr0.remove();
      assertFalse(listItr0.hasNext());
  }

  @Test
  public void test18()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      listItr0.previous();
      int int0 = listItr0.previousIndex();
      assertEquals((-1), int0);
  }

  @Test
  public void test19()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      Object object1 = listItr0.previous();
      listItr0.add(object1);
      assertTrue(listItr0.hasPrevious());
      
      listItr0.previous();
      boolean boolean0 = listItr0.hasPrevious();
      assertFalse(boolean0);
  }

  @Test
  public void test20()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      myArrayList0.add((Object) "9e1h1,$)DN&s}F#K4");
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      int int0 = listItr0.previousIndex();
      assertEquals(0, int0);
  }

  @Test
  public void test21()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Integer integer0 = new Integer((-15));
      myArrayList0.add((Object) integer0);
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      listItr0.previous();
      listItr0.next();
      listItr0.remove();
      assertFalse(listItr0.hasPrevious());
  }

  @Test
  public void test22()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.add(listItr0);
      int int0 = listItr0.nextIndex();
      assertEquals(1, int0);
  }

  @Test
  public void test23()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      myArrayList0.add(object0);
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      listItr0.next();
      assertEquals(1, listItr0.previousIndex());
  }

  @Test
  public void test24()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.add(listItr0);
      listItr0.next();
      listItr0.remove();
      assertTrue(listItr0.hasPrevious());
  }

  @Test
  public void test25()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      Object object1 = listItr0.previous();
      assertFalse(listItr0.hasPrevious());
      
      listItr0.add(object1);
      boolean boolean0 = listItr0.hasPrevious();
      assertTrue(boolean0);
  }

  @Test
  public void test26()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      Object object0 = new Object();
      listItr0.add(object0);
      listItr0.add(object0);
      listItr0.add(myArrayList0);
      listItr0.previous();
      listItr0.previous();
      listItr0.remove();
      boolean boolean0 = listItr0.hasNext();
      assertTrue(listItr0.hasPrevious());
      assertTrue(boolean0);
  }

  @Test
  public void test27()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      Object object1 = listItr0.previous();
      listItr0.add(object1);
      listItr0.add(object0);
      assertTrue(listItr0.hasPrevious());
  }

  @Test
  public void test28()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      int int0 = listItr0.previousIndex();
      assertEquals(0, int0);
  }

  @Test
  public void test29()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      listItr0.previous();
      listItr0.next();
      listItr0.add((Object) null);
      Object object1 = listItr0.previous();
      listItr0.add(object1);
      listItr0.previous();
      assertEquals(0, listItr0.previousIndex());
  }

  @Test
  public void test30()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.add("t.b");
      int int0 = listItr0.nextIndex();
      assertEquals(1, int0);
  }

  @Test
  public void test31()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.add("t.b");
      boolean boolean0 = listItr0.hasNext();
      assertFalse(boolean0);
  }

  @Test
  public void test32()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      Object object0 = new Object();
      listItr0.add(object0);
      assertEquals(0, listItr0.previousIndex());
      
      listItr0.previous();
      boolean boolean0 = listItr0.hasNext();
      assertTrue(boolean0);
  }

  @Test
  public void test33()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      listItr0.remove();
      // Undeclared exception!
      try { 
        listItr0.set(listItr0);
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.example.listitr.ListItr", e);
      }
  }

  @Test
  public void test34()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      listItr0.set(linkedList0);
      assertEquals(1, listItr0.nextIndex());
  }

  @Test
  public void test35()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      // Undeclared exception!
      try { 
        listItr0.previous();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.example.listitr.ListItr", e);
      }
  }

  @Test
  public void test36()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      Object object0 = new Object();
      linkedList0.add(object0);
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      assertFalse(listItr0.hasNext());
      
      listItr0.previous();
      int int0 = listItr0.nextIndex();
      assertEquals(0, int0);
  }

  @Test
  public void test37()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      Object object0 = new Object();
      myArrayList0.add(object0);
      ListItr listItr0 = new ListItr(myArrayList0, 1);
      boolean boolean0 = listItr0.hasPrevious();
      assertTrue(boolean0);
      assertFalse(listItr0.hasNext());
  }

  @Test
  public void test38()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      boolean boolean0 = listItr0.hasPrevious();
      assertFalse(boolean0);
  }

  @Test
  public void test39()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = null;
      try {
        listItr0 = new ListItr(myArrayList0, 705);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index: 705
         //
         verifyException("com.example.listitr.ListItr", e);
      }
  }

  @Test
  public void test40()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = null;
      try {
        listItr0 = new ListItr(myArrayList0, (-1));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index: -1
         //
         verifyException("com.example.listitr.ListItr", e);
      }
  }

  @Test
  public void test41()  throws Throwable  {
      MyArrayList myArrayList0 = new MyArrayList();
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      boolean boolean0 = listItr0.hasNext();
      assertFalse(boolean0);
  }

  @Test
  public void test42()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      int int0 = listItr0.previousIndex();
      assertEquals((-1), int0);
  }

  @Test
  public void test43()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      // Undeclared exception!
      try { 
        listItr0.remove();
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.example.listitr.ListItr", e);
      }
  }

  @Test
  public void test44()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      // Undeclared exception!
      try { 
        listItr0.next();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.example.listitr.ListItr", e);
      }
  }

  @Test
  public void test45()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      MyArrayList myArrayList0 = new MyArrayList((Collection) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      int int0 = listItr0.nextIndex();
      assertEquals(0, int0);
  }

  @Test
  public void testS503_hasPrevious_S503()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      linkedList0.add(new Object());
      MyArrayList myArrayList0 = new MyArrayList((Collection<Object>) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
      listItr0.next();
      listItr0.hasPrevious();
  }




  @Test
  public void testS479_remove_S119()  throws Throwable  {
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      linkedList0.add(new Object());
      linkedList0.add(new Object());
      MyArrayList myArrayList0 = new MyArrayList((Collection<Object>) linkedList0);
      ListItr listItr0 = new ListItr(myArrayList0, 0);
//      listItr0.printState(); // 95
      listItr0.next();
//      listItr0.printState(); // 511
      listItr0.remove();
//      listItr0.printState(); // 95
      
      //assertTrue(listItr0.isS479());
      // next 		ENABLED
      // previous 	NOT_ENABLED
      // remove 	ENABLED
      
      //assertTrue(listItr0.isS119());
      // next 		NOT_ENABLED
      // previous 	NOT_ENABLED
      // remove 	ENABLED
      
  }

}