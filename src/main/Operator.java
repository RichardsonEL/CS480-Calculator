/* Eland Richardson
 * Lab 2
 * CS 480
 * 10/13/2023
 * Calculator Operator Class
 */

package main;

public class Operator implements Token{

	private String op;
	private int precidence;
	
	public Operator(String s) {
		System.out.println(s);
	}
	
}
