package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution04 extends AbstractSolution {

	/** Aufgabe 4 - Faktorisierung
	 * Parameter: int[0] Zufallszahl (dezimal)
	 * Lösung: String Primfaktoren, aufsteigen, getrennt durch * (z.B. 2*2*5*7)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution04(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		Helper h = new Helper();
		return h.primeFactors(task.getIntArray(0));
	}

}
