import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore.Entry;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class metrics {

	
	static Map<String,Integer> operators; 
	static Map<String,Integer> operands; 
	static String[] JavaReservedWords = { "abstract","assert","boolean","break","byte","case",
			"catch","char","class","const","continue","default","do","double","else","extends",
			"false","final","finally","float","for","goto","if","implements","import","instanceof",
			"int","interface","long","native","new","null","package","private","protected","public",
			"return","short","static","strictfp","super","switch","synchronized","this","throw","throws",
			"transient","true","try","void","volatile","while","StringTokenizer","synchronized","instanceof",
			"implements","protected","interface","transient","Exception","Character","ArrayList","volatile",
			"continue","strictfp","boolean","default","extends","finally","package","Integer","Scanner","Matcher",
			"Pattern","return","double","native","switch","throws","String","System","while","break","catch","const",
			"final","float","short","super","false","regex","case","byte","enum","goto","long","this","void","null","true","charAt",
			"Math","File","util","java","try","for","new","out","do","if","io",".","(",")","{","}","[","]",">",
			"<","+","++","-","--","/","*", "=", "!", "&&", "||","\'", "\"", ";"}; 
	
	static int n1;
	static int n2; 
	static int N1=0; 
	static int N2=0;
	static int N, n;
	static double V, L, D, E, T; 

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fileName = "ArrayImp.java";

		
		
		Scanner in = new Scanner (new File(fileName)); 
		

		operators = new HashMap<String,Integer>();
		operands = new HashMap<String,Integer>();
		
		

		while (in.hasNext()){
			String line = in.nextLine();
			line = CleanLine(line); // Clean up the lines from comments or print statements
			if(!line.isEmpty()){
				countFrequency(line); // Count frequency of Operands and Operators 
			}
		}
		
		printOutput();

	}
	
	private static void printOutput() {
		System.out.println();
	    System.out.println("------------    Operators    --------------");
	    System.out.format("%-15s%-15s","Unique Operators   ", "  Frequency of occurence");
	    System.out.println();
	    System.out.println("-------------------------------------------");

	    
		for (Map.Entry entry : operators.entrySet()) {
		    System.out.println();
		    System.out.format("%15s%15d",entry.getKey(), entry.getValue());
		    N1=N1 + (int)entry.getValue();
		}
		
		
	    System.out.println();
	    System.out.println();

	    
		n1= operators.size();		
		
		
		System.out.println();
	    System.out.println("-------------    Operands     -------------");
	    System.out.format("%-15s%-15s","Unique operands   ", "  Frequency of occurence");
	    System.out.println();
	    System.out.println("-------------------------------------------");

		for (Map.Entry entry : operands.entrySet()) {
		    System.out.println();
		    System.out.format("%15s%15d",entry.getKey(), entry.getValue());
		    N2=N2+ (int)entry.getValue();
		}
		
	    System.out.println();
	    System.out.println();

		n2= operands.size(); 
		
		n = n1+n2;
		N = N1+N2; 
		V = N * Math.log(n); 
		L = 2 / (double)n1 * (double)n2/N2; 
		D = 1/L; 
		E = V/L; 
		T= E/18;
		
	    System.out.println("-------------------------------------------");

	    DecimalFormat formatter = new DecimalFormat("#0.0000");

	    
		System.out.println();
		System.out.println("Number of unique operators, n1= "+ n1);
		System.out.println("Number of unique operands, n2= "+ n2);
		System.out.println();
		System.out.println("Total occurrences of operators, N1= "+ N1);
		System.out.println("Total occurrences of operands, N2= "+ N2);
		System.out.println();
		System.out.println("The Vocabulary, n= n1 + n2 = "+ n1 + " + "+n2 + " = "+ n);
		System.out.println();
		System.out.println("The Length, N= N1 + N2 = "+ N1 + " + "+N2 + " = "+ N);
		System.out.println();
		System.out.println("The Volume, V = N * log2(n) bits = "+N+" * "+ formatter.format(Math.log(n)) + " = "+ formatter.format(V));
		System.out.println();
		System.out.println("The Level, L = (2/n1) * (n2/N2) = (2 / "+n1+") * ("+n2+"/"+ N2+ ") = ("+formatter.format(2.0/n1)+") * ("+formatter.format((double)n2/N2)+") = "+ formatter.format(L));
		System.out.println();
		System.out.println("The Difficulty, D=1/L = 1/"+formatter.format(L)+" = "+formatter.format(D));
		System.out.println();
		System.out.println("The Effort, E=V/L = "+formatter.format(V)+"/"+formatter.format(L)+" = "+formatter.format(E));
		System.out.println();
		System.out.println("The computed programming Time, T=E/S seconds = "+formatter.format(E)+"/18 = "+ formatter.format(T)+ " seconds = "+ formatter.format(T/60)+ " minutes");
		
	}

	public static void countFrequency(String line){

		String [] words = line.split("(?<=[().><=':{}\\[\\]\\-+\\*;/\"\'\\++ ])|(?=[().><=':{}\\[\\]\\-+\\*;/\"\'\\++ ])");

		
		for (String word:words){

			boolean exists = false; 

			if(!word.equals(" ")){  // If word is not empty
	

				for(String s: JavaReservedWords){
					if(s.equals(word)) { // if word is an operator
						
					
							Integer f = operators.get(word);
						    //checking null
						    if(f==null) f=0;
						    operators.put(word,f+1);
						    exists = true; 
						
					}	
				}	
				
				if(!exists){  // if word is an operand
					 Integer f = operands.get(word);
					    //checking null
					    if(f==null) f=0;
					    operands.put(word,f+1);

				}
				
			}
			
		}

		
	}
	
	public static String CleanLine(String line){
		if(!line.isEmpty()) {
			// Remove any comment or print statement to not check for predicates
			line = removeComment(line); 
			line = removePrintStatement(line);
		}
		return line; 
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
		
			line = line.trim();
			if(line.contains("System.out.print")){

				//System.out.println(" there is a comment " +  i );
				line = line.substring(0, 0);
			}
		
		return line;
	}	

}
