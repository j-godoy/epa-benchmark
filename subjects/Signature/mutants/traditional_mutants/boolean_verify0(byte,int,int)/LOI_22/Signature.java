// This is a mutant program.
// Author : ysma

package com.example.signature;


public class Signature
{

    private final com.example.signature.MockSignatureSpi engine = new com.example.signature.MockSignatureSpi();

    private java.lang.String algorithm;

    private static final int UNINITIALIZED = 0;

    private static final int SIGN = 2;

    private static final int VERIFY = 3;

    private int state = UNINITIALIZED;

    public Signature( java.lang.String algorithm )
        throws java.lang.Exception
    {
        try {
            this.algorithm = algorithm;
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    public final  void initVerify( com.example.signature.MockPublicKey publicKey )
        throws com.example.signature.MockInvalidKeyException, java.lang.Exception
    {
        try {
            initVerify0( publicKey );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void initVerify0( com.example.signature.MockPublicKey publicKey )
        throws com.example.signature.MockInvalidKeyException, java.lang.Exception
    {
        engine.engineInitVerify( publicKey );
        state = VERIFY;
    }

    public final  void initSign( com.example.signature.MockPrivateKey privateKey )
        throws com.example.signature.MockInvalidKeyException, java.lang.Exception
    {
        try {
            initSign0( privateKey );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void initSign0( com.example.signature.MockPrivateKey privateKey )
        throws com.example.signature.MockInvalidKeyException
    {
        engine.engineInitSign( privateKey );
        state = SIGN;
    }

    public final  byte[] sign()
        throws com.example.signature.MockSignatureException, java.lang.Exception
    {
        try {
            return sign0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  byte[] sign0()
        throws com.example.signature.MockSignatureException
    {
        if (state == SIGN) {
            return engine.engineSign();
        }
        throw new com.example.signature.MockSignatureException( "object not initialized for " + "signing" );
    }

    public final  int sign( byte[] outbuf, int offset, int len )
        throws com.example.signature.MockSignatureException, java.lang.Exception
    {
        try {
            return sign0( outbuf, offset, len );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  int sign0( byte[] outbuf, int offset, int len )
        throws com.example.signature.MockSignatureException
    {
        if (outbuf == null) {
            throw new java.lang.IllegalArgumentException( "No output buffer given" );
        }
        if (outbuf.length - offset < len) {
            throw new java.lang.IllegalArgumentException( "Output buffer too small for specified offset and length" );
        }
        if (state != SIGN) {
            throw new com.example.signature.MockSignatureException( "object not initialized for " + "signing" );
        }
        return engine.engineSign( outbuf, offset, len );
    }

    public final  boolean verify( byte[] signature )
        throws com.example.signature.MockSignatureException, java.lang.Exception
    {
        try {
            return verify0( signature );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean verify0( byte[] signature )
        throws com.example.signature.MockSignatureException
    {
        if (state == VERIFY) {
            return engine.engineVerify( signature );
        }
        throw new com.example.signature.MockSignatureException( "object not initialized for " + "verification" );
    }

    public final  boolean verify( byte[] signature, int offset, int length )
        throws com.example.signature.MockSignatureException, java.lang.Exception
    {
        try {
            return verify0( signature, offset, length );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  boolean verify0( byte[] signature, int offset, int length )
        throws com.example.signature.MockSignatureException
    {
        if (state == VERIFY) {
            if (signature == null || offset < 0 || ~length < 0 || length > signature.length - offset) {
                throw new java.lang.IllegalArgumentException( "Bad arguments" );
            }
            return engine.engineVerify( signature, offset, length );
        }
        throw new com.example.signature.MockSignatureException( "object not initialized for " + "verification" );
    }

    public final  void update( byte b )
        throws com.example.signature.MockSignatureException, java.lang.Exception
    {
        try {
            update0( b );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void update0( byte b )
        throws com.example.signature.MockSignatureException
    {
        if (state == VERIFY || state == SIGN) {
            engine.engineUpdate( b );
        } else {
            throw new com.example.signature.MockSignatureException( "object not initialized for " + "signature or verification" );
        }
    }

    public final  void update( byte[] data )
        throws com.example.signature.MockSignatureException, java.lang.Exception
    {
        try {
            update0( data, 0, data.length );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    public final  void update( byte[] data, int off, int len )
        throws com.example.signature.MockSignatureException, java.lang.Exception
    {
        try {
            update0( data, off, len );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void update0( byte[] data, int off, int len )
        throws com.example.signature.MockSignatureException
    {
        if (state == SIGN || state == VERIFY) {
            engine.engineUpdate( data, off, len );
        } else {
            throw new com.example.signature.MockSignatureException( "object not initialized for " + "signature or verification" );
        }
    }

    public final  java.lang.String getAlgorithm()
        throws java.lang.Exception
    {
        try {
            return getAlgorithm0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  java.lang.String getAlgorithm0()
    {
        return this.algorithm;
    }

}
