package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution18 extends AbstractSolution {

	/** Aufgabe 18 - Diffie-Hellman
	 * Parameter: int[0] p, int[1] g, double[0] B
	 * Zwischenlösung: String A
	 * Parameter 2: String[0] Chiffretext (ASCII Werte durch _ getrennt)
	 * Lösung: String Klartext (Zeichenfolge, nicht case-sensitive)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution18(Connection con, TaskObject task) {
		super(con, task);
	}
	
	
	@Override
	public String run() {
		//In dieser Aufgabe muss eine Zwischenlösung abgegeben werden
		int p = task.getIntArray(0);
		int g = task.getIntArray(1);
		double B = task.getDoubleArray(0);
		DFH d = new DFH();
		StringBuilder sd = new StringBuilder();
		
		
		
		//Generate a random a
		d.generateDFHPublic(p);
		
		//calculate A
		double A = d.generateDFHA(g, p);
		
		//Sending A to Server
		String[] k = new String[1];
		sd.append(A);
		k[0] = sd.toString();
		this.sendMoreParams(k);
	
		//Calculate private Key
		d.generateDFHKey(p, B);
		
		
		String chiffre = task.getStringArray(0);
		
		return d.decryptDFHMessage(chiffre);
		
	}

}
