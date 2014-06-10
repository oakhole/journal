package com.oakhole.sequence;

public class SimpleSequence {

	private long sequence;

	public SimpleSequence() {
		sequence = 0L;
	}

	public synchronized void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public synchronized byte nextByte() {
		if (sequence >= 127L)
			sequence = 1L;
		else
			sequence++;
		return (byte) (int) sequence;
	}

	public synchronized byte currentByte() {
		return (byte) (int) sequence;
	}

	public synchronized short nextShort() {
		if (sequence >= 32767L)
			sequence = 1L;
		else
			sequence++;
		return (short) (int) sequence;
	}

	public synchronized short currentShort() {
		return (short) (int) sequence;
	}

	public synchronized int nextInteger() {
		if (sequence >= 0x7fffffffL)
			sequence = 1L;
		else
			sequence++;
		return (int) sequence;
	}

	public synchronized int currentInteger() {
		return (int) sequence;
	}

	public synchronized long nextLong() {
		if (sequence >= 0x7fffffffffffffffL)
			sequence = 1L;
		else
			sequence++;
		return (long) (int) sequence;
	}

	public synchronized long currentLong() {
		return sequence;
	}
}
