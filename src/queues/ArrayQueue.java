package queues;

import interfaces.AbstractQueues;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;

/**
 * Hier wird eine Queue implementiert
 * 
 * @author Daniel
 * @version 03.05.2011 19:27:08
 */
public class ArrayQueue implements AbstractQueues {

	private static final int MIN_VALUE = 1; //Wert den die Queue-Groesse minimum haben sollte.
	private static final String OBJECT_NOT_NULL = "Objekt darf nicht null sein."; //Objekt darf nich null sein
	private static final String GREATER_THAN_NULL = "Queue-Groesse sollte mindestens >= " + MIN_VALUE + " sein.";
	private static final int MAX_DEFAULT_VALUE=100;
	private static final String DELIMITER = "<->";
	private int max_size = 0; // maximale anzahl der Elemente in einer Queue
	private Object elements[] = null;// elemente der queue
	private int size = 0; // groesse der Queue
	
	
	/**
	 * Der StackQueue-Konstruktor
	 * @throws IllegalArgumentException
	 */
	public ArrayQueue()
	{
		
			max_size =MAX_DEFAULT_VALUE;
			elements = new Object[MAX_DEFAULT_VALUE];
	}
	
	/**
	 * Der ArrayQueue-Konstruktor
	 * @param size
	 * @throws IllegalArgumentException
	 */
	public ArrayQueue(int size) throws IllegalArgumentException
	{
		if (size>=MIN_VALUE) 
		{
			max_size =size;
			elements = new Object[max_size];
			
		}
		else
		{
			throw new IllegalArgumentException(GREATER_THAN_NULL);
		}
	}
	
	@Override
	public Object top() {
		if (isEmpty())
			return null;
		return elements[size];
	}

	@Override
	public Object add(Object object) throws IllegalArgumentException, QueueIsFullException{
		checkObjectNull(object);
		elements[size] = object;
		size++;
		return object;
	}

	/**
	 * Prueft, ob das angegebene Objekt null ist oder nicht.<br>
	 * Ist das Objekt null, dann wird eine IllegalArgumentException geworfen.
	 * 
	 * @param object
	 * @throws IllegalArgumentException
	 */
	private void checkObjectNull(Object object) throws IllegalArgumentException {
		if (object == null)
			throw new IllegalArgumentException(OBJECT_NOT_NULL);

	}

	/**
	 * Gibt an ob die Queue gefuellt, oder leer ist.
	 * 
	 * @return
	 */
	public boolean isFull() {
		if (max_size == 0)
			return false;
		return size() == max_size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * Prueft ob es schon ein Object gibt.
	 * @return
	 */
	public boolean contains(Object object) throws IllegalArgumentException
	{
		if (isEmpty()) return false;
		if (object== null) throw new IllegalArgumentException(OBJECT_NOT_NULL);
		for (int i=0;i<size();i++)
		{
			if (elements[i].equals(object))
				return true;
			
		}
		return false;
	}
	
	@Override
	public Object remove() throws QueueIsEmptyException {
		if (isEmpty())
			throw new QueueIsEmptyException();
		
		Object object = elements[0];
		elements[0] = null;
		//Alle weiteren Objecte nach vorne verschieben.
		Object puffer=null;
		for (int i=1;i<size;i++)
		{
			puffer = elements[i];
			elements[i]=null;
			elements[i-1]=puffer;
		}
		size--;
		return object;
	}
	
	@Override
	public String toString() {
		StringBuilder stringbuilder= new StringBuilder();
		stringbuilder.append("Queuegroesse:").append(size());
		stringbuilder.append(" ");
		if (isEmpty())
		{
			stringbuilder.append("Die Queue ist leer.");
		}
		for (int i = 0;i<size();i++)
		{
			stringbuilder.append(elements[i]);
			stringbuilder.append(DELIMITER);	
		}
		return stringbuilder.toString();
	}

}
