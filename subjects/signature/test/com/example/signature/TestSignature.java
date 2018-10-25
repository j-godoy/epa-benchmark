package com.example.signature;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSignature {

	  @Test(timeout = 4000)
	  public void test00()  throws Throwable  {
	      Signature mSignature0 = new Signature("object not initialized for signing");
	      byte[] byteArray0 = new byte[4];
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)62);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      mSignature0.initVerify(mPublicKey0);
	      boolean boolean0 = mSignature0.verify(byteArray0, (int) (byte)0, (int) (byte)3);
	      ////assertFalse(boolean0);
	  }

	  @Test(timeout = 4000)
	  public void test01()  throws Throwable  {
	      Signature mSignature0 = new Signature("q&kak)j.{D@b");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)17);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      byte[] byteArray0 = new byte[3];
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      mSignature0.initVerify(mPublicKey0);
	      boolean boolean0 = mSignature0.verify(byteArray0);
	      ////assertFalse(boolean0);
	  }

	  @Test(timeout = 4000)
	  public void test02()  throws Throwable  {
	      Signature mSignature0 = new Signature((String) null);
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)1);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      byte[] byteArray0 = new byte[3];
	      mSignature0.initVerify(mPublicKey0);
	      mSignature0.update(byteArray0, (int) (byte)0, (int) (byte)1);
	      //assertArrayEquals(new byte[] {(byte)0, (byte)0, (byte)0}, byteArray0);
	  }

	  @Test(timeout = 4000)
	  public void test03()  throws Throwable  {
	      Signature mSignature0 = new Signature((String) null);
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)1);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      byte[] byteArray0 = new byte[3];
	      mSignature0.initVerify(mPublicKey0);
	      mSignature0.update(byteArray0);
	      //assertNull(mSignature0.getAlgorithm());
	  }

	  @Test(timeout = 4000)
	  public void test04()  throws Throwable  {
	      Signature mSignature0 = new Signature((String) null);
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)1);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      mSignature0.initVerify(mPublicKey0);
	      mSignature0.update((byte)1);
	      //assertNull(mSignature0.getAlgorithm());
	  }

	  @Test(timeout = 4000)
	  public void test05()  throws Throwable  {
	      Signature mSignature0 = new Signature("gd*oiE/,\"M$v");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte) (-124));
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      mSignature0.initVerify(mPublicKey0);
	      mSignature0.initVerify(mPublicKey0);
	      //assertTrue(mPublicKey0.isValid());
	  }

	  @Test(timeout = 4000)
	  public void test06()  throws Throwable  {
	      Signature mSignature0 = new Signature("q&kak)j.{D@b");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)17);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      mSignature0.initVerify(mPublicKey0);
	      mSignature0.initSign(mPrivateKey0);
	      //assertTrue(mPrivateKey0.isValid());
	  }

	  @Test(timeout = 4000)
	  public void test07()  throws Throwable  {
	      Signature mSignature0 = new Signature("Gr*x+=M-65-Zr(u[u<z");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)1);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      mSignature0.initVerify(mPublicKey0);
	      String string0 = mSignature0.getAlgorithm();
	      //assertEquals("Gr*x+=M-65-Zr(u[u<z", string0);
	  }

	  @Test(timeout = 4000)
	  public void test08()  throws Throwable  {
	      Signature mSignature0 = new Signature("Gr*x+=M-65-Zr(u[u<z");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)1);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      byte[] byteArray0 = new byte[1];
	      mSignature0.initSign(mPrivateKey0);
	      mSignature0.update(byteArray0);
	      //assertArrayEquals(new byte[] {(byte)0}, byteArray0);
	  }

	  @Test(timeout = 4000)
	  public void test09()  throws Throwable  {
	      Signature mSignature0 = new Signature("q&kak)j.{D@b");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)17);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      mSignature0.initSign(mPrivateKey0);
	      //assertTrue(mPrivateKey0.isValid());
	  }

	  @Test(timeout = 4000)
	  public void test10()  throws Throwable  {
	      Signature mSignature0 = new Signature("q&kak)j.{D@b");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)17);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      String string0 = mSignature0.getAlgorithm();
	      //assertNotNull(string0);
	  }

	  @Test(timeout = 4000)
	  public void test11()  throws Throwable  {
	      Signature mSignature0 = new Signature("");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)16);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      byte[] byteArray0 = new byte[18];
	      mSignature0.initSign(mPrivateKey0);
	      mSignature0.update(byteArray0, 0, (int) (byte)16);
	      //assertEquals("", mSignature0.getAlgorithm());
	  }

	  @Test(timeout = 4000)
	  public void test12()  throws Throwable  {
	      Signature mSignature0 = new Signature((String) null);
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)1);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      mSignature0.update((byte)1);
	      //assertNull(mSignature0.getAlgorithm());
	  }

	  @Test(timeout = 4000)
	  public void test13()  throws Throwable  {
	      Signature mSignature0 = new Signature((String) null);
	      try { 
	        mSignature0.update((byte)1);
	        fail("Expecting exception: Exception");
	      
	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test14()  throws Throwable  {
	      Signature mSignature0 = new Signature("");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)20);
	      byte[] byteArray0 = new byte[7];
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      mSignature0.initVerify(mPublicKey0);
	      // Undeclared exception!
	      try { 
	        mSignature0.verify(byteArray0, (int) (byte)0, (int) (byte)20);
	        fail("Expecting exception: IllegalArgumentException");
	      
	      } catch(IllegalArgumentException e) {
	         //
	         // Bad arguments
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test15()  throws Throwable  {
	      Signature mSignature0 = new Signature("Gr*x+=M-65-Zr(u[u<z");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)1);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      byte[] byteArray0 = new byte[1];
	      mSignature0.initVerify(mPublicKey0);
	      // Undeclared exception!
	      try { 
	        mSignature0.verify(byteArray0, (int) (byte)0, (-1));
	        fail("Expecting exception: IllegalArgumentException");
	      
	      } catch(IllegalArgumentException e) {
	         //
	         // Bad arguments
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test16()  throws Throwable  {
	      Signature mSignature0 = new Signature("]o]PX^\u0007S+vS");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)0);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      mSignature0.initVerify(mPublicKey0);
	      byte[] byteArray0 = new byte[2];
	      // Undeclared exception!
	      try { 
	        mSignature0.verify(byteArray0, (-35), (int) (byte)0);
	        fail("Expecting exception: IllegalArgumentException");
	      
	      } catch(IllegalArgumentException e) {
	         //
	         // Bad arguments
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test17()  throws Throwable  {
	      Signature mSignature0 = new Signature("");
	      byte[] byteArray0 = new byte[18];
	      try { 
	        mSignature0.verify(byteArray0, (int) (byte)16, (int) (byte)0);
	        fail("Expecting exception: Exception");
	      
	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  private void verifyException(String string, Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Test(timeout = 4000)
	  public void test18()  throws Throwable  {
	      Signature mSignature0 = new Signature("q&kak)j.{D@b");
	      byte[] byteArray0 = new byte[3];
	      try { 
	        mSignature0.verify(byteArray0);
	        fail("Expecting exception: Exception");
	      
	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test19()  throws Throwable  {
	      Signature mSignature0 = new Signature("");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)16);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      byte[] byteArray0 = new byte[18];
	      mSignature0.initSign(mPrivateKey0);
	      int int0 = mSignature0.sign(byteArray0, (int) (byte)16, (int) (byte)0);
	      //assertEquals(0, int0);
	  }

	  @Test(timeout = 4000)
	  public void test20()  throws Throwable  {
	      Signature mSignature0 = new Signature("7om.exaple.sign;ture.MSignature");
	      byte[] byteArray0 = new byte[19];
	      // Undeclared exception!
	      try { 
	        mSignature0.sign(byteArray0, 4601, 4601);
	        fail("Expecting exception: IllegalArgumentException");
	      
	      } catch(IllegalArgumentException e) {
	         //
	         // Output buffer too small for specified offset and length
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test21()  throws Throwable  {
	      Signature mSignature0 = new Signature("MJV");
	      // Undeclared exception!
	      try { 
	        mSignature0.sign((byte[]) null, 2, (-1567));
	        fail("Expecting exception: IllegalArgumentException");
	      
	      } catch(IllegalArgumentException e) {
	         //
	         // No output buffer given
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test22()  throws Throwable  {
	      Signature mSignature0 = new Signature("");
	      byte[] byteArray0 = new byte[18];
	      try { 
	        mSignature0.sign(byteArray0, (int) (byte)16, (int) (byte)0);
	        fail("Expecting exception: Exception");
	      
	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test23()  throws Throwable  {
	      Signature mSignature0 = new Signature((String) null);
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)1);
	      MockPrivateKey mPrivateKey0 = new MockPrivateKey(mCaesarCipher0);
	      mSignature0.initSign(mPrivateKey0);
	      byte[] byteArray0 = mSignature0.sign();
	      //assertArrayEquals(new byte[] {}, byteArray0);
	  }

	  @Test(timeout = 4000)
	  public void test24()  throws Throwable  {
	      Signature mSignature0 = new Signature("D4q.{yWQb2:");
	      try { 
	        mSignature0.sign();
	        fail("Expecting exception: Exception");
	      
	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test25()  throws Throwable  {
	      Signature mSignature0 = new Signature("");
	      byte[] byteArray0 = new byte[18];
	      try { 
	        mSignature0.update(byteArray0, 0, (int) (byte)16);
	        fail("Expecting exception: Exception");
	      
	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test26()  throws Throwable  {
	      Signature mSignature0 = new Signature("private key cannot be null");
	      MCaesarCipher mCaesarCipher0 = new MCaesarCipher((byte)3);
	      MockPublicKey mPublicKey0 = new MockPublicKey(mCaesarCipher0);
	      mSignature0.initVerify(mPublicKey0);
	      // Undeclared exception!
	      try { 
	        mSignature0.verify((byte[]) null, (int) (byte)46, 7);
	        fail("Expecting exception: IllegalArgumentException");
	      
	      } catch(IllegalArgumentException e) {
	         //
	         // Bad arguments
	         //
	         verifyException("com.example.signature.MSignature", e);
	      }
	  }

	  @Test(timeout = 4000)
	  public void test27()  throws Throwable  {
	      Signature mSignature0 = new Signature("Gr*x+=M-65-Zr(u[u<z");
	      String string0 = mSignature0.getAlgorithm();
	      //assertEquals("Gr*x+=M-65-Zr(u[u<z", string0);
	  }

}
