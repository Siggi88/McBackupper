package io.siggi.mcbackupper.nbt;

import java.nio.charset.StandardCharsets;

public final class NBTString extends NBTTag {

	private final String value;
	final byte[] byteValue;

	public NBTString(String value) {
		this.value = value;
		this.byteValue = value.getBytes(StandardCharsets.UTF_8);
		if (byteValue.length > 65535) {
			throw new IllegalArgumentException("String is too long, max is 65535 bytes when expressed in UTF-8.");
		}
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int getType() {
		return 8;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NBTString)) {
			return false;
		}
		NBTString o = (NBTString) other;
		return o.value.equals(value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
