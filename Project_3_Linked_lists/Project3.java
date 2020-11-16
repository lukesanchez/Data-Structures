/**
* COP 3530: Project 3 – Linked lists
*<p> 
* The Project3 class ingest data from a given CSV file. 
* The program first creates a stack that is implemented from a Linked List. 
* It then parses the CSV file to extract Country objects, and inserts them into the stack one by one. 
* The Stack of Country objects is then printed from top to bottom. 
* A queue is then created, which also is implemented from a Linked List. 
* Items are then popped from the stack, one at a time, and inserted into the queue. 
* The Countries are inserted alternately to the Front and End of the queue, until the the stack is empty. 
* The Queue is then printed from the front to end. 
* Next the intervalDelete method is called to remove countries that are within the given interval.   
* The Queue is then reprinted to show all of the countries, but those that were deleted. 
* Next, each country is removed from the queue one by one from the queue and pushed to the original stack
* The Countries are removed by alternating between removing from the front and end of the queue. 
* Finally the stack is printed one more time from top to bottom. 
*  <p>
*
* @author Luke Sanchez
* @version Oct. 25, 2020
*/

import java.io.*;

public class Project3 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Stack stkNations = new Stack(); 
		parseCSV(stkNations);
		
		System.out.println("\nStack Contents:");
		stkNations.printStack(); 
		
		Queue queNations = new Queue();
		
		int i = 0;
		while (!stkNations.isEmpty()) {
			i++; 
			if( (i % 2) == 0)
				queNations.insertEnd(stkNations.pop());
			else
				queNations.insertFront(stkNations.pop());
		}
		
		System.out.println("\nQueue Contents:");
		queNations.printQueue();
		
		queNations.intervalDelete(0.025, 0.035);
		
		System.out.println("\nQueue Contents:");
		queNations.printQueue();
		
		i = 0; 
		while (!queNations.isEmpty()) {
			i++; 
			if( (i % 2) == 0)
				stkNations.push(queNations.removeEnd());
			else
				stkNations.push(queNations.removeFront());	
		}
		
		System.out.println("\nStack Contents:");
		stkNations.printStack(); 
	}// end main
		
	public static void parseCSV(Stack nationsStack) throws FileNotFoundException, IOException {

		File inputFile = new File("Countries3.csv");
		
		if (!inputFile.exists())
		System.out.println("\nThat file does not exist!");
		
		System.out.println("Importing data from " + inputFile.toString() + "...\n");
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		br.readLine(); //skips first line of file.   
		String line = null;
		while ((line = br.readLine()) != null) {
		String[] values = line.split(",");
		String name = values[0];
		String capital = values[1];
		String population = values[2];
		String gdp = values[3];
		String c19Cases = values[4];
		String c19Deaths = values[5];
		
		Country state = new Country(name, capital, Long.parseLong(population), Double.parseDouble(gdp),
								Integer.parseInt(c19Cases), Integer.parseInt(c19Deaths));
		
		if(state.getC19CFR() >= 0.01 && state.getC19CFR() < 0.10) 
			nationsStack.push(state);
		}
		System.out.println("There were " + Country.getNumCountries() + " records read.");
		br.close();
	} //end parseCSV
}
