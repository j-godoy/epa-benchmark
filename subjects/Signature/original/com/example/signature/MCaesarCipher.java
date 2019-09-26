package com.example.signature;

import java.util.Arrays;

public class MCaesarCipher {

	private final byte shift;

	public MCaesarCipher(byte shift) {
		this.shift = shift;
	}

	public byte[] sign(byte[] bytes) {
		if (bytes == null) {
			throw new NullPointerException();
		}
		byte[] encoded_bytes = encode(bytes);
		return encoded_bytes;
	}

	private byte[] encode(byte[] bytes) {
		byte[] sign = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			sign[i] = (byte) (bytes[i] + shift);
		}
		return sign;
	}

	private byte[] decode(byte[] bytes) {
		byte[] sign = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			sign[i] = (byte) (bytes[i] - shift);
		}
		return sign;
	}

	public boolean verify(byte[] bytes, byte[] signature) {
		if (bytes == null || signature == null) {
			throw new NullPointerException();
		}
		if (bytes.length != signature.length) {
			return false;
		}
		byte[] decoded_bytes = decode(signature);
		if (Arrays.equals(bytes, decoded_bytes)) {
			return true;
		} else {
			return false;
		}
	}

}
