package file_processing;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

import content.Text;

public class Reader  {
	
	public static LinkedList<Text> readFile(String s) throws IOException {
	
	LinkedList<Text> books = new LinkedList<>();	
		
	File file = new File(s);
	file.createNewFile();
	
	Scanner parser = new Scanner(file);

	/* Author$Title$Volume$Price$Date$
	*	e.g. Coolkyoushinja$Miss Kobayashi's Dragon Maid$1$10.24$050310
	*/
	
	parser.close();
	
	return books;
	
	}
	
}
