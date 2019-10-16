/* 
 * [DataFileReader]: Inputs a file containing entries from a library
 * that will then be processed into a master list for filtering
 * and evaluation by the program.
 */

import java.util.Scanner;
import java.util.LinkedList;
import java.io.*;

public class DataFileReader {
	
	public static LinkedList<Media> importData(String s) throws IOException {
		
		// Allows customization of file name should a new one be provided and is present
		String filename = s;
		
		Scanner reader = null;
		
		// Passes if the file exists
		try {
			reader = new Scanner(new FileReader(filename));
			System.out.println("[Library file successfully processed!]\n");
		} catch (IOException ex) {
			System.out.println("A file by this name does not exist.");
			throw new IOException("\nPlease provide the name of a library file that exists."); 
		}
		
		LinkedList<Media> masterList = new LinkedList<>();

		while (reader.hasNextLine()) {
			
			String entry = reader.nextLine();
						
			String length = entry.substring(entry.lastIndexOf(",") + 1, entry.length());
			length = length.trim();

			if (!(length.matches("[[0-9][h][r][m][\\s]]+"))) {	// Condition for being a Series
				
					String title = cleanTitle(entry);
					
					String year = cleanYear(entry);
	
					String star = cleanRatings(entry);
					
					// Handles count of series divisions
					String count = entry.substring(entry.lastIndexOf(",") + 1, entry.length()).trim();
				
					// Adds title as a series object in the Media master list
					masterList.add(new Series(title, year, star, count));
				
				} else { // Condition for being a Movie
					
					String title = cleanTitle(entry);
					
					String year = cleanYear(entry);

					String star = cleanRatings(entry);
					
					// Adds title as a movie object in the Media master list
					masterList.add(new Movie(title, year, star, length));
					
				}
			
		}
		
		reader.close();
		
		// Returns the master list for use in later parts of the program
		return masterList;
	}
	
	// Cleans out the title from an entry
	private static String cleanTitle(String entry) {
		return entry.substring(0, entry.indexOf("(") - 1).trim();
	}

	// Cleans out the year from an entry
	private static String cleanYear(String entry) {
		return entry.substring(entry.lastIndexOf("(") + 1, entry.lastIndexOf(")"));
		
	}
	
	// Cleans out the ratings from an entry
	private static String cleanRatings(String entry) {
		String star = "";
		
		try {
			star = entry.substring(entry.indexOf("stars,") - 4, entry.indexOf("stars,"));
			star = star.trim();
		}
		catch (Exception ex) {
			// If an entry is without a rating, it will be stored as having "-1" rating
			star = "-1";
		}
		
		return star;
	}
	
}