import java.util.Scanner;

public class ArrayImp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[] array = new char[20];
				
		Scanner in = new Scanner(System.in);  // get k-formula 
	
		String input = in.nextLine(); 
		int j= input.length()-1; 
		char A ; 
		char B; 
		int i=0; 
		
		while (array[i] > -1 && j>-1) { // go through the formula from right to left
			if (input.charAt(j) != '*') {  // if character is not a * 
				
				array[i]=input.charAt(j); // push it to the following cell
				i++; 
				j--; 
			}
			else {  // if it is a star
				if(i>1){  // if stack is having at least 2 characters
					System.out.print(input.charAt(j)); // then print * 
					i--;
					A = array[i]; // pop first char
					i--;
					B = array[i]; // pop second char
					 
					System.out.print(A);  // print first char
					System.out.println(B); // print second char
					array[i]= A; // push it to the following cell
					i++;  // push back first char
					j--;
				} 
				else { // if there is less than 2 char
					 System.out.println("Invalid formula"); break;  // invalid formula and break from the loop 
				}
			}
		} 

	}

}
