/*
 * [UserInteraction]: Class which functions as the foundation for the user
 * interaction between the program and the user (i.e. the menu, mainly)
 */

import java.util.Scanner;

public class Menu {
	
	public static void menu(Catalog cat) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Totally Not Netflix!\n");
		
		boolean userExit = false;
		
		// Master while loop which determines continuation of menu
		while (!userExit) {

			System.out.println("////////////////////////////////////////");
			System.out.println("\n       Enter an option below!\n");
			System.out.println("   [1]: Add Filter");
			System.out.println("   [2]: Print Current List");
			System.out.println("   [3]: Remove Filter");
			System.out.println("   [4]: View Filters");
			System.out.println("   [5]: Clear Filters");
			System.out.println("   [6]: Exit\n");
			System.out.println("/////////////////////////////////////////");
			
			System.out.print("\nSelection: ");
			String in = input.next();
			
			switch (in) {
		
			// Adding filters
			case "1": 
					{
						input.nextLine();
						System.out.println("\nTo add a filter, ensure that it is entered in the correct format: field relation target (include spaces, case insensitive)");
						System.out.println("\n [field] => title, genre, rating, year, length (movies)");
						System.out.println(" [relation] => <, >, =, <=, >=, \"contains\" (title only)");
						System.out.println(" [target] => title: strings || genre: movie or series || year or rating: numeric values || length: XXhrXXm format");
						System.out.println("\n Note that entries currently unrated will appear as having a rating of -1 stars.");
						
						System.out.print("\nEnter a filter: ");
						String filter = input.nextLine();
						
						try { 
							cat.addFilter(new Filter(filter));
							System.out.println(cat.toString());
							System.out.println();
							} catch (Exception ex) {
								System.out.println("\n" + ex.getMessage() + "\n");
							}
						
						break;
					}
					
				// Printing current list
				case "2":
					{
						if (cat.retrieveFilters().isEmpty()) {
							System.out.println("\nNo filters have been applied.");
							break;
						} else {
							System.out.println("\nCurrent Filtered List: ");
							System.out.println(cat.retrieveData());
							System.out.println(cat.toString() + "\n");
						}
						
						break;
					}
					
				// Removing filters
				case "3": 
					{
						System.out.println("\nCurrent filters: ");
						System.out.println(cat.retrieveFilters());
						
						System.out.print("Select an index of the filter to remove [0 to return]: ");
						
						input.nextLine();
						String request = input.nextLine();
						
						try {
							int filterIndex = Integer.parseInt(request);
							if (filterIndex != 0) {
							cat.removeFilter(filterIndex);
							break;
							}
						} catch (Exception ex) {
							System.out.println("\nPlease enter a proper single integer index!");
						}
						
						System.out.println();
						break;
					}
					
				// Viewing filters
				case "4": 
					{
						System.out.println("\nCurrent filters: ");
						System.out.println(cat.retrieveFilters());
						
						break;
					}
					
				// Clearing filters
				case "5": 
					{
						input.nextLine();
						System.out.print("\nAre you sure you want to clear filters? [Y/N] ");
						String clearChoice = input.nextLine();
						
						if (clearChoice.equalsIgnoreCase("yes") || clearChoice.equalsIgnoreCase("y")) {
							 cat.clearFilters();
							 System.out.println("All filters cleared!\n");
						} else {
							System.out.println("Returning to menu...\n");
							break;
						}
						
						break;
					}
					
				// Exiting the program
				case "6": 
					{
						input.nextLine();
						System.out.print("\nAre you sure you want to quit? [Y/N] ");
						String exitChoice = input.nextLine();
						
						if (exitChoice.equalsIgnoreCase("yes") || exitChoice.equalsIgnoreCase("y")) {
							userExit = true;
							System.out.println("Exiting menu...\n");
							break;
						} else {
							System.out.println("Returning to menu...\n");
							break;
						}
					}
					
				default: 
					{
						System.out.println("\nPlease enter a numeric value matching an option below.\n"); 
						input.nextLine(); break;
					}
				
			} // End switch statement

		} // End while loop
			
		input.close();
	} // End method
		
} // End class


