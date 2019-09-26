/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Ristretto Mail API.
 *
 * The Initial Developers of the Original Code are
 * Timo Stich and Frederik Dietz.
 * Portions created by the Initial Developers are Copyright (C) 2004
 * All Rights Reserved.
 *
 * Contributor(s):
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */
package com.example.smtpprotocol;


/**
 * Represents a reponse from a SMTP server.
 * This response is normally obtained by calling SMTPInputStream.readSingleLineResponse().
 * 
 * 
 * @author Timo Stich <tstich@users.sourceforge.net>
 */
public class MockSMTPResponse {

    private int code;
    private String message;
    private String domain;
    
    public MockSMTPResponse() {
    	this(200, "250 OK", "jg");
    }
    
    /**
     * Constructs a SMTPResponse.
     * 
     * @param code the return code
     * @param hasSuccessor true if there is another reponse to read from the server
     * @param message the human readable message
     */
    public MockSMTPResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    /**
     * Constructs a SMTPResponse.
     * 
     * @param code the return code
     * @param hasSuccessor true if there is another reponse to read from the server
     * @param message the human readable message
     * @param domain the domain of the server
     */
    public MockSMTPResponse(int code, String message, String domain) {
        this.code = code;
        this.message = message;
        this.domain = domain;
    }

    /**
     * @return Returns the code.
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Returns the domain.
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain The domain to set.
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Is the reponse an error response?
     * True if the return code is >= 400.
     * 
     * @return true if the response is an error response. 
     */
    public boolean isERR() {
        return code >= 400;
    }

    /**
     * Is the reponse an ok response?
     * True if the return code is < 400.
     * 
     * @return true if the response is an ok response. 
     */
    public boolean isOK() {
        return code < 400;
    }
   }
