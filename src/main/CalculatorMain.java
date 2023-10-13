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
			} else if(equation.charAt(i) == '=' && i == (equation.length() - 1)) {
				//Skip token for equals sign if input at end of equation.
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
			} else if(t.isOperator()) {
				if(!(operators.isEmpty())) {
					while(!(operators.isEmpty()) && ((!(operators.peek().getType().equals("(")) && 
							(operators.peek().getPrecidence() > t.getPrecidence())) || 
							(operators.peek().getPrecidence() == t.getPrecidence() && !(t.getType().equals("^"))))) {
						output.add(operators.pop());
					}
				}
				operators.push(t);
			}
			if(t.isOperator() && t.getType().equals("(")) {
				operators.push(t);
			}
			if(t.isOperator() && t.getType().equals(")")) {
				//try {
					while(!(operators.isEmpty()) && !(operators.peek().getType().equals("("))) {
						output.add(operators.pop());
					}
				//}
				//catch(Exception e) {
					//System.out.println("ERROR: Parenthesis are mismatched. Please try again.");
					//System.exit(0);
				//}
				if(!(operators.isEmpty()) && operators.peek().getType().equals("(")) {
					operators.pop();
				}
			}
		}
		while(!(operators.isEmpty())) {
			if(operators.peek().equals("(") || operators.peek().equals(")")) {
				operators.pop();
			} else {
				output.add(operators.pop());
			}
		}
		
		//Calculate result using reverse Polish notation
		double result = 0;
		for(int i = 0; i < output.size(); i++) {
			
			if(output.get(i).isOperator()) {
				if(output.get(i).getType().equals("(") || output.get(i).getType().equals(")")) {
					output.remove(i);
					i--;
				} else {
				if(i > 0 && (output.get(i).getType().equals("sin") || output.get(i).getType().equals("cos") || output.get(i).getType().equals("tan") || output.get(i).getType().equals("ln") || output.get(i).getType().equals("log"))) {
					result = output.get(i).operate(output.get(i-1).getValue());
					output.get(i-1).setValue(result);
					output.remove(i);
					i--;
				} else if(i > 1){
					result = output.get(i).operate(output.get(i-2).getValue(), output.get(i-1).getValue());
					output.get(i-2).setValue(result);
					output.remove(i);
					output.remove(i-1);
					i = i-2;
				}
				}
			}
			
		}
		System.out.println("Result: " + result);
		
		input.close();
	}
	
}
