package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution01 extends AbstractSolution {
	
	/** Aufgabe 1 - Klartext
	 * Parameter: String[0] Klartext
	 * Lösung: String Klartext
	 */

	/* Konstruktor - NICHT verändern */
	public Solution01(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		//Klartextparameter per task.getStringArray() auf index 0 holen
		//Das ganze als Lösung zurückgeben.
		return task.getStringArray(0);
	}

}
