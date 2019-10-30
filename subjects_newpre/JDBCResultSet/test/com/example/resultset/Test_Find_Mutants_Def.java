/*
 * This file was automatically generated by EvoSuite
 * Thu Aug 16 15:49:49 GMT 2018
 */

package com.example.resultset;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.resultset.JDBCResultSet;

public class Test_Find_Mutants_Def {

  @Test(timeout = 1300)
  public void test00()  throws Throwable  {
      int[] intArray0 = new int[11];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.first();
      int int0 = jDBCResultSet0.getInt("columnlabel0");
      assertEquals(0, int0);
  }

  @Test(timeout = 1300)
  public void test01()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.first();
      jDBCResultSet0.deleteRow();
      assertEquals(2, JDBCResultSet.CLOSE_CURSORS_AT_COMMIT);
  }

  @Test(timeout = 1300)
  public void test02()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.wasNull();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test03()  throws Throwable  {
      int[] intArray0 = new int[0];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      try {
        jDBCResultSet0.updateInt("", 0);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test04()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      try {
        jDBCResultSet0.updateInt(2772, 2772);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test05()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.refreshRow();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test06()  throws Throwable  {
      int[] intArray0 = new int[4];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.next();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test07()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.moveToCurrentRow();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test08()  throws Throwable  {
      int[] intArray0 = new int[0];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.isLast();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test09()  throws Throwable  {
      int[] intArray0 = new int[0];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.isFirst();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test10()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.isBeforeFirst();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test11()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.isAfterLast();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test12()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.getRow();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test13()  throws Throwable  {
      int[] intArray0 = new int[6];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.cancelRowUpdates();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test14()  throws Throwable  {
      try {
        JDBCResultSet.buildNewJDBCResultSet((int[]) null);
        fail("Expecting exception: NullPointerException");

      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test15()  throws Throwable  {
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewMJDBCResultSet(4, 4);
      assertEquals(1007, JDBCResultSet.CONCUR_READ_ONLY);
  }

  @Test(timeout = 1300)
  public void test16()  throws Throwable  {
      try {
        JDBCResultSet.buildNewMJDBCResultSet(5, (-1286));
        fail("Expecting exception: IllegalArgumentException");

      } catch(IllegalArgumentException e) {
         //
         // Invalid row number
         //
      }
  }

  @Test(timeout = 1300)
  public void test17()  throws Throwable  {
      try {
        JDBCResultSet.buildNewMJDBCResultSet(27, 27);
        fail("Expecting exception: IllegalArgumentException");

      } catch(IllegalArgumentException e) {
         //
         // Too many columns
         //
      }
  }

  @Test(timeout = 1300)
  public void test18()  throws Throwable  {
      try {
        JDBCResultSet.buildNewMJDBCResultSet((-1455), (-1455));
        fail("Expecting exception: IllegalArgumentException");

      } catch(IllegalArgumentException e) {
         //
         // column labels has to be greater than zero
         //
      }
  }

  @Test(timeout = 1300)
  public void test19()  throws Throwable  {
      int[] intArray0 = new int[2];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.last();
      jDBCResultSet0.updateInt("columnLabel0", (-1137));
      jDBCResultSet0.updateRow();
      assertEquals(1007, JDBCResultSet.CONCUR_READ_ONLY);
  }

  @Test(timeout = 1300)
  public void test20()  throws Throwable  {
      int[] intArray0 = new int[3];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.first();
      try {
        jDBCResultSet0.getInt(696);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test21()  throws Throwable  {
      int[] intArray0 = new int[11];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.first();
      try {
        jDBCResultSet0.getInt(0);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test22()  throws Throwable  {
      int[] intArray0 = new int[2];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.moveToInsertRow();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test23()  throws Throwable  {
      int[] intArray0 = new int[10];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.afterLast();
      try {
        jDBCResultSet0.deleteRow();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test24()  throws Throwable  {
      int[] intArray0 = new int[0];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      try {
        jDBCResultSet0.updateRow();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test25()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnLabel0", 3024);
      try {
        jDBCResultSet0.previous();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test26()  throws Throwable  {
      int[] intArray0 = new int[0];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      try {
        jDBCResultSet0.previous();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test27()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnlabel0", 8);
      try {
        jDBCResultSet0.relative(0);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test28()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      try {
        jDBCResultSet0.relative(6);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test29()  throws Throwable  {
      int[] intArray0 = new int[6];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.absolute(0);
      assertTrue(boolean0);
  }

  @Test(timeout = 1300)
  public void test30()  throws Throwable  {
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewMJDBCResultSet(1, 1);
      boolean boolean0 = jDBCResultSet0.absolute((-4586));
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test31()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnLabel0", (-2071));
      try {
        jDBCResultSet0.absolute((-2071));
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test32()  throws Throwable  {
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewMJDBCResultSet(1, 1);
      jDBCResultSet0.moveToInsertRow();
      try {
        jDBCResultSet0.absolute(0);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test33()  throws Throwable  {
      int[] intArray0 = new int[10];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.afterLast();
      int int0 = jDBCResultSet0.getRow();
      assertEquals(0, int0);
  }

  @Test(timeout = 1300)
  public void test34()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnlabel0", 0);
      try {
        jDBCResultSet0.last();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test35()  throws Throwable  {
      int[] intArray0 = new int[9];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      try {
        jDBCResultSet0.last();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test36()  throws Throwable  {
      int[] intArray0 = new int[2];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnLabel0", (-1137));
      try {
        jDBCResultSet0.first();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test37()  throws Throwable  {
      int[] intArray0 = new int[4];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      try {
        jDBCResultSet0.first();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test38()  throws Throwable  {
      int[] intArray0 = new int[3];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      try {
        jDBCResultSet0.afterLast();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test39()  throws Throwable  {
      int[] intArray0 = new int[10];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnlabel0", 0);
      try {
        jDBCResultSet0.beforeFirst();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test40()  throws Throwable  {
      int[] intArray0 = new int[4];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      try {
        jDBCResultSet0.beforeFirst();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test41()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      boolean boolean0 = jDBCResultSet0.isLast();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test42()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      boolean boolean0 = jDBCResultSet0.isAfterLast();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test43()  throws Throwable  {
      int[] intArray0 = new int[38];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      boolean boolean0 = jDBCResultSet0.isBeforeFirst();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test44()  throws Throwable  {
      int[] intArray0 = new int[9];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("table.columnlabel0", 20);
      try {
        jDBCResultSet0.afterLast();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test45()  throws Throwable  {
      int[] intArray0 = new int[6];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      try {
        jDBCResultSet0.getInt("BCa?./t {_ynkluuvt");
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test46()  throws Throwable  {
      int[] intArray0 = new int[11];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnlabel0", 1026);
      try {
        jDBCResultSet0.getInt("columnlabel0");
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test47()  throws Throwable  {
      int[] intArray0 = new int[2];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnLabel0", (-1137));
      jDBCResultSet0.updateInt("schema.table.columnlabel0", (-1137));
      assertEquals(1004, JDBCResultSet.TYPE_SCROLL_INSENSITIVE);
  }

  @Test(timeout = 1300)
  public void test48()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      try {
        jDBCResultSet0.getInt((String) null);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test49()  throws Throwable  {
      int[] intArray0 = new int[3];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      jDBCResultSet0.close();
      assertEquals(1, JDBCResultSet.HOLD_CURSORS_OVER_COMMIT);
  }

  @Test(timeout = 1300)
  public void test50()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.isFirst();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test51()  throws Throwable  {
      int[] intArray0 = new int[4];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.cancelRowUpdates();
      assertEquals(1008, JDBCResultSet.CONCUR_UPDATABLE);
  }

  @Test(timeout = 1300)
  public void test52()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      int int0 = jDBCResultSet0.getRow();
      assertEquals(0, int0);
  }

  @Test(timeout = 1300)
  public void test53()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.previous();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test54()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.relative(0);
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test55()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.absolute(1005);
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test56()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.isBeforeFirst();
      assertTrue(boolean0);
  }

  @Test(timeout = 1300)
  public void test57()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      try {
        jDBCResultSet0.insertRow();
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test58()  throws Throwable  {
      int[] intArray0 = new int[0];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.beforeFirst();
      assertEquals(1, JDBCResultSet.HOLD_CURSORS_OVER_COMMIT);
  }

  @Test(timeout = 1300)
  public void test59()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.next();
      assertTrue(boolean0);
  }

  @Test(timeout = 1300)
  public void test60()  throws Throwable  {
      int[] intArray0 = new int[2];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.last();
      jDBCResultSet0.updateRow();
      assertEquals(1, JDBCResultSet.HOLD_CURSORS_OVER_COMMIT);
  }

  @Test(timeout = 1300)
  public void test61()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.wasNull();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test62()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.updateInt("columnlabel0", 1691);
      jDBCResultSet0.insertRow();
      assertEquals(1001, JDBCResultSet.FETCH_REVERSE);
  }

  @Test(timeout = 1300)
  public void test63()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.close();
      try {
        jDBCResultSet0.getInt(8);
        fail("Expecting exception: Exception");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test64()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.first();
      int int0 = jDBCResultSet0.getInt(1);
      assertEquals(0, int0);
  }

  @Test(timeout = 1300)
  public void test65()  throws Throwable  {
      int[] intArray0 = new int[7];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.refreshRow();
      assertEquals(1005, JDBCResultSet.TYPE_SCROLL_SENSITIVE);
  }

  @Test(timeout = 1300)
  public void test66()  throws Throwable  {
      int[] intArray0 = new int[2];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.isAfterLast();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test67()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      boolean boolean0 = jDBCResultSet0.isLast();
      assertFalse(boolean0);
  }

  @Test(timeout = 1300)
  public void test68()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToCurrentRow();
      assertEquals(1004, JDBCResultSet.TYPE_SCROLL_INSENSITIVE);
  }

  @Test(timeout = 1300)
  public void test69()  throws Throwable  {
      int[] intArray0 = new int[1];
      JDBCResultSet jDBCResultSet0 = JDBCResultSet.buildNewJDBCResultSet(intArray0);
      jDBCResultSet0.moveToInsertRow();
      boolean boolean0 = jDBCResultSet0.isFirst();
      assertFalse(boolean0);
  }
}