/**
* This Priority class creates a priority queue of given countries. 
* The constructor creates a Country array of a given size. 
* The insert() method sorts the queue array based on the country's case fatality rate. 
* The remove() method removes the lowest queue item and returns it. 
* The Class also includes the isEmpty(), isFull(), and printQueue() methods. 
* The printQueue method print through the queue based on the priority order. 
*
* @author Luke Sanchez
* @version Oct. 3, 2020
*/

public class Priority {
	private int maxSize;
	private int numItems; 
	private Country[] queueArray;
	
	
	public Priority(int s) {
		maxSize = s; 
		queueArray = new Country[maxSize]; 
		numItems = 0; 
	}

	public void insert(Country nation) {
		int j;
		
		if(isFull())
			System.out.println("Queue is full.");
		
		if(numItems == 0)
			queueArray[numItems++] = nation; 
	
		else {
			for(j = numItems-1; j >= 0; j--) {
				if(nation.getC19CFR() < queueArray[j].getC19CFR()) 
					queueArray[j + 1] = queueArray[j]; 
				else 
					break;
				}
			queueArray[j + 1] = nation; 
			numItems++; 
		}
	}
	
    public Country remove() {
    	Country temp = null;
    	
		if(isEmpty())
			System.out.println("Queue is Empty.");
        
		else {
        	temp = queueArray[0]; 
        	
            for (int i = 1; i < numItems; i++) {
            	queueArray[i - 1] = queueArray[i];
            }
            
            numItems--;
        }
        return temp;
    }
	
	public boolean isEmpty() {
		return (numItems == 0); 
	}
	
	public boolean isFull() {
		return (numItems == maxSize); 
	}
	
	public void printQueue() {
		System.out.println("\nCountry \t\t\t   Capitol     \t\t      Population \t GDP  \t\t    Cases \t Deaths");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		for (Object country : queueArray) {
			System.out.println(country);
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
	}

}//end Priority
