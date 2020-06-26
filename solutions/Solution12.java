package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;


import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution12 extends AbstractSolution {

	/** Aufgabe 12 - MixColumns()
	 * Parameter: String[0] HEX String (128 Bit)
	 * Lösung: String HEX String (128 Bit)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution12(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		AES a = new AES();
		String x = a.makeHexBig(task.getStringArray(0));
		String[] b = a.chopHexStream(x);
		for(int i = 0; i < 4; i++) {
			String[] p = a.chopHexStream(b[i]);
			p = a.AESMixColumns(p);
			b[i] = a.stringArrayToString(p);
		}
		return a.stringArrayToString(b);
	}

}
