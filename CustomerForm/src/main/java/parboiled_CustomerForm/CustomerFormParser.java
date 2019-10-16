package parboiled_CustomerForm;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

@BuildParseTree
public class CustomerFormParser extends BaseParser<Object> {
	
	Rule FullContact() {
		// Master rule for the input contact information
			return Sequence(FullName(), FullAddress(), PhoneNumber());
	}
	
	Rule FullAddress() {
		// Establishes legal pattern according to prompt: "street number street name, city, state, country name"
		return Sequence(
				St_Num(), Space(), St_Name(), Separator(), City(), Separator(), State(), Separator(), Country() 
				);
	}
	
	Rule FullName() {
		// Establishes legal pattern according to prompt, allowing for middle names or middle initials
		return FirstOf(
				Sequence(Word(), Space(), FirstOf(MidInit(), Word()), Space(), Word()),
				
				// As the prompt indicates and as per the Quiz 3 grammar, this requires a FirstName_Space_LastName input
				Sequence(Word(), Space(), Word())
				);
	}
	
	Rule PhoneNumber() {
		// Establishes legal pattern according to prompt, only in the form (XXX) XXX-XXXX (where X is a digit)
		return Sequence(AreaCode(), Space(), Prefix(), LineNumber());
	}
	
	Rule AreaCode() {
		// Area code portion of phone number => (XXX) (where X is a digit)
		return Sequence('(', NTimes(3, Numerals()), ')');
	}
	
	Rule Prefix() {
		// Prefix portion of phone number => XXX- (where X is a digit)
		return Sequence(NTimes(3, Numerals()), '-');
	}
	
	Rule LineNumber() {
		// Line number of phone number => XXXX (where X is a digit)
		return NTimes(4, Numerals());
	}
	
	 Rule Word() {
		 // Splits the names into an initial character and a subsequent "chunk" of remaining characters
		 return Sequence(FirstChar(), CharChunk());
	 }
	 
	 Rule MidInit() {
		 // Used when either a "X." or "X" is present (where X is a capital character)
		 return FirstOf(Sequence(CapitalChar(), '.'), CapitalChar());
	 }
	
	 Rule Name() {
		 // Returns names according to traditional rules but allows instances of a space
		 return FirstOf(Sequence(FirstChar(), CharChunk(), '.'), Sequence(FirstChar(), CharChunk(), ' '), Sequence(FirstChar(), CharChunk()), Sequence(CharChunk(), ' '));
	 }
	 
	 Rule FirstChar() {
		 // Returns if capital character found
		 return CapitalChar();
	 }
	 
	 Rule St_Num() {
		 // Returns a certain amount of numerals for the street number
		 return OneOrMore(Numerals());
	 }
	 
	 Rule St_Name() {
		 // Returns one or more name rules to match a given street name
		 return OneOrMore(Name());
	 }
	 
	 Rule City() {
		 // Returns Washington, DC if present (since it is a legal city) or another chain of names
		 return FirstOf("Washington, DC", OneOrMore(Name()));
	 }
	 
	 Rule State() {
		 // Returns only two capital letters for a legal state identifier
		 return FirstOf(Word(), NTimes(2, CapitalChar()));
	 }
	 
	 Rule Country() {
		 // Returns an acronym (either US or USA) or one or more names (United States of America)
		 return FirstOf(Acronym(), OneOrMore(Name()));
	 }
	 	 
	Rule CharChunk() {
		// Will continuously return lower case characters until the white space is reached
		return OneOrMore(LowerChar());
	}
	
	Rule Acronym() {
		// Returns either US or USA
		return FirstOf(NTimes(3, CapitalChar()), NTimes(2, CapitalChar()));
	}
	
	Rule CapitalChar() {
		// Selects from a range of all capital letters in the English alphabet
        return CharRange('A', 'Z');
    }
	
	Rule LowerChar() {
		// Selects from a range of all lower case letters in the English alphabet
        return CharRange('a', 'z');
    }
	
	Rule Numerals() {
		// Returns numeric digits
		return CharRange('0', '9');
	}
	
	Rule Separator() {
		// Returns for instances of ", "
		return Sequence(Comma(), Space());
	}
	
	Rule Comma() {
		// Returns for instances of commas
		return Ch(',');
	}
	
	Rule Space() {
		// Returns for instances of spaces
		return Ch(' ');
	}

}
