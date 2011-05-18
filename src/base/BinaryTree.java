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
	private int maxElementLength = 0;
	private Comparator comparator;

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
		if (root == null)
			return 0;
		DList totalLevels = new DList();
		DList currentLevel = new DList();
		currentLevel.addFirst(root);
		totalLevels.addFirst(currentLevel);
		for (int i = 0; i < totalLevels.size; i++) {
			DList newLevel = new DList();
			currentLevel = (DList) totalLevels.getLast();
			DListElement listElement = (DListElement) currentLevel.first;
			while (listElement != null && listElement.data != null) {
				if (((TreeElement) listElement.data).leftChild != null)
					newLevel.addLast(((TreeElement) listElement.data).leftChild);
				if (((TreeElement) listElement.data).rightChild != null)
					newLevel.addLast(((TreeElement) listElement.data).rightChild);
				listElement = listElement.next;
			}
			if (newLevel.size != 0)
				totalLevels.addLast(newLevel);
		}
		return totalLevels.size;
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

	@Override
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

	public String printTreeRootUp() {
		DList totalLevels = treeToListOfArrays();
		totalLevels.removeLast();
		DList treeAsString = treeToString(totalLevels, maxElementLength);
		DListElement aktuellesElement = treeAsString.first;
		StringBuilder ausgabe = new StringBuilder();
		while (aktuellesElement != null) {
			ausgabe.append(aktuellesElement.data);
			if (aktuellesElement.next != null)
				ausgabe.append("\n");
			aktuellesElement = aktuellesElement.next;
		}
		return ausgabe.toString();
	}

	public String printTreeRootDown() {
		DList totalLevels = treeToListOfArrays();
		totalLevels.removeLast();
		DList treeAsString = treeToString(totalLevels, maxElementLength);
		DListElement aktuellesElement = treeAsString.last;
		StringBuilder ausgabe = new StringBuilder();
		while (aktuellesElement != null) {
			ausgabe.append(aktuellesElement.data);
			if (aktuellesElement.previous != null)
				ausgabe.append("\n");
			aktuellesElement = aktuellesElement.previous;
		}
		return ausgabe.toString();
	}

	/**
	 * @return
	 */
	private DList treeToListOfArrays() {
		if (root == null)
			return null;
		DList totalLevels = new DList();
		Object[] currentLevel = new Object[1];
		currentLevel[0] = root;
		totalLevels.addLast(currentLevel);
		int newLevelSize = 1;
		for (int i = 0; i < totalLevels.size; i++) {
			newLevelSize = newLevelSize * 2;
			Object[] newLevel = new Object[newLevelSize];
			currentLevel = (Object[]) totalLevels.getLast();
			int currentElement = 0;
			int addIn = 0;
			boolean hinzufuegen = false;
			while (currentElement < currentLevel.length) {
				TreeElement listElement = (TreeElement) currentLevel[currentElement];
				if (listElement != null) {
					int elementsLength = listElement.data.toString()
							.toCharArray().length;
					if (elementsLength > maxElementLength) // get max
						maxElementLength = elementsLength;// element-size
					newLevel[addIn] = listElement.leftChild;
					addIn++;
					newLevel[addIn] = listElement.rightChild;
					hinzufuegen = true;
				} else {
					newLevel[addIn] = null;
					addIn++;
					newLevel[addIn] = null;
				}
				currentElement++;
				addIn++;
			}
			if (hinzufuegen) {
				totalLevels.addLast(newLevel);
			}
		}
		return totalLevels;
	}

	/**
	 * @param totalLevels
	 * @param maxElementLength
	 * @return
	 */
	private DList treeToString(DList totalLevels, int maxElementLength) {
		Object[] currentLevel;
		DList treeAsString = new DList();
		DListElement element = totalLevels.first;
		int tabs = totalLevels.size;
		maxElementLength = maxElementLength / 8;
		StringBuilder maxElementTabs = new StringBuilder();
		for (int tabsDone = 0; tabsDone <= maxElementLength; tabsDone++) {
			maxElementTabs.append("\t");
		}
		while (element != null) {
			StringBuilder sb = new StringBuilder();
			StringBuilder delimiter = new StringBuilder();
			for (int j = 0; j < (2 << tabs - 2) - 1; j++) {
				delimiter.append(maxElementTabs);
			}
			if (tabs > 0) {
				sb.append(delimiter);
			}
			delimiter.append(maxElementTabs);
			delimiter.append(delimiter);
			currentLevel = (Object[]) element.data;
			for (int i = 0; i < currentLevel.length; i++) {
				if (currentLevel[i] != null) {
					int elementLength = (currentLevel[i]).toString()
							.toCharArray().length / 8 + 1;
					if (elementLength < maxElementLength) {
						sb.append(currentLevel[i]);
						while (elementLength <= maxElementLength) {
							sb.append("\t");
							elementLength++;
						}
					} else {
						sb.append(currentLevel[i]);
					}
				}
				if (i < currentLevel.length - 1) {
					sb.append(delimiter);
				}
			}
			tabs--;
			treeAsString.addLast(sb.toString());
			element = element.next;
		}
		return treeAsString;
	}

	public String printTreeRootLeft() {
		TreeElement currentElement = getMostRightOf(root);
		StringBuilder ausgabe = new StringBuilder();
		StringBuilder maxElementTabs = new StringBuilder();
		for (int tabsDone = 0; tabsDone <= maxElementLength; tabsDone++) {
			maxElementTabs.append("\t");
		}
		for (int i = 0; i < size; i++) {
			int tabsToDo = levelOf(currentElement);
			while (tabsToDo != 0) {
				ausgabe.append(maxElementTabs);
				tabsToDo--;
			}
			ausgabe.append(currentElement.data);
			ausgabe.append("\n");

			if (currentElement.leftChild != null) {
				currentElement = getMostRightOf(currentElement.leftChild);
			} else if (!currentElement.isRoot()) {
				int vergleichsWert = comparator.compare(currentElement.data,
						currentElement.father.data);
				if (vergleichsWert > -1)
					currentElement = currentElement.father;
				else {
					while (vergleichsWert < 0 && !currentElement.isRoot()) {
						vergleichsWert = comparator
								.compare(currentElement.data,
										currentElement.father.data);
						currentElement = currentElement.father;
					}
					// if(!currentElement.isRoot())
					// currentElement = currentElement.father;
				}
			}
		}
		return ausgabe.toString();
	}

	public int levelOf(TreeElement subTree) {
		if (size == 0)
			return 0;
		else {
			int level = 0;
			TreeElement currentElement = subTree;
			while (!currentElement.isRoot()) {
				level++;
				currentElement = currentElement.father;
			}
			return level;
		}
	}
}