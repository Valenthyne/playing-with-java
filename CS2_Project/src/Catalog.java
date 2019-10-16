/*
 * [Catalog]: Serves as the class which will contain and maintain
 * the three primary lists in the program, the Media Master List, 
 * Media Current List, and Filter Filter List.
 */

import java.util.LinkedList;
import java.util.Iterator;

public class Catalog {
	
	// These fields are the three lists that will be used primarily used throughout the project
	private LinkedList<Media> masterList;
	private LinkedList<Media> currentList;
	private LinkedList<Filter> filterList;

	Catalog(LinkedList<Media> list) { // Constructor will take list object retrieved from [DataFileReader] class
		masterList = list;
		currentList = new LinkedList<Media>(list);
		filterList = new LinkedList<Filter>();
	}
	
	protected void useFilter(Filter f) throws InvalidFilterException {
		
		Iterator<Media> iterator = currentList.iterator();
		
		// Iterates through the current filtered list and removes any object that does not satisfy the argument filter
		while (iterator.hasNext()) {
			if (!iterator.next().applyFilter(f)) {
				iterator.remove();
			}
		}
		
	}
	
	protected void addFilter(Filter filter) throws InvalidFilterException {
		
		// First checks to see if using the filter will not throw an InvalidFilterException
		useFilter(filter);
		
		// If the filter is satisfactory, it is added to the filter list
		filterList.add(filter);
		
	}
	
	protected void removeFilter(int index) {
		
		try {
			removeFilter(filterList.get(index - 1)); // Removes provided filter minus 1 to match list indices vs user input
			System.out.println("\nFilter successfully removed!");
			System.out.println(toString() + "\n");
		} catch (Exception ex) {
			System.out.println("Either index does not exist or invalid entry.\n"); // If invalid input, returns with this
		}
		
	}
		
	protected void removeFilter(Filter filter) throws InvalidFilterException {
		
		// Removes the filter from the list based upon the argument
		filterList.remove(filter);
		
		// Reverts the currentList to the masterList
		currentList = new LinkedList<Media>(masterList);
		
		// Cycles through all available filters and applies them to the filtered list
		for (Filter f: filterList) {
			useFilter(f);
		}
		
	}
	
	// Simply clears all filters present within the list and returns a new list based upon the master list
	protected void clearFilters() {
		
		filterList.clear();
		currentList = new LinkedList<Media>(masterList);
		
	}
	
	protected String retrieveData() {
		
		// Thought about using StringBuilder in terms of efficiency 
		StringBuilder s = new StringBuilder();
		
		// Every entry within the filtered list will be printed as requested
		for (Media element: currentList) {
			s.append(element + "\n");
		}
		
		// Returns the master string with all filtered entries
		return s.toString();
		
	}
	
	
	protected String retrieveFilters() {
		
		if (filterList.isEmpty()) {
			return "\nNo filters at the moment!\n";
		}
		
		// Thought about using StringBuilder in terms of efficiency 
		StringBuilder s = new StringBuilder();
		
		// Every entry within the filter list will be printed as requested
		for (int i = 1; i <= filterList.size(); i++) {
			s.append(i + ": " + filterList.get(i-1) + "\n");
		}
		
		// Returns the master string with all filters entries
		return s.toString();
	}
	
	// Prints count of total titles, filtered titles, and filters for user reference
	public String toString() {
		return "Total titles: " + masterList.size() + " | Current titles: " + currentList.size() + " | Filters: " + filterList.size();
	}
			
}