package interfaces;

import exceptions.ArrayIsEmptyException;
import exceptions.ArrayIsFullException;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;

/**
 * Interface fuer den Array.
 * @author Daniel
 *
 */
public interface Array {
	
	/**
	 * Fuegt ein Object in das Array ein.
	 * @param object
	 * @throws IllegalArgumentException
	 * @throws QueueIsEmptyException 
	 */
	public void add(Object object) throws IllegalArgumentException,ArrayIsFullException, QueueIsEmptyException;
	/**
	 * Entfernt ein Objekt aus dem Array.
	 * @param object
	 * @throws IllegalArgumentException
	 * @throws QueueIsFullException 
	 */
	public void remove(Object object) throws IllegalArgumentException,ArrayIsEmptyException, QueueIsFullException;
	/**
	 * Liefert ein Element aus dem Array
	 * @param index
	 * @throws IllegalArgumentException
	 */
	public Object getElement(int index) throws IllegalArgumentException;
	/**
	 * Liefert die groese des Arrays zurueck.
	 * @return
	 */
	public int getSize();
	/**
	 * Gibt an, ob das Array voll oder nicht voll ist.
	 * @return true, wenn der Array leer ist.
	 */
	public boolean isFull();
	/**
	 * Gibt an, ob das Array leer ist oder nicht.
	 * @return true, wenn der Array leer ist.
	 */
	public boolean isEmpty();
	/**
	 * Gibt an, ob ein Object in dem Array vorhanden ist, oder nicht.
	 * 
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean contains(Object object) throws IllegalArgumentException;
}
