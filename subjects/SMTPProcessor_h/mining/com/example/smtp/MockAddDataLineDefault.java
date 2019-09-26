package com.example.smtp;

public class MockAddDataLineDefault {

   protected MockSMTPMessage message;

   public MockAddDataLineDefault(MockSMTPMessage message) {
      this.message = message;
   }

   public void addDataLine(byte[] line) throws MockIOException{
   }

   public void flush() throws MockIOException{
   }
}
