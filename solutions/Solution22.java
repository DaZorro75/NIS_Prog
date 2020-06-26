package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution22 extends AbstractSolution {

	/** Aufgabe 22 - ElGamal Entschlüsselung
	 * Key: String[] {p, alpha, beta} Public Key
	 * Parameter: String[0] Chiffretext
	 * Lösung: String Klartext (nicht case-sensitive)
	 */

	ElGamal e = new ElGamal();
	int[] publicKey = e.generateKeys();
	
	
	/* Konstruktor - NICHT verändern */
	public Solution22(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		String s = task.getStringArray(0);
		String s0 = e.decrypt(s);
		return s0;
	}
	
	/**
	 * Diese Aufgabe erfordert das Übergeben eines Keys.
	 * Geben Sie in dieser Methode Ihren generierten public key zurück.
	 * @return String[] Alle Key Werte
	 */
	@Override
	public String[] getKey() {
		return e.getPublicKey();
	}

}
