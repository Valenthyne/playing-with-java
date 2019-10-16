/* COMP 2150 (Spring 2018) Java Programming Project: Too Much to Watch!
 * Authored by Bradley Martin (partnered with James Wilson)
 */

import java.io.IOException;

public class Client {

	public static void main(String[] args) throws IOException {
		
		// Generates a catalog object based upon the library file containing each of the entries
		try {
			Catalog cat = new Catalog(DataFileReader.importData("NetflixUSA_Oct15_cleaned.txt"));
			Menu.menu(cat);
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			// The program will indeed terminate through the main method and conclude with this line
			System.out.println("Thank you for using Totally Not Netflix!");
		}
	}

}
