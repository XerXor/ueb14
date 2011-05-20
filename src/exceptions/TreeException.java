package exceptions;

/**
 * Eigene Exceptions-Klasse ist die Basis aller eigenen Exceptions.
 * 
 * @category Workproject: stacklists
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 20.05.2011 20:59:53
 */
public class TreeException extends EigeneExceptions {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 */
	public TreeException() {
		super();
	}

	/**
	 * Erstelle eine Exception mit einer vorgegebenen Nachricht.
	 * 
	 * @param message
	 */
	public TreeException(String message) {
		super(message);
	}
}
