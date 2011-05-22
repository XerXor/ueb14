package base;

import queues.ArrayQueue;
import exceptions.ArrayIsEmptyException;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;

/**
 * Bilded einen Array ab.
 * @author Daniel
 * @author Alexei
 * @version 09.05.2011 11:39:21
 */
public class Array implements interfaces.Array {

	private static final String ERROR_ARRAY_IS_EMPTY = "Array is empty";
	private static final int GREATER_THAN_MIN = 0;
	private static final String ERROR_INDEX_GROESSER_ZAHL_NULL = "Index muss groesser als 0 sein.";
	/**
	 * Fehlermeldung, wenn ein Object null ist.
	 */
	public static final String ERROR_OBJECT_NULL = "Object darf nicht null sein.";
	/**
	 * Fehlermeldung wenn ein Object im Array nicht gefunden wurde.
	 */
	public static final String ERROR_OBJECT_NOT_FOUND = "Object existiert nicht im Array";
	/**
	 * Fehlermeldung wenn ein mindest index nicht existiert.
	 */
	public static final String ERROR_MIN_INDEX = "Der Index-Wert muss groesser als 0 sein.";
	/**
	 * Fehlermeldung wenn ein Array vollkommen belegt ist.
	 */
	public static final String ERROR_IS_FULL = "Array ist vollkommen mit Werten besetzt.";

	/**
	 * Mindestwert fuer den Index
	 */
	public static final int MIN_VALUE = 1;
	public static final String ERROR_QUEUE_SIZE_MINIMUM = "Queue groesse muss groesser als " + MIN_VALUE + " sein.";
	/**
	 * Defaultgroesse.
	 */
	public static final int MIN_DEFAULT_SIZE = 100;

	private ArrayQueue queue = null;
	
	private int max_size = 0;
	private int size = 0;

	/**
	 * Alle Elemente in dem Array
	 */
	private Object elements[] = null;

	/**
	 * Konstruktor <br>
	 * Default-Groese ist 100
	 * @throws QueueIsFullException 
	 * @throws IllegalArgumentException 
	 */
	public Array() throws IllegalArgumentException, QueueIsFullException {
		initQueue(MIN_DEFAULT_SIZE);
		elements = new Object[MIN_DEFAULT_SIZE];
		max_size = MIN_DEFAULT_SIZE;
	}

	/**
	 * Initialsiert eine Queue mit 
	 * entsprechenden Werten.
	 * Diese Werte stellen den Index des Arrays dar.
	 * @param size
	 * @throws IllegalArgumentException
	 * wird geworfen wenn die Arraysize < = 0 ist.
	 * @throws QueueIsFullException
	 * wenn die Queue voll ist wird dieser Fehler geworfen. 
	 */
	private void initQueue(int size) throws IllegalArgumentException, QueueIsFullException
	{
		if (size<MIN_VALUE) throw new IllegalArgumentException(ERROR_QUEUE_SIZE_MINIMUM);
		queue = new ArrayQueue(size);
		for (int i =0;i<size;i++)
		{
			queue.add(i);
		}
	}
	
	/**
	 * Konstruktor <br>
	 * @param size groesse des Arrays
	 * @throws QueueIsFullException 
	 * 
	 */
	public Array(int size) throws IllegalArgumentException, QueueIsFullException {
		if (size < MIN_VALUE)
			throw new IllegalArgumentException(ERROR_MIN_INDEX);
		initQueue(size);
		elements = new Object[size];
		max_size = size;
	}

	@Override
	public void add(Object object) throws IllegalArgumentException, QueueIsEmptyException {
		if (isFull())
			throw new IllegalArgumentException(ERROR_IS_FULL);
			int index = (Integer) queue.remove();
				elements[index] = object;
			size++;
		}


	/**
	 * Liefert das Element eines Objects
	 * 
	 * @param object
	 * @return
	 */
	public int getIndex(Object object) throws IllegalArgumentException {
		if (object == null)
			throw new IllegalArgumentException(ERROR_OBJECT_NULL);
		int counter =0;
		for (int i = 0; i < elements.length; i++) {
			if (elements[i]!=null)
			{
				counter++;
			}
			if (elements[i] == object) {
				return counter;
			}
		}
		return -1;
	}

	
	/**
	 * Liefert das Element eines Objects
	 * 
	 * @param object
	 * @return
	 */
	private int getInternalIndex(Object object) throws IllegalArgumentException {
		if (object == null)
			throw new IllegalArgumentException(ERROR_OBJECT_NULL);
		for (int i = 0; i < elements.length; i++) {
			
			if (elements[i].equals(object)) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public void remove(Object object) throws IllegalArgumentException,ArrayIsEmptyException, QueueIsFullException {

		int index = 0;
		if (!isEmpty()) {
			index = getInternalIndex(object);
			if (index >= 0) {
				elements[index] = null;
				queue.add(index);
				size--;
			} else {
				throw new ArrayIsEmptyException();
			}
		}
	}

	@Override
	public Object getElement(int index) throws IllegalArgumentException {
		if (index < GREATER_THAN_MIN) throw new IllegalArgumentException(ERROR_INDEX_GROESSER_ZAHL_NULL);
		if (isEmpty()) throw new IllegalArgumentException(ERROR_ARRAY_IS_EMPTY);
		int counter = 0;
		for (int i =0;i<elements.length;i++)
		{
			if (elements[i]!=null)
			{
				if (counter == index)
				{
					return elements[i];
				}
				else
				{
					counter ++;
				}
			}
		}
		throw new IllegalArgumentException("Kein Element mit dem Index " + index + "vorhanden.");
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isFull() {
		return size==max_size;
	}

	@Override
	public boolean contains(Object object) throws IllegalArgumentException {
		if (getIndex(object)>=0) return true;
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == GREATER_THAN_MIN;
	}
	
	public String toString()
	{
		StringBuilder stringbuilder = new StringBuilder();
		if (isEmpty())
			stringbuilder.append("Array ist leer");
		else
		{
			stringbuilder.append("Arraysize:").append(getSize()).append(" ");
			for (int i=0;i<size;i++)
			{
				stringbuilder.append(i).append(".");
				stringbuilder.append(getElement(i)).append("->");
			}
		}
		if (queue.isEmpty())
		{
			stringbuilder.append("Queue ist leer.");
		}
		else
		{
			stringbuilder.append(queue.toString());
		}
		return stringbuilder.toString();
	}
	
}
