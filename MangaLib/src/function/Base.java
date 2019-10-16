package function;

import content.Library;
import file_processing.Reader;

public class Base {

	public static void main(String[] args) {
	
		try {
			
			Library lib = new Library(Reader.readFile("library.txt"));
			Menu.menu(lib);
		
		} catch (Exception ex) {
			
			System.out.println("Some error.");
			
		} finally {
			
			System.out.println("Terminating program...");
			
		}
		
	}

}
