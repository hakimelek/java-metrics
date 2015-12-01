import java.util.Scanner;

public class Stack {

	private char[] stack;
	
	private int stackSize; 
	private int Top = 0; 
	
	
	public Stack(int size){  // Stack constructor 
		stackSize = size;  // Stack size
		stack = new char[size]; // Instantiate an new stack 		
	}
	
	
	public void push(char c){  // push method, add to stack 
		if (Top < stackSize){
			stack[Top] =  c ;
			Top++;

		}
		else {
			System.out.println("Stack overflow");
		} 
	}
	
	public char pop(){   // pop method, remove from stack 
		char result = 0;  
		if (Top>0){
			Top--; 
			result = stack[Top]; 
		}
		else {
			System.out.println("Stack underfow");
		}
		
		return result; 
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);  // get k-formula 
		Stack stack1 = new Stack(20);  // create a new stack 
	
		String input = in.nextLine(); 
		int j= input.length()-1; 
		char A ; 
		char B; 
		
		while (stack1.Top > -1 && j>-1) { // go through the formula from right to left
			if (input.charAt(j) != '*') {  // if character is not a * 
				stack1.push(input.charAt(j)); // push it to the stack
				j--; 
			}
			else {  // if it is a star
				if(stack1.Top>1){  // if stack is having at least 2 characters
					System.out.print(input.charAt(j)); // then print * 
					A = stack1.pop(); // pop first char
					B = stack1.pop(); // pop second char
					System.out.print(A);  // print first char
					System.out.println(B); // print second char
					stack1.push(A);  // push back first char
					j--;
				} 
				else { // if there is less than 2 char
					 System.out.println("Invalid formula"); 
					 break;  // invalid formula and break from the loop 
				}
			}
		} 		
		
	}
}
