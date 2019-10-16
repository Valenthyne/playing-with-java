import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class DirTreeGen {
	
	static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		LinkedList<String> branch = new LinkedList<>();
		
		System.out.println("Welcome!");
		
		boolean stay = true;
		
		File path = new File("C:\\");
		
		while (stay) {
			
			System.out.println("\n       Current: " + path.getAbsolutePath() + "\n");
			System.out.println("   [1]: Select Drive");
			System.out.println("   [2]: Print Contents");
			System.out.println("   [3]: Navigate");
			System.out.println("   [4]: Return");
			System.out.println("   [5]: Create Folder Branch");
			System.out.println("   [6]: Clear Branch");
			System.out.println("   [7]: Generate Folders");
			System.out.println("   [8]: Exit");
			
			System.out.print("\nSelection: ");
		
			String selection = input.next();
			System.out.println();
			
			switch (selection) {
		
			// Determine drive letter for operation
			case "1": 
					{
						input.nextLine();
						System.out.print("Enter a drive letter: ");
						String response = input.nextLine();
						path = new File(response + ":\\");	
						break;
					}
					
				// Printing current list
				case "2":
					{
						System.out.println("Current directory: " + path.getAbsolutePath());
						File[] pathContents = path.listFiles();
						
//						listFiles(pathContents, path.getAbsolutePath());
						try {
						listDirectories(pathContents, path.getAbsolutePath());
						}
						catch (Exception ex) {
							System.out.println("Drive is empty.");
						}
						
						break;
					}
					
				// Navigate to specified directory
				case "3": 
					{
						input.nextLine();
						System.out.print("What folder? ");
						try {
						String goHere = input.nextLine();
						path = navigate(goHere, path);
						}
						catch (Exception ex) {
							System.out.println("\nPath does not exist.");
						}
						break;
					}
					
				// Returns to parent directory
				case "4": 
					{
						path = goBack(path);
						System.out.println("Going back...");
						break;
					}
					
				// Generates a branch
				case "5": 
					{
						branch = generate();
						break;
					}
					
					
				case "6": 
				{
					branch.clear();
					System.out.println("Directory branch cleared.");
					break;
				}
				
				
				case "7": 
				{
					String pathString = path.getAbsolutePath();
	
					for (String s: branch) {
						String directory = pathString + s;
						new File(directory).mkdir();
					}
					
					System.out.println("Directories created!");
					break;
				}
					
				// Exiting the program
				case "8": 
					{
						stay = false;
						break;
					}
					
					
				default: 
					{
						System.out.println("Invalid option!!");
						break;
					}
				
			} // End switch statement
			
			
		} // End while loop
		
		input.close();
		
	}
	
	// Takes path as input and prints all files 
	public static void listFiles(File[] list, String path) {
		System.out.println("Files in: " + path);
		
		for (File f: list) {
			if (f.isFile()) {
			System.out.println("--| " + f.getName());
			}
		}
		
	}
	
	// Takes path as input and prints all directories 
	public static void listDirectories(File[] list, String path) {
		System.out.println("Directories in: " + path);
		
		for (File f: list) {
			if (f.isDirectory()) {
			System.out.println("~~| " + f.getName());
			}
		}
		
	}
	
	// Navigates forward to directory indicated by user
	public static File navigate(String destination, File file) throws IOException {
		String oldPath = file.getAbsolutePath();
		String newPath = oldPath + "\\" + destination;
		
		File newPathFile = new File(newPath);
		
		if (!(newPathFile.exists())) {
			throw new IOException();
		}
		
		return new File(newPath);
	}
	
	// Navigates backward to parent directory, stopping at root drive
	public static File goBack(File file) {
		String oldPath = file.getAbsolutePath();
		String newPath = oldPath.substring(0, oldPath.lastIndexOf("\\") + 1);
		return new File(newPath);
	}
	
	public static LinkedList<String> generate() {
		
		LinkedList<String> br = new LinkedList<>();
		
		boolean cont = true;
		
		System.out.println("List preferred directories for this branch (Q to quit): ");
		
		while (cont) {
			String newDirectory = input.nextLine();
			
			if (newDirectory.equalsIgnoreCase("Q")) {
				cont = false;
			} else {
				br.add("\\" + newDirectory);
			}
			
		}
		
		return br;
	
	}
	
}
