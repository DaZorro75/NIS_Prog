package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution07 extends AbstractSolution {

	/** Aufgabe 7 - DES R-Block
	 * Parameter: String[0] Binärstring (64 Bit), int[0] Runde
	 * Lösung: String R-Block (Binärstring 48 Bit)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution07(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		Helper h = new Helper();
		String key = "0000000000000000000000000000000000000000000000000000000000000000";
		h.generateRBlocks(task.getStringArray(0), key);
		return h.DES_R_BLOCKS[task.getIntArray(0)];
		
	}

}
