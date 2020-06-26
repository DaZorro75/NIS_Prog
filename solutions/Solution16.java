package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution16 extends AbstractSolution {
	
	/** Aufgabe 16 - RC4 Keyscheduleing
	 * Parameter: String[0] Key, getrennt durch _ (z.B. 1_7_1_7)
	 * Lösung: String State Table, getrennt durch _ (z.B. 2_1_3_0)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution16(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		RC4 r = new RC4();

		int[] Key = r.StateStringToArray(task.getStringArray(0));
		String x = "";
		for(int i = 0; i < r.RC4KSA(Key).length-1; i++) {
			x += r.RC4KSA(Key)[i] + "_";
		}
		x += r.RC4KSA(Key)[r.RC4KSA(Key).length-1];
		return x;
	}

}
