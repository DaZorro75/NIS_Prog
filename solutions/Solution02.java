package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution02 extends AbstractSolution {

	/** Aufgabe 2 - XOR
	 * Parameter: String[0] HEX String A, String[1] HEX String B
	 * Lösung: String A MOD B als Binärstring
	 */

	/* Konstruktor - NICHT verändern */
	public Solution02(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		Helper h = new Helper();
		return h.XOR(task.getStringArray(0), task.getStringArray(1));
		
	}

}
