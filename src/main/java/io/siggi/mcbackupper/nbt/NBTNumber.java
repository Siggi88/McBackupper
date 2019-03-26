package io.siggi.mcbackupper.nbt;

public abstract class NBTNumber extends NBTTag {

	NBTNumber() {
	}

	public abstract byte getByte();

	public abstract short getShort();

	public abstract int getInt();

	public abstract long getLong();

	public abstract float getFloat();

	public abstract double getDouble();
}
