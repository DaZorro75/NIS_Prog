package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution21 extends AbstractSolution {

	/** Aufgabe 21 - ElGamal Verschlüsselung
	 * Parameter: int[0] p, int[1] alpha, int[2] beta, String[0] Klartext
	 * Lösung: String Chiffretext (ASCII Werte [HEX] durch _ getrennt mit vorangestelltem y1)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution21(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		ElGamal e = new ElGamal();
		e.setKey(task.getIntArray(0), task.getIntArray(1), task.getIntArray(2));
		return e.encrpyt(task.getStringArray(0));
	}

}
