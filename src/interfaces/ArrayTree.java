package interfaces;

/**
 * Baum und Tree gekapselt.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.05.2011
 */
public interface ArrayTree {
	/**
	 * Object hinzufügen
	 * 
	 * @param object
	 * @throws IllegalArgumentException
	 */
	public void add(Object object) throws IllegalArgumentException;

	/**
	 * Entferne Element
	 * 
	 * @param object
	 * @throws IllegalArgumentException
	 */
	public void remove(Object object) throws IllegalArgumentException;

	/**
	 * Groesse des Elements
	 * 
	 * @return
	 */
	public int size();
}
