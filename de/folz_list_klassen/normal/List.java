package de.folz_list_klassen.normal;
 // Java-Beispiele
// Prof. Dr. H. G. Folz
// 2001
//
//
 
import java.util.Iterator;
import java.util.NoSuchElementException;


/** Implementierung einer einfach-verketteten linearen Liste
 */
public class List implements AbstractList {
    protected int size;
    protected ListElement first;
    protected ListElement last;

    /** Leere Liste erzeugen  */
    public List() {
        first = null;
        last = null;
        size = 0;
    }

    public void add(int index, Object o) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index:" + index +
                                                ", size:" + size);
        if (index == size)
            addLast(o);
        else if (index == 0)
            addFirst(o);
        else {
            // beginne beim erstem Element
            ListElement le = first;
            // gehe bis zum Element index-1
            for (int i = 0; i < index-1; i++)
                le = le.next;
            // dort anhaengen
            ListElement tmp = new ListElement(o, le.next);
            le.next = tmp;
            size++;
        }
    }

    /** Element am Anfang der Liste einfuegen   */
    public void addFirst(Object o) {
        ListElement tmp = new ListElement(o, first);
        first = tmp;
        size++;
        if (last == null)
           last = first;
    }

    /** Element am Ende der Liste anhaengen   */
    public void addLast(Object o) {
        if (last == null)
            addFirst(o);
        else {
            last.next = new ListElement(o, null);
            last = last.next;
            size++;
        }
    }

    /** Komplette Liste loeschen        */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /** Enthaelt die Liste das Objekt? */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /** ein Aufzaehlungsobjekt zurueckgeben, mit dem über die Liste
     *  iteriert werden kann
     */
    public Iterator iterator() {
        return new ListItr(first);
    }

    /** Objekt an der Stelle index zurueckgeben */
    public Object get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index:" + index +
                                                ", size:" + size);
        ListElement le = first;
        int i = 0;
        while (i < index) {
            le = le.next;
            i++;
        }
        return le.data;
    }

    public Object getFirst() {
        if (size == 0)
            throw new NoSuchElementException();
        return first.data;
    }

    public Object getLast() {
        if (size == 0)
            throw new NoSuchElementException();
        return last.data;
    }

    /** An Welcher Stelle steht das Objekt?     */
    public int indexOf(Object o) {
        int index = 0;
        ListElement le = first;
        while (le != null) {
            if (le.data.equals(o))
                return index;
            le = le.next;
            index++;
        }
        return -1;
     }

    /** Entferne Objekt         */
    public void remove(Object o) {
        if (size == 0 || o == null ) return;
        if (first.data.equals(o))
            removeFirst();
        else if (last.data.equals(o))
            removeLast();
        else {
            ListElement le = first;
            // gehe bis zum Element vor dem gesuchten
            while (le.next != last) {
                if (le.next.data.equals(o)) {
                    le.next = le.next.next;
                    size--;
                    break;
                }
                le = le.next;
            }
        }
    }
    /**
     * Lösche Element an der Stelle index
     */
    public void remove(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index:" + index +
                                                ", size:" + size);
        if (index == size-1)
            removeLast();
        else if (index == 0)
            removeFirst();
        else {
            // beginne beim erstem Element
            ListElement le = first;
            // gehe bis zum Element index-1
            for (int i = 0; i < index-1; i++)
                le = le.next;
            // Das i-te Element einfach überspringen
            le.next = le.next.next;
            size--;
        }
    }

    public void removeFirst() {
        if (size == 0) return;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }
        size--;
    }

    public void removeLast() {
        if (size == 0) return;	// leere Liste ?
        if (first == last) {    // Nur ein Listenelement
            first = null;
            last = null;
        } else {
            ListElement tmp = first;
            // vorletztes Element suchen
            while (tmp.next != last)
                tmp = tmp.next;
            last = tmp;// tmp zeigt nun auf das vorletzte Listenelement
            last.next = null;
        }
        size--;
    }

    /** Wert eines Elementes veraendern, den alten Wert zurueckgeben */
    public Object set(int index, Object o) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index:" + index +
                                                ", size:" + size);
        // beginne beim erstem Element
        ListElement le = first;
        // gehe bis zum Element index
        for (int i = 0; i < index; i++)
                le = le.next;
        Object alterWert = le.data;
        le.data = o;
        return alterWert;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        ListElement le = first;
        while (le != null) {
            sb.append(" -> ").append(le.data.toString());
            le = le.next;
        }
        return sb.toString();
    }
}


/**
 *  Listenelement, zeigt auf ein Inhaltsobjekt und auf das
 *  naechste Listenelement
 */
class ListElement {
    Object data;
    ListElement next;

    ListElement (Object o, ListElement le) {
        data = o;
        next = le;
    }
}

class ListItr implements Iterator {
    private ListElement le;
    public ListItr(ListElement start) {
        le = start;
    }
    public boolean hasNext() {
        return le != null;
    }

    public Object next() throws NoSuchElementException {
        if (le == null)
            return new NoSuchElementException();
        Object o = le.data;
        le = le.next;
        return  o;
    }
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}

