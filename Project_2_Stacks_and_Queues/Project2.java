/**
* COP 3530: Project 2 – Stacks and Priority Queues
*<p> The Project2 class manipulates data from a given CSV file. 
* The program first creates five(5) Priority queues named excellent, vGood, good, fair, and poor.
* It then parses the CSV file to extract Country objects, and inserts them into their respective priority queue. 
* The program then displays the sorted queues from Poor - Excellent. 
* Then a Stack of Country objects is created based on the number of parsed countries. 
* One by one each country is removed from their respective queues and push into the final Stack. 
* The program then prints out the final Stack and exits. <p>
*
* @author Luke Sanchez
* @version Oct. 3, 2020
*/

import java.io.*;

public class Project2 {

	public static void main(String[] args) throws IOException {
		
		//create priority queues 
		Priority excellent = new Priority(29);
		Priority vGood = new Priority(39);
		Priority good = new Priority(65);
		Priority fair = new Priority(13);
		Priority poor = new Priority(7);
		
		parseCSV(excellent, vGood, good, fair, poor);
		
		//Print Sorted Queues
		System.out.println("\nPOOR Priority Queue Contents:");
		poor.printQueue();
		
		System.out.println("\nFAIR Priority Queue Contents:");
		fair.printQueue();
		
		System.out.println("\nGOOD Priority Queue Contents:");
		good.printQueue();
		
		System.out.println("\nVERY GOOD Priority Queue Contents:");
		vGood.printQueue();
		
		System.out.println("\nEXCELLENT Priority Queue Contents:");
		excellent.printQueue();

		Stack countryStack = new Stack(Country.getNumCountries()); //create stack 
		
		//remove countries one by one from the sorted queues and push them to the stack
		while(!poor.isEmpty()) {
			countryStack.push(poor.remove());
		}
		
		while (!fair.isEmpty()) {
			countryStack.push(fair.remove());
		}
		
       	while (!good.isEmpty()) {
 		countryStack.push(good.remove());
		}
		
		while (!vGood.isEmpty()) {
			countryStack.push(vGood.remove());
		}

		while (!excellent.isEmpty()) {
			countryStack.push(excellent.remove());
		}
		
		System.out.println("\nStack Contents:");
		countryStack.printStack();
	} //end main
	
	
	public static void parseCSV(Priority excellent, Priority vGood, Priority good,
								Priority fair, Priority poor) throws FileNotFoundException, IOException {
			
	    File inputFile = new File("Countries2.csv");
		
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
			
			if(state.getC19CFR() < 0.01)
				excellent.insert(state);
			
			else if(state.getC19CFR() >= 0.01 && state.getC19CFR() < 0.02) 
				vGood.insert(state);

			else if(state.getC19CFR() >= 0.02 && state.getC19CFR() < 0.05) 
				good.insert(state);
			
			else if(state.getC19CFR() >= 0.05 && state.getC19CFR() < 0.10)
				fair.insert(state);
			
			else if(state.getC19CFR() >= 0.10)
				poor.insert(state);
	    }
	System.out.println("There were " + Country.getNumCountries() + " records read.");
	br.close();
	} //end parseCSV
}//end project2
