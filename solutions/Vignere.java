package de.unidue.iem.tdr.nis.client.solutions;

import java.util.Arrays;

public class Vignere extends Helper{

//--------------------------------------------------------------------------------------------------------------------------------------------
	
	//Global Variables
	int[][] vign = new int[26][26];
	
//--------------------------------------------------------------------------------------------------------------------------------------------
	
	//Methods
	public String decryptVignere(String code, String key) {
		/*
		 * codeTable	= Decrypting Table
		 * code2		= Copied Message
		 * key2			= Copied Decrypting key
		 * clear		= Cleartext   
		 */
		buildVignere();
		char[] code2 = new char[code.length()];
		char[] key2;
		char [] clear = new char[code.length()];
		
		
		//Writing the Coded Message to Array
		for(int i = 0; i < code.length(); i++) {
			code2[i] = code.charAt(i);
		}
		
		//Writing Key to Array
		if(key.length() >= code.length()) {
		key2  = new char[key.length()];
		for(int i = 0; i < key.length(); i++) {
			key2[i] = key.charAt(i);
			}
		}
		else {
			key2  = new char[code.length()];
			for(int i = 0; i < key.length(); i++) {
				key2[i] = key.charAt(i);
			}
			for(int i = 0; i + key.length() < key2.length; i++) {
				key2[i + key.length()] = key.charAt(i);
			}
		}
		
		//DecryptingAlgorithm
		
		for(int i = 0; i < code2.length; i++) {
			/*
			 * IndexK	= Index for Char at Keyposition i
			 * IndexM	= Index for Encryption char
			 */
			int IndexK = 0;
			int IndexM = 0;
			int IndexL = 0;
			
			
			//Find Index for Keychar
			if(isBigLetter(key2[i]) == true) {
				for (int j = 0; j < AlphabetBig.length; j++) {
				if(key2[i] != AlphabetBig[j]) {
					IndexK++;
					continue;
					}
				else {
					IndexK = j;
					break;
					}
				}
			}
			else {
				for (int j = 0; j < AlphabetSmall.length; j++) {
					if(key2[i] != AlphabetSmall[j]) {
						IndexK++;
						continue;
						}
					else {
						IndexK = j;
						break;
						}
					}
			}
			//System.out.println("Schlüsselsymbol " + key2[i] + " ist bei Index " + IndexK);
		
		//Find Index of Encrypted Symbol
		if(isBigLetter(code2[i]) == true) {
			for (int j = 0; j < AlphabetBig.length; j++) {
			if(code2[i] != AlphabetBig[j]) {
				IndexL++;
				continue;
				}
			else {
				IndexL = j;
				break;
				}
			}
		}
		else {
			for (int j = 0; j < AlphabetSmall.length; j++) {
				if(code2[i] != AlphabetSmall[j]) {
					IndexL++;
					continue;
					}
				else {
					IndexL = j;
					break;
					}
				}
		}
		//System.out.println("Verschlüsseltes Symbol  " + code2[i] + " ist bei Index " + IndexL);
	
		
		
		//Find Encrypted Symbol in Vignère-Table 
		while (IndexL != vign[IndexK][IndexM]) {
			IndexM++;
			}		
		//System.out.println("Gefundes Klaretextsymbol " + AlphabetBig[IndexM] + " Koordinaten: " + IndexL + "-" + IndexM);
		
		//Decrypt the Symbol 
		clear[i] = AlphabetBig[IndexM];
			}
		return charArrayToString(clear);
	}

	private int[][] buildVignere() {
		//Initializing the Table
			for (int i = 0; i < vign[0].length; i++) {
				for (int j = 0; j < vign[0].length; j++) {
					vign[i][j] = j;
				}
			}
			
			/*System.out.println("Aufgebaute Tabelle:");
			print(vign);
			System.out.print("\n");
			*/
			
			
//-------------------------------------------------------------------------------------------------------------------------------------------
				int[] row = new int[AlphabetBig.length];
				for (int i = 1; i < AlphabetBig.length; i++) {
					//Save Previous Row as Array
					for(int j = 0; j < AlphabetBig.length; j++) {
						row[j] = vign[i-1][j];
					}
					//Forward
					int[] temp = oneForward(row);
					//Back to Table 
					for (int k = 0; k < AlphabetBig.length; k++) {
						vign[i][k] = temp[k];
					}
					/*System.out.println("Aktuelle Zeile: " + i + "\n");
					System.out.println("Tabelle aktuell: " + "\n");
					print(vign);
					System.out.println("\n");
					*/
			}
		//print(vign);
		return vign ;
		}

}
