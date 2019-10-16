// [Movie]: Class intended to store objects that satisfy the requirements for what entails a movie entry

public class Movie extends Media {
	
	private double runtime;
	private String length;
	
	Movie(String a, String b, String c, String d) {
		this.name = a;
		this.year = b;
		this.rating = Double.parseDouble(c);
		this.length = d;
		
		// Breaks apart the "hour" and "minute" lengths into numeric values for evaluation
		this.runtime = extractRuntime(d);

	}
	
	// Overridden method which springs only when the user is seeking to compare by length or genre (movie)
	@Override
	protected boolean applyFilter(Filter filter) throws InvalidFilterException {
		
		// Uses similar method as that above to break apart the "length" target into numeric values for comparison
		if (filter.getField().toLowerCase().equals("length")) {
			String d = filter.getTarget().toLowerCase();
			
			double runtimeX = extractRuntime(d);
			
			switch (filter.getRelation()) {
			case "<": return this.runtime < runtimeX;
			case "=": return this.runtime == runtimeX;
			case ">": return this.runtime > runtimeX;
			case "<=": return this.runtime < runtimeX || this.runtime == runtimeX;
			case ">=": return this.runtime > runtimeX || this.runtime == runtimeX;
			default: return false;
			}
			
		}

		if (filter.getField().toLowerCase().equals("genre")) {
			if (filter.getTarget().equals("movie")) {
				return true;
			} else {
				return false;
			}
		} else {
			return super.applyFilter(filter);
		}
		
	}
	
	private double extractRuntime(String d) {
		
		double runtime = 0;
		
		if (d.contains("hr") && d.contains("m")) {
			String hour = d.substring(0, d.indexOf(" "));
			String min = d.substring(d.indexOf(" "), d.length());
			
			double hourNum = Double.parseDouble(hour.substring(0, hour.indexOf("h"))) * 60;
			double minNum = Double.parseDouble(min.substring(1, min.indexOf("m"))); 
			runtime = hourNum + minNum; 
			
 		} else if (d.contains("hr")){
 			runtime = Double.parseDouble(d.substring(0, d.indexOf("h"))) * 60;
 		} else {
 			runtime = Double.parseDouble(d.substring(0, d.indexOf("m"))); 			
 		}
		
		return runtime;
	}
	
	// Prints out movie entry for user to see
	public String toString() {
		return "Movie: " + name + " | " + year + " | " + rating + " stars | " + length; 
	}

}
