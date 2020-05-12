package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution05 extends AbstractSolution {

	/** Aufgabe 5 - Vigen�re
	 * Parameter: String[0] Chiffretext, String[1] Key
	 * L�sung: String Klartext
	 */

	/* Konstruktor - NICHT ver�ndern */
	public Solution05(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		Helper h = new Helper();
		/*
		 *Debugging for unknown Server Errors ("7", "12")
		 * System.out.println("MESSAGE: " + task.getStringArray(0));
		 * System.out.println("KEY: " + task.getStringArray(1));
		 *System.out.println("THE SYSTEM DECRYPTED THIS TO: " + h.Vignere(task.getStringArray(0), task.getStringArray(1)));
		 */
		
		return h.Vignere(task.getStringArray(0), task.getStringArray(1));
	}

}
