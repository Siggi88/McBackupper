package io.siggi.mcbackupper.nbt;

public final class NBTIntArray extends NBTTag {

	private final int[] array;

	public NBTIntArray(int[] array) {
		this.array = array;
	}

	public int[] getArray() {
		return array;
	}

	@Override
	public int getType() {
		return 11;
	}
}
