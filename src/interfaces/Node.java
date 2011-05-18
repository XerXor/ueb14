package interfaces;

/**
 * Konoten eines Baumes
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.05.2011
 */
public interface Node {

	/**
	 * Liefert das Datenelement des Knotens zurueck.
	 * 
	 * @return
	 */
	public Object getData();

	/**
	 * Linkes Kind
	 * 
	 * @return
	 */
	public Node getLeftChild();

	/**
	 * Rechtes Kind
	 * 
	 * @return
	 */
	public Node getRightChild();

	/**
	 * Handelt es sich um einen Root.
	 * 
	 * @return
	 */
	public boolean isRoot();

	/**
	 * Handel es sich um ein Blatt.
	 * 
	 * @return
	 */
	public boolean isLeave();

	/**
	 * Handelt es sich um einen Knoten.
	 * 
	 * @return
	 */
	public boolean isNode();

	/**
	 * Hohle den Bruder des Knoten.
	 * 
	 * @return
	 */
	public Node getBrother();

	/**
	 * Hohle den Onkelt des Elements.
	 * 
	 * @return
	 */
	public Node getUncle();

	/**
	 * Hohle den Opa des Elements.
	 * 
	 * @return
	 */
	public Node getGrandfather();

	/**
	 * Hole das Elternelement des Knoten
	 * 
	 * @return
	 */
	public Node getFather();
}
