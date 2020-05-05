package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution11 extends AbstractSolution {

	/** Aufgabe 11 - AES Schlüssel-Generierung
	 * Parameter: String[0] Key (HEX String 128 Bit)
	 * Lösung: String HEX String der 3 Rundenschlüssel (128 Bit x3) durch _ getrennt
	 */

	/* Konstruktor - NICHT verändern */
	public Solution11(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		return null;
	}

}
