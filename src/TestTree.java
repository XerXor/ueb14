import exceptions.TreeException;
import base.BinaryTree;

public class TestTree {

	public static BinaryTree tree;
	static int a = 0;

	public static final void main(String[] args) {
		try {
			tree = new BinaryTree(new IntegerComparator());
			tree.insert(1000);
			tree.insert(500);
			tree.insert(100);
			tree.insert(10);
			tree.insert(60);
			tree.insert(2000);
			tree.insert(1500);
			tree.insert(2500);
			tree.insert(750);
			tree.insert(150);
			tree.insert(250);
			tree.insert(1100);
			tree.insert(1750);
			tree.insert(1600);
			tree.insert(2000);
		} catch (TreeException e) {
		}

		System.out.println("Root Left:");
		System.out.println(tree.printTreeRootLeft());
		System.out.println("\n\nRoot On Top:");
		System.out.println(tree.printTreeRootUp());
		System.out.println("\n\nRoot On Ground:");
		System.out.println(tree.printTreeRootDown());
		System.out.println("\n\nIn-Order:");
		System.out.println(tree);
		
		System.out.println("\n#####################################################################\n");
		try {
			tree.remove(1000);
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Root Left:");
		System.out.println(tree.printTreeRootLeft());
		System.out.println("\n\nRoot On Top:");
		System.out.println(tree.printTreeRootUp());
		System.out.println("\n\nRoot On Ground:");
		System.out.println(tree.printTreeRootDown());
		System.out.println("\n\nIn-Order:");
		System.out.println(tree);
	}
}
