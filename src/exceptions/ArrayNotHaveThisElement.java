package exceptions;

/**
 * Wird geworfen wenn ein Element nicht in einem Array vorkommt.
 * @author Daniel
 * @author Alexei
 * @version 09.05.2011 12:50:55
 */
public class ArrayNotHaveThisElement extends EigeneExceptions{

	private final static String ERROR_ARRAY_DONT_HAVE_THIS_EMLEMENT="Element ist nicht im Array vorhanden.";
	
	public ArrayNotHaveThisElement()
	{
		super(ERROR_ARRAY_DONT_HAVE_THIS_EMLEMENT);
	}
}
