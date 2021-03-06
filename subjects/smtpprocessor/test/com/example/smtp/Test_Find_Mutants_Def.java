/*
 * This file was automatically generated by EvoSuite
 * Sun Aug 12 05:18:58 GMT 2018
 */

package com.example.smtp;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.smtp.SMTPProcessor;

public class Test_Find_Mutants_Def {

  @Test(timeout = 1300)
  public void test00()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.MAIL("%PMfb<u)|fY");
  }

  @Test(timeout = 1300)
  public void test01()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.MAIL("");
      sMTPProcessor0.RCPT_TO("");
      try {
        sMTPProcessor0.DATA("");
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test02()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.QUIT();
      try {
        sMTPProcessor0.DATA((String) null);
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test03()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.MAIL("$`@c2V0j> wsD");
      sMTPProcessor0.RCPT_TO("$`@c2V0j> wsD");
      sMTPProcessor0.RCPT_TO("Kh|WaGKz");
  }

  @Test(timeout = 1300)
  public void test04()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.QUIT();
      try {
        sMTPProcessor0.RCPT_TO("(^5iwueBP'z;u");
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test05()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.QUIT();
      try {
        sMTPProcessor0.MAIL("{'sE@O");
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test06()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.QUIT();
      try {
        sMTPProcessor0.QUIT();
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test07()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.MAIL("$`@c2V0j> wsD");
      sMTPProcessor0.RCPT_TO("$`@c2V0j> wsD");
      sMTPProcessor0.DATA("Kh|WaGKz");
  }

  @Test(timeout = 1300)
  public void test08()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.MAIL("IH@I|G$Y|e~wgbt");
      try {
        sMTPProcessor0.MAIL("IH@I|G$Y|e~wgbt");
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test09()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.QUIT();
      try {
        sMTPProcessor0.RSET();
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test10()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.QUIT();
      try {
        sMTPProcessor0.NOOP();
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test11()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.NOOP();
  }

  @Test(timeout = 1300)
  public void test12()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      try {
        sMTPProcessor0.DATA((String) null);
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test13()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.QUIT();
      try {
        sMTPProcessor0.HELO("KO}");
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test14()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.MAIL("$`@$c2V0j> wsD");
      sMTPProcessor0.RCPT_TO("$`@$c2V0j> wsD");
      try {
        sMTPProcessor0.DATA((String) null);
        fail("Expecting exception: NullPointerException");

      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test15()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      try {
        sMTPProcessor0.RCPT_TO("$`@$c2V0j> wsD");
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test16()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.HELO("KO}");
      sMTPProcessor0.MAIL("KO}");
  }

  @Test(timeout = 1300)
  public void test17()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.RSET();
      sMTPProcessor0.MAIL("IH@I|G$Y|e~wgbt");
  }
  
  @Test(timeout = 1300)
  public void test00_o()  throws Throwable  {
      SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
      sMTPProcessor0.MAIL("MAIL FROM:MAIL FROM:YN0SRD<");
      sMTPProcessor0.RCPT_TO("MAIL FROM:MAIL FROM:YN0SRD<");
      try {
        sMTPProcessor0.DATA("MAIL FROM:MAIL FROM:YN0SRD<");
        fail("Expecting exception: IllegalStateException");

      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

}