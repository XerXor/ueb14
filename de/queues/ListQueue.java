package de.queues;

import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;
import folz_list_klassen.normal.DList;
import interfaces.AbstractQueues;

public class ListQueue  implements AbstractQueues {

	DList liste = new DList();
	
	/**
	 * Der StackQueue-Konstruktor
	 * @throws IllegalArgumentException
	 */
	public ListQueue()
	{
		
		
	}
	
	@Override
	public Object top() throws QueueIsEmptyException {
		if (isEmpty()) throw new QueueIsEmptyException();
		return liste.getFirst();
		
	}

	@Override
	public Object add(Object object) throws IllegalArgumentException,
			QueueIsFullException {
		if (object == null) throw new IllegalArgumentException("Object ist null.");
		liste.addFirst(object);
		return object;
	}

	@Override
	public Object remove() throws QueueIsEmptyException {
		if (isEmpty()) throw new QueueIsEmptyException();
		Object object = liste.getFirst();
		liste.removeFirst();
		return object;
	}

	@Override
	public int size() {
		return liste.size();
	}

	@Override
	public boolean isEmpty() {
		return liste.size()==0;
	}

}
