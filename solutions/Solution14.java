package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution14 extends AbstractSolution {

	/** Aufgabe 14 - AES Drei Runden
	 * Parameter: String[0] Datenblock (HEX String 128 Bit)
	 * 			  String[1] Key (HEX String 128 Bit)
	 * 			  String[2] Schlüsselraum als String (z.B. "128")
	 * Lösung: String Ausgabe alle drei Runden (HEX String) durch _ getrennt
	 */

	/* Konstruktor - NICHT verändern */
	public Solution14(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		AES a = new AES();
		String datablock 		= task.getStringArray(0);
		String key 				= task.getStringArray(1);
		String[] t0 			= a.chopHexStream(datablock);
		String[][] DATA_BLOCK 	= new String[4][4];
		String result 			= "";
		
		//Generating the Keys
		a.AESRoundKeyGeneration(key);
		
		//Writing the Keystream to DATA_BLOCK
		for(int i = 0; i < 4; i++) {
			String[] t1 = a.chopHexStream(t0[i]);
			for(int j = 0; j < 4; j++) {
				DATA_BLOCK[j][i] = t1[j];
			}
		}
		
		//First Round
		a.AESDoFirstRound(DATA_BLOCK);
		
		//Two more Rounds
		for(int i = 1; i < 3; i++) {
			DATA_BLOCK = a.AESDoNormalRound(DATA_BLOCK, i);
		}
		
		//DATA_BLOCK to String
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				result += a.AES_DATABLOCKS[j][i];
			}
		}
		result += "_";
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				result += a.AES_DATABLOCKS[j][4+i];
			}
		}
		result += "_";
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				result += a.AES_DATABLOCKS[j][8+i];
			}
		}
		return result;
	}

}
