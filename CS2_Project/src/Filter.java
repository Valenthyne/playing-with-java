/*
 * [Filter]: Retains and performs the majority of filter operations
 * upon a given title for comparison.
 */

public class Filter {
	
	// Each of the fields that a filter will receive
	private String field;
	private String relation;
	private String target;
	
	Filter(String filter) throws InvalidFilterException {
		
		// Splits filters at the spaces to get an array of, if the filter is fitting, at least size 3
		String[] splitFilter = filter.split(" ");
		
		if (splitFilter.length < 3) { // Filter received is too short
			throw new InvalidFilterException("Filter too short. Please enter in the form [field] [relation] [target]!");
		}
		
		String target = splitFilter[2];
		
		for (int i = 3; i < splitFilter.length; i++) { // Concatenates target in case spaces are used in filter (like in a title)
			target += " " + splitFilter[i]; 
		}
		
		// Keeps everything lower case for purposes of comparison
		this.field = splitFilter[0].toLowerCase();
		this.relation = splitFilter[1].toLowerCase();
		this.target = target.toLowerCase();
	
	}
	
	// Filter operation will determine from the "relation" field whether the filter applies to a given entry
	protected boolean filter(String s) {
		
		switch (relation) {
		case "<": return s.compareToIgnoreCase(target) < 0;
		case "=": return s.compareToIgnoreCase(target) == 0;
		case ">": return s.compareToIgnoreCase(target) > 0;
		case "contains": return s.contains(target);
		default: return false;
		}
	}
	
	// Overloaded version of the above which works on numeric targets such as length, rating, or years
	protected boolean filter(double d) throws InvalidFilterException {
		
		Double targetNumber = 0.0;
		
		try {
			targetNumber = Double.parseDouble(target);
		} catch (NumberFormatException ex) {
			throw new InvalidFilterException("Field requires numeric target.");
		}
			
		switch (relation) {
		case "<": return targetNumber.compareTo(d) > 0;
		case "=": return targetNumber.compareTo(d) == 0;
		case ">": return targetNumber.compareTo(d) < 0;
		case "<=": return targetNumber.compareTo(d) > 0 || targetNumber.compareTo(d) == 0;
		case ">=": return targetNumber.compareTo(d) < 0 || targetNumber.compareTo(d) == 0;
		case "contains": throw new InvalidFilterException("Relation \"contains\" is invalid for a numeric target.");
		default: return false;
		}
	}
	
	// Simple get methods used for use in comparisons
	protected String getField() { return field; }
	protected String getRelation() { return relation; }
	protected String getTarget() { return target; }
	
	// Prints the entered filter (for validation purposes)
	public String toString() {
		return field + " " + relation + " " + target;
	}
	

}
