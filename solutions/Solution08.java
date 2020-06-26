package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution08 extends AbstractSolution {

	/** Aufgabe 8 - DES Feistel
	 * Parameter: String[0] Binärstring (64 Bit), String[1] Rundenschlüssel (Binärstring 48 Bit)
	 * Lösung: String L-Block XOR R-Block (Binärstring)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution08(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		Helper h = new Helper();
		DES    d = new DES();
		String input = task.getStringArray(0);
		String Key  = task.getStringArray(1);
		int[] lBlock = new int[32];
		int[] rBlock = new int[32];
		
		//Converting the Input to Array
		int[] p0 = h.BinaryStringToIntArray(input);
		
		//Generating L-/R-Blocks
		for(int i = 0; i < lBlock.length; i++) {
			lBlock[i] = p0[i];
		}
		for(int i = 0; i < rBlock.length; i++) {
			rBlock[i] = p0[i + lBlock.length];
		}
		
		//Converting the given roundkey to Array
		int[] rKey = BinaryStringToIntArray(Key);
		
		return h.IntArrayToString(d.functionF(lBlock, rBlock, rKey));
	}

}
