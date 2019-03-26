package io.siggi.mcbackupper.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class NBTIO {

	private NBTIO() {
		// no instantiation!
	}

	private static final int TAG_END = 0;
	private static final int TAG_BYTE = 1;
	private static final int TAG_SHORT = 2;
	private static final int TAG_INT = 3;
	private static final int TAG_LONG = 4;
	private static final int TAG_FLOAT = 5;
	private static final int TAG_DOUBLE = 6;
	private static final int TAG_BYTEARRAY = 7;
	private static final int TAG_STRING = 8;
	private static final int TAG_LIST = 9;
	private static final int TAG_COMPOUND = 10;
	private static final int TAG_INTARRAY = 11;
	private static final int TAG_LONGARRAY = 12;

	public static NBTFileData read(InputStream inputStream) throws IOException {
		DataInputStream in = (inputStream instanceof DataInputStream)
				? ((DataInputStream) inputStream)
				: new DataInputStream(inputStream);
		NBTPrefix rootPrefix = readNBTPrefix(in);
		if (rootPrefix.type != TAG_COMPOUND) {
			throw new NBTIOException("Root Tag is not a Compound!");
		}
		NBTCompound compound = (NBTCompound) readTag(rootPrefix.type, in);
		return new NBTFileData(rootPrefix.name, compound);
	}

	public static void write(OutputStream outputStream, NBTFileData fileData) throws IOException {
		DataOutputStream out = (outputStream instanceof DataOutputStream)
				? ((DataOutputStream) outputStream)
				: new DataOutputStream(outputStream);
		writeNBTPrefix(out, new NBTPrefix(TAG_COMPOUND, fileData.rootName));
		writeTag(out, fileData.getRootCompound());

	}

	private static NBTPrefix readNBTPrefix(DataInputStream in) throws IOException {
		int type = in.read();
		if (type == 0) {
			return new NBTPrefix(0, null); // it's a TAG_End
		}
		int nameLength = ((int) in.readShort()) & 0xffff;
		byte[] n = new byte[nameLength];
		in.readFully(n, 0, nameLength);
		String name = new String(n, StandardCharsets.UTF_8);
		return new NBTPrefix(type, name);
	}

	private static void writeNBTPrefix(DataOutputStream out, NBTPrefix prefix) throws IOException {
		out.write(prefix.type);
		if (prefix.type != 0) {
			byte[] n = prefix.name.getBytes(StandardCharsets.UTF_8);
			out.writeShort(n.length);
			out.write(n);
		}
	}

	private static NBTTag readTag(int type, DataInputStream in) throws IOException {
		switch (type) {
			case TAG_BYTE:
				return new NBTByte(in.readByte());
			case TAG_SHORT:
				return new NBTShort(in.readShort());
			case TAG_INT:
				return new NBTInt(in.readInt());
			case TAG_LONG:
				return new NBTLong(in.readLong());
			case TAG_FLOAT:
				return new NBTFloat(in.readFloat());
			case TAG_DOUBLE:
				return new NBTDouble(in.readDouble());
			case TAG_BYTEARRAY: {
				int length = in.readInt();
				byte[] array = new byte[length];
				in.readFully(array, 0, length);
				return new NBTByteArray(array);
			}
			case TAG_STRING: {
				int length = ((int) in.readShort()) & 0xffff;
				byte[] b = new byte[length];
				in.readFully(b, 0, length);
				return new NBTString(new String(b, StandardCharsets.UTF_8));
			}
			case TAG_LIST: {
				int listType = in.read();
				int length = in.readInt();
				if (length > 0 && listType == 0) {
					throw new NBTIOException("List Type cannot be TAG_End if length is greater than 0!");
				}
				NBTList list = new NBTList(length);
				for (int i = 0; i < length; i++) {
					list.add(readTag(listType, in));
				}
				return list;
			}
			case TAG_COMPOUND: {
				NBTCompound compound = new NBTCompound();
				while (true) {
					NBTPrefix prefix = readNBTPrefix(in);
					if (prefix.type == 0) {
						break;
					}
					compound.put(prefix.name, readTag(prefix.type, in));
				}
				return compound;
			}
			case TAG_INTARRAY: {
				int length = in.readInt();
				int[] array = new int[length];
				for (int i = 0; i < length; i++) {
					array[i] = in.readInt();
				}
				return new NBTIntArray(array);
			}
			case TAG_LONGARRAY: {
				int length = in.readInt();
				long[] array = new long[length];
				for (int i = 0; i < length; i++) {
					array[i] = in.readLong();
				}
				return new NBTLongArray(array);
			}
			default:
				throw new NBTIOException("Unsupported TAG ID: " + type);
		}
	}

	private static void writeTag(DataOutputStream out, NBTTag tag) throws IOException {
		switch (tag.getType()) {
			case TAG_BYTE:
				out.writeByte(((NBTByte) tag).getByte());
				break;
			case TAG_SHORT:
				out.writeShort(((NBTShort) tag).getShort());
				break;
			case TAG_INT:
				out.writeInt(((NBTInt) tag).getInt());
				break;
			case TAG_LONG:
				out.writeLong(((NBTLong) tag).getLong());
				break;
			case TAG_FLOAT:
				out.writeFloat(((NBTFloat) tag).getFloat());
				break;
			case TAG_DOUBLE:
				out.writeDouble(((NBTDouble) tag).getDouble());
				break;
			case TAG_BYTEARRAY: {
				NBTByteArray byteArray = (NBTByteArray) tag;
				byte[] array = byteArray.getArray();
				out.writeInt(array.length);
				out.write(array);
			}
			break;
			case TAG_STRING: {
				byte[] byteValue = ((NBTString) tag).byteValue;
				out.writeShort(byteValue.length);
				out.write(byteValue);
			}
			break;
			case TAG_LIST: {
				NBTList list = (NBTList) tag;
				out.write(list.getListContentType());
				int size = list.size();
				out.writeInt(size);
				for (NBTTag childTag : list) {
					writeTag(out, childTag);
				}
			}
			break;
			case TAG_COMPOUND: {
				NBTCompound compound = (NBTCompound) tag;
				for (Map.Entry<String, NBTTag> entry : compound.entrySet()) {
					NBTPrefix pr = new NBTPrefix(entry.getValue().getType(), entry.getKey());
					writeNBTPrefix(out, pr);
					writeTag(out, entry.getValue());
				}
				out.write(0); // TAG_End
			}
			break;
			case TAG_INTARRAY: {
				NBTIntArray intArray = (NBTIntArray) tag;
				int[] array = intArray.getArray();
				out.writeInt(array.length);
				for (int i : array) {
					out.writeInt(i);
				}
			}
			break;
			case TAG_LONGARRAY: {
				NBTLongArray longArray = (NBTLongArray) tag;
				long[] array = longArray.getArray();
				out.writeInt(array.length);
				for (long l : array) {
					out.writeLong(l);
				}
			}
			break;
			default:
				throw new NBTIOException("Unsupported TAG ID: " + tag.getType());
		}
	}

	public static class NBTFileData {

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

	private static class NBTPrefix {

		public final int type;
		public final String name;

		public NBTPrefix(int type, String name) {
			this.type = type;
			this.name = name;
			if (name == null && type != 0) {
				throw new NullPointerException("name cannot be null if type is not 0!");
			}
		}
	}
}
