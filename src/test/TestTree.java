import base.BinaryTree;

public class TestTree {

	public BinaryTree tree = new BinaryTree(new IntegerComparator());
	static int a = 0;

	public static final void main(String[] args) {
		TestTree testTree = new TestTree();
		testTree.tree.insert(1000);
		testTree.tree.insert(100);
		testTree.tree.insert(50);
		testTree.tree.insert(10);
		testTree.tree.insert(60);
		testTree.tree.insert(1500);
		testTree.tree.insert(1200);
		testTree.tree.insert(1800);
		testTree.tree.insert(200);
		testTree.tree.insert(150);
		testTree.tree.insert(250);
		testTree.tree.insert(1100);
		testTree.tree.insert(1400);
		testTree.tree.insert(1600);
		testTree.tree.insert(2000000);
		

		System.out.println("Root Left:");
		System.out.println(testTree.tree.printTreeRootLeft());
		System.out.println("\n\nRoot On Top:");
		System.out.println(testTree.tree.printTreeRootUp());
		System.out.println("\n\nRoot On Ground:");
		System.out.println(testTree.tree.printTreeRootDown());
	}
}
