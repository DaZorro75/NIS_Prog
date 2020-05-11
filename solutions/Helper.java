package de.unidue.iem.tdr.nis.client.solutions;
import java.util.*;
public class Helper{
	
	/** Hier können Sie ihre allgemeinen Funktionen erstellen,
	 * welche in allen Lösungen verfügbar sind.
	 * Sie können auch statische Methoden oder Subklassen benutzen.
	 * Sie können jedoch NICHT die Signatur des Konstruktors verändern.
	 */

//----------------------------------------------------------------------------------------------------------------------- 
	 
	//Tables and Necessary Values
	 
	char[] AlphabetBig = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	char[] AlphabetSmall = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	char[] HexValues = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f'};
	String[] BinaryHexValues = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "1010", "1011", "1100", "1101", "1110", "1111"}; 
	String PrimeFactors = "";
	int[][] vign = new int[26][26];
	int DESROUND = 0;
	String[] DESKEYS = new String[16];
	
//-----------------------------------------------------------------------------------------------------------------------
	
			
			//Converting Methods
	
			private String charArrayToString(char[] a) {
				String out = "";
				for (int i = 0; i < a.length; i++) {
					out += a[i];
				}
				return out;
			}

			public String HexToBinary(String hex) {
				/*
				 * Copy String to Array
				 */
				char[] Temp = new char[hex.length()];
				for (int i = 0; i < hex.length(); i++) {
					Temp[i] = hex.charAt(i);
					/*
					 * Debug Area
					 * System.out.println(Arrays.toString(Temp));
					 * System.out.println("Länge des HexArrays: " + HexValues.length);
					 * System.out.println("Länge des ValueArrays: " + BinaryHexValues.length);
					 */
					
				}
				/*
				 * Checking the Array for invalid Values 
				 */
				for(int i = 0; i < Temp.length; i++) {
					int j = 0;
					while (j < HexValues.length) {
						if (Temp[i] != HexValues[j]) {
							j++;
						}
						else if (j == HexValues.length) {
							System.err.println("Kein Hexadezimaler Wert: " + Temp[i]);
						}
						else {
							break;
						}
					}
				}
				String out = "";
				for (int i = 0; i < Temp.length; i++) {
					
					for (int j = 0; j < HexValues.length; j++) {
						if (Temp[i] == HexValues[j]) {
							out += BinaryHexValues[j];
							break;
						}
					}
					
				}
				return out;
			}

			public int[] BinaryStringToIntArray(String binary) {
			/*
			 * Writing String to Array
			 */
			char[] t = new char[binary.length()];
			for (int i = 0; i < t.length; i++) {
				t[i] = binary.charAt(i);
			}
			/*
			 * Writing chars to ints
			 */
			int[] out = new int[t.length];
			for (int i = 0; i < out.length; i++) {
				if (t[i] == '0') {
					out[i] = 0;
				}
				else if (t[i] == '1') {
					out[i] = 1;
				}
				else {
					System.err.println("Fehler bei der Umwandlung");
					break;
					}
				}
			return out;
			}
			
			public String IntArrayToString(int[] x) {
				String result = "";
				for (int i = 0; i < x.length; i++) {
					if (x[i] == 0) {
						result += "0";
					}
					else {
						result += "1";
					}
				}
				return result;
			}
			
			public int[] EliminateLeadingZeroes(int[] arr) {
			int fz = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == 0) {
					continue;
				}
				else if (i == 0 && arr[i] == 1) {
					break;
				}
				else {
					fz = i;
					break;
				}
			}
			int[] temp = new int[arr.length - fz];
			for (int i = fz; i < arr.length; i++) {
				temp[i - fz] = arr[i];
			}
			return temp;
			}
			

//-----------------------------------------------------------------------------------------------------------------------		

			//Mathematical Methods				
		
			public int modulo(int a, int b) { 
				if (b > a && a != 0) {
					
				int x = 1;
				int ta = a;
				int tb = b;
				/*
				for (int i = 0; i < b; i++) {
					if (a < b) {
					a += ta;
					x++;
					}
					else {
						break;
					}
				}
				*/
				double d = b / a;
				x = (int) d;
				
				System.out.println("Teiler: " + x);
				if (a != 1 && b != 2) {
				int o = ta * x;
				int p = tb - o;
				return p;
				}
				else {
					return 1;
					}
				
				}
					
				else if (a == 0 || a == b || b == 0) {
					return 0;
				}
				
				else {
					return modulo (b, a);
				}
			}
		
			public int[] BinaryAddition(int[] a, int[] b) {
 				if (a.length != b.length) {
					System.err.println("Fehler bei der Binäraddition: Die Arrays sind nicht gleich lang!");
					return null;
				}
				else {
					int sum;
					int over = 0;
					int[] result = new int[a.length];
					for (int i = a.length-1; i > -1; i--) {
						sum = a[i] + b [i] + over;
						if (sum == 0) {
							result[i] = 0;
							over = 0;
						}
						else if (sum == 1) {
							result[i] = 1;
							over = 0;
						}
						else if (sum == 2) {
							result[i] = 0;
							over = 1;
						}
						else if (sum == 3) {
							result[i] = 1;
							over = 1;
						}
					}
					return result;
				}
				
			}
			
			public String XOR(String a, String b) {
				int [] hex1 = BinaryStringToIntArray(HexToBinary(a));
				System.out.println("Binäres Array Nr. 1: " + Arrays.toString(hex1));
				int [] hex2 = BinaryStringToIntArray(HexToBinary(b));
				System.out.println("Binäres Array Nr. 2: " + Arrays.toString(hex2));
				int [] temp;
				int s;
				
				/*
				 * Aligning the Length of the Arrays 
				 */
				if (hex1.length > hex2.length) {
					s = hex1.length - hex2.length;
					int [] hex22 = new int[hex1.length];
					// Filling with Zeroes
					for (int i = 0; i < s; i++) {
						hex22[i] = 0;
					}
					for(int i = s; i < hex22.length; i++) {
						hex22[i] = hex2[i-s];
					}
					hex2 = hex22;	
				}
				else if (hex1.length < hex2.length) {
					s = hex2.length - hex1.length;
					int [] hex11 = new int[hex2.length];
					// Filling with Zeroes
					for (int i = 0; i < s; i++) {
						hex11[i] = 0;
					}
					for(int i = s; i < hex11.length; i++) {
						hex11[i] = hex1[i-s];
					}
					hex1 = hex11;	
				}
				else {
					
				}
				
				System.out.println("Angeglichenes Array 1: " +Arrays.toString(hex1));
				System.out.println("Angeglichenes Array 2: " +Arrays.toString(hex2));
				
				
				int[] t1 = new int[hex1.length];
				for (int i = 0; i < hex1.length; i++) {
					t1[i] = hex1[i] + hex2[i];	
				}
				System.out.println(Arrays.toString(t1));
				
				temp = new int[hex1.length];
				for (int i = 0; i < temp.length; i++) {
					temp[i] = modulo(t1[i], 2);
					
				}
				//System.out.println("Vor dem Angleichen: " + Arrays.toString(temp));
				temp = EliminateLeadingZeroes(temp);
				//System.out.println("Nach dem Angleichen: " + Arrays.toString(temp)); 	
				return IntArrayToString(temp);	
			}
			
			public int[] getPrimeNumbers(int n) {
				//If n is Prime, calculate for double n
				int[] numbers = new int[n+n];
				boolean[] marked = new boolean[n+n];
				ArrayList<Integer> Primes = new ArrayList<>();
				// Fill the Array with Numbers from 2 to n
				for (int i = 2; i < numbers.length; i++) {
					numbers[i] = i;
				}
				//Make all Numbers unmarked
				for (int i = 0; i < marked.length; i++) {
					marked[i] = false;
				}
				
				//Calculate Prime Numbers
				for (int i = 2; i < n+n; i++) {
					if (marked[i] == false) {
					Primes.add(numbers[i]);	
					marked[i] = true;
					int k = i;
					
					while (k < n) {
						k += i;
						if (k < n) {
							marked[k] = true;
							}
						}
					
					}
				}
				
				//Convert Result to Array
				Integer[] t = Primes.toArray(new Integer[0]);
				int[] result = new int[t.length];
				for (int i = 0; i < t.length; i++) {
					result[i] = Integer.valueOf(t[i]);
				}
				//System.out.println("Gefundene Primzahlen: " + Arrays.toString(result));
				return result;
			}
			
			public String primeFactors(int n) {
				int[] PrimeNumbers = getPrimeNumbers(n);
				int pos = 0;
				try {
				while (modulo(PrimeNumbers[pos], n) != 0) {
					pos++;
				}
				//System.out.println("Gefundener Primfaktor: " + PrimeNumbers[pos]);
				if (PrimeNumbers.length != 1) {
					this.PrimeFactors += PrimeNumbers[pos] + "*";
					primeFactors(n / PrimeNumbers[pos]);
				}
				else {
					this.PrimeFactors += PrimeNumbers[pos];
					}
				}
				catch (java.lang.ArrayIndexOutOfBoundsException e) {
			
					}
				
				return chopLastChar(this.PrimeFactors);
				
			}


//-----------------------------------------------------------------------------------------------------------------------

			//Helping Methods
			
			private String chopLastChar(String a) {
				char[] input = new char[a.length()];
				char[] output = new char[input.length-1];
				for (int i = 0; i < input.length; i++) {
					input[i] = a.charAt(i);
				}
				for (int i = 0; i < output.length; i++) {
					output[i] = input[i];
				}
				StringBuilder s = new StringBuilder();
				for (int i = 0; i < output.length; i++) {
					s.append(output[i]);
				}
				return s.toString();
			}
				
			private int[] oneForward(int [] input) {
				/*
				 * Note: This Method was implemented wrong at first, with a shift to the right.
				 */
			int[] result = new int[input.length];
			//System.out.println("Aktuell bearbeitet: " + Arrays.toString(input));
			
			for(int i = 0; i < input.length-1; i++) {
					result[i] = input[i+1];
				
			}
			result[input.length-1] = input[0];
			//System.out.println("Ausgabe: " + Arrays.toString(result));
			return result;
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
			
			private boolean isBigLetter(char c) {
				for (int i = 0; i < AlphabetBig.length+1; i++) {
					try {
					if (AlphabetBig[i] == c) {
						return true;
					}
					else {
						continue;
					}
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {
						return false;
					}
				}
				return false;
			}
			
			
//-----------------------------------------------------------------------------------------------------------------------
		
			//Encryption & Decryption
			public String Vignere(String code, String key) {
				/*
				 * codeTable	= Decrypting Table
				 * code2		= Copied Code
				 * key2			= Copied Decrypting key
				 * clear		= Cleartext   
				 */
				buildVignere();
				char[] code2 = new char[code.length()];
				char[] key2  = new char[key.length()];
				char [] clear = new char[code.length()];
				
				//Writing the Coded Message to Array
				for(int i = 0; i < code.length(); i++) {
					code2[i] = code.charAt(i);
				}
				
				//Writing Key to Array
				for(int i = 0; i < key.length(); i++) {
					key2[i] = key.charAt(i);
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
			
			public String generateDESRoundKeys(String key) {
				
				return null;
			}
			
//-----------------------------------------------------------------------------------------------------------------------
			
			//Copied Methods (Only for Debugging)
			/*
			 * Methode zur Ausgabe mehrzeiliger Matrizen. Copyright (C) by S3 UDE
			 */
			public void print(int[][] values) {
				if (values == null) {
					System.out.println("null");
				} else {
					System.out.printf("%dx%x:%n", values.length, values[0].length);

					for (int[] row : values) {
						System.out.println(Arrays.toString(row));
					}
				}
			}
			
//-----------------------------------------------------------------------------------------------------------------------
			
			//Constructor
			public Helper() {
	
	}
}