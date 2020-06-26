package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution17 extends AbstractSolution {

	/** Aufgabe 17 - Verschlüsselung
	 * Parameter: String[0] Key, getrennt durch _ (z.B. 1_7_1_7), String[1] Klartext
	 * Lösung: String Chiffretext (Binärstring)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution17(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		RC4 r = new RC4();
		int[] Key = r.StateStringToArray(task.getStringArray(0));
		String plain = task.getStringArray(1);
		String s = "";
		for(int i = 0; i < r.RC4Encryption(Key, plain).length; i++) {
			s += r.HexToBinary(r.RC4Encryption(Key, plain)[i]);
		}
		return s;
	}

}
