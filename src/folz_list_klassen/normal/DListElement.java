package folz_list_klassen.normal;

/**
 *  Listenelement, zeigt auf ein Inhaltsobjekt, auf das
 *  naechste und auf das vorige Listenelement
 */
public class DListElement {
    public Object data;
    public DListElement next;
    public DListElement previous;

    DListElement (Object o, DListElement n, DListElement p) {
        data = o;
        next = n;
        previous = p;
    }
}
