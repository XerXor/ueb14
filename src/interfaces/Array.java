package interfaces;

/**
 * Interface fuer den Array.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.05.2011
 */
public interface Array {
	/**
	 * Fuegt ein Object in das Array ein.
	 * 
	 * @param object
	 * @throws IllegalArgumentException
	 */
	public void add(Object object) throws IllegalArgumentException;

	/**
	 * Entfernt ein Objekt aus dem Array.
	 * 
	 * @param object
	 * @throws IllegalArgumentException
	 */
	public void remove(Object object) throws IllegalArgumentException;

	/**
	 * Liefert ein Element aus dem Array
	 * 
	 * @param index
	 * @throws IllegalArgumentException
	 */
	public Object getElement(int index) throws IllegalArgumentException;

	/**
	 * Liefert die groese des Arrays zurueck.
	 * 
	 * @return
	 */
	public int getSize();

	/**
	 * Gibt an, ob das Array voll oder nicht voll ist.
	 * 
	 * @return true, wenn der Array leer ist.
	 */
	public boolean isFull();

	/**
	 * Gibt an, ob ein Object in dem Array vorhanden ist, oder nicht.
	 * 
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean contains(Object object) throws IllegalArgumentException;
}
