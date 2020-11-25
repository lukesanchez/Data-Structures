/**
 * COP 3530: Project 4 – Binary Search Trees
 * <p>
 * This Project4 class contains two methods main() and parseCSV().
 * In the main() method, a BinarySearchTree(BST) is created.
 * That BST is then passed into the parseCSV() to ingest data from a CSV file.
 * Once the data is added to the BST, the entire tree is printed in Order.
 * Then some Country nodes are deleted from BST and then the BST is printed Preorder.
 * After that, the find method is used to find Four Country nodes.
 * Five more Country nodes are then deleted using the delete method.
 * Finally the print postOrder() method is used to print the BST in post order.
 * <p>
 * @author Luke Sanchez
 * @version Nov. 15, 2020
 */

import java.io.*;

public class Project4 {

	public static void main(String[] args) throws IOException {

		BinarySearchTree treeNations = new BinarySearchTree();
		parseCSV(treeNations);

		treeNations.printInorder();

		treeNations.delete("Greece");
		treeNations.delete("Mongolia");
		treeNations.delete("Norway");

		treeNations.printPreorder();

		treeNations.find("Mongolia");
		treeNations.find("Cyprus");
		treeNations.find("United States");
		treeNations.find("Norway");

		treeNations.delete("Qatar");
		treeNations.delete("Somalia");
		treeNations.delete("Canada");
		treeNations.delete("Yemen");
		treeNations.delete("New Zealand");

		treeNations.printPostorder();
	}// end main

	/**
	 * The parseCSV method parses a CSV file to extract Country data.
	 * A input CSV file needs to be specified to extract the data.
	 * A bufferedReader object is created to hold the CSV data.
	 * For every line in the file the country data is extracted.
	 * The Country data is then inserted into the passed in BST.
	 *
	 * @param nationsTree BST that was created in main.
	 * @throws IOException if file is not found.
	 */
	public static void parseCSV(BinarySearchTree nationsTree) throws IOException {

		File inputFile = new File("Countries4.csv");

		if (!inputFile.exists())
			System.out.println("\nThat file does not exist!");

		System.out.println("Importing data from " + inputFile.toString() + "...\n");

		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		br.readLine(); //skips first line of file.
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			String name = values[0];
			String c19Cases = values[4];
			String c19Deaths = values[5];
			double c19CFR;
			if (Integer.parseInt(c19Deaths) <= 0)
				c19CFR = 0.0;
			else
				c19CFR = Integer.parseInt(c19Deaths) / Double.parseDouble(c19Cases);

			nationsTree.insert(name, c19CFR);
		}
		br.close();
	} //end parseCSV
}
