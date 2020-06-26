package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution04 extends AbstractSolution {

	/** Aufgabe 4 - Faktorisierung
	 * Parameter: int[0] Zufallszahl (dezimal)
	 * Lösung: String Primfaktoren, aufsteigen, getrennt durch * (z.B. 2*2*5*7)
	 */

	String PrimeFactors = "";
	
	/* Konstruktor - NICHT verändern */
	public Solution04(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public int modulo(int a, int b) { 
		if (b > a && a != 0) {
			
		int x = 1;
		int ta = a;
		int tb = b;
		/*
		for (int i = 0; i < b; i++) {
			if (a < b) {
			a += ta;
			x++;
			}
			else {
				break;
			}
		}
		*/
		double d = b / a;
		x = (int) d;
		
		//System.out.println("Teiler: " + x);
		if (a != 1 && b != 2) {
		int o = ta * x;
		int p = tb - o;
		return p;
		}
		else {
			return 1;
			}
		
		}
			
		else if (a == 0 || a == b || b == 0) {
			return 0;
		}
		
		else {
			return modulo (b, a);
		}
	}

	@Override
	public String primeFactors(int n) {
		Helper h = new Helper();
		int[] PrimeNumbers = getPrimeNumbers(n);
		int pos = 0;
		try {
		while (this.modulo(PrimeNumbers[pos], n) != 0) {
			pos++;
		}
		//System.out.println("Gefundener Primfaktor: " + PrimeNumbers[pos]);
		if (PrimeNumbers.length != 1) {
			this.PrimeFactors += PrimeNumbers[pos] + "*";
			primeFactors(n / PrimeNumbers[pos]);
		}
		else {
			this.PrimeFactors += PrimeNumbers[pos];
			}
		}
		catch (java.lang.ArrayIndexOutOfBoundsException e) {
	
			}
		
		return h.chopLastChar(this.PrimeFactors);
		
	}
	
	@Override
	public String run() {
		return this.primeFactors(task.getIntArray(0));
	
	}

}
