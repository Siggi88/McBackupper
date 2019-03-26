package io.siggi.mcbackupper.nbt;

import java.io.IOException;

public class NBTIOException extends IOException {

	NBTIOException() {
		super();
	}

	NBTIOException(String message) {
		super(message);
	}

	NBTIOException(Throwable cause) {
		super(cause);
	}

	NBTIOException(String message, Throwable cause) {
		super(message, cause);
	}
}
