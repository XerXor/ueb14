package base;

import java.util.Comparator;

import exceptions.ArrayIsEmptyException;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;

/**
 * Verwaltung eines Baumes ueber ein Array.
 * @author Daniel
 * @author Alexei
 * @version 17.05.2011 13:10:55
 */
public class ArrayTree implements interfaces.ArrayTree,Comparator<Object>{

	BinaryTree dustbintree = new BinaryTree(this);
	Array array = null;
	
	public void ArrayTree(int size) throws IllegalArgumentException, QueueIsFullException
	{
		array = new  Array(size);
	}
	
	@Override
	public void add(Object object) throws IllegalArgumentException, QueueIsEmptyException {
		array.add(object);
		dustbintree.insert(object);
	}

	@Override
	public void remove(Object object) throws IllegalArgumentException, ArrayIsEmptyException, QueueIsFullException {
		array.remove(object);
		dustbintree.remove(object);
	}

	@Override
	public int size() {
		return array.getSize();
	}

	@Override
	public int compare(Object arg0, Object arg1) {
		if ((arg0 instanceof Person) & (arg1 instanceof Person))
		{
			Person person1 = (Person)arg0;
			Person person2 = (Person)arg1;
			return person1.getName().compareTo(person2.getName());
		}
		throw new ClassCastException("Only Persons are allowed.");
			
	}

	public String toString()
	{
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(dustbintree.toString());
		stringbuilder.append(array.toString());
		return stringbuilder.toString();
	}
}
