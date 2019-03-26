package io.siggi.mcbackupper.nbt;

public final class NBTLong extends NBTNumber {

	private final long value;

	public NBTLong(long value) {
		this.value = value;
	}

	@Override
	public byte getByte() {
		return (byte) value;
	}

	@Override
	public short getShort() {
		return (short) value;
	}

	@Override
	public int getInt() {
		return (int) value;
	}

	@Override
	public long getLong() {
		return (long) value;
	}

	@Override
	public float getFloat() {
		return (float) value;
	}

	@Override
	public double getDouble() {
		return (double) value;
	}

	@Override
	public int getType() {
		return 4;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NBTLong)) {
			return false;
		}
		NBTLong o = (NBTLong) other;
		return o.value == value;
	}

	@Override
	public int hashCode() {
		return (int) (this.value ^ (this.value >>> 32));
	}
}
