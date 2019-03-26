package io.siggi.mcbackupper.nbt;

public final class NBTFloat extends NBTNumber {

	private final float value;

	public NBTFloat(float value) {
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
		return 5;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NBTFloat)) {
			return false;
		}
		NBTFloat o = (NBTFloat) other;
		return o.value == value;
	}

	@Override
	public int hashCode() {
		return Float.floatToIntBits(this.value);
	}
}
