/**
 * The BinarySearchTree class contains methods to create and utilize BSTs.
 * The class contains a BST constructor to create a Binary Search Tree.
 * Includes the methods: insert, find, delete, getSuccessor, printInorder, printPreorder, and printPostorder.
 *
 * @author Luke Sanchez
 * @version Nov. 15 2020
 */

class BinarySearchTree {

	/**
	 * The Node class is used to create node objects.
	 * A node object has data for name, CFR, and pointers to it's left & right child.
	 * The class has a Node constructor to create a Node object.
	 * The class also has a printNode method to output the values of a specified Node.
	 */
	private class Node {
		String name;
		double CFR;
		Node leftChild;
		Node rightChild;

		public Node(String name, double CFR) {
		this.name = name;
		this.CFR = CFR;
		}

		public void printNode() {
		System.out.printf("%-40s%,-30.6f\n", name, CFR);
		}
	}//end Node class


	Node root;

	BinarySearchTree() { root = null; } //BinarySearchTree constructor

	
	/**
	 * The insert method inserts a node alphabetically into a Binary Search Tree.
	 * The method requires the parameters of name and CFR to create a Node and insert it correctly.
	 * A BinarySearchTree needs to be created before calling this method.
	 * @param name String of the country name.
	 * @param CFR double of the country's Covid-19 CFR.
	 */
	public void insert(String name, double CFR) {
		Node newNode = new Node(name, CFR); 
		if (root == null)
			root = newNode; 
		else {
			Node current = root;
			Node parent; 
			while (true) {
				parent = current; 
				if (name.compareToIgnoreCase(current.name) < 0) {
					current = current.leftChild; 
					if (current == null) {
						parent.leftChild = newNode; 
						return; 
					}
				}
				else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode; 
						return; 
					}	
				}
			}
		}	
	}


	/**
	 * The find method traverses through a BST to find the entered country name.
	 * The method starts at the root method then expands out to compare each node.
	 * If a matching Node is found, then it prints out the Node data and returns the Node's CFR.
	 * If no matching Node is found, then it returns -1.
	 *
	 * @param name String of a country name you're trying to find.
	 * @return double CFR of matching country, or -1 if matching country is not found.
	 */
	public double find(String name) {
		Node current = root;
		int nodesVisited = 0;
		while (!name.equalsIgnoreCase(current.name)) {
			nodesVisited++;
			if(name.compareToIgnoreCase(current.name) < 1)
				current = current.leftChild;
			else
				current = current.rightChild;
			if(current == null){
				System.out.printf("\n%s is not found\n%d nodes visited\n", name, nodesVisited);
				return -1;
			}
		}
		System.out.printf("\n%s is found with CFR %.6f\n%d nodes visited\n", name, current.CFR, nodesVisited);
		return current.CFR;
	}


	/**
	 * The delete method delete a specified country Node from the Binary Search Tree.
	 * The method traverses through the BST until it finds a match or it reaches the end of the BST.
	 * The method requires a String parameter of a country name (Case-insensitive).
	 * The method handles if the Node to be deleted has none, one, or two children.
	 *
	 * @param key String of country name to delete from BST.
	 */
	public void delete(String key) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;

		while (!key.equalsIgnoreCase(current.name)) {
			parent = current;
			if (key.compareToIgnoreCase(current.name) < 0) {
				isLeftChild = true;
				current = current.leftChild;
			}
			else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null){
				System.out.printf("\n%s not found in tree\n", key);
				return; //Node not found
			}
		}
			if(current.leftChild == null && current.rightChild == null) {
				if (current == root)
					root = null;
				else if (isLeftChild)
					parent.leftChild = null;
				else
					parent.rightChild = null;
			}

			else if(current.rightChild == null)
				if (current == root)
					root = current.leftChild;
				else if (isLeftChild)
					parent.leftChild = current.leftChild;
				else
					parent.rightChild = current.leftChild;

			else if(current.leftChild == null)
				if(current == root)
					root = current.rightChild;
				else if(isLeftChild)
					parent.leftChild = current.rightChild;
				else
					parent.rightChild = current.rightChild;

			else {
				Node successor = getSuccessor(current);

				if (current == root)
					root = successor;
				else if (isLeftChild)
					parent.leftChild = successor;
				else
					parent.rightChild = successor;

				successor.leftChild = current.leftChild;
			}
			System.out.printf("\n%s has been deleted from the tree", key);
		}//end delete

	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;

		while(current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}

		if (successor != delNode.rightChild){
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	/**
	 * The printInorder method prints intended BST in order from Left Node Right.
	 * The method includes heading and footer indicators.
	 */
	public void printInorder(){
		if (root != null){
			System.out.println("\nInorder Traversal:\n");
			System.out.printf("%-39s %-36s\n", "Name", "CFR");
			System.out.println("-------------------------------------------------");
			printInorder(root);
			System.out.println("-------------------------------------------------");
		}
	}

	private void printInorder(Node localRoot){
		if (localRoot != null){
			printInorder(localRoot.leftChild);
			localRoot.printNode();
			printInorder(localRoot.rightChild);
		}
	}

	/**
	 * The printPreorder method prints intended BST in pre order from Node Left Right.
	 * The method includes heading and footer indicators.
	 */
	public void printPreorder(){
		if (root != null){
			System.out.println("\n\nPreorder Traversal:\n");
			System.out.printf("%-39s %-36s\n", "Name", "CFR");
			System.out.println("-------------------------------------------------");
			printPreorder(root);
			System.out.println("-------------------------------------------------");
		}
	}

	private void printPreorder(Node localRoot){
		if (localRoot != null){
			localRoot.printNode();
			printPreorder(localRoot.leftChild);
			printPreorder(localRoot.rightChild);
		}
	}

	/**
	 * The printPreorder method prints intended BST in post order from Left Right Node.
	 * The method includes heading and footer indicators.
	 */
	public void printPostorder(){
		if (root != null){
			System.out.println("\n\nPostorder Traversal:\n");
			System.out.printf("%-39s %-36s\n", "Name", "CFR");
			System.out.println("-------------------------------------------------");
			printPostorder(root);
			System.out.println("-------------------------------------------------");
		}
	}

	private void printPostorder(Node localRoot){
		if (localRoot != null){
			printPostorder(localRoot.leftChild);
			printPostorder(localRoot.rightChild);
			localRoot.printNode();
		}
	}
}//end BinarySearchTree
