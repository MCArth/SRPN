import java.util.ArrayList;

/**
* Is passed list of operators and then executes them on the stack.
*
* @author Arthur Baker
* @version 1.0
* @release 23/11/2018
*/

public class ProcessOperator {
	private DoStack myStack = new DoStack();
	
	/**
	* Processes operators that were parsed to it from ProcessCommand.
	* Then it performs them on numbers in stack and pushes output to stack.
	* 
	* @param operators
	* 			ArrayList containing operators.
	* 				The operator used last in the line will be last in the list.
	*/
    public void useOperators(ArrayList<Character> operators) {
    	
    	/*Goes through for loop for every element in list, from the last added
    	*Hence performs operations based on where operator is, from right to left
    	*Hence in some cases output will not match srpn as srpn prioritises different operations eg (2*2+1 will not match the same output as srpn)
    	*However this is just for in-line calculations which require this feature*/
    	for(int i=operators.size()-1;i>-1;i--) {
    	
    		
    		//If nothing in stack and = used
    		if(myStack.getSize()==0 && operators.get(i) == '=' ) {
    			System.err.println("Stack empty.");
    		}
    		
    		//Looks at top element in stack when = used
    		else if(operators.get(i) == '=') {
				myStack.peek();
    		}
    	
    		else if(myStack.getSize()>1){
    		
    			//Gets numbers to use pops from stack
    			long a = myStack.useNum();
    			long b = myStack.useNum();
    		
    			//For all different cases of operator
    			//Goes into case if the operator is that char
    			switch(operators.get(i)) {
    				case'+':
    					myStack.push(a+b); //Performs operation and pushes to stack
    					break;
    				case'-':
    					myStack.push(b-a);
    					break;
    				case'/':
    					if(!(a==0)) {
    						myStack.push(b/a);
    					}
    					//Special case for dividing by 0
    					else {
    						System.err.println("Divide by 0.");
    						myStack.push(b); //Pushes the numbers that were popped back onto the stack as they were not used
    						myStack.push(a);
    					}
    					break;
    				case'*':
    					myStack.push(b*a);
    					break;
    				case'%':
    					if(!(a==0)) {
    						myStack.push(b%a);	
    					}
    					//Case for x mod 0 - srpn exits upon this
    					else {
    						System.err.println("Floating point exception");
    						System.exit(1);
    					}
    					break;
    				case'^':
    					if(!(a<0)) {
    						myStack.push((long) Math.pow(b, a));
    					}    				
    					//Special case for if negative power
    					else {
    						System.err.println("Negative power.");
    						myStack.push(b); //Again, pushing numbers not used in an operation
    						myStack.push(a);
    					}
    					break;	
    			}
    		}
    
    		//This is reached if there is one or less operands in stack and hence can't perform operation
    		else {
    			System.err.println("Stack underflow.");
    		}
    		
    		operators.remove(operators.size()-1); //Removes the operator that was just processed from list
    	}
    }
}
