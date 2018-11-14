/******************************************************************************
 * This program is a 100% Java Email Server.
 ******************************************************************************
 * Copyright (c) 2001-2009, Eric Daugherty (http://www.ericdaugherty.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of the copyright holder nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 ******************************************************************************
 * For current versions and more information, please visit:
 * http://www.ericdaugherty.com/java/mail
 *
 * or contact the authors at:
 * java@ericdaugherty.com
 * andreask1@vivodinet.gr
 *
 ******************************************************************************
 * This program is based on the CSRMail project written by Calvin Smith.
 * http://crsemail.sourceforge.net/
 ******************************************************************************
 *
 * $Rev$
 * $Date$
 *
 ******************************************************************************/

package com.example.smtp;

import java.io.UnsupportedEncodingException;

public class MockPlainServerMode {

	protected final MockFinalizeAuthentication finalizeAuthentication;

	public MockPlainServerMode(boolean isSMTP) {
		if (isSMTP) {
			finalizeAuthentication = new MockFinalizeAuthenticationSMTP();
		} else {
			finalizeAuthentication = new MockFinalizeAuthenticationPOP3();
		}
	}

	public byte[] evaluateResponse(byte[] responseBytes) throws MockSaslException {

		String[] usernameandpassword = null;
		try {
			usernameandpassword = new String((responseBytes), "UTF-8").split("\u0000");
		} catch (UnsupportedEncodingException uee) {
			throw new MockSaslException();
		}
		String username, password;
		if (usernameandpassword.length < 2) {
			return null;
		} else if (usernameandpassword.length == 3) {
			username = usernameandpassword[1];
			password = usernameandpassword[2];
		} else {
			username = usernameandpassword[0];
			password = usernameandpassword[1];
		}
		return finalizeAuthentication.finalize(username, password);
	}

	protected abstract class MockFinalizeAuthentication {
		public abstract byte[] finalize(String username, String password) throws MockSaslException;
	}

	protected class MockFinalizeAuthenticationSMTP extends MockFinalizeAuthentication {

		public byte[] finalize(String username, String password) throws MockSaslException {
			try {
				return "Authentication Successful".getBytes("US-ASCII");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
			}
			return null;
		}
	}

	protected class MockFinalizeAuthenticationPOP3 extends MockFinalizeAuthentication {

		public byte[] finalize(String username, String password) throws MockSaslException {
			try {
				return "Authentication Successful".getBytes("US-ASCII");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	public void conclude() {
	}

}
