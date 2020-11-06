/**
* COP 3530: Project 1 – Array Searches and Sorts
* <p>
* This Project1 Class contains multiple methods for parsing and manipulating an object array of countries. 
* It begins by parsing a user entered CSV file to create a Country array using the constructor from the Country Class.  
* Once the Country array has been initialize with the values from the CSv file, the program enter a menu loop. 
* This userSelection loop prompts the user to enter a choice between 1-6 to selection a menu option.
* the menu options include: 1. Print a countries report, 2. Sort by Name, 3. Sort by COVID-19 CFR,
* 4. Sort by GDP per capita, 5. Find and print a given country, and  6. Quit.
* This userSelcetion method handles erroneous input and will continue until the user enter "6".
* Based on what the user selects from the menu the program will execute. 
*
* @author Luke Sanchez
* @version Sep. 11, 2020
*/

import java.io.*;
import java.util.Scanner;

public class Project1{
	
	public static void main(String[] args) throws IOException {
				boolean loop = true; 
				
		    	Country[] nations = parseCSV();
		    	
		    	while (loop) {
		    		loop = userSelection(nations);
		    
		    	}
		    	
	} //end main
	
	public static Country[] parseCSV() throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(System.in); 
		File inputFile;
		
		while(true) {
			System.out.print("Enter CSV file: ");
			inputFile = new File(scanner.nextLine()); 
			
	        if (!inputFile.exists()) {
	            System.out.println("\nThat file does not exist!");
	            continue;
	        }
	        System.out.println("Importing data from " + inputFile.toString() + "...\n");
	        break;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		br.readLine(); //skips first line of file.   
	    String line = null;
	    int i = 0;
	    Country[] countriesArray = new Country[153];
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
	        countriesArray[i] = state; 
			i++;
	    }
		System.out.println("There were " + Country.getNumCountries() + " records read.");
		return countriesArray;
	} //end parseCSV
	
		
	public static void printOptions() {
		System.out.println("\n1. Print a countries report");
		System.out.println("2. Sort by Name");
		System.out.println("3. Sort by COVID-19 CFR");
		System.out.println("4. Sort by GDP per capita");
		System.out.println("5. Find and print a given country");
		System.out.println("6. Quit");
	}//end printOptions
 	
	
	public static boolean userSelection(Country[] countriesArray)	{
		String userInput = "0";
		Scanner input = new Scanner(System.in);
		
		printOptions();
		while(!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") &&
			  !userInput.equals("4") && !userInput.equals("5") && !userInput.equals("6")) {
			System.out.print("Enter your choice: ");
			userInput = input.nextLine();
			
			switch (userInput) {
				case "1": 
					printCountries(countriesArray);
					break;
				case "2":
					insertionSort(countriesArray);
					break;
				case "3":
					sortCFR(countriesArray);
					break;
				case "4":
					sortGDPPerCapita(countriesArray);
					break;
				case "5":
					findName(countriesArray);
					break;
				case "6": 
					System.out.print("\nProgram Exited!");
					return false;
				default:
					System.out.println("\nPlease enter a valid choice! (1-6)");
			}
		}
		return true;
	}//end userSelection
	
	
	public static void printCountries(Country[] countriesArray) {
		System.out.println("\nCountry \t\t\t   Capitol     \t\t      Population \t GDP  \t\t    Cases \t Deaths");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		
        for (Object country : countriesArray) {
            System.out.println(country);
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
	}//end printCountries
	
	
	public static void sortCFR(Country[] values) { //Selection Sort
        for(int outer = 0; outer < values.length - 1; outer++) {
            int lowest = outer;
            for(int inner = outer + 1; inner < values.length; inner++) {
                if(values[inner].getC19CFR() < values[lowest].getC19CFR()) {
                    lowest = inner;
                }
            }
            if (lowest != outer) {
                Country temp = values[lowest];
                values[lowest] = values[outer];
                values[outer] = temp;
            }
        }
        System.out.println("\nCountries sorted by COVID-19 CFR");
    }//end sortCFR
	
	
	public static void sortGDPPerCapita(Country[] values) { //Bubble Sort
		for (int outer = 0; outer < values.length - 1; outer++) {
			for (int inner = values.length - 1; inner > outer; inner--) {
				if (values[inner].getGDPPerCapita() > values[inner-1].getGDPPerCapita()) {
					Country temp = values[inner];
					values[inner] = values[inner - 1];
					values[inner - 1] = temp;
				}
			}
		}
		System.out.println("\nCountries sorted by GDP per Capita");
	}//end sortGDPPerCapita
	
	
	  public static void insertionSort(Country[] values) { //Insertion Sort
		  for(int i = 1; i < values.length; i++){
		        int j = i - 1;
		        Country temp = values[i];
		        while((j > -1) && ((values[j].getName().compareTo(temp.getName())) > 0)){
		            values[j + 1] = values[j];
		            j--;
		        }
		        values[ j + 1] = temp;
		    }
		  System.out.println("\nCountries sorted by name alphabetically");
	  }// end insertionSort
	
	
	public static void findName(Country[] values) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\nEnter country name: ");
		String userCountry = keyboard.nextLine().toUpperCase();
		boolean countryFound = false;
		
		if(values[0].getName().charAt(0) == 'A') { //checks if array is sorted by names
			System.out.println("Binary search\n");
	        int low = 0;
	        int high = values.length - 1;
	        int mid;

	        while (low <= high) {
	            mid = (low + high) / 2;
	            
	            if (userCountry.equalsIgnoreCase(values[mid].getName())) {
	                countryFound = true;
	                System.out.println(values[mid].toSingularString());
	                break;
	            } 
	            else if (values[mid].getName().compareTo(userCountry) > 0) {
	                high = mid - 1;  
	            } 
	            else {
	            	low = mid + 1;
	            }
	        }
	        if(!countryFound) {
		    	System.out.println("Error: country " + userCountry + " not found");
	        }
		}
		
		else { //array is unsorted
			System.out.println("Sequential search\n");
			
			for (int i = 0; i < values.length; i++){ //Sequential Search
		        if (userCountry.equalsIgnoreCase(values[i].getName())){
		        	countryFound = true;
		        	System.out.println(values[i].toSingularString());
		        	break;	
		        }
			}
		    if(!countryFound) {
		    	System.out.println("Error: country " + userCountry + " not found");
		    }
		}	
	}//end findName
	
}//end Project1
