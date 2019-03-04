import java.util.Stack;

/**
* Contains the stack and handles all operations to do with the stack
* such as pushing to it and displaying the stack.
*
* @author Arthur Baker
* @version 1.0
* @release 23/11/2018
*/

public class DoStack {

	private static Stack<Long> numStack = new Stack<Long>(); 
	
	/**
	* Pushes numbers to the stack.
	* 
	* @param pushee
	* 			The number to be pushed to the stack
	*/
	public void push(long pushee) {
		
		//Checks to make sure stack isn't too large
		if(numStack.size()<23) {
			
			//These two ifs check if the number to push is above or below the limits of what the SRPN stack can store
			if(pushee > 2147483647){
				pushee = 2147483647;
			}
			else if(pushee < -2147483648){
				pushee = -2147483648;
			}
			
			numStack.push(pushee);
		}
		
		else { //If stack is full
			System.err.println("Stack overflow.");
		}
	}
	
	/**
	* Method that will be called when using number in calculation.
	* Removes number from stack and returns that number.
	* 
	* @return	the number that has been popped from stack
	*/
	public long useNum() {
		long x = numStack.peek(); //Looks at top element of stack (that will be removed)
		numStack.pop(); //Removes top element of stack
		return x;
	}
	
	/**
	* Displays the entire stack.
	*/	
	public void outputStack() {
		
		//Displays min int value as in stack if nothing in stack
		if(numStack.isEmpty()){
			System.out.println(-2147483648);
		}
		
		Object[] intArray = numStack.toArray();
		//Iterates through the stack and prints each value
		for(int x = 0;x < intArray.length; x++) {
			System.out.println(intArray[x]);
		}		
	}
	

	/**
	* Accessor method for getting size of stack.
	* 
	* @return	Size of the stack
	*/
	public int getSize(){
		return numStack.size();
	}
	
	/**
	* Prints number on top of stack.
	*/
	public void peek() {
		System.out.println(numStack.peek());
	}
}
