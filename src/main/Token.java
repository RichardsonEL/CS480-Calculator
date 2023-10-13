/* Eland Richardson
 * Lab 2
 * CS 480
 * 10/13/2023
 * Calculator Token Interface
 */

package main;

public interface Token {

	public boolean isOperator();
	
	public double getValue();
	
	public void setValue(double x);
	
	public String getType();
	
	public int getPrecidence();
	
	public double operate(double x);
	
	public double operate(double x, double y);
	
	public String toString();
}
