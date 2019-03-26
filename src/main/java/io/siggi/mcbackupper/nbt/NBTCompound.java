package io.siggi.mcbackupper.nbt;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class NBTCompound extends NBTTag implements Map<String, NBTTag> {

	private final Map<String, NBTTag> children;

	public NBTCompound() {
		this.children = new LinkedHashMap<>();
	}

	@Override
	public int getType() {
		return 10;
	}

	@Override
	public int size() {
		return children.size();
	}

	@Override
	public boolean isEmpty() {
		return children.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return children.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return children.containsValue(value);
	}

	@Override
	public NBTTag get(Object key) {
		return children.get(key);
	}

	@Override
	public NBTTag put(String key, NBTTag value) {
		return children.put(key, value);
	}

	@Override
	public NBTTag remove(Object key) {
		return children.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends NBTTag> m) {
		children.putAll(m);
	}

	@Override
	public void clear() {
		children.clear();
	}

	@Override
	public Set<String> keySet() {
		return children.keySet();
	}

	@Override
	public Collection<NBTTag> values() {
		return children.values();
	}

	@Override
	public Set<Entry<String, NBTTag>> entrySet() {
		return children.entrySet();
	}
}
