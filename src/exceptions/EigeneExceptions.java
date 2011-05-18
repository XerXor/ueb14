package exceptions;

/**
 * Eigene Exceptions-Klasse ist die Basis aller eigenen Exceptions.
 * @category Workproject: stacklists
 * @author Daniel
 * @version 27.04.2011 08:47:53
 */
public class EigeneExceptions extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String message;
	/**
	 * Konstruktor
	 */
	public EigeneExceptions()
	{
		super();
	}
	
	/**
	 * Erstelle eine Exception mit einer vorgegebenen Nachricht. 
	 * @param message
	 */
	public EigeneExceptions(String message)
	{
		super(message);
		this.message = message;
	}
	
	/**
	 * Legt die Message fest.
	 * @param message
	 */
	protected void setMessage(String message)
	{
		this.message = message;
	}

	
	
}
