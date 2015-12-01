import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LOCcounter {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fileName = "Stack.java";
				
		int LOC= countLOC(fileName); 
		int Predicates = countPredicates("Stack.java"); 
		int V = countCyclomaticNumber(Predicates); 
		
		System.out.println("Lines of Codes: " + LOC);
		System.out.println("Number of predicates: " + Predicates);
		System.out.println("Number of Cyclomatic Number: " + V);	
	}
	
	
	public static int countLOC(String FileName) throws FileNotFoundException{
		
		int count = 0; 
		Scanner in = new Scanner (new File(FileName)); 
		
		while(in.hasNext()){
			if(!in.nextLine().isEmpty()) {
				count++; 
			}
		}
		
		return count;
		
	}
	
	public static int countPredicates(String FileName) throws FileNotFoundException{
		
		int count = 0; 
		Scanner in = new Scanner (new File(FileName)); 
		int predicate = 0; 
		
		while(in.hasNext()){
			
			String line = in.nextLine();
			if(!line.isEmpty()) {
				
				// Remove any comment or print statement to not check for predicates
				line = removeComment(line); 
				line = removePrintStatement(line);

				for(int i=0; i<line.length(); i++){
			// Check for conditions		
					// if contains "else"
					if(line.charAt(i) == 'e' && line.charAt(i+1) == 'l' && line.charAt(i+2) == 's' && line.charAt(i+3) == 'e'){
					
						// if contains " else if"
						if (line.charAt(i+5) == 'i' && line.charAt(i+6) == 'f'){
							predicate++;
							i+=6; 

						}			
						// if contains simple "else"
						else {
							predicate++;
							i+=3; 

						}
					}			
					// if contains "if"
					else if (line.charAt(i) == 'i' && line.charAt(i+1) == 'f'){
						predicate++;
						i++; 

					}
					// if contains "&&"
					else if(line.charAt(i) == '&' && line.charAt(i+1) == '&'){
						predicate++;
						i++; 

					}
					// if contains "||"
					else if(line.charAt(i) == '|' && line.charAt(i+1) == '|'){
						predicate++;
						i++; 

					}
					
			// Check for loops		
					// if contains "for"
					else if(line.charAt(i) == 'f' && line.charAt(i+1) == 'o' && line.charAt(i+2) == 'r'){
						predicate++;
						i+=2; 

					}
					// if contains while
					else if(line.charAt(i) == 'w' && line.charAt(i+1) == 'h' && line.charAt(i+2) == 'i' && line.charAt(i+3) == 'l' && line.charAt(i+4) == 'e'){
						predicate++;
						i+=4; 

					}
				
				}
			}
		}
		
		return predicate;
		
	}
	
	public static int countCyclomaticNumber(int PredicateNum){
		return PredicateNum + 1; 
	}
	
	
	public static String removeComment(String line){
		for (int i = 0; i<line.length(); i++){
			if(line.charAt(i) == '/' && line.charAt(i+1) == '/'){
				//System.out.println(" there is a comment " +  i );
				line = line.substring(0, i);
			}
		}
		
		return line;
	}
	
	public static String removePrintStatement(String line){
			if(line.contains("System.out.print")){

				//System.out.println(" there is a comment " +  i );
				line = line.substring(0, 0);
			}
		
		return line;
	}

	

	
	

}
