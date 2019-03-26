package io.siggi.mcbackupper.nbt;

public final class NBTByteArray extends NBTTag {

	private final byte[] array;

	public NBTByteArray(byte[] array) {
		this.array = array;
	}

	public byte[] getArray() {
		return array;
	}

	@Override
	public int getType() {
		return 7;
	}
}
