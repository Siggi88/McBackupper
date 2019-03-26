package io.siggi.mcbackupper.nbt;

public final class NBTDouble extends NBTNumber {

	private final double value;

	public NBTDouble(double value) {
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
		return 6;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NBTDouble)) {
			return false;
		}
		NBTDouble o = (NBTDouble) other;
		return o.value == value;
	}

	@Override
	public int hashCode() {
		long l = Double.doubleToLongBits(this.value);
		return (int) (l ^ (l >>> 32));
	}
}
