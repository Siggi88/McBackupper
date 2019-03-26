package io.siggi.mcbackupper.nbt;

public class NBTFileData {

	private final String rootName;
	private final NBTCompound rootCompound;

	public String getRootName() {
		return rootName;
	}

	public NBTCompound getRootCompound() {
		return rootCompound;
	}

	public NBTFileData(String rootName, NBTCompound rootCompound) {
		this.rootName = rootName;
		this.rootCompound = rootCompound;
	}
}
