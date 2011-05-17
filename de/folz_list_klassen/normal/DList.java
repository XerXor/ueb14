package de.folz_list_klassen.normal;
// Java-Beispiele
// Prof. Dr. H. G. Folz
// 2001
//
// Doppelt-verkettete lineare Liste

 
import java.util.Iterator;
import java.util.NoSuchElementException;


/** Implementierung einer doppelt-verketteten linearen Liste
 */
public class DList implements AbstractList {
    public int size;
    public DListElement first;
    public DListElement last;

    /** Leere Liste erzeugen  */
    public DList() {
        first = null;
        last = null;
        size = 0;
    }

    /** private Methode, um ein Listenelement vor einem anderen
     *  einzufuegen
     */
    private DListElement addBefore(Object o, DListElement le) {
        DListElement neu;
        // Liste noch leer?
        if (le == null) {
            neu = new DListElement(o, null, null);
            first = neu;
            last = neu;
            size = 1;
        } else {
            neu = new DListElement(o, le, le.previous);
            if (neu.previous != null)
                neu.previous.next = neu;
            else
                first = neu;
            neu.next.previous = neu;
            size++;
        }
        return neu;
    }

    /** gib die Referenz auf das index-te Element zurueck */
    private DListElement entry(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index:" + index +
                                                ", size:" + size);
        DListElement le = first;
        // gehe bis zum Element index
        int i = 0;
        while (i < index) {
            le = le.next;
            i++;
        }
        return le;
    }


    public void add(int index, Object o) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index:" + index +
                                                ", size:" + size);
        if (index == size)
            addLast(o);
        else {
            addBefore(o, entry(index));
        }
    }

    /** Element am Anfang der Liste einfuegen   */
    public void addFirst(Object o) {
        addBefore(o, first);
    }

    /** Element am Ende der Liste anhaengen   */
    public void addLast(Object o) {
        if (last == null)
            addFirst(o);
        else {
            last.next = new DListElement(o, null, last);
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
        return new DListIterator(this, 0);
    }

     public java.util.ListIterator iterator(int index) {
        return new DListIterator(this, index);
    }

    /** Objekt an der Stelle index zurueckgeben */
    public Object get(int index) {
        return entry(index).data;
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
        DListElement le = first;
        while (le != null) {
            if (le.data.equals(o))
                return index;
            le = le.next;
            index++;
        }
        return -1;
     }
     /** allgemeine private Methode zum Entfernen eines Elements */
     private void remove(DListElement le) {
         if (le == null)
             throw new NoSuchElementException("remove(null)");
         // erstes Element ?
         if (le.previous == null) {
             first = le.next;
             // letztes Element?
             if (le.next == null)
                 last = null;
             else
                 le.next.previous = null;
         } else {
             le.previous.next = le.next;
             // letztes Element?
             if (le.next == null)
                 last = le.previous;
             else
                 le.next.previous = le.previous;
         }
         size--;
     }
     
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
            DListElement le = first;
            // gehe bis zum Element index-1
            for (int i = 0; i < index-1; i++)
                le = le.next;
            // Das i-te Element einfach überspringen
            le.next = le.next.next;
            le.next.previous = le;
            size--;
        }
    }

    /** Entferne Objekt         */
    public void remove(Object o) {
        if (size == 0 || o == null ) return;
        DListElement le = first;
         // gehe bis zum gesuchten Element
        while (le != null) {
            if (le.data.equals(o)) {
                remove(le);
                break;
            }
            le = le.next;
        }
    }

    public void removeFirst() {
        if (size != 0)
            remove(first);
    }

    public void removeLast() {
        if (size != 0)
            remove(last);
    }

    /** Wert eines Elementes veraendern */
    public Object set(int index, Object o) {
        DListElement le = entry(index);
        Object alterWert = le.data;
        le.data = o;
        return alterWert;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        DListElement le = first;
        while (le != null) {
            sb.append(" <-> ").append(le.data.toString());
            le = le.next;
        }
        return sb.toString();
    }
}




