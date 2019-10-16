// Abstract class which will serve as the container for both Movie and Series objects

public abstract class Media {
	
	protected String name;
	protected String year;
	protected double rating;
	
	public String getTitle() {
		return this.name;
	}
	
	// Checks whether or not a given Media entry will satisfy the filter provided by the user
	protected boolean applyFilter(Filter filter) throws InvalidFilterException {
		
		switch(filter.getField().toLowerCase()) {
			case "title": return filter.filter(name.toLowerCase());
			case "year": 
					
					// For entries that have no year, they will be automatically excluded
					if (year.equals("")) 
						return false;
					
					// For entries with multiple years, the first is taken as benchmark
					if (year.contains("-")) {
						return filter.filter(Double.parseDouble(year.substring(0, year.indexOf('-'))));
					} else // For entries with just single years
						return filter.filter(Double.parseDouble(year));

			case "rating": return filter.filter(rating);
		default: throw new InvalidFilterException("Invalid filter form. Please enter according to [field relation target]!");
		}
		
	}

}
