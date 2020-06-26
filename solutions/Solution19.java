package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;


import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution19 extends AbstractSolution {

	/** Aufgabe 19 - RSA Verschlüsselung
	 * Parameter: int[0] n, int[1] e, String[0] Klartext
	 * Lösung: String Chiffretext (ASCII Werte durch _ getrennt)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution19(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		RSA r = new RSA();
		r.setRSAKey(task.getIntArray(0), task.getIntArray(1));
		int[] message = r.encryptRSA(r.plainToDecimalASCII(task.getStringArray(0)));
		String result = "";
		for(int i = 0; i < message.length-1; i++) {
			result += message[i] + "_";
		}
		result += message[message.length-1];
		return result;
			}

}
