import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* Contains main, takes strings from command line and passes them to ProcessCommand.
* Also handles exiting program on end.
*
* @author Arthur Baker
* @version 1.0
* @release 23/11/2018
*/
public class SRPN {		
	/**
	* Main method. Same purpose as overall class.
	* Takes input from command line and passes them to ProcessCommand while also handling exiting the program.
	*/
    public static void main (String[] args) {
        ProcessCommand srpn = new ProcessCommand();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));        
        try {
            //Keep on accepting input from the command-line
            while(true) {
            	String command = reader.readLine();
                
                //Close on an End-of-file (EOF) (Ctrl-D on the terminal)
            	if(command == null)
                {
            		//Exit code 0 for a graceful exit
            		System.exit(0);
                }
                
                //Otherwise, (attempt to) process the character
                srpn.doCommand(command);
            	
            }
        } 
        //Exit upon IOException
        catch(IOException e) {
        	System.err.println(e.getMessage());
        	System.exit(1);
        }
    }
}
