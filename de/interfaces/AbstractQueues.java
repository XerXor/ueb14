package de.interfaces;

import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;

/**
 * Beschreibt die Methoden einer Queue <br>
 * Unter Quue versteht man eine Warteschlange die nach dem FIFO-Prinzip
 * Elemente anordnet und verwaltet
 * @author Daniel
 * @version 03.05.2011 19:07:28
 */
public interface AbstractQueues {

	/**
	 * Gibt das Erste element in der Queue an.
	 * @return erstes Element der Queue oder null
	 * @throws QueueIsEmptyException
	 * Wenn die Queue leer ist.
	 */
	public Object top() throws QueueIsEmptyException;
	/**
	 * Fuegt ein Element in der Warteschlange ein.
	 * @param object
	 * @return hinzugefuegtes object
	 * @throws IllegalArgumentException
	 * wenn das uebergebene Object null ist.
	 * @throws QueueIsFullException
	 * wenn die Queue voll ist.
	 */
	public Object add(Object object) throws IllegalArgumentException, QueueIsFullException;
	/**
	 * Das erste Element verlaesst die Queue
	 * @return geloeschtes object
	 * @throws IllegalArgumentException
	 * wenn das Object null ist.
	 * @throws QueueIsEmptyException
	 * wenn die Queue leer ist.
	 */
	public Object remove() throws QueueIsEmptyException;
	
	/**
	 * Groesse der Queue
	 * @return groesse der Queue
	 */
	public int size();
	
	
	/**
	 * Gibt an, ob die Liste leer ist oder nicht.
	 * @return
	 */
	public boolean isEmpty();
}
