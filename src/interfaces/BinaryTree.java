package interfaces;

/**
 * Alle Methoden eines Binaerbaumes.
 * @author Daniel
 *
 */
public interface BinaryTree {
	/**
	 * Einfuegen eines Elements in den Baum
	 * @param object
	 */
	public void insert(Object object) throws IllegalArgumentException;
	/**
	 * Loescht ein Element aus dem Tree.
	 * @param object
	 * @throws IllegalArgumentException
	 */
	public void remove(Object object) throws IllegalArgumentException;
	public int size();
	/**
	 * Gibt an, ob sich ein Element in dem Tree befindet oder nicht.
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean contains(Object object) throws IllegalArgumentException;
	
	/**
	 * Liefert ein Object aus dem Baum zurueck.
	 * @param index
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Object get(int index) throws IllegalArgumentException;
	
	/**
	 * Liefert den Root des Baumes zurueck.
	 * @return
	 */
	public Object getRoot();
	
	/**
	 * Liefert die Tiefe des Baumes zurueck.
	 * @return
	 */
	int getLevels();
	
}
