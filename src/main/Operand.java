/* Eland Richardson
 * Lab 2
 * CS 480
 * 10/13/2023
 * Calculator Operand Class
 */

package main;

public class Operand implements Token{

	private double value;
	
	public Operand(String s) {
		System.out.println(s);
		this.value = Double.valueOf(s);
	}
	
	public double getValue() {
		return value;
	}
	
	public boolean isOperator() {
		return false;
	}
}
