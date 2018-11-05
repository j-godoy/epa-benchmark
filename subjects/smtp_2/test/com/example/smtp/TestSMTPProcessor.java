package com.example.smtp;

import static org.junit.Assert.fail;
import org.junit.Test;

public class TestSMTPProcessor {

	@Test
	public void testExample() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
//		p.helo("relay.example.com");
		p.noop();
		p.reset();
		p.mail("bob@example.com");
		p.rcpt("alice@example.com");
		p.rcpt("theboss@example.com");
		p.data("Hello Alice.\nThis is a test message with 4 lines in the message body.\nYour friend\nBob");
		p.quit();
	}
	
	@Test
	public void testExample2() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.auth("PLAIN abc bca");
	}

	@Test
	public void testInvalidmail() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.mail("invalidMail");
		try {
			p.rcpt("alice@example.com");
			fail();
		} catch (Exception ex) {

		}
	}

	@Test
	public void testInvalidRcpt() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.mail("bob@example.com");
		p.rcpt("invalid_mail");
		p.data("This message will not be sent");
	}

	@Test
	public void testReset() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.mail("bob@example.com");
		p.rcpt("alice@example.com");
		p.data("This message will be sent");
		p.reset();
		try {
			p.mail("bob@example.com");
			fail();
		} catch(Exception e) {}
	}

	@Test
	public void test00() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("B o(p?@Gb");
		sMTPProcessor0.rcpt("B o(p?@Gb");
		sMTPProcessor0.reset();
	}

	@Test
	public void test01() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("oRB o(@b");
		sMTPProcessor0.rcpt("oRB o(@b");
		sMTPProcessor0.rcpt("oRB o(@b");
	}

	@Test
	public void test02() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("oRB o(@b");
		sMTPProcessor0.rcpt("oRB o(@b");
		sMTPProcessor0.noop();
	}

	@Test
	public void test03() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("oRB o(@b");
		sMTPProcessor0.rcpt("oRB o(@b");
	}

	@Test
	public void test04() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("f|:2\"\u0006@,Ri");
		sMTPProcessor0.reset();
	}

	@Test
	public void test06() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("<");
		sMTPProcessor0.quit();
	}

	@Test
	public void test07() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("B`2I(p?@3");
		sMTPProcessor0.noop();
	}

	@Test
	public void test09() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.reset();
	}

	@Test
	public void test10() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.quit();
	}

	@Test
	public void test11() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.noop();
	}

	@Test
	public void test12() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("_=E@+hd~hFy@");
		sMTPProcessor0.rcpt("_=E@+hd~hFy@");
		sMTPProcessor0.quit();
	}

	@Test
	public void test13() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("-5+1&");
	}

	@Test
	public void testEvoSuiteEvoSuite00() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("j8I#@}31'H% S>8");
		sMTPProcessor0.rcpt("j8I#@}31'H% S>8");
		sMTPProcessor0.reset();
	}

	@Test
	public void testEvoSuiteEvoSuite01() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("j]=I@}31'H%JS>");
		sMTPProcessor0.rcpt("j]=I@}31'H%JS>");
		sMTPProcessor0.quit();
	}

	@Test
	public void testEvoSuiteEvoSuite02() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("=okq@{(?HiR%/OEKK/]");
		sMTPProcessor0.rcpt("=okq@{(?HiR%/OEKK/]");
		sMTPProcessor0.noop();
	}


	@Test
	public void testEvoSuiteEvoSuite04() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("");
		sMTPProcessor0.reset();
	}

	@Test
	public void testEvoSuiteEvoSuite05() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("");
		sMTPProcessor0.quit();
	}

	@Test
	public void testEvoSuiteEvoSuite06() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("");
		sMTPProcessor0.noop();
	}

	private void verifyException(String string, IllegalStateException e) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testEvoSuite10() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.quit();
		// Undeclared exception!
		try {
			sMTPProcessor0.data("1]\":m,");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite11() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		sMTPProcessor0.rcpt("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		sMTPProcessor0.rcpt("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
	}

	@Test
	public void testEvoSuite12() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.quit();
		// Undeclared exception!
		try {
			sMTPProcessor0.rcpt("");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite13() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
//		sMTPProcessor0.helo("");
		sMTPProcessor0.mail("");
	}


	@Test
	public void testEvoSuite16() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		// Undeclared exception!
		try {
			sMTPProcessor0.data("K.");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite17() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		// Undeclared exception!
		try {
			sMTPProcessor0.rcpt("");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite18() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		sMTPProcessor0.rcpt("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		sMTPProcessor0.data("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
	}

	@Test
	public void testEvoSuite19() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		// Undeclared exception!
		try {
			sMTPProcessor0.mail("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite21() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.reset();
		sMTPProcessor0.mail("j]=I@}31'H%JS>");
	}

	@Test
	public void testEvoSuite23() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.noop();
	}

	@Test
	public void testEvoSuite24() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.quit();
		// Undeclared exception!
		try {
			sMTPProcessor0.helo(".^wZ8^ze|K>`-]{<Z]>");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite25() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.mail("jH8n#@}31'H% JS>8");
		sMTPProcessor0.rcpt("jH8n#@}31'H% JS>8");
		sMTPProcessor0.data(".");
		sMTPProcessor0.quit();
	}
}
