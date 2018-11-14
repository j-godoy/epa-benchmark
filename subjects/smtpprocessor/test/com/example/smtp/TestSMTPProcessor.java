package com.example.smtp;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

public class TestSMTPProcessor {

	@Test
	public void testExample() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.HELO("relay.example.com");
		p.NOOP();
		p.RSET();
		p.MAIL("bob@example.com");
		p.RCPT_TO("alice@example.com");
		p.RCPT_TO("theboss@example.com");
		p.DATA("Hello Alice.\nThis is a test message with 4 lines in the message body.\nYour friend\nBob");
		p.QUIT();
	}

	@Test
	public void testQuit() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.HELO("relay.example.com");
		p.QUIT();
		try {
			p.HELO("relay.example.com");
			fail();
		} catch (Exception ex) {
			// connection is closed
		}
	}

	@Test
	public void testInvalidMail() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.MAIL("invalidMail");
		try {
			p.RCPT_TO("alice@example.com");
			fail();
		} catch (Exception ex) {

		}
	}

	@Test
	public void testInvalidRcpt() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.MAIL("bob@example.com");
		p.RCPT_TO("invalid_mail");
		try {
			p.DATA("This message will not be sent");
			fail();
		} catch (Exception ex) {

		}
	}

	@Test
	public void testReset() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.MAIL("bob@example.com");
		p.RCPT_TO("alice@example.com");
		p.DATA("This message will be sent");
		p.RSET();
		p.MAIL("bob@example.com");
		p.RCPT_TO("alice@example.com");
		p.DATA("This message will be sent");
		p.QUIT();
	}

	@Test
	public void testMessageTooLarge() throws MockSocketException, Exception  {
		SMTPProcessor p = new SMTPProcessor();
		p.MAIL("bob@example.com");
		p.RCPT_TO("alice@example.com");

		char[] chars = new char[1048576];
		Arrays.fill(chars, '*');
		String textTooLarge = new String(chars);
		p.DATA(textTooLarge);
		try {
			p.QUIT();
			fail();
		} catch (Exception ex) {

		}
	}

	@Test
	public void test00() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("B o(p?@Gb");
		sMTPProcessor0.RCPT_TO("B o(p?@Gb");
		sMTPProcessor0.RSET();
	}

	@Test
	public void test01() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("oRB o(@b");
		sMTPProcessor0.RCPT_TO("oRB o(@b");
		sMTPProcessor0.RCPT_TO("oRB o(@b");
	}

	@Test
	public void test02() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("oRB o(@b");
		sMTPProcessor0.RCPT_TO("oRB o(@b");
		sMTPProcessor0.NOOP();
	}

	@Test
	public void test03() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("oRB o(@b");
		sMTPProcessor0.RCPT_TO("oRB o(@b");
		sMTPProcessor0.HELO("oRB o(@b");
	}

	@Test
	public void test04() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("f|:2\"\u0006@,Ri");
		sMTPProcessor0.RSET();
	}

	@Test
	public void test05() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("<");
		sMTPProcessor0.RCPT_TO("<");
	}

	@Test
	public void test06() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("<");
		sMTPProcessor0.QUIT();
	}

	@Test
	public void test07() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("B`2I(p?@3");
		sMTPProcessor0.NOOP();
	}

	@Test
	public void test08() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("B`2I(p?@3");
		sMTPProcessor0.HELO("B`2I(p?@3");
	}

	@Test
	public void test09() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.RSET();
	}

	@Test
	public void test10() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.QUIT();
	}

	@Test
	public void test11() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.NOOP();
	}

	@Test
	public void test12() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("_=E@+hd~hFy@");
		sMTPProcessor0.RCPT_TO("_=E@+hd~hFy@");
		sMTPProcessor0.QUIT();
	}

	@Test
	public void test13() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("-5+1&");
	}

	@Test
	public void test14() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.HELO("_=E@+hd~hFy@");
	}

	@Test
	public void testEvoSuiteEvoSuite00() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("j8I#@}31'H% S>8");
		sMTPProcessor0.RCPT_TO("j8I#@}31'H% S>8");
		sMTPProcessor0.RSET();
	}

	@Test
	public void testEvoSuiteEvoSuite01() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("j]=I@}31'H%JS>");
		sMTPProcessor0.RCPT_TO("j]=I@}31'H%JS>");
		sMTPProcessor0.QUIT();
	}

	@Test
	public void testEvoSuiteEvoSuite02() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("=okq@{(?HiR%/OEKK/]");
		sMTPProcessor0.RCPT_TO("=okq@{(?HiR%/OEKK/]");
		sMTPProcessor0.NOOP();
	}

	@Test
	public void testEvoSuiteEvoSuite03() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("=okq@{(?HiR%/OEKK/]");
		sMTPProcessor0.RCPT_TO("=okq@{(?HiR%/OEKK/]");
		sMTPProcessor0.HELO("=okq@{(?HiR%/OEKK/]");
	}

	@Test
	public void testEvoSuiteEvoSuite04() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("");
		sMTPProcessor0.RSET();
	}

	@Test
	public void testEvoSuiteEvoSuite05() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("");
		sMTPProcessor0.QUIT();
	}

	@Test
	public void testEvoSuiteEvoSuite06() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("");
		sMTPProcessor0.NOOP();
	}

	@Test
	public void testEvoSuiteEvoSuite07() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("");
		sMTPProcessor0.HELO("");
	}

	@Test
	public void testEvoSuiteEvoSuite08() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("\"'-JQ(Z0R<-%");
	}

	@Test
	public void testEvoSuiteEvoSuite09() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("j8I#@}31'H6% JS>8");
		sMTPProcessor0.RCPT_TO("K.");
		// Undeclared exception!
		try {
			sMTPProcessor0.DATA("K.");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	private void verifyException(String string, IllegalStateException e) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testEvoSuite10() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.QUIT();
		// Undeclared exception!
		try {
			sMTPProcessor0.DATA("1]\":m,");
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
		sMTPProcessor0.MAIL("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		sMTPProcessor0.RCPT_TO("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		sMTPProcessor0.RCPT_TO("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
	}

	@Test
	public void testEvoSuite12() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.QUIT();
		// Undeclared exception!
		try {
			sMTPProcessor0.RCPT_TO("");
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
		sMTPProcessor0.HELO("");
		sMTPProcessor0.MAIL("");
	}

	@Test
	public void testEvoSuite14() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.QUIT();
		// Undeclared exception!
		try {
			sMTPProcessor0.MAIL("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite15() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.QUIT();
		// Undeclared exception!
		try {
			sMTPProcessor0.QUIT();
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite16() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		// Undeclared exception!
		try {
			sMTPProcessor0.DATA("K.");
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
			sMTPProcessor0.RCPT_TO("");
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
		sMTPProcessor0.MAIL("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		sMTPProcessor0.RCPT_TO("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		sMTPProcessor0.DATA("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
	}

	@Test
	public void testEvoSuite19() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.MAIL("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
		// Undeclared exception!
		try {
			sMTPProcessor0.MAIL("MAIL FROM:MAIL FROM:&G1~ZTQ@|}A");
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite20() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.QUIT();
		// Undeclared exception!
		try {
			sMTPProcessor0.RSET();
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
		sMTPProcessor0.RSET();
		sMTPProcessor0.MAIL("j]=I@}31'H%JS>");
	}

	@Test
	public void testEvoSuite22() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.QUIT();
		// Undeclared exception!
		try {
			sMTPProcessor0.NOOP();
			fail("Expecting exception: IllegalStateException");

		} catch (IllegalStateException e) {
			//
			// no message in exception (getMessage() returned null)
			//
			verifyException("com.example.smtp.SMTPProcessor", e);
		}
	}

	@Test
	public void testEvoSuite23() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.NOOP();
	}

	@Test
	public void testEvoSuite24() throws Throwable {
		SMTPProcessor sMTPProcessor0 = new SMTPProcessor();
		sMTPProcessor0.QUIT();
		// Undeclared exception!
		try {
			sMTPProcessor0.HELO(".^wZ8^ze|K>`-]{<Z]>");
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
		sMTPProcessor0.MAIL("jH8n#@}31'H% JS>8");
		sMTPProcessor0.RCPT_TO("jH8n#@}31'H% JS>8");
		sMTPProcessor0.DATA(".");
		sMTPProcessor0.QUIT();
	}
}
