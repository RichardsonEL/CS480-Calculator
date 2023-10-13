/* Eland Richardson
 * Lab 2
 * CS 480
 * 10/13/2023
 * Calculator Operator Class
 */

package main;

public class Operator implements Token{

	private String type;
	private int precidence;
	
	public Operator(String s) {
		this.type = s.toLowerCase();
		if(s.equals("{") || s.equals("}")) {
			this.precidence = 5;
		} else if(s.equals("(") || s.equals(")")) {
			this.precidence = 4;
		} else if(s.equals("^")) {
			this.precidence = 3;
		} else if(s.equals("sin") || s.equals("cos") || s.equals("tan") || s.equals("cot") || s.equals("ln") || s.equals("log")) {
			this.precidence = 2;
		} else if(s.equals("*") || s.equals("/")) {
			this.precidence = 1;
		} else if(s.equals("+") || s.equals("-")) {
			this.precidence = 0;
		} else {
			System.out.println("ERROR: Operator " + s + " is not a valid operator. Please try again.");
			System.exit(0);
		}
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getPrecidence() {
		return this.precidence;
	}
	
	public double operate(double x, double y) {
		switch (type) {
			case "+":
				return (x+y);
			case "-":
				return (x-y);
			case "*":
				return (x*y);
			case "/":
				if(y == 0) {
					System.out.println("ERROR: Cannot divide by zero.");
					System.exit(0);
					return 0;
				} else {
					return (x / y);
				}
			case "^":
				return Math.pow(x, y);
			default:
				return 0;
		}
	}
	
	public double operate(double x) {
		switch (type) {
			case "sin":
				return Math.sin(x);
			case "cos":
				return Math.cos(x);
			case "tan":
				return Math.tan(x);
			case "cot":
				return (Math.cos(x)/Math.sin(x));
			case "ln":
				return Math.log(x);
			case "log":
				return Math.log10(x);
			default:
				return 0;
		}
	}
	
	public boolean isOperator() {
		return true;
	}

	public double getValue() {
		return 0;
	}

	public void setValue(double x) {		
	}
	
	public String toString() {
		return this.type;
	}
}
