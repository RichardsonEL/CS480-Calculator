/* Eland Richardson
 * Lab 2
 * CS 480
 * 10/13/2023
 * Calculator Main Class
 */

package main;
import java.io.*;
import java.util.*;

public class CalculatorMain {

	public static void main(String[] args) {
		//Create resources
		Scanner input = new Scanner(System.in);
		
		//Prompt user for equation
		System.out.println("Please enter an equation to calculate:");
		String equation = input.nextLine();
		//Remove spaces from equation.
		equation = equation.replace(" ", "");
		
		//Tokenize equation characters into operators and operands
		LinkedList<Token> tokens = new LinkedList<Token>();
		int tokenLength = 0;
		for(int i = 0; i < equation.length(); i++) {
			//Check if character is an operand.
			if(Character.isDigit(equation.charAt(i)) || equation.charAt(i) == '.') {
				tokenLength++;
				if(i+1 < equation.length()) {
					if(!(Character.isDigit(equation.charAt(i+1)) || equation.charAt(i+1) == '.')) {
						tokens.add(new Operand(equation.substring(((i+1)-tokenLength), (i+1))));
						tokenLength = 0;
					}
				} else {
					tokens.add(new Operand(equation.substring(((i+1)-tokenLength), (i+1))));
					tokenLength = 0;
				}
			//Check if minus character is unary or binary.
			} else if(equation.charAt(i) == '-') {
				if(i == 0) {
					tokenLength++;
				} else if(0 < i && i < (equation.length() - 1) ) {
					if(!(Character.isDigit(equation.charAt(i-1))) && equation.charAt(i-1) != ')') {
						tokenLength++;
					} else {
						tokens.add(new Operator(equation.substring(i, i+1)));
						tokenLength = 0;
					}
				} else {
					tokens.add(new Operator(equation.substring(i, i+1)));
					tokenLength = 0;
				}
				
			//Else if the character is a letter-based operator.
			} else if(Character.isLetter(equation.charAt(i))) {
				tokenLength++;
				if(i+1 < equation.length()) {
					if(!(Character.isLetter(equation.charAt(i+1)))) {
						tokens.add(new Operator(equation.substring(((i+1)-tokenLength), (i+1))));
						tokenLength = 0;
					}
				}
			//Else add the single symbol character as an operator.
			} else {
				tokens.add(new Operator(equation.substring(i, i+1)));
				tokenLength = 0;
			}
		}
		
		//Organize tokens using Shunting Yard algorithm.
		LinkedList<Token> output = new LinkedList<Token>();
		Stack<Token> operators = new Stack<Token>();
		while(!(tokens.isEmpty())) {
			Token t = tokens.pop();
			if(!(t.isOperator())) {
				output.add(t);
			}
		}
		
		input.close();
	}
	
}
