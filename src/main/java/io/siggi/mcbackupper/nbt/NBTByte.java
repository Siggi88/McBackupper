package io.siggi.mcbackupper.nbt;

public final class NBTByte extends NBTNumber {

	private final byte value;

	public NBTByte(byte value) {
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
		return 1;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NBTByte)) {
			return false;
		}
		NBTByte o = (NBTByte) other;
		return o.value == value;
	}

	@Override
	public int hashCode() {
		return (int) value;
	}
}
