package de.exceptions;

/**
 * Wird geworfen wenn der Array voll ist.
 * @author Daniel
 * @author Alexei
 * @version 09.05.2011 12:49:32
 */
public class ArrayIsFullException extends EigeneExceptions{

	private static final String ERROR_ARRAY_IS_FULL_EXCEPTION = "Der Array ist voll.";
	public ArrayIsFullException()
	{
		super(ERROR_ARRAY_IS_FULL_EXCEPTION);
	}
}
