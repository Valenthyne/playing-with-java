

// [Series]: Class intended to store objects that satisfy the requirements for what entails a movie entry

public class Series extends Media {
	
	private String count;
	
	Series(String a, String b, String c, String d) {
		this.name = a;
		this.year = b;
		this.rating = Double.parseDouble(c);
		this.count = d;
	}
	
	// Overridden method which springs only when the user is seeking to compare by genre (series) and accounts for lack of length comparison
	@Override
	protected boolean applyFilter(Filter filter) throws InvalidFilterException {
		
		// Defers to the Movie method if length comparison is requested
		if (filter.getField().toLowerCase().equals("length")) {
			return false;
		}
		
		if (filter.getField().toLowerCase().equals("genre")) {
			if (filter.getTarget().equals("series")) {
				return true;
			} else {
				return false;
			} 
		} else {
				return super.applyFilter(filter);
		}
	
	}
	
	// Prints out movie entry for user to see
	public String toString() {
		return "Series: " + name + " | " + year + " | " + rating  + " stars | " + count; 
	}

}
