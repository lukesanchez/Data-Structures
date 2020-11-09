/**
* This Stack class creates a stack array of country objects. 
* The Stack constructor creates a Country array with a given length. 
* The class provides the push(), pop(), isEmpty(), isFull(), and printStack() methods. 
* The printStack() method prints from the top of the stack to the bottom of the stack. 
*
* @author Luke Sanchez
* @version Oct. 3, 2020
*/

public class Stack {
	private int maxSize;
	private int top;
	private Country[] stackArray;
	
	
	public Stack(int s) {
		maxSize = s; 
		stackArray = new Country[maxSize]; 
		top = -1; 
	}
	
	public void push(Country nation) {
		
		if(isFull()) {
			System.out.println("Stack is full.");
		}
		else {
		stackArray[++top] = nation; 	
		}
	}
		
	public Country pop() {
		if(isEmpty()) {
			System.out.println("Stack is Empty.");
			return null;
		}
		else {
			return stackArray[top--];
		}
	}
	
	public boolean isEmpty() {
		return (top == -1); //true if stack is empty 
	}
	
	public boolean isFull() {
		return (top == maxSize - 1); //true if stack is full
	}
	
	public void printStack() {
		System.out.println("\nCountry \t\t\t   Capitol     \t\t      Population \t GDP  \t\t    Cases \t Deaths");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		for (int i = top; i >= 0; i--)
            System.out.println(stackArray[i]);
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
	}
}//end Stack
