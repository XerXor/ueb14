package exceptions;
/**
 * Wird geworden wenn eine Queue leer ist.
 * @author Daniel
 * @version 03.05.2011 20:09:30
 */
public class QueueIsEmptyException extends EigeneExceptions{
	private static final String ERROR_QUEUE_IS_EMPTY="Queue ist leer.";
	
	public QueueIsEmptyException()
	{
		super (ERROR_QUEUE_IS_EMPTY);
	}
	
	public QueueIsEmptyException(String additionalinformation)
	{
		super(ERROR_QUEUE_IS_EMPTY);
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(getMessage()).append(" ").append(additionalinformation);
		setMessage(getMessage()+" "+additionalinformation);
	}
}
