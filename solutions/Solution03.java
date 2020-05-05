package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution03 extends AbstractSolution {

	/** Aufgabe 3 - Modulo
	 * Parameter: int[0] Dezimalzahl 1, int[1] Dezimalzahl 2
	 * Lösung: Zahl 1 MOD Zahl 2
	 */

	/* Konstruktor - NICHT verändern */
	public Solution03(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		Helper h = new Helper();
		int res = h.modulo(task.getIntArray(0), task.getIntArray(1));
		return Integer.toString(res);	}

}
