package com.example.stackar;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
        try {
            com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type java.lang.NegativeArraySizeException; message: null");
        } catch (java.lang.NegativeArraySizeException e) {
        }
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        try {
            com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((-1));
            org.junit.Assert.fail("Expected exception of type java.lang.NegativeArraySizeException; message: null");
        } catch (java.lang.NegativeArraySizeException e) {
        }
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr5 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean6 = stackAr5.isFull();
        java.lang.Object obj7 = null;
        stackAr5.push(obj7);
        java.lang.Object obj9 = new java.lang.Object();
        stackAr5.push(obj9);
        stackAr1.push(obj9);
        java.lang.Class<?> wildcardClass12 = obj9.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(wildcardClass12);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        java.lang.Object obj4 = stackAr1.top();
        boolean boolean5 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr7 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean8 = stackAr7.isFull();
        java.lang.Object obj9 = null;
        stackAr7.push(obj9);
        java.lang.Object obj11 = new java.lang.Object();
        stackAr7.push(obj11);
        stackAr7.makeEmpty();
        stackAr1.push((java.lang.Object) stackAr7);
        stackAr1.makeEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        java.lang.Class<?> wildcardClass7 = obj5.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) (short) 10);
        java.lang.Object obj2 = stackAr1.top();
        org.junit.Assert.assertNull(obj2);
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr10.topAndPop();
        boolean boolean18 = stackAr10.isFull();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        java.lang.Object obj4 = stackAr1.top();
        boolean boolean5 = stackAr1.isFull();
        java.lang.Object obj6 = stackAr1.topAndPop();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNull(obj6);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        java.lang.Object obj4 = stackAr1.top();
        boolean boolean5 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr7 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean8 = stackAr7.isFull();
        java.lang.Object obj9 = null;
        stackAr7.push(obj9);
        java.lang.Object obj11 = new java.lang.Object();
        stackAr7.push(obj11);
        stackAr7.makeEmpty();
        stackAr1.push((java.lang.Object) stackAr7);
        boolean boolean15 = stackAr1.isEmpty();
        stackAr1.makeEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        boolean boolean4 = stackAr1.isFull();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test11");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr(0);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test12");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        java.lang.Object obj2 = stackAr1.top();
        boolean boolean3 = stackAr1.isFull();
        java.lang.Object obj4 = stackAr1.top();
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNull(obj4);
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test13");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        com.example.stackar.StackAr stackAr5 = new com.example.stackar.StackAr((int) (short) 10);
        java.lang.Object obj6 = stackAr5.topAndPop();
        stackAr1.push(obj6);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj6);
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test14");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = stackAr1.top();
        java.lang.Object obj6 = stackAr1.top();
        boolean boolean7 = stackAr1.isFull();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test15");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr5 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean6 = stackAr5.isFull();
        java.lang.Object obj7 = null;
        stackAr5.push(obj7);
        java.lang.Object obj9 = new java.lang.Object();
        stackAr5.push(obj9);
        stackAr1.push(obj9);
        stackAr1.makeEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test16");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr(100);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test17");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.topAndPop();
        boolean boolean4 = stackAr1.isFull();
        boolean boolean5 = stackAr1.isFull();
        java.lang.Object obj6 = stackAr1.top();
        java.lang.Object obj7 = stackAr1.top();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertNull(obj7);
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test18");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.topAndPop();
        boolean boolean4 = stackAr1.isFull();
        boolean boolean5 = stackAr1.isFull();
        java.lang.Object obj6 = stackAr1.top();
        java.lang.Class<?> wildcardClass7 = stackAr1.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test19");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr(10);
        java.lang.Class<?> wildcardClass2 = stackAr1.getClass();
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test20");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        boolean boolean5 = stackAr1.isFull();
        boolean boolean6 = stackAr1.isFull();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test21");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        java.lang.Object obj4 = stackAr1.top();
        boolean boolean5 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr7 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean8 = stackAr7.isFull();
        java.lang.Object obj9 = null;
        stackAr7.push(obj9);
        java.lang.Object obj11 = new java.lang.Object();
        stackAr7.push(obj11);
        stackAr7.makeEmpty();
        stackAr1.push((java.lang.Object) stackAr7);
        boolean boolean15 = stackAr1.isEmpty();
        boolean boolean16 = stackAr1.isEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test22");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        java.lang.Object obj2 = stackAr1.top();
        boolean boolean3 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr5 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean6 = stackAr5.isFull();
        java.lang.Object obj7 = null;
        stackAr5.push(obj7);
        java.lang.Object obj9 = new java.lang.Object();
        stackAr5.push(obj9);
        stackAr5.makeEmpty();
        java.lang.Object obj12 = stackAr5.topAndPop();
        stackAr1.push(obj12);
        com.example.stackar.StackAr stackAr15 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean16 = stackAr15.isFull();
        java.lang.Object obj17 = null;
        stackAr15.push(obj17);
        stackAr15.makeEmpty();
        stackAr1.push((java.lang.Object) stackAr15);
        java.lang.Class<?> wildcardClass21 = stackAr1.getClass();
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(obj12);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(wildcardClass21);
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test23");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr10.topAndPop();
        stackAr10.makeEmpty();
        java.lang.Object obj19 = stackAr10.top();
        stackAr10.push((java.lang.Object) 0.0d);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertNull(obj19);
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test24");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) ' ');
        java.lang.Object obj2 = stackAr1.top();
        boolean boolean3 = stackAr1.isEmpty();
        com.example.stackar.StackAr stackAr5 = new com.example.stackar.StackAr((int) '#');
        stackAr1.push((java.lang.Object) stackAr5);
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test25");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = stackAr1.top();
        java.lang.Object obj6 = stackAr1.top();
        boolean boolean7 = stackAr1.isEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test26");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) '#');
        boolean boolean2 = stackAr1.isFull();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test27");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        java.lang.Object obj2 = stackAr1.top();
        boolean boolean3 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr5 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean6 = stackAr5.isFull();
        java.lang.Object obj7 = null;
        stackAr5.push(obj7);
        java.lang.Object obj9 = new java.lang.Object();
        stackAr5.push(obj9);
        stackAr5.makeEmpty();
        java.lang.Object obj12 = stackAr5.topAndPop();
        stackAr1.push(obj12);
        boolean boolean14 = stackAr1.isEmpty();
        boolean boolean15 = stackAr1.isEmpty();
        boolean boolean16 = stackAr1.isFull();
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(obj12);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test28");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        stackAr1.makeEmpty();
        com.example.stackar.StackAr stackAr7 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean8 = stackAr7.isFull();
        java.lang.Object obj9 = null;
        stackAr7.push(obj9);
        java.lang.Object obj11 = stackAr7.top();
        java.lang.Object obj12 = stackAr7.topAndPop();
        boolean boolean13 = stackAr7.isEmpty();
        stackAr1.push((java.lang.Object) stackAr7);
        java.lang.Object obj15 = stackAr1.topAndPop();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNull(obj12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertNotNull(obj15);
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test29");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr10.topAndPop();
        java.lang.Object obj18 = stackAr10.top();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertNull(obj18);
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test30");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr4 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean5 = stackAr4.isFull();
        java.lang.Object obj6 = null;
        stackAr4.push(obj6);
        java.lang.Object obj8 = new java.lang.Object();
        stackAr4.push(obj8);
        stackAr1.push((java.lang.Object) stackAr4);
        stackAr4.makeEmpty();
        java.lang.Object obj12 = stackAr4.topAndPop();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNull(obj12);
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test31");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) '#');
        java.lang.Object obj2 = stackAr1.top();
        org.junit.Assert.assertNull(obj2);
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test32");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) '#');
        com.example.stackar.StackAr stackAr3 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean4 = stackAr3.isFull();
        java.lang.Object obj5 = stackAr3.top();
        java.lang.Object obj6 = stackAr3.top();
        boolean boolean7 = stackAr3.isFull();
        java.lang.Object obj8 = stackAr3.top();
        stackAr1.push(obj8);
        try {
            java.lang.Class<?> wildcardClass10 = obj8.getClass();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(obj8);
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test33");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Class<?> wildcardClass8 = stackAr1.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(wildcardClass8);
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test34");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = stackAr1.top();
        java.lang.Object obj6 = stackAr1.topAndPop();
        boolean boolean7 = stackAr1.isEmpty();
        boolean boolean8 = stackAr1.isFull();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test35");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        java.lang.Object obj4 = stackAr1.top();
        boolean boolean5 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr7 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean8 = stackAr7.isFull();
        java.lang.Object obj9 = null;
        stackAr7.push(obj9);
        java.lang.Object obj11 = new java.lang.Object();
        stackAr7.push(obj11);
        stackAr7.makeEmpty();
        stackAr1.push((java.lang.Object) stackAr7);
        boolean boolean15 = stackAr1.isEmpty();
        java.lang.Object obj16 = stackAr1.topAndPop();
        java.lang.Class<?> wildcardClass17 = stackAr1.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(obj16);
        org.junit.Assert.assertNotNull(wildcardClass17);
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test36");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        stackAr1.makeEmpty();
        com.example.stackar.StackAr stackAr7 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean8 = stackAr7.isFull();
        java.lang.Object obj9 = null;
        stackAr7.push(obj9);
        java.lang.Object obj11 = stackAr7.top();
        java.lang.Object obj12 = stackAr7.topAndPop();
        boolean boolean13 = stackAr7.isEmpty();
        stackAr1.push((java.lang.Object) stackAr7);
        boolean boolean15 = stackAr1.isEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(obj11);
        org.junit.Assert.assertNull(obj12);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test37");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr10.topAndPop();
        java.lang.Object obj18 = stackAr10.topAndPop();
        try {
            java.lang.Class<?> wildcardClass19 = obj18.getClass();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertNull(obj18);
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test38");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr1.top();
        java.lang.Object obj18 = stackAr1.top();
        stackAr1.makeEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNotNull(obj17);
        org.junit.Assert.assertNotNull(obj18);
    }

    @Test
    public void test39() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test39");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = stackAr1.top();
        java.lang.Object obj6 = stackAr1.topAndPop();
        boolean boolean7 = stackAr1.isEmpty();
        boolean boolean8 = stackAr1.isEmpty();
        stackAr1.makeEmpty();
        java.lang.Object obj10 = stackAr1.topAndPop();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertNull(obj10);
    }

    @Test
    public void test40() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test40");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        stackAr1.makeEmpty();
        java.lang.Class<?> wildcardClass6 = stackAr1.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test41() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test41");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        java.lang.Object obj4 = stackAr1.top();
        boolean boolean5 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr7 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean8 = stackAr7.isFull();
        java.lang.Object obj9 = null;
        stackAr7.push(obj9);
        java.lang.Object obj11 = new java.lang.Object();
        stackAr7.push(obj11);
        stackAr7.makeEmpty();
        stackAr1.push((java.lang.Object) stackAr7);
        boolean boolean15 = stackAr1.isEmpty();
        java.lang.Object obj16 = stackAr1.topAndPop();
        java.lang.Object obj17 = stackAr1.topAndPop();
        try {
            java.lang.Class<?> wildcardClass18 = obj17.getClass();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(obj16);
        org.junit.Assert.assertNull(obj17);
    }

    @Test
    public void test42() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test42");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.topAndPop();
        boolean boolean4 = stackAr1.isFull();
        stackAr1.push((java.lang.Object) ' ');
        boolean boolean7 = stackAr1.isEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test43() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test43");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr10.topAndPop();
        stackAr10.makeEmpty();
        java.lang.Object obj19 = stackAr10.top();
        boolean boolean20 = stackAr10.isEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertNull(obj19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
    }

    @Test
    public void test44() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test44");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = stackAr1.top();
        stackAr1.push((java.lang.Object) (-1.0f));
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj5);
    }

    @Test
    public void test45() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test45");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        boolean boolean5 = stackAr1.isFull();
        java.lang.Class<?> wildcardClass6 = stackAr1.getClass();
        stackAr1.makeEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test46() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test46");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr10.topAndPop();
        boolean boolean18 = stackAr10.isEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
    }

    @Test
    public void test47() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test47");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = stackAr1.top();
        java.lang.Object obj6 = stackAr1.topAndPop();
        boolean boolean7 = stackAr1.isEmpty();
        boolean boolean8 = stackAr1.isEmpty();
        java.lang.Object obj9 = stackAr1.topAndPop();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertNull(obj9);
    }

    @Test
    public void test48() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test48");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr4 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean5 = stackAr4.isFull();
        java.lang.Object obj6 = stackAr4.top();
        java.lang.Object obj7 = stackAr4.top();
        java.lang.Object obj8 = stackAr4.top();
        stackAr1.push((java.lang.Object) stackAr4);
        stackAr4.makeEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertNull(obj7);
        org.junit.Assert.assertNull(obj8);
    }

    @Test
    public void test49() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test49");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) ' ');
        java.lang.Object obj2 = stackAr1.top();
        java.lang.Object obj3 = stackAr1.topAndPop();
        boolean boolean4 = stackAr1.isFull();
        java.lang.Object obj5 = stackAr1.top();
        boolean boolean6 = stackAr1.isFull();
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test50() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test50");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr1.top();
        java.lang.Object obj18 = stackAr1.top();
        java.lang.Object obj19 = stackAr1.top();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNotNull(obj17);
        org.junit.Assert.assertNotNull(obj18);
        org.junit.Assert.assertNotNull(obj19);
    }

    @Test
    public void test51() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test51");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Class<?> wildcardClass17 = stackAr10.getClass();
        boolean boolean18 = stackAr10.isEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNotNull(wildcardClass17);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
    }

    @Test
    public void test52() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test52");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        boolean boolean5 = stackAr1.isFull();
        java.lang.Class<?> wildcardClass6 = stackAr1.getClass();
        java.lang.Class<?> wildcardClass7 = stackAr1.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(wildcardClass6);
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test53() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test53");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) ' ');
        java.lang.Object obj2 = stackAr1.top();
        java.lang.Object obj3 = stackAr1.topAndPop();
        boolean boolean4 = stackAr1.isFull();
        java.lang.Object obj5 = stackAr1.top();
        java.lang.Object obj6 = stackAr1.top();
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
    }

    @Test
    public void test54() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test54");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr1.top();
        java.lang.Class<?> wildcardClass18 = obj17.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNotNull(obj17);
        org.junit.Assert.assertNotNull(wildcardClass18);
    }

    @Test
    public void test55() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test55");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) ' ');
        java.lang.Object obj2 = stackAr1.top();
        java.lang.Object obj3 = stackAr1.topAndPop();
        java.lang.Object obj4 = stackAr1.top();
        boolean boolean5 = stackAr1.isEmpty();
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test56() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test56");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) (short) 1);
        com.example.stackar.StackAr stackAr3 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean4 = stackAr3.isFull();
        java.lang.Object obj5 = stackAr3.top();
        java.lang.Object obj6 = stackAr3.top();
        stackAr1.push((java.lang.Object) stackAr3);
        java.lang.Object obj8 = stackAr1.topAndPop();
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertNotNull(obj8);
    }

    @Test
    public void test57() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test57");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        java.lang.Object obj2 = stackAr1.top();
        boolean boolean3 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr5 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean6 = stackAr5.isFull();
        java.lang.Object obj7 = null;
        stackAr5.push(obj7);
        java.lang.Object obj9 = new java.lang.Object();
        stackAr5.push(obj9);
        stackAr5.makeEmpty();
        java.lang.Object obj12 = stackAr5.topAndPop();
        stackAr1.push(obj12);
        com.example.stackar.StackAr stackAr15 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean16 = stackAr15.isFull();
        java.lang.Object obj17 = null;
        stackAr15.push(obj17);
        stackAr15.makeEmpty();
        stackAr1.push((java.lang.Object) stackAr15);
        java.lang.Object obj21 = stackAr15.top();
        boolean boolean22 = stackAr15.isFull();
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(obj12);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNull(obj21);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
    }

    @Test
    public void test58() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test58");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) (short) 10);
        java.lang.Object obj2 = stackAr1.topAndPop();
        boolean boolean3 = stackAr1.isEmpty();
        org.junit.Assert.assertNull(obj2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
    }

    @Test
    public void test59() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test59");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = stackAr1.top();
        java.lang.Object obj4 = stackAr1.top();
        boolean boolean5 = stackAr1.isFull();
        com.example.stackar.StackAr stackAr7 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean8 = stackAr7.isFull();
        java.lang.Object obj9 = null;
        stackAr7.push(obj9);
        java.lang.Object obj11 = new java.lang.Object();
        stackAr7.push(obj11);
        stackAr7.makeEmpty();
        stackAr1.push((java.lang.Object) stackAr7);
        boolean boolean15 = stackAr1.isEmpty();
        java.lang.Object obj16 = stackAr1.top();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj3);
        org.junit.Assert.assertNull(obj4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(obj16);
    }

    @Test
    public void test60() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test60");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr10.topAndPop();
        stackAr10.makeEmpty();
        java.lang.Object obj19 = stackAr10.top();
        stackAr10.makeEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertNull(obj19);
    }

    @Test
    public void test61() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test61");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        stackAr1.makeEmpty();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test62() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test62");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = stackAr1.top();
        java.lang.Object obj6 = stackAr1.topAndPop();
        java.lang.Object obj7 = stackAr1.top();
        java.lang.Object obj8 = stackAr1.topAndPop();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj5);
        org.junit.Assert.assertNull(obj6);
        org.junit.Assert.assertNull(obj7);
        org.junit.Assert.assertNull(obj8);
    }

    @Test
    public void test63() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test63");
        com.example.stackar.StackAr stackAr1 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        java.lang.Object obj3 = null;
        stackAr1.push(obj3);
        java.lang.Object obj5 = new java.lang.Object();
        stackAr1.push(obj5);
        stackAr1.makeEmpty();
        java.lang.Object obj8 = stackAr1.topAndPop();
        com.example.stackar.StackAr stackAr10 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean11 = stackAr10.isFull();
        java.lang.Object obj12 = null;
        stackAr10.push(obj12);
        java.lang.Object obj14 = stackAr10.top();
        java.lang.Object obj15 = stackAr10.topAndPop();
        stackAr1.push((java.lang.Object) stackAr10);
        java.lang.Object obj17 = stackAr10.topAndPop();
        stackAr10.makeEmpty();
        java.lang.Object obj19 = stackAr10.top();
        java.lang.Object obj20 = stackAr10.topAndPop();
        com.example.stackar.StackAr stackAr22 = new com.example.stackar.StackAr((int) 'a');
        java.lang.Object obj23 = stackAr22.top();
        boolean boolean24 = stackAr22.isFull();
        com.example.stackar.StackAr stackAr26 = new com.example.stackar.StackAr((int) 'a');
        boolean boolean27 = stackAr26.isFull();
        java.lang.Object obj28 = null;
        stackAr26.push(obj28);
        java.lang.Object obj30 = new java.lang.Object();
        stackAr26.push(obj30);
        stackAr26.makeEmpty();
        java.lang.Object obj33 = stackAr26.topAndPop();
        stackAr22.push(obj33);
        stackAr10.push((java.lang.Object) stackAr22);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNull(obj8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(obj14);
        org.junit.Assert.assertNull(obj15);
        org.junit.Assert.assertNull(obj17);
        org.junit.Assert.assertNull(obj19);
        org.junit.Assert.assertNull(obj20);
        org.junit.Assert.assertNull(obj23);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNull(obj33);
    }
}

