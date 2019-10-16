/* Directory Tree Generator: Authored by Drachen (June 2018)
 * Version: v0.1.2
 * 
 * A program intended to allow a user to create directory "branches",
 * locate a preferred directory, and create a tree of sub-directories
 * in the current path for mass-cataloging purposes.
 */

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Scanner;

public class DirTreeGen {

	static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("DirTreeGen v0.1.2 (6/17/2018)" + "\nAuthored by Drachen\n");

		boolean stay = true;
		File path = new File("C:\\");
		LinkedList<String> branch = new LinkedList<>();
		

		System.out.println("Welcome! Let's build some trees. o/");

		while (stay) {
			
			// Booleans to handle status of sub-menus
			boolean navigation = true;
			boolean branches = true;
			boolean directoryOperations = true;

			System.out.println("\n       Path: " + path.getAbsolutePath() + "\n");
			System.out.println("   [1]: Navigation");
			System.out.println("   [2]: Branching");
			System.out.println("   [3]: Generation");
			System.out.println("   [4]: Exit");
			System.out.print("\nSelection: ");

			String selection = input.next();
			System.out.println();

			switch (selection) { // Master menu switch-statement to handle all sub-menus

					
				case "1": // Navigation menu
					
					while (navigation) {
					
						System.out.println("       Path: " + path.getAbsolutePath());
						System.out.println("\n       Navigation Menu\n");
						System.out.println("   [1]: Change Directory");
						System.out.println("   [2]: Previous Directory");
						System.out.println("   [3]: Drive Selection");
						System.out.println("   [4]: Print Contents");
						System.out.println("   [5]: Return");		
						System.out.print("\nSelection: ");
						
						String choice = input.next();
						System.out.println();
						
						switch (choice) {
						
							case "1": // Change directory
							{
								input.nextLine();
								System.out.print("What directory? ");
								try {
								String goHere = input.nextLine();
								path = navigate(goHere, path);
								listDirectories(path);
								}
								catch (Exception ex) {
									System.out.println("\nPath does not exist.");
								}
								System.out.println();
								break;
							}
								
							case "2": // Return
							{
								path = goBack(path);
								System.out.println("Going back...");
								System.out.println();
								listDirectories(path);
								System.out.println();
								break;
							}
							
							case "3": // Select Drive
							{
								input.nextLine();
								System.out.print("Enter a drive letter: ");
								String response = input.nextLine();
								path = new File(response + ":\\");
								System.out.println();
								break;
							}
							
							case "4": // Print Contents
							{
								System.out.println("Current directory: " + path.getAbsolutePath());
								
								
								try {
									listDirectories(path);
									System.out.println();
								}
								catch (Exception ex) {
									System.out.println("Drive is empty.");
									System.out.println();
								}
								
								break;
							}
								
							case "5": // Go back
							{
								System.out.println("Returning to main menu...");
								navigation = false;
								break;
							}
							
							default: System.out.println("Uh, you okay there? Try an actual option.\n");
							
						} // End switch-statement for navigation
					
					} // End while-loop for navigation
					
				break;	
					
				case "2": // Branching operations
					
					while (branches) { 
					
						System.out.println("       Path: " + path.getAbsolutePath());
						System.out.println("\n       Branch Operations\n");
						System.out.println("   [1]: Design Branch");
						System.out.println("   [2]: View Branch");
						System.out.println("   [3]: Clear Branch");
						System.out.println("   [4]: Return");
						System.out.print("\nSelection: ");
						
						String choice = input.next();
						System.out.println();
						
						switch (choice) {
						
							case "1": // Navigates to branch designer
								{
									branch = generateBranch();
									break;
								}
								
							case "2": // Views current branch
							{
								viewBranch(branch);
								break;
							}
						
								
							case "3": // Clear branch
							{
								branch.clear();
								System.out.println("Directory branch cleared.");
								System.out.println();
								break;
							}
						
							case "4": // Go back 
							{
								System.out.println("Returning to main menu...");
								branches = false;
								break;
							}
							
							default: System.out.println("Uh, you okay there? Try an actual option.\n");
							
						} // End switch-statement for branching operations
					
					} // End while-loop for branching operations
					
					break;
					
				case "3": // Generation menu
					
					while (directoryOperations) {
						
						System.out.println("       Path: " + path.getAbsolutePath());
						System.out.println("\n       Generation Menu\n");
						System.out.println("   [1]: Create Directories");
						System.out.println("   [2]: Generate Branch");
						System.out.println("   [3]: Unload Branch");
						System.out.println("   [4]: Return");
						
						System.out.print("\nSelection: ");
						
						String choice = input.next();
						System.out.println();
						
						switch (choice) {
						
							case "1": // Creates single directories, terminates with Q
							{
								generate(path);
								break;
							}
										
							case "2": // Generate (in current directory)
							{
								String pathString = path.getAbsolutePath();
				
								for (String s: branch) {
									String directory = pathString + s;
									File newDir = new File(directory);
									if (newDir.exists()) {
										System.out.println("\nDirectory already exists: " + directory);
									} else {
										newDir.mkdir();
									}
								}
								
								System.out.println("Generation complete!");
								System.out.println();
								break;
							}
							
							case "3": // Generate (in sub-directories)
							{
					
								File[] pathContents = path.listFiles();
								LinkedList<File> pathDirectories = new LinkedList<>();
								
								for (File f: pathContents) {
									if (f.isDirectory() && f.canWrite() && f.canRead() && f.canExecute() && !f.isHidden()) {
										System.out.println("Loading: " + f.getAbsolutePath());
										pathDirectories.add(f);
									}
								}
																
								for (File f: pathDirectories) {
									String pathString = "";

									 pathString = f.getAbsolutePath();
									 
									 for (String s: branch) {
											String directory = pathString + s;
											File newDir = new File(directory);
											
											if (newDir.exists()) {
												System.out.println("Fail: " + directory + " already exists.");
											} else {
												newDir.mkdir();
												System.out.println("New: " + s + " in " + newDir.getAbsolutePath());
											}
										}
									
								}
									
								System.out.println("\nOperation complete!");
								System.out.println();
								break;
							}
							
							case "4": // Go back
							{
								System.out.println("Returning to main menu...");
								directoryOperations = false;
								break;
							}
							
							default: System.out.println("Uh, you okay there? Try an actual option.\n");
						
						} // End switch-statement for directoryOperations
						
					} // End while-loop for directoryOperations
				
				break;
				
				case "4": // Exit program
				{
					stay = false;
					break;
				}
				
				default: // Default for when an invalid option is selected
				{
					System.out.println("Uh, you okay there? Try an actual option.");
					break;
				}
						
			} // End switch statement
			
			
		} // End while loop
		
		System.out.println("Thank you for using me! ^o^");
		input.close();
		
	}
	
	// Takes path as input and prints all files 
	static void listFiles(File path) {
		File[] pathContents = path.listFiles();
		String pathString = path.getAbsolutePath();
		
		System.out.println("Files in: " + pathString);
		for (File f: pathContents) {
			if (f.isFile() && f.canWrite() && f.canRead() && f.canExecute() && !f.isHidden()) {
			System.out.println("--| " + f.getName());
			}
			
		}
		
	}
	
	// Takes path as input and prints all directories 
	static void listDirectories(File path) {
		File[] pathContents = path.listFiles();
		String pathString = path.getAbsolutePath();
		
		System.out.println("Directories in: " + pathString);
		if (pathContents.length == 0) {
			System.out.println("--> Directory is empty.");
		} else {
			for (File f: pathContents) {
					if (f.isDirectory() && f.canWrite() && f.canRead() && f.canExecute() && !f.isHidden()) {
						System.out.println("~~| " + f.getName());
					}
			}
		}
	
	}
	
	// Navigates forward to directory indicated by user
	static File navigate(String destination, File file) throws IOException {
		String oldPath = file.getAbsolutePath();
		String newPath = oldPath + "\\" + destination;
		
		File newPathFile = new File(newPath);
		
		if (!(newPathFile.exists())) {
			throw new IOException();
		}
		
		return new File(newPath);
	}
	
	// Navigates backward to parent directory, stopping at root drive
	static File goBack(File file) {
		String oldPath = file.getAbsolutePath();
		String newPath = oldPath.substring(0, oldPath.lastIndexOf("\\") + 1);
		return new File(newPath);
	}
	
	static void generate(File path) {
		
		boolean cont = true;
		input.nextLine();
		
		while (cont) {

			System.out.print("New Directory:  ");
			String newDirectory = input.nextLine();
			
			if (!(newDirectory.equalsIgnoreCase(" "))) {
				
				File newDir = new File(path.getAbsolutePath() + "\\" + newDirectory);
				if (newDir.exists()) {
					System.out.println("Directory already exists: " + newDirectory);
				} else {
					newDir.mkdir();
					System.out.println("Directory created: " + newDirectory);
				}
				System.out.println();
			} else {
				System.out.println();
				cont = false;
			}
			
		}
		
	}
	
	static LinkedList<String> generateBranch() {
		
		LinkedList<String> br = new LinkedList<>();
		boolean cont = true;
		
		System.out.println("Input directory names for your branch: ");
		input.nextLine();
		
		while (cont) {
		
			String newDirectory = input.nextLine();
			
			if (newDirectory.equals("")) {
				cont = false;
			} else {
				br.add("\\" + newDirectory);
			}
			
		}
		
		System.out.println("Branch created!\n");
		
		TreeSet<String> noDupe = new TreeSet<>(br);
		br = new LinkedList<>(noDupe);
		
		return br;
	}
	
	static void viewBranch(LinkedList<String> br) {
		System.out.println("Branch Structure");
		System.out.println("----------------");
		
		if (br.isEmpty()) {
			System.out.println("Branch is empty.");
			return;
		}
		
		for (String s: br) {
			System.out.println("==| " + s);
		}
		
		System.out.println();
		
	}
	
}