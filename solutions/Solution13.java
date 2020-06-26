package de.unidue.iem.tdr.nis.client.solutions;

import de.unidue.iem.tdr.nis.client.Connection;
import de.unidue.iem.tdr.nis.client.TaskObject;
import de.unidue.iem.tdr.nis.client.AbstractSolution;

public class Solution13 extends AbstractSolution {

	/** Aufgabe 13 - AES SubBytes(), ShiftRows(), MixColumns()
	 * Parameter: String[0] HEX String (128 Bit)
	 * Lösung: String HEX String (128 Bit)
	 */

	/* Konstruktor - NICHT verändern */
	public Solution13(Connection con, TaskObject task) {
		super(con, task);
	}

	@Override
	public String run() {
		AES a = new AES();
		//Global Values
		String[][] DATA_BLOCK = new String[4][4];
		String[] t0 = a.chopHexStream(task.getStringArray(0));
		
		//Write the Key to the first 4x4-block
		for(int i = 0; i < 4; i++) {
			String[] t1 = a.chopHexStream(t0[i]);
			for(int j = 0; j < 4; j++) {
				DATA_BLOCK[j][i] = t1[j];
			}
		}
		
		//SubBytes
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				int[] binHex = a.BinaryStringToIntArray(a.HexToBinary(DATA_BLOCK[j][i]));
				binHex = a.AESsubBytes(binHex);
				DATA_BLOCK[j][i] = a.binaryToHex(binHex);
			}
		}
		
		//ShiftRows
		DATA_BLOCK = a.AESShiftRows(DATA_BLOCK);
		
		//MixColumns
		for(int i = 0; i < 4; i++) {
			String[] temp = new String[4];
			for(int j = 0; j < 4; j++) {
				temp[j] = DATA_BLOCK[j][i];
			}
			temp = a.AESMixColumns(temp);
			for(int j = 0; j < 4; j++) {
				DATA_BLOCK[j][i] = temp[j];
			}
		}
		
		//Write DATA_BLOCK To Hexstream
		String result = "";
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				result += DATA_BLOCK[j][i];
			}
		}
		
		return result;
		
	}
}
