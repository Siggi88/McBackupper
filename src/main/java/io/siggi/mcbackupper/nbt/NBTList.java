package io.siggi.mcbackupper.nbt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class NBTList extends NBTTag implements List<NBTTag> {

	private int type = 0;
	private final List<NBTTag> list;

	public NBTList() {
		this.list = new ArrayList<>();
	}

	public NBTList(int initialCapacity) {
		this.list = new ArrayList<>(initialCapacity);
	}

	@Override
	public int getType() {
		return 9;
	}

	public int getListContentType() {
		return type;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<NBTTag> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(NBTTag e) {
		preAdd(e);
		return list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		try {
			return list.remove(o);
		} finally {
			if (list.isEmpty()) {
				type = 0;
			}
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends NBTTag> c) {
		for (NBTTag t : c) {
			preAdd(t);
		}
		return list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends NBTTag> c) {
		for (NBTTag t : c) {
			preAdd(t);
		}
		return list.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		try {
			return list.removeAll(c);
		} finally {
			if (list.isEmpty()) {
				type = 0;
			}
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		try {
			return list.retainAll(c);
		} finally {
			if (list.isEmpty()) {
				type = 0;
			}
		}
	}

	@Override
	public void clear() {
		list.clear();
		type = 0;
	}

	@Override
	public NBTTag get(int index) {
		return list.get(index);
	}

	@Override
	public NBTTag set(int index, NBTTag element) {
		preAdd(element);
		return list.set(index, element);
	}

	@Override
	public void add(int index, NBTTag element) {
		preAdd(element);
		list.add(index, element);
	}

	@Override
	public NBTTag remove(int index) {
		return list.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<NBTTag> listIterator() {
		return new NBTListIterator(list.listIterator());
	}

	@Override
	public ListIterator<NBTTag> listIterator(int index) {
		return new NBTListIterator(list.listIterator(index));
	}

	@Override
	public List<NBTTag> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private void preAdd(NBTTag tag) {
		if (tag == null) {
			throw new NullPointerException();
		}
		if (type != 0 && type != tag.getType()) {
			throw new IllegalArgumentException("Type mismatch!");
		}
		type = tag.getType();
	}

	private class NBTListIterator implements ListIterator<NBTTag> {

		private final ListIterator<NBTTag> li;

		public NBTListIterator(ListIterator<NBTTag> li) {
			this.li = li;
		}

		@Override
		public boolean hasNext() {
			return li.hasNext();
		}

		@Override
		public NBTTag next() {
			return li.next();
		}

		@Override
		public boolean hasPrevious() {
			return li.hasPrevious();
		}

		@Override
		public NBTTag previous() {
			return li.previous();
		}

		@Override
		public int nextIndex() {
			return li.nextIndex();
		}

		@Override
		public int previousIndex() {
			return li.previousIndex();
		}

		@Override
		public void remove() {
			li.remove();
			if (list.isEmpty()) {
				type = 0;
			}
		}

		@Override
		public void set(NBTTag e) {
			preAdd(e);
			li.set(e);
		}

		@Override
		public void add(NBTTag e) {
			preAdd(e);
			li.add(e);
		}
	}
}
