package base;

import java.util.Comparator;

/**
 * A BinaryTree that works iterative. It needs a Comparator to insert Objects in
 * the proper order.
 * 
 * @author Alexei Felberg
 * @author Daniel Rhein
 * @version 09.05.2011
 */
public class BinaryTree implements interfaces.BinaryTree {
	private static final String OUT_DELIMITER = "\t";
	private static final String EXCEPTION_OUT_OF_BOUNDS = "Index is out of BinaryTree bounds!";
	private static final String EXCEPTION_RECEIVED_NULL = "Es wurde null an BinaryTree uebergeben.";
	private static final String EXCEPTION_IS_EMPTY = "BinaryTree is empty!";
	private TreeElement root;
	private int size = 0;
	private Comparator<Object> comparator;

	public BinaryTree(Comparator<Object> comparator)
			throws IllegalArgumentException {
		if (comparator == null)
			throw new IllegalArgumentException(EXCEPTION_RECEIVED_NULL);
		this.comparator = comparator;
	}

	@Override
	public void insert(Object object) throws IllegalArgumentException {
		if (object == null)
			throw new IllegalArgumentException(EXCEPTION_RECEIVED_NULL);
		if (size == 0) {
			root = new TreeElement(null, null, object);
			size++;
			return;
		}
		TreeElement currentElement = root;
		boolean again = true;
		while (again) {
			int vergleichsWert = comparator
					.compare(object, currentElement.data);
			if (vergleichsWert == -1) {
				if (currentElement.leftChild == null) {
					currentElement.leftChild = new TreeElement(currentElement,
							object);
					size++;
					again = false;
				} else
					currentElement = currentElement.leftChild;
			} else {
				if (currentElement.rightChild == null) {
					currentElement.rightChild = new TreeElement(currentElement,
							object);
					size++;
					again = false;
				} else
					currentElement = currentElement.rightChild;
			}
		}
	}

	@Override
	public void remove(Object object) throws IllegalArgumentException {
		if (object == null)
			throw new IllegalArgumentException(EXCEPTION_RECEIVED_NULL);
		if (size == 0)
			throw new RuntimeException(EXCEPTION_IS_EMPTY);
		TreeElement aktuellesElement = root;
		boolean weiter = true;
		while (weiter) {
			int vergleichsWert = comparator.compare(object,
					aktuellesElement.data);
			if (vergleichsWert < 0) {
				if (aktuellesElement.leftChild == null) {
					weiter = false;
				} else
					aktuellesElement = aktuellesElement.leftChild;
			} else if (vergleichsWert > 0) {
				if (aktuellesElement.rightChild == null) {
					weiter = false;
				} else
					aktuellesElement = aktuellesElement.rightChild;
			} else {
				if (vergleichsWert == 0) {
					System.out.println(aktuellesElement);
					removePosition(aktuellesElement);
					size--;
					return;
				}
			}
		}
	}

	/**
	 * Like {@link #insert(Object)}, but with TreeElements instead of Objects.
	 */
	protected void insert(TreeElement treeElement)
			throws IllegalArgumentException {
		if (treeElement == null)
			throw new IllegalArgumentException(EXCEPTION_RECEIVED_NULL);
		if (size == 0)
			root = treeElement;
		TreeElement aktuellesElement = root;
		boolean weiter = true;
		while (weiter) {
			int vergleichsWert = comparator.compare(treeElement.data,
					aktuellesElement.data);
			if (vergleichsWert < 0) {
				if (aktuellesElement.leftChild == null) {
					aktuellesElement.leftChild = treeElement;
					treeElement.father = aktuellesElement;
					weiter = false;
				} else
					aktuellesElement = aktuellesElement.leftChild;
			} else {
				if (aktuellesElement.rightChild == null) {
					aktuellesElement.rightChild = treeElement;
					treeElement.father = aktuellesElement;
					weiter = false;
				} else
					aktuellesElement = aktuellesElement.rightChild;
			}
		}
	}

	/**
	 * Removes the received TreeElement from the BinaryTree and reinsert
	 * children of the received TreeElement, if any exists.
	 * 
	 * @param aktuellesElement
	 *            the target TreeElement, which has to be removed.
	 */
	protected void removePosition(TreeElement aktuellesElement) {
		TreeElement rightChild;
		TreeElement leftChild;
		if (aktuellesElement.isRoot())
			if (aktuellesElement.rightChild != null) {
				root = aktuellesElement.rightChild;
				aktuellesElement.rightChild = null;
				root.father = null;
				if (aktuellesElement.leftChild != null) {
					aktuellesElement.leftChild.father = root;
					getMostLeftOf(root).leftChild = aktuellesElement.leftChild;
					aktuellesElement.leftChild = null;
				}
			} else if (aktuellesElement.leftChild != null) {
				leftChild = aktuellesElement.leftChild;
				aktuellesElement.leftChild.father = null;
				aktuellesElement.leftChild = null;
				root = leftChild;
			} else {
				if (aktuellesElement.equals(aktuellesElement.father.leftChild))
					aktuellesElement.father.leftChild = null;
				else
					aktuellesElement.father.rightChild = null;
				if (aktuellesElement.rightChild != null) {
					rightChild = aktuellesElement.rightChild;
					aktuellesElement.rightChild.father = null;
					aktuellesElement.rightChild = null;
					insert(rightChild);
				}
				if (aktuellesElement.leftChild != null) {
					leftChild = aktuellesElement.leftChild;
					aktuellesElement.leftChild.father = null;
					aktuellesElement.leftChild = null;
					insert(leftChild);
				}
				aktuellesElement.father = null;
			}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object object) throws IllegalArgumentException {
		if (object == null)
			throw new IllegalArgumentException(EXCEPTION_RECEIVED_NULL);
		if (size != 0) {
			TreeElement aktuellesElement = root;
			boolean weiter = true;
			while (weiter) {
				int vergleichsWert = comparator.compare(object,
						aktuellesElement.data);
				if (vergleichsWert == 0)
					return true;
				else if (vergleichsWert < 0) {
					if (aktuellesElement.leftChild == null) {
						weiter = false;
					} else
						aktuellesElement = aktuellesElement.leftChild;
				} else {
					if (aktuellesElement.rightChild == null) {
						weiter = false;
					} else
						aktuellesElement = aktuellesElement.rightChild;
				}
			}
		}
		return false;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException(EXCEPTION_OUT_OF_BOUNDS);
		int i = 0;
		TreeElement currentElement = getMostLeftOf(root);
		while (i < index && currentElement != null) {
			i++;
			if (i == index) {
				return currentElement.data;
			}
			currentElement = nextOf(currentElement);
		}
		return null; // this return is unreachable
	}

	protected TreeElement nextOf(TreeElement element) {
		if (size > 1) {
			TreeElement currentElement = element;
			if (currentElement.rightChild != null) {
				// returns the most left TreeElement (if any exists)of
				// targetElement's rightChild,
				// else returns targetElement's rightChild
				return getMostLeftOf(currentElement.rightChild);
			} else {
				int vergleichsWert = comparator.compare(currentElement.data,
						currentElement.father.data);
				if (vergleichsWert < 1) {
					return currentElement.father;
				} else {
					while (vergleichsWert == 1 && !currentElement.isRoot()) {
						vergleichsWert = comparator
								.compare(currentElement.data,
										currentElement.father.data);
						currentElement = currentElement.father;
					}
					return currentElement;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRoot() {
		if (size != 0)
			return root.data;
		return null;
	}

	@Override
	public int getLevels() {
		// TODO fertig machen!
		return 0;
	}

	/**
	 * Method searches the subTree for the most left TreeElement.
	 * 
	 * @return if any left child exists, returns the most left TreeElement of
	 *         the subTree, else returns root of the subTree.
	 */
	protected TreeElement getMostLeftOf(TreeElement subTree) {
		TreeElement mostLeft = subTree;
		while (mostLeft.leftChild != null)
			mostLeft = mostLeft.leftChild;
		return mostLeft;
	}

	/**
	 * Method searches the subTree for the most right TreeElement.
	 * 
	 * @return if any left child exists, returns the most right TreeElement of
	 *         the subTree, else returns root of the subTree.
	 */
	protected TreeElement getMostRightOf(TreeElement subTree) {
		TreeElement mostRight = subTree;
		while (mostRight.rightChild != null)
			mostRight = mostRight.rightChild;
		return mostRight;
	}

	public String toString() {
		TreeElement currentElement = getMostLeftOf(root);
		StringBuilder stringBuilder = new StringBuilder(
				currentElement.toString());
		int onIndex = 1;
		while (onIndex < size) {
			currentElement = nextOf(currentElement);
			stringBuilder.append(OUT_DELIMITER + currentElement);
			onIndex++;
		}
		return stringBuilder.toString();
	}
}