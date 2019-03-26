package io.siggi.mcbackupper.util;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;

public enum Compression {

	Gzip(1, (in) -> new GZIPInputStream(in), (out) -> new GZIPOutputStream(out)),
	Zlib(2, (in) -> new InflaterInputStream(in), (out) -> new DeflaterOutputStream(out));

	private final int typeId;
	private final FunctionWithThrowable<InputStream, FilterInputStream, IOException> decompress;
	private final FunctionWithThrowable<OutputStream, FilterOutputStream, IOException> compress;

	private Compression(int typeId, FunctionWithThrowable<InputStream, FilterInputStream, IOException> decompress, FunctionWithThrowable<OutputStream, FilterOutputStream, IOException> compress) {
		this.typeId = typeId;
		this.decompress = decompress;
		this.compress = compress;
	}

	private static final Map<Integer, Compression> types = new HashMap<>();

	static {
		for (Compression c : values()) {
			types.put(c.typeId, c);
		}
	}

	public static Compression forId(int typeId) {
		return types.get(typeId);
	}

	public FilterInputStream decompress(InputStream in) throws IOException {
		return decompress.apply(in);
	}

	public FilterOutputStream compress(OutputStream out) throws IOException {
		return compress.apply(out);
	}
}
