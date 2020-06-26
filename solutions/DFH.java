package de.unidue.iem.tdr.nis.client.solutions;

import java.util.Random;

public class DFH extends Helper{

//-----------------------------------------------------------------------------------------------------------------------
	
	//Global variables
	int DFH_a;
	String Key;
	int KeyInt;

//-----------------------------------------------------------------------------------------------------------------------
	
	
	public void generateDFHPublic(int p) {
		//Generating Random a
		Random r = new Random();
		DFH_a = r.nextInt(p-1);
	}
	
	public int generateDFHA(int g, int p) {
		return (int) bigModulo(g, DFH_a, p);
	}
	
	public void generateDFHKey(int p, double b) {
		int k = (int) bigModulo((int) b, DFH_a, p);
		this.KeyInt = k;
		this.Key = Integer.toString(k);
	}
	
	public String decryptDFHMessage(String s) {
		RC4 r = new RC4();
		
		//Chop the String
		String[] m = new String[getASCIIStringLength(s)];
		int k = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '_') {
			}
			else if(s.charAt(i) != '_' && s.charAt(i+1) != '_' && s.charAt(i+2) != '_') {
				m[k] = s.substring(i, i+3);
				i += 2;
				k++;
			}
			else {
				m[k] = s.substring(i, i+2);
				i++;
				k++;
			}
		}
			//convert Strings to ints
			int[] mInt = new int[m.length];
			for(int i = 0; i < m.length; i++) {
				mInt[i] = Integer.parseInt(m[i]);
			}
			
			int[] XORED = new int[mInt.length];
			int[] key = DecimalToBinaryArray(this.KeyInt);
			for(int i = 0; i < mInt.length; i++) {
			
				//convert current number to binary
				int[] bin2 = DecimalToBinaryArray(mInt[i]);
				
				
				//Fitting the Arrays
				if(key.length < bin2.length) {
					int diff = bin2.length-key.length;
					int [] temp = new int[bin2.length];
					for(int j = 0; j < diff; j++) {
						temp[j] = 0;
					}
					for(int j = 0; j < key.length; j++) {
						temp[diff+j] = key[j];
					}
					key = temp;
				}
				else if (key.length > bin2.length) {
					int diff = key.length - bin2.length;
					int [] temp = new int[key.length];
					for(int j = 0; j < diff; j++) {
						temp[j] = 0;
					}
					for(int j = 0; j < bin2.length; j++) {
						temp[diff+j] = bin2[j];
					}
					bin2 = temp;
				}
				
				int[] xor2 = r.XOR2(key, bin2);
				XORED[i] = binaryToDecimal(xor2);
		}
			
			
		
	return charArrayToString(ASCIIToClear(XORED));
	}
}
