package com.thoughtworks.apidoc.resources;

public class SortKey {
    public final String key;
    public final boolean ascending;

    SortKey(String key, boolean ascending) {
        this.key = key;
        this.ascending = ascending;
    }

    public static SortKey fromString(String string) {
        if (string.charAt(0) == '-') {
            return new SortKey(string.substring(1), false);
        } else {
            return new SortKey(string, true);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SortKey sortKey = (SortKey) o;

        return ascending == sortKey.ascending && (key != null ? key.equals(sortKey.key) : sortKey.key == null);

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (ascending ? 1 : 0);
        return result;
    }
}
