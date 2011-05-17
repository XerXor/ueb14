package de.folz_list_klassen.normal;
// Java-Beispiele
// Prof. Dr. H. G. Folz
// 2001
//
// Interface, das alle relevanten List-Operationen enthält

 
import java.util.Iterator;

public interface AbstractList extends java.lang.Iterable {

    public void add(int index, Object o);
    public void addFirst(Object o);
    public void addLast(Object o);
    public void clear();
    public boolean contains(Object o);
    public Object get(int index);
    public Object getFirst();
    public Object getLast();
    public int indexOf(Object o);
    public Iterator iterator();
    public void remove(Object o);
    public void remove(int index);
    public void removeFirst();
    public void removeLast();
    public Object set(int index, Object o);
    public int size();
}
