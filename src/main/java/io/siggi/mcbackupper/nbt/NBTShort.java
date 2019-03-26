package io.siggi.mcbackupper.nbt;

public final class NBTShort extends NBTNumber {

	private final short value;

	public NBTShort(short value) {
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
		return 2;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NBTShort)) {
			return false;
		}
		NBTShort o = (NBTShort) other;
		return o.value == value;
	}

	@Override
	public int hashCode() {
		return (int) value;
	}
}
