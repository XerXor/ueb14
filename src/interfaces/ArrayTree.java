package interfaces;

import exceptions.ArrayIsEmptyException;
import exceptions.ArrayNotHaveThisElementException;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;
import exceptions.TreeException;

/**
 * Baum und Tree gekapselt.
 * @author Daniel
 *
 */
public interface ArrayTree {
	/**
	 * Object hinzufügen
	 * @param object
	 * @throws IllegalArgumentException
	 * @throws QueueIsEmptyException 
	 */
	public void add(Object object) throws TreeException, QueueIsEmptyException;
	/**
	 * Entferne Element
	 * @param object
	 * @throws IllegalArgumentException
	 * @throws QueueIsFullException 
	 * @throws ArrayIsEmptyException 
	 * @throws ArrayNotHaveThisElementException 
	 */
	public void remove(Object object) throws TreeException, ArrayIsEmptyException, QueueIsFullException, ArrayNotHaveThisElementException;
	/**
	 * Groesse des Elements
	 * @return
	 */
	public int size(); 
}
