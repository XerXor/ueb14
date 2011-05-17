package base;

import interfaces.Node;

//TODO Java-Doc ergaenzen!!
/**
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.05.2011
 */
public class TreeElement implements Node {

	protected TreeElement leftChild;
	protected TreeElement rightChild;
	protected TreeElement father;
	protected Object data;

	/**
	 * Constructor with a Father-Reference. Useful for iterative methods.
	 * 
	 * @param leftChild
	 *            a TreeElement as leftChild or null.
	 * @param rightChild
	 *            a TreeElement as rightChild or null.
	 * @param data
	 *            an Object that should be carried by this TreeElement.
	 * @param father
	 *            the Father-Element of this TreeElement.
	 */
	public TreeElement(TreeElement father, TreeElement leftChild,
			TreeElement rightChild, Object data) {
		if (data == null)
			throw new NullPointerException();
		this.father = father;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.data = data;
	}

	/**
	 * Constructor without a Father-Reference.
	 * 
	 * @param leftChild
	 *            a TreeElement as leftChild or null.
	 * @param rightChild
	 *            a TreeElement as rightChild or null.
	 * @param data
	 *            an Object that should be carried by this TreeElement.
	 */
	public TreeElement(TreeElement leftChild, TreeElement rightChild,
			Object data) {
		this(null, leftChild, rightChild, data);
	}

	public TreeElement(Object data) {

		this(null, null, null, data);
	}

	public TreeElement(TreeElement father, Object data) {
		this(father, null, null, data);
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public Node getLeftChild() {
		return leftChild;
	}

	@Override
	public Node getRightChild() {
		return rightChild;
	}

	@Override
	public boolean isRoot() {
		return father == null;
	}

	@Override
	public boolean isLeave() {
		if (isRoot() || rightChild != null || leftChild != null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isNode() {
		return !isLeave() && !isRoot();
	}

	@Override
	public Node getBrother() {
		try {
			if (father.rightChild.equals(this)) {
				return father.leftChild;
			} else {
				return father.rightChild;
			}
		} catch (NullPointerException nullPointerException) {
			return null;
		}
	}

	@Override
	public Node getUncle() {
		try {
			if (father.father.rightChild.equals(father)) {
				return father.father.leftChild;
			} else {
				return father.father.rightChild;
			}
		} catch (NullPointerException nullPointerException) {
			return null;
		}
	}

	@Override
	public Node getGrandfather() {
		try {
			return father.father;
		} catch (NullPointerException nullPointerException) {
			return null;
		}
	}

	@Override
	public Node getFather() {
		return father;
	}

	public String toString() {
		return data.toString();
	}
}
