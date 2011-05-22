package base;

import java.util.Comparator;

import exceptions.ArrayIsEmptyException;
import exceptions.ArrayNotHaveThisElementException;
import exceptions.QueueIsEmptyException;
import exceptions.QueueIsFullException;
import exceptions.TreeException;

/**
 * Verwaltung eines Baumes ueber ein Array.
 * @author Daniel
 * @author Alexei
 * @version 17.05.2011 13:10:55
 */
public class ArrayTree implements interfaces.ArrayTree,Comparator<Object>{

	private static final String STRING_NOT_PERSON_EXCEPTION = "Only Persons are allowed.";
	private static final String STRING_NEW_LINE = "\n";
	BinaryTree dustbintree;
	Array array = null;
	
	public ArrayTree(int size) throws IllegalArgumentException,QueueIsFullException,TreeException 
	{
			dustbintree = new BinaryTree(this);
			array = new  Array(size);
	}
	
	@Override
	public void add(Object object) throws TreeException, QueueIsEmptyException {
		array.add(object);
		dustbintree.insert(object);
	}

	@Override
	public void remove(Object object) throws TreeException, ArrayIsEmptyException, QueueIsFullException, ArrayNotHaveThisElementException {
		if (array.contains(object))
		{
			array.remove(object);
			dustbintree.remove(object);
		}
		else
		{
			throw new ArrayNotHaveThisElementException(object.toString() + "ist nicht vorhanden. Und kann daher nicht geloescht werden.");
		}
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
		throw new ClassCastException(STRING_NOT_PERSON_EXCEPTION);
	}
	/**
	 * Liefert ein String, der den Baum repraesentiert. Es liegen 3 Varianten vor, auf die der
	 * Baum ausgegeben werden kann.<br>
	 * <blockquote>
	 * Variationen:
	 * <li>1 : Wurzel oben, Baum wird nach unten aufgebaut.</li>
	 * <li>2 : Wurzel unten, Baum wird nach oben aufgebaut.</li>
	 * <li>3 : Wurzel links, Baum wird nach rechts aufgebaut.</li>
	 * </blockquote>
	 * @param variation - eine der oben genannten Variationen. Moeglich ist 1, 2 und 3.
	 * @return ein String der den Baum repraesentiert, nach einer der 3 moeglichen Variationen.
	 */
	public String printVariation(int variation){
		String treeAsString="";
		switch(variation){
		case 1:
			treeAsString = dustbintree.printTreeRootDown();
			break;
		case 2:
			treeAsString = dustbintree.printTreeRootUp();
			break;
		case 3:
			treeAsString = dustbintree.printTreeRootLeft();
			break;		
		}
		return treeAsString;
	}
	
	public String toString()
	{
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(dustbintree.toString());
		stringbuilder.append(STRING_NEW_LINE);
		stringbuilder.append(array.toString());
		return stringbuilder.toString();
	}
	
	/**
	 * Gibt an, ob die Werte null sind.
	 * @return
	 */
	public boolean isEmpty()
	{
		return (array.isEmpty() && dustbintree.isEmpty());
	}
}
