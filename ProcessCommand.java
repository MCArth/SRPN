import java.util.ArrayList;
import java.util.Arrays; 

/**
* Takes in string from command-line and processes it,
* extracting operands and operators.
* Also handles octal conversion if necessary when processing numbers.
*
* @author Arthur Baker
* @version 1.0
* @release 23/11/2018
*/

public class ProcessCommand {
	
	private Random myRandom = new Random();
	private DoStack myStack = new DoStack();
	private ProcessOperator doOperators = new ProcessOperator();
	private ArrayList<Character> operators = new ArrayList<>();
	private String[] validOperators = {"+","-","*","/","^","%","r","="};
	private boolean commentOn = false;

	/**
	* Parses the string, resolving it to various operands, operators, and functions - e.g. 'd' or 'r'.
	* 
	* @param string
	* 			The string to be parsed
	*/
	public void doCommand(String string) {
		String strNum = ""; //Numbers will be concatenated with this
				
		//For each character in string
		for(int i = 0; i<string.length(); i++) {
			char charAt = string.charAt(i);
			
			//Checks if comment is on, and if so, won't process any of the letters
			if(commentOn) {
				if(charAt=='#') { //If there is a second #, end the comment
					commentOn=false;
				}
			}

			else if(Arrays.asList(validOperators).contains(String.valueOf(charAt))) {
				
				//Checks to see if there is a minus, and whether it is operator or sign by looking at next value in string
				if(i+1<string.length()) {				
					if(!(charAt=='-' && Character.isDigit(string.charAt(i+1)))) {
						operators.add(charAt);
					}
					else {
						strNum = "-";
					}
				}
				else {
					operators.add(charAt);	
				}
			}
			
			
			else if(Character.isDigit(charAt)) {
				strNum = strNum + charAt; //Concatenates the digit char to the end of the existing number (object type String)
				boolean numEnded=false;
				
				//Says the number has ended and hence pushes number to stack if at the conclusion of the number
				//Does so in two cases: If next digit in string is not a number, or if it is the end of the string
				if(i+1<string.length()) {
					if(!(Character.isDigit(string.charAt(i+1)))) {
						numEnded=true;
					}
				}
				else {
					numEnded=true;
				}
				
				
				if(numEnded) {
					long toPush = octalCheck(strNum); //Checks to see if 0 at start to convert to octal, and if so, converts it
					
					//Checks for error when converting to octal (error code returned from octal if num contains 8 or 9 and hence isn't octal but was num started with 0)
					//If error, skips over and nothing is done
					if (!(toPush==10000000000000L)) {
						myStack.push(toPush);
						strNum = ""; //Sets strNum ready for the next number in string
					}
				}
			}
			
			//Representing all the different cases of initiating commenting
			else if(charAt=='#') {
				
				if(i==0) { //If at start of line, next char must be whitespace
					if(i+1<string.length()) {
						if(string.charAt(i+1)==' ') {
							commentOn = true;
						}
					}
				}
				else { //both previous and next char must be whitespace
					if(i+1<string.length()) {
						if(string.charAt(i+1)==' ' && string.charAt(i-1)==' ') {
							commentOn = true;
						}
					}
					else {//If at end of line, previous char must be whitespace
						if(string.charAt(i-1)==' '){
							commentOn = true;
						}
					}
				}
			}
			
			//Does nothing if char is whitespace
			else if(charAt==' ') {
			}
			
			//displays stack
			else if(charAt == 'd') {
	    		myStack.outputStack();
	    	}
			
			//pushes 'random' number to stack
			else if(charAt == 'r') {
    			myStack.push(myRandom.getRandomNum());
    		}
			
			else {
				System.err.println("Unrecognised operator or operand \"" + charAt + "\".");
			}
			
			//Case of equals used in-line
			//for = to be used in-line, char before it must have been whitespace
			if(charAt == '=') {
				if(string.length()>1 && !(i==0)) {
					if(string.charAt(i-1)==' ') {
						operators.remove(operators.size()-1);
						doOperators.useOperators(operators);
						myStack.peek();
					}
				}
			}
		}
				
		//If there are operators to process, processes them
		if(!(operators.isEmpty())) {
			doOperators.useOperators(operators);
		}
	}
	
	/**
	* Called when pushing a number that was entered on command line to stack.
	* Checks if it has a 0 at start and if so, attempts to convert to octal.
	* 
	* @param strNum
	* 			The string of a number to potentially convert to octal.
	* 
	* @return	Returns either the same number or number as octal if converted.
	* 				Returns error code if attempted to convert to octal but could not.
	*/
	private long octalCheck(String strNum) {
		
		boolean errorOcc = false;
		long num;
		//Checks that number entered isn't too big and if it is sets it to the max/min value
		try {
			num = Integer.parseInt(strNum);
		}
		catch(NumberFormatException e) {
			errorOcc = true;
			if(strNum.charAt(0)=='-') {
				num = -2147483648;
			}
			else {
				num = 2147483647;
			}						
		}
		
		//Checks if number starts with 0, and if it does will convert to octal if possible
		if(strNum.charAt(0)=='0' || (strNum.charAt(0)=='-' && strNum.charAt(1)=='0')) {
			
			boolean isOct = true;
			
			//Checks to see if contains any invalid characters
			for(int i = 1; i<strNum.length(); i++) {
				if(strNum.charAt(i)=='8' || strNum.charAt(i)=='9') {
					isOct = false;
				}
			}
			
			if(isOct) {
				
				/*
				*If number was too large it was set to 2147483647 or -2147483648 which will return error if you parse it
				*Hence return -1, which srpn returns for hex decimals too large
				*srpn does seem to overflow when dealing with hex values before it returns -1, but this code does not deal with that
				*/
				if(!(errorOcc)) {
					num = Long.parseLong(Long.toString(num),8);
				}
				else {
					System.out.println("reached");
					num = -1;
				}
				
				return num;
				
			}
			else if(num==8 || num==9) { //For cases 08 and 09 where respectively 8 and 9 are still added to stack
				return num;
			}
			else {
				return 10000000000000L; //Returns error code as octal number contained 8 or 9 which they shouldn't
			}	
		}
		else {
			return num; //Just returns the number if doesn't start with 0
		}
	}
}
