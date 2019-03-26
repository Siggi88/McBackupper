package io.siggi.mcbackupper.nbt;

public final class NBTInt extends NBTNumber {

	private final int value;

	public NBTInt(int value) {
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
		return 3;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NBTInt)) {
			return false;
		}
		NBTInt o = (NBTInt) other;
		return o.value == value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}
