package interfaces;

import exceptions.TreeException;

/**
 * Alle Methoden eines Binaerbaumes.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.05.2011
 */
public interface BinaryTree {
	/**
	 * Einfuegen eines Elements in den Baum
	 * 
	 * @param object
	 */
	public void insert(Object object) throws TreeException;

	/**
	 * Loescht ein Element aus dem Tree.
	 * 
	 * @param object
	 * @throws IllegalArgumentException
	 */
	public void remove(Object object) throws TreeException;

	public int size();

	/**
	 * Gibt an, ob sich ein Element in dem Tree befindet oder nicht.
	 * 
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean contains(Object object) throws TreeException;

	/**
	 * Liefert ein Object aus dem Baum zurueck.
	 * 
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Object get(int index) throws TreeException;

	/**
	 * Liefert den Root des Baumes zurueck.
	 * 
	 * @return
	 */
	public Object getRoot();

	/**
	 * Liefert die Tiefe des Baumes zurueck.
	 * 
	 * @return
	 */
	int getLevels();

}
