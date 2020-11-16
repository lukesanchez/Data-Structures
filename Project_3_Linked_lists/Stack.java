/**
* This Stack class creates a stack of country objects using linked list. 
* The Stack constructor creates a Country Link that contains the countryData and a ref to the top Link. 
* The class provides the push(), pop(), isEmpty(), isFull(), and printStack() methods. 
* The printStack() method prints from the top of the stack to the bottom of the stack. 
* 
* The Link Class is also included in this file.
* The Link Class contains Country objects and next & previous ref pointers to the Link. 
* The Link constructor creates a Link object and populates the countryData variable. 
* The Next Link value is used solely by the stack to create a singly linked list. 
* While the Queue uses both previous and next to create a doubly linked list. 
*
* @author Luke Sanchez
* @version Oct. 25, 2020
*/

class Link {
	
	Country countryData; // data item
	Link previous; 
	Link next;
	
	public Link(Country nation) {
		countryData = nation;
	}
}
	
public class Stack {
	
	private Link top; 
	private Link bottom;
	
	public Stack(){ 
	top = null; 
	bottom = null; 
	}
		
	public void push(Country state) {
		
		Link newLink = new Link(state);
		
		if ( isEmpty() ) 
			bottom = newLink; 
		
		newLink.countryData = state; 
		newLink.next = top; 
		top = newLink; 
	}
	
	
	public Country pop() {
		if(isEmpty()) {
			System.out.println("Stack is Empty.");
			return null;
		}
		else {
			Country temp = top.countryData; 
			top = (top).next; 
			return temp; 
		}
	}
	
	public boolean isEmpty() {
		return top == null; 
	}
	
	public boolean isFull() {
		return false; //Should always return false since Stack is implemented using Linkedlist. 
	}
	
	public void printStack() {
		if ( top == null ) 
			System.out.println("Stack Underflow.");
		
		else {
			Link current = top;
			System.out.println("\nCountry \t\t\t   Capitol     \t\t      Population \t GDP  \t\t    Cases \t Deaths");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			while (current != bottom.next) {
				System.out.printf("%s\n", current.countryData);
				current = current.next; 
			}
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		}	
	}
}
