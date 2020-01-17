package softindex.map;

import static java.util.Arrays.stream;

import java.util.Objects;

public class Map {
    private static final int PROBE = 1;
    private static final int INITIAL_CAPACITY = 128;
    private static final double LOAD_FACTOR = 0.7;
    private Entry[] table = new Entry[INITIAL_CAPACITY];
    private int size = 0;


    public long put(int key, long value) {
        long oldValue = -1;
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new Entry(key, value);
            size++;
            addCapacity();
        } else {
            oldValue = table[index].value;
            table[index].value = value;
        }
        return oldValue;
    }

    public long get(int key) {
        Entry entry = table[getIndex(key)];
        return (entry == null) ? -1 : entry.value;
    }

    public int size() {
        return size;
    }

    private int getIndex(int key) {
        int index = hash(key);
        while (table[index] != null && table[index].key != key) {
            index = hash(index + PROBE);
        }
        return index;
    }

    private int hash(int integer) {
        return integer % table.length;
    }

    private void addCapacity() {
        if (size == (int)(table.length * LOAD_FACTOR)) {
            Entry[] oldTable = table;
            table = new Entry[table.length * 2];
            size = 0;
            rehash(oldTable);
            oldTable = null;
        }
    }

    private void rehash(Entry[] oldTable) {
        stream(oldTable)
                .filter(Objects::nonNull)
                .forEach(entry -> put(entry.key, entry.value));
    }

    private static class Entry {
        private int key;
        private long value;

        private Entry(int key, long value) {
            this.key = key;
            this.value = value;
        }
    }
}
