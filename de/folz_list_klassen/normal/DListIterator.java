package de.folz_list_klassen.normal;

import java.util.NoSuchElementException;

class DListIterator implements java.util.ListIterator {
    private DList l;
    private DListElement le;
    private int index = 0;
    DListIterator(DList l, int index) {
        if (index < 0 || index >= l.size())
        throw new IndexOutOfBoundsException("index:" + index +
                                            ", size:" + l.size());
        this.l = l;
        this.index = index;
        le = l.first;
        // gehe bis zum Element index
        int i = 0;
        while (i < index) {
            le = le.next;
            i++;
        }
    }
    public void add(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    public boolean hasNext() {
        return index != l.size();
    }
     public boolean hasPrevious() {
        return index != 0;
    }

    public Object next() throws NoSuchElementException {
        if (le == null)
            return new NoSuchElementException();
        Object o = le.data;
        le = le.next;
        index++;
        return  o;
    }
    public Object previous() throws NoSuchElementException {
        if (le == null)
            return new NoSuchElementException();
        le = le.previous;
        Object o = le.data;
        index--;
        return  o;
    }
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    public void set(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    public int nextIndex() {
        return index;
    }
    public int previousIndex() {
        return index-1;
    }
}