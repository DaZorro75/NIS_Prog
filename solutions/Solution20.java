package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution20 extends AbstractSolution {

	/** Aufgabe 20 - RSA Entschlüsselung
	 * Key: String[] {n, e} Public Key
	 * Parameter: String[0] Chiffretext
	 * Lösung: String Klartext (nicht case-sensitive)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution20(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		return null;
	}
	
	/**
	 * Diese Aufgabe erfordert das Übergeben eines Keys.
	 * Geben Sie in dieser Methode Ihren generierten public key zurück.
	 * @return String[] Alle Key Werte
	 */
	@Override
	public String[] getKey() {
		return null;
	}

}
