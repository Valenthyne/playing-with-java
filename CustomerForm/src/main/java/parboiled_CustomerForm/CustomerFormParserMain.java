// Customer Form Parser | Authored by Bradley Martin | COMP 4040 Project -- Fall Semester | Professor Fatih Sen

package parboiled_CustomerForm;

import java.util.Scanner; 

import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.common.StringUtils;
import static org.parboiled.support.ParseTreeUtils.printNodeTree;
import org.parboiled.support.ParsingResult;

public class CustomerFormParserMain {
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Contact Form Parser!\n");
		
        Scanner stdin = new Scanner(System.in);
        
        CustomerFormParser parser = Parboiled.createParser(CustomerFormParser.class);
        
        while (true) { // Loop will run until blank input is entered

        	System.out.println("Be sure to abide by the proper grammar. Invalid inputs will not be processed. "
        			+ "\nEnter nothing for each value to quit!\n");
        	
        	System.out.print("Enter a name: ");
        	String inName = stdin.nextLine();
        	System.out.print("Enter an address: ");
        	String inAddr = stdin.nextLine();
        	System.out.print("Enter a phone number: ");
        	String inPnum = stdin.nextLine();
        	
        	String input = inName + inAddr + inPnum;
        	
            if (StringUtils.isEmpty(input))
            	break; // Terminates main method when blank is entered

            // Creates a parse tree object based on the received input
            
            ParsingResult<?> result = new RecoveringParseRunner<String>(parser.FullContact()).run(input); 
           
        	System.out.println();

            // Checks to see if any invalid input generated caused an error in the parse tree
        	if (!result.hasErrors()) {
                // Prints the generated parse tree
        		System.out.println("\n"+ printNodeTree(result));
        	} 
        	else {
        		// Invalid input was entered at some point
        		System.out.println("Invalid entry: abide by the request grammar."
        				+ "\nPlease try again.\n");
        	}
        
        }
        
        System.out.println("\nThanks for playing! End program.");
        stdin.close();
    }

}
