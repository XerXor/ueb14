package de.exceptions;
/**
 * Wird geworfen wenn eine Queue voll ist.
 * @author Daniel
 * @version 03.05.2011 20:03:45
 */
public class QueueIsFullException extends EigeneExceptions{
	private static final String ERROR_QUEUE_IS_FULL="Queue ist voll.";
	
	public QueueIsFullException()
	{
		super (ERROR_QUEUE_IS_FULL);
	}
	
	public QueueIsFullException(String additionalinformation)
	{
		super(ERROR_QUEUE_IS_FULL);
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(getMessage()).append(" ").append(additionalinformation);
		setMessage(getMessage()+" "+additionalinformation);
	}
}
