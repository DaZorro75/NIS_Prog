package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution10 extends AbstractSolution {

	/** Aufgabe 10 - AES GF2^8
	 * Parameter: String[0] HEX Zahl 1, String[1] HEX Zahl 2
	 * Lösung: String HEX Ergebnis (nicht case-sensitive) mit führenden Nullen
	 */

	/* Konstruktor - NICHT verändern */
	public Solution10(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		Helper h = new Helper();
		AES    a = new AES();
		return h.binaryToHex(a.AESMultiplication(h.BinaryStringToIntArray(h.HexToBinary(task.getStringArray(0))), h.BinaryStringToIntArray(h.HexToBinary(task.getStringArray(1)))));
	}

}
