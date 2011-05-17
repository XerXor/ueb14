package de.interfaces;

import exceptions.ArrayIsEmptyException;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;

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
	public void add(Object object) throws IllegalArgumentException, QueueIsEmptyException;
	/**
	 * Entferne Element
	 * @param object
	 * @throws IllegalArgumentException
	 * @throws QueueIsFullException 
	 * @throws ArrayIsEmptyException 
	 */
	public void remove(Object object) throws IllegalArgumentException, ArrayIsEmptyException, QueueIsFullException;
	/**
	 * Groesse des Elements
	 * @return
	 */
	public int size(); 
}
