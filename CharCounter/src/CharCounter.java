import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 
public class CharCounter {
	
	// Static variables to be used as counters for non-letter characters
	static int digits = 0;
	static int specials = 0;
	static int spaces = 0;
	
	public static void main(String[] args) throws IOException{
		
		// Establish start time for program
		long start = System.currentTimeMillis();
		
		// Print count array evaluated by inner method argument
		printCountArray(count("input.txt"));
		
		// Establish end time for program
		long stop = System.currentTimeMillis();
		
		// Print time difference
		System.out.println("Total time elapsed: " + (stop-start) + " ms");
	}
	
	public static int[] count(String filename) throws IOException {
		
		// Set aside 26-length count array for letter values
		int[] count = new int[26];

		// Use buffered reader to piece through the file
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		// Input string from which to read
		String input = "";
		
		// Terminates while loop on condition of end of file being reached (input = null)
		while((input = reader.readLine()) != null) {
			
			// Sets characters to upper case since case doesn't matter here
			input = input.toUpperCase();
			
			// For-each loop to cycle through input string array
			for(char ch: input.toCharArray()) {
				
				if(Character.isLetter(ch)) { // Increments counter by appropriate letter position if a character
					count[ch - 'A']++;
				} else if (Character.isDigit(ch)) {	// Increments digit counter if digit
					digits++;
				} else if (Character.isWhitespace(ch)) { // Increments space counter if whitespace
					spaces++;
				} else {	// Increments special character counter if not any of the above
					specials++;
				}
				
			}
			
		}
		
		reader.close();
		return count; // Returns count array
	}
	
	public static void printCountArray(int[] count) {
		
		// Use char in its ASCII numeric form as index for array
		char ch = 65; 
		for (int i = 0; i < count.length; i++, ch++) {
			System.out.println(ch + ": " + count[i]);
		}
		
		System.out.println("\nTotal spaces: " + spaces);
		System.out.println("Total digits: " + digits);
		System.out.println("Total other characters: " + specials);
		
	}
 
}