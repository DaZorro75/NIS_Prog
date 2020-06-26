package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;

import java.util.Arrays;

import de.unidue.iem.tdr.nis.client.AbstractSolution;


public class Solution15 extends AbstractSolution {

	/** Aufgabe 15 - RC4 Generation Loop
	 * Parameter: String[0] State Table (Werte durch _ getrennt, z.B. "2_1_3_0")
	 * 			  String[1] Klartext
	 * Lösung: String State Table der Loop ohne Trennzeichen (z.B. 2130)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution15(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		RC4 r = new RC4();
		int[] STable = r.StateStringToArray(task.getStringArray(0));
		
		
		
		String t = task.getStringArray(1);
		
		/** 
		 * Debug
		 * System.out.println("State-String: " + task.getStringArray(0));
		 *System.out.println("Klartext: " + t);
		 *System.out.println("Systemausgabe: " + r.RC4PRGA(STable, t)); 
		 */
		
		
		return r.RC4PRGA(STable, t);
	}

}
