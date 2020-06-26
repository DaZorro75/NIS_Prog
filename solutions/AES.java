package de.unidue.iem.tdr.nis.client.solutions;

public class AES extends Helper{

	/**
	 * Diese Klasse stellt alle AES-Methoden und Tabellen zur Verfügung.
	 */
//--------------------------------------------------------------------------------------------------------------------------------------------

	
	
	//Tables and global variables
	String[][] AES_S_BOX = {{"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
							{"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
							{"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
							{"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
							{"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
							{"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
							{"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
							{"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
							{"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
							{"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
							{"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
							{"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
							{"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
							{"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
							{"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
							{"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"}};
	String[][] AES_C_BOX = {{"02", "03", "01", "01"}, 
							{"01", "02", "03", "01"},
							{"01", "01", "02", "03"}, 
							{"03", "01", "01", "02"}};
	String[][] AES_ROUNDKEYS = new String[4][44];
	String[] AES_RCON = {"01", "02", "04", "08", "10", "20","40","80","1B", "36"};
	int[] irrPoly = {1, 1, 0, 1, 1, 0, 0, 0, 1};
	String[][] AES_DATABLOCKS = new String[4][40];

//--------------------------------------------------------------------------------------------------------------------------------------------
	
	//Helping Methods
	public int[] AESsubBytes(int[] input) {
		int[] bin1 = new int[4];
		int[] bin2 = new int[4];
		String hex1;
		String hex2;
		int x;
		int y;
		String output;
		
		
		//Writing each 4-bit block into its array
		for(int i = 0; i < 4; i++) {
			bin1[i] = input[i];
		}
		for(int i = 0; i < 4; i++) {
			bin2[i] = input[4+i];
		}
		hex1 = IntArrayToString(bin1);
		hex2 = IntArrayToString(bin2);
		x = findBinaryString(hex1);
		y = findBinaryString(hex2);
		output = this.AES_S_BOX[x][y];
		return BinaryStringToIntArray(HexToBinary(output));
	}
	
	public String[] AESMixColumns(String[] input) {
		String[] r0			= new String[4];

		
		//Convert the Input to Binary for Multiplication
		int[] bin0 = BinaryStringToIntArray(HexToBinary(input[0]));
		int[] bin1 = BinaryStringToIntArray(HexToBinary(input[1]));
		int[] bin2 = BinaryStringToIntArray(HexToBinary(input[2]));
		int[] bin3 = BinaryStringToIntArray(HexToBinary(input[3]));
		
		/*
		System.out.println("I1: " + input[0] + "---" + Arrays.toString(bin0));
		System.out.println("I2: " + input[1] + "---" + Arrays.toString(bin1));
		System.out.println("I3: " + input[2] + "---" + Arrays.toString(bin2));
		System.out.println("I4: " + input[3] + "---" + Arrays.toString(bin3));
		*/
		
		
		//Perform the Swapping
		for(int i = 0; i < 4; i++) {
			String h0 = binaryToHex(AESMultiplication(BinaryStringToIntArray(HexToBinary(AES_C_BOX[i][0])), bin0));
			String h1 = binaryToHex(AESMultiplication(BinaryStringToIntArray(HexToBinary(AES_C_BOX[i][1])), bin1));
			String h2 = binaryToHex(AESMultiplication(BinaryStringToIntArray(HexToBinary(AES_C_BOX[i][2])), bin2));
			String h3 = binaryToHex(AESMultiplication(BinaryStringToIntArray(HexToBinary(AES_C_BOX[i][3])), bin3));
			
			/*
			System.out.println("1.: " + h0);
			System.out.println("2.: " + h1);
			System.out.println("3.: " + h2);
			System.out.println("4.: " + h3);
			*/
			
			String x0 = binaryToHex(BinaryStringToIntArray(AESAddition(h0, h1)));
			//System.out.println("1. Zwischenergebnis: " + x0);
			String x1 = binaryToHex(BinaryStringToIntArray(AESAddition(x0, h2)));
			//System.out.println("2. Zwischenergebnis: " + x1);
			String x2 = binaryToHex(BinaryStringToIntArray(AESAddition(x1, h3)));
			//System.out.println("------------------------------------------------" + "\n" + "Ergebnis: " + x2 +"\n" + "------------------------------------------------");
			r0[i] = x2;
			}
		
			
		//System.out.println(Arrays.toString(r0));
		return r0;
		
	}
	
	public String makeStreamFromColumn(String[] input) {
		String r = "";
		for(int i = 0; i < 4; i++) {
			r += input[i];
		}
		return r;
	}
	
	private String[] getColumnFromKey(int r) {
		String[] result = new String[4];
		for(int i = 0; i < 4; i++) {
			result[i] = AES_ROUNDKEYS[i][r];
		}
		return result;
	}
	
	private void writeColumnToKey(String[] column, int r) {
		for(int i = 0; i < 4; i++) {
			AES_ROUNDKEYS[i][r] = column[i];
		}
	}
	
	public String[][] getRoundKey(int r) {
		//Writing the Keystring to KeyBlock
		String[][] KEY_BLOCK = new String[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				KEY_BLOCK[j][i] = AES_ROUNDKEYS[j][r*4+i];
			}
		}
		return KEY_BLOCK;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	//Methods
	public int[] AESModuloIrrPoly(int[] a) {
		//Multiplies to 8-Bit Numbers in AES
		int[] multiplicationResult = new int[17];
		int[] tempResult = new int[17];
		int[] result = new int[8];
		int[] DivR = new int[8];
		int[] rest = new int[17];
		
		//Expanding a to 17
		for(int i = 0; i < a.length; i++) {
			multiplicationResult[i] = a[i];
		}
		
		
		//Polynomial Devision
		int k = 0;
		while(k > 8 || k == 0 || k == 8) {
		//Find the Highest Polynom in multiplicationResult
		for(int i = 0; i < 17; i++) {
			if(multiplicationResult[i] == 1) {
				k = i;
				}
			}
		
		//checking
		int counter = 0;
		for(int i = 0; i < multiplicationResult.length; i++) {
			if(multiplicationResult[i] == 1) {
				counter++;
			}
		}
		if(counter == 0) {
			break;
		}
		
		//Writing the result and checking the highest Polynom
		if(k >= 8) {
			DivR[k-8]++;
			multiplicationResult[k] = 0;
			// Multiplication back
			rest = new int[17];
		for(int i = 0; i < 8; i++) {
			if(DivR[i] == 0) {
				continue;
			}
			else {
				for(int j = 0; j < 9; j++) {
						if(irrPoly[j] == 0) {
							continue;
						}
						else {
						rest[i + j]=1;
						}
					}
				}
			}
		//Subtraction
		for(int n = 0; n < 17; n++) {
			tempResult[n] = multiplicationResult[n]- rest[n];
			}
		multiplicationResult = tempResult;
		DivR[k-8] = 0;
		k--;
		}
		else {
		 break;	
			}
		}
		
	//Writing the result to the result-Array
	int[] result2 = new int[8];
		for(int i = 0; i < 8; i++) {
		if(multiplicationResult[i] == -1) {
			result2[i] = 1;
		}
		else {
			result2[i] = multiplicationResult[i];
		}
	}
	
	//Flipping the Array for correct interretation
		for(int i = 0 ; i < 8; i++) {
			result[7-i] = result2[i];
		}
		//System.out.println("Endergebnis: " + Arrays.toString(result));
		return result;
	}
	
	public int[] AESMultiplication(int[] a, int[] b) {
			//Multiplies to 8-Bit Numbers in AES
			int[] multiplicationResult = new int[17];
			int[] tempResult = new int[17];
			int[] result = new int[8];
			int[] DivR = new int[8];
			int[] rest = new int[17];
			
			//Flip the Arrays for Calculation
			int[] flipA = new int[8];
			int[] flipB = new int[8];
			for(int i = 0 ; i < 8; i++) {
				flipA[7-i] = a[i];
			}
			for(int i = 0 ; i < 8; i++) {
				flipB[7-i] = b[i];
			}
			
						
			//Multiply the Numbers and eliminate all values >1
			for(int i = 0; i < 8; i++) {
					if(flipA[i] == 1) {
						for(int j = 0; j < 8; j++) {
							if(flipB[j] == 1) {
								multiplicationResult[i + j]++;
							}
						}
					}
				}
			
			for(int i = 0; i < 17; i++) {
				if(multiplicationResult[i] == 2) {
					multiplicationResult[i] = 0;
				}
			}
			
			//System.out.println("Multiplikationsergebnis: " + Arrays.toString(multiplicationResult));
			
			//Polynomial Devision
			int k = 0;
			while(k > 8 || k == 0 || k == 8) {
			//Find the Highest Polynom in multiplicationResult
			for(int i = 0; i < 17; i++) {
				if(multiplicationResult[i] == 1) {
					k = i;
					}
				}
			
			//Writing the result and checking the highest Polynom
			if(k >= 8) {
				DivR[k-8]++;
				multiplicationResult[k] = 0;
				// Multiplication back
				rest = new int[17];
			for(int i = 0; i < 8; i++) {
				if(DivR[i] == 0) {
					continue;
				}
				else {
					for(int j = 0; j < 8; j++) {
							if(irrPoly[j] == 0) {
								continue;
							}
							else {
							rest[i + j]=1;
							}
						}
					}
				}
			//Subtraction
			for(int n = 0; n < 17; n++) {
				tempResult[n] = multiplicationResult[n]- rest[n];
				}
			multiplicationResult = tempResult;
			DivR[k-8] = 0;
			k--;
			}
			else {
			 break;	
				}
			}
			
		//Writing the result to the result-Array
		int[] result2 = new int[8];
			for(int i = 0; i < 8; i++) {
			if(multiplicationResult[i] == -1) {
				result2[i] = 1;
			}
			else {
				result2[i] = multiplicationResult[i];
			}
		}
		
		//Flipping the Array for correct interretation
			for(int i = 0 ; i < 8; i++) {
				result[7-i] = result2[i];
			}
			//System.out.println("Endergebnis: " + Arrays.toString(result));
			return result;
		}

	public String AESAddition(String a, String b) {
		int [] hex1 = BinaryStringToIntArray(HexToBinary(a));
		//System.out.println("Binäres Array Nr. 1: " + Arrays.toString(hex1));
		int [] hex2 = BinaryStringToIntArray(HexToBinary(b));
		//System.out.println("Binäres Array Nr. 2: " + Arrays.toString(hex2));
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
		int[] t1 = new int[hex1.length];
		for (int i = 0; i < hex1.length; i++) {
			t1[i] = hex1[i] + hex2[i];	
		}
		temp = new int[hex1.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = modulo(t1[i], 2);
			
		}	
		return IntArrayToString(temp);	
	}

	public void AESRoundKeyGeneration(String key) {
		
		//Global Variables
		int AES_ROUND = 0;
		
		
		//Chop the Key
		String[] t0 = chopHexStream(key);
		
		//Write the Key to the first 4x4-block
		for(int i = 0; i < 4; i++) {
			String[] t1 = chopHexStream(t0[i]);
			for(int j = 0; j < 4; j++) {
				this.AES_ROUNDKEYS[j][i] = t1[j];
			}
		}
		
		
		for(int i = 4; i < 44; i++) {
			//Is RoundkeyColumn1?
			if(modulo(i, 4) == 0) {
				AES_ROUND++;
				String[] column = generateFirstRoundKeyBlock(getColumnFromKey(i-1), getColumnFromKey(i-4), AES_ROUND);
				writeColumnToKey(column, i);
				//printString(AES_ROUNDKEYS);
				
			}
			else {
				String[] column = new String[4];
				String[] prev    = getColumnFromKey(i-1);
				String[] prev4   = getColumnFromKey(i-4);
				for(int j = 0; j < 4; j++) {
					column[j] = binaryToHex(BinaryStringToIntArray(AESAddition(prev4[j],prev[j])));
				}
				writeColumnToKey(column, i);
			}
		}
		
	}

	public String[][] AESShiftRows(String[][] DATA_BLOCK) {
		String[][] result = new String[4][4];

				result[0][0] = DATA_BLOCK[0][0];
				result[0][1] = DATA_BLOCK[0][1];
				result[0][2] = DATA_BLOCK[0][2];
				result[0][3] = DATA_BLOCK[0][3];
				
				result[1][0] = DATA_BLOCK[1][1];
				result[1][1] = DATA_BLOCK[1][2];
				result[1][2] = DATA_BLOCK[1][3];
				result[1][3] = DATA_BLOCK[1][0];
		
				result[2][0] = DATA_BLOCK[2][2];
				result[2][1] = DATA_BLOCK[2][3];
				result[2][2] = DATA_BLOCK[2][0];
				result[2][3] = DATA_BLOCK[2][1];
		
				result[3][0] = DATA_BLOCK[3][3];
				result[3][1] = DATA_BLOCK[3][0];
				result[3][2] = DATA_BLOCK[3][1];
				result[3][3] = DATA_BLOCK[3][2];
			
			
		
		return result;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------

	//AES-Keyschedule
	public String[] AESrotWord(String[] input) {
		String[] result = new String[4];
		result[0] = input[1];
		result[1] = input[2];
		result[2] = input[3];
		result[3] = input[0];
		return result;
	}
	
	public String AESSubWord(int[] input) {
		return binaryToHex(AESsubBytes(input));
	}
	
	
	public String[] generateFirstRoundKeyBlock(String[] column0, String[] column4, int round) {
		String[] result = new String[4];
		//rotWord
		result = AESrotWord(column0);
		//System.out.println("Nach RotWord(); " + Arrays.toString(result));
		//SubBytes
		for(int i = 0; i < 4; i++) {
		result[i] = AES_S_BOX[findHexChar(result[i].charAt(0))][findHexChar(result[i].charAt(1))];
		}
		//System.out.println("Nach SubWord(); " + Arrays.toString(result));
		
		//Add Result To Column 4 earlier (column4)
		for(int i = 0; i < 4; i++) {
			result[i] = binaryToHex(BinaryStringToIntArray(AESAddition(result[i], column4[i])));
			//System.out.println("Führe Addition aus: " + "--" + i +  "--" + Arrays.toString(result));
		}
		
		//Add Rcon
		result[0] = binaryToHex(BinaryStringToIntArray(AESAddition(result[0], AES_RCON[round-1])));
		//System.out.println("Ergebnis: " + Arrays.toString(result));
		
		return result;
	}

//--------------------------------------------------------------------------------------------------------------------------------------------
	
		//AES-Encryption
		public String[][] AESDoFirstRound(String[][] DATA_BLOCK) {
			String[][] ROUNDKEY = getRoundKey(0);
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++)  {
					DATA_BLOCK[j][i] = binaryToHex(BinaryStringToIntArray(AESAddition(DATA_BLOCK[j][i], ROUNDKEY[j][i])));
				}
			}
			//Saving the Encrypted Datablock
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					AES_DATABLOCKS[j][i] = DATA_BLOCK[j][i];
				}
			}
			return DATA_BLOCK;
		}
		
		public String[][] AESDoNormalRound(String[][] DATA_BLOCK, int r) {
			String[][] ROUNDKEY = getRoundKey(r);
			
			//SubBytes
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					int[] binHex = BinaryStringToIntArray(HexToBinary(DATA_BLOCK[j][i]));
					binHex = AESsubBytes(binHex);
					DATA_BLOCK[j][i] = binaryToHex(binHex);
				}
			}
			
			//ShiftRows
			DATA_BLOCK = AESShiftRows(DATA_BLOCK);
			
			//MixColumns
			for(int i = 0; i < 4; i++) {
				String[] temp = new String[4];
				for(int j = 0; j < 4; j++) {
					temp[j] = DATA_BLOCK[j][i];
				}
				temp = AESMixColumns(temp);
				for(int j = 0; j < 4; j++) {
					DATA_BLOCK[j][i] = temp[j];
				}
			}
			
			//AddRoundKey
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++)  {
					DATA_BLOCK[j][i] = binaryToHex(BinaryStringToIntArray(AESAddition(DATA_BLOCK[j][i], ROUNDKEY[j][i])));
				}
			}
			
			//Save to AES_DATABLOCKS
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					AES_DATABLOCKS[j][r*4+i] = DATA_BLOCK[j][i];
				}
			}
			return DATA_BLOCK;
		}
}
