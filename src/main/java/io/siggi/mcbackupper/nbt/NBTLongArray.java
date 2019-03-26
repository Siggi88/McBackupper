package io.siggi.mcbackupper.nbt;

public final class NBTLongArray extends NBTTag {

	private final long[] array;

	public NBTLongArray(long[] array) {
		this.array = array;
	}

	public long[] getArray() {
		return array;
	}

	@Override
	public int getType() {
		return 12;
	}
	
}
