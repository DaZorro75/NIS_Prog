package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution08 extends AbstractSolution {

	/** Aufgabe 8 - DES Feistel
	 * Parameter: String[0] Bin�rstring (64 Bit), String[1] Rundenschl�ssel (Bin�rstring 48 Bit)
	 * L�sung: String L-Block XOR R-Block (Bin�rstring)
	 */

	/* Konstruktor - NICHT ver�ndern */
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
