/**
* This Queue Class creates a double-ended queue using a double-ended doubly linked list.
* The Queue constructor creates a linked list with references to the first/last Links. 
* The insertEnd() and removeEnd() methods modify the last link in the queue. 
* The insertFront() and removeFront() methods modify the first link in the queue.
* The intervalDelete() method deletes links that fall in-between a C19 CFR interval. 
* The Class also includes the isEmpty(), isFull(), and printQueue() methods.
* The printQueue() method prints the queue from front to end. 
*
* @author Luke Sanchez
* @version Oct. 25, 2020
*/

public class Queue {

	private Link first; // ref to first link
	private Link last; // ref to last link

	public Queue() {
		first = null; // no links on list yet
		last = null;
	}
	
	public boolean isEmpty() { 
		return first == null;
	}
	
	public boolean isFull() {
		return false; 
	}
	
	public void insertFront(Country nation) {
		Link newLink = new Link(nation); 
		
		if( isEmpty() ) 
			last = newLink;
		else
			first.previous = newLink; 
		newLink.next = first; 
		first = newLink; 
	}
	
	public void insertEnd(Country nation) {
		Link newLink = new Link(nation); 
		
		if( isEmpty() ) 
			first = newLink;
		else {
			last.next = newLink; 
			newLink.previous = last;
		}	
		last = newLink;
	}
	
	public Country removeFront() {
		Link temp = first; 
		if(first.next == null)
			last = null;
		else
			first.next.previous = null;
		first = first.next;
		return temp.countryData;
	}
	
	public Country removeEnd() {
		Link temp = last;
		if(first.next == null)
			first = null; 
		else
			last.previous.next = null;
		last = last.previous;
		return temp.countryData;
	}
	
	public boolean intervalDelete(double min, double max) {
		Link current = first; 
		boolean countryDeleted = false; 
		while (current != last.next)  {
			if (current.countryData.getC19CFR() > min && current.countryData.getC19CFR() < max) {
				countryDeleted = true; 
				if(current == first)
					first = current.next;
				else
					current.previous.next = current.next;
				if(current == last)
					last = current.previous; 
				else
					current.next.previous = current.previous; 
			}
			current = current.next;
		}
	return countryDeleted; 
	}
	
	public void printQueue() {
		if ( last == null )
			System.out.println("Queue Underflow.");
		
		else {
			Link current = first;
			System.out.println("\nCountry \t\t\t   Capitol     \t\t      Population \t GDP  \t\t    Cases \t Deaths");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			while (current != last.next) {
				System.out.println(current.countryData);
				current = current.next; 
			}
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		}	
	}
}
