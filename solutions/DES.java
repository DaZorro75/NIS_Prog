package de.unidue.iem.tdr.nis.client.solutions;

public class DES extends Helper{

	/**
	 * Diese Klasse stellt alle Methoden und Tabellen für die DES-Verschlüsselung bereit.
	 */

//--------------------------------------------------------------------------------------------------------------------------------------------
	
	//DES-Constants
	int[] LShift = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
	int[][] DES_PC2 = {{14, 17, 11, 24, 1, 5}, {3, 28, 15, 6, 21, 10}, {23, 19, 12, 4, 26, 8}, {16, 7, 27, 20, 13, 2}, {41, 52, 31, 37, 47, 55}, {30, 40, 51, 45, 33, 48}, {44, 49, 39, 56, 34, 53}, {46, 42, 50, 36, 29, 32}};
	int[][] DES_IP = {{58, 50, 42, 34, 26, 18, 10, 2}, {60, 52, 44, 36, 28, 20, 12, 4}, {62, 54, 46, 38, 30, 22, 14, 6}, {64, 56, 48, 40, 32, 24, 16, 8}, {57, 49, 41, 33, 25, 17, 9, 1}, {59, 51, 43, 35, 27, 19, 11, 3}, {61, 53, 45, 37, 29, 21, 13, 5}, {63, 55, 47, 39, 31, 23, 15, 7}};
	int[][] DES_P1 = {{16, 7, 20, 21}, {29, 12, 28, 17}, {1, 15, 23, 26}, {5, 18, 31, 10}, {2, 8, 24, 14}, {32, 27, 3, 9}, {19, 13, 30, 6}, {22, 11,4, 25}};
	int[][] DES_E = {{32, 1, 2, 3, 4, 5}, {4, 5, 6, 7, 8 , 9}, {8, 9, 10, 11, 12, 13}, {12, 13, 14, 15, 16, 17}, {16, 17, 18, 19, 20, 21}, {20, 21, 22, 23, 24, 25}, {24, 25, 26, 27, 28, 29}, {28, 29, 30, 31, 32, 1}};
	int DESROUND = 1;
	//Important Note: DESKEYS[0] is the original Key, not the first generated Key!!
	String[] DESKEYS = new String[17];
	String[] DES_R_BLOCKS = new String[17];
	String[] DES_L_BLOCKS = new String[17];
	int[][] DES_S1 = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8}, {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0}, {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
	int[][] DES_S2 = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10}, {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5}, {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15}, {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
	int[][] DES_S3 = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8}, {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1}, {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7}, {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
	int[][] DES_S4 = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15}, {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9}, {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4}, {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7 ,2, 14}};
	int[][] DES_S5 = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9}, {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6}, {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14}, {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
	int[][] DES_S6 = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11}, {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8}, {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6}, {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
	int[][] DES_S7 = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1}, {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6}, {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2}, {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
	int[][] DES_S8 = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7}, {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2}, {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8}, {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
	int[][][]  DES_SBOXES =  {DES_S1, DES_S2, DES_S3, DES_S4, DES_S5, DES_S6, DES_S7, DES_S8};
	
//--------------------------------------------------------------------------------------------------------------------------------------------

		//Methods
		public void generateDESRoundKeys(String key) {
		//System.out.println("----------------------Generierung der DES-Roundkeys----------------------");
		
		int[][] PC1 = buildPC1();
			DESKEYS[0] = key;
			int[] pK = new int[key.length()-8];
			
			
			// Initial Permutation
			int[] oK = BinaryStringToIntArray(DESKEYS[0]);
			//System.out.println("Verwendeter Schlüssel mit Länge: " + oK.length + "\n" + Arrays.toString(oK));
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 7; j++) {
					PC1[i][j] = oK[PC1[i][j]-1];
				}
			}
			/*System.out.println("PC1: ");
			print(PC1);
			*/
			int c = 0;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 7; j++) {
					pK[c]= PC1[i][j];
					c++;
				}
			}
			//System.out.println("Permutierter Schlüssel der Länge: " + pK.length + "\n" + Arrays.toString(pK));
			
			// Creating Initial C-Block and D-Block
			int[] cBlock = new int[pK.length/2];
			int[] dBlock = new int[pK.length/2];
			
			for(int i = 0; i < cBlock.length; i++) {
				cBlock[i] = pK[i];
			}
			for (int i = cBlock.length; i < 2*cBlock.length; i++) {
				dBlock[i-cBlock.length] = pK[i];
			}
			//System.out.println("Generierte C/D-Blöcke: " + Arrays.toString(cBlock) + Arrays.toString(dBlock));
			
			
			
			
			//Keygen
			for(int i = 0; i < DESKEYS.length-1; i++) {
				cBlock = shiftBy(cBlock, LShift[DESROUND-1]);
				dBlock = shiftBy(dBlock, LShift[DESROUND-1]);
				DESKEYS[DESROUND] = roundToKey(cBlock, dBlock);
				DESROUND++;
			}
			
			//System.out.println("Generierte Rundenschlüssel: " + this.getGeneratedRoundKeys());
		}
		
		public void generateRBlocks (String input, String key) {
			//Global Arrays and Values
			int[] inputA = BinaryStringToIntArray(input);
			int[] lBlock = new int[32];
			int[] rBlock = new int[32];		
			
			//Generate the Roundkeys
			this.generateDESRoundKeys(key);
			
			
			//Copy the Table for the initial Permutation
			int[][] PC1 = new int[8][8];
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					PC1[i][j] = this.DES_IP[i][j];
				}
			}
			
			//Doing the initial Permutation
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					PC1[i][j] = inputA[PC1[i][j]-1];
				}
			}
			int[] perm0 = new int[inputA.length];
			int c = 0;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					perm0[c] = PC1[i][j];
					c++;
				}
			}
			
			//Writing Initial L-/R-Blocks
			for(int i = 0; i < lBlock.length; i++) {
				lBlock[i] = perm0[i];
			}
			for(int i = 0; i < rBlock.length; i++) {
				rBlock[i] = perm0[i + lBlock.length];
			}
			
			//Saving the first R-Block / L-Block
			this.DES_R_BLOCKS[0] = IntArrayToString(rBlock);
			this.DES_L_BLOCKS[0] = IntArrayToString(lBlock);
			
			
			//Generating the R-Blocks for 16 Rounds
				for(int k = 0; k < 15; k++) {
					//Generating R-Blocks with F-Function
					int[] currRblock = this.BinaryStringToIntArray(DES_R_BLOCKS[k]);
					int[] currLblock = this.BinaryStringToIntArray(DES_L_BLOCKS[k]);
					int[] currRoukey = this.BinaryStringToIntArray(DESKEYS[k+1]);
					
					DES_R_BLOCKS[k+1] = IntArrayToString(this.functionF(currLblock, currRblock, currRoukey)); 
					DES_L_BLOCKS[k+1] = IntArrayToString(currRblock);
					
					
					//Initial R-Block becomes L-Block, XOR -> new R-Block
				}
		}
		
		public int[] functionF(int[]lBlock, int[] rBlock, int[] rKey) {
			//Expanding the R-Block
			 //Copying the E-Block
			int[][] E = new int[8][6];
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 6; j++) {
					E[i][j] = this.DES_E[i][j];
				}
			}
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 6; j++) {
					E[i][j] = rBlock[E[i][j]-1];
				}
			}
			int[] rExpanded = new int[48];
			int c = 0;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 6; j++) {
					rExpanded[c] = E[i][j];
					c++;
				}
			}
			
			//(rExpanded)XOR(rKey)
			/*
			System.out.println("Expandierter R-Block und Key-Block:");
			System.out.println(Arrays.toString(rExpanded));
			*/
			for(int i = 0; i < rExpanded.length; i++) {
				String u = intToString(rExpanded[i]);
				String z = intToString(rKey[i]);
				rExpanded[i] = getIntFromString(XOR(u, z));
			}
			/*
			System.out.println(Arrays.toString(rKey));
			System.out.println("R-Block nach dem XOR:\n" + Arrays.toString(rExpanded));
			*/
			
			//S-Boxes
			int[] sAfter = new int[32];
			for(int i = 0; i < 8; i++) {
				int[] temp = new int[6];
				int[] temp2 = new int[4];
				
				//Copy Current 6-bit Block to Temp Array
				for(int j = 0; j < 6; j++) {
					temp[j] = rExpanded[6*i+j];
				}
				
				//Perfom S-Boxes
				temp2 = fFunktionS(temp, i);
				
				//Copy Result to sAfter
				for(int j = 0; j < 4; j++) {
					sAfter[4*i+j] = temp2[j];
				}
				}
			
			//System.out.println("Nach den S-Boxen:" + Arrays.toString(sAfter));
			//Permutation 
			int[][] p1 = new int[8][4];
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 4; j++) {
					p1[i][j] = DES_P1[i][j];
				}
			}
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 4; j++) {
					p1[i][j] = sAfter[p1[i][j]-1];
				}
			}
			int[] afterRRound = new int[32];
			int b = 0;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 4; j++) {
					afterRRound[b] = p1[i][j];
					b++;
				}
			}
			
			//XOR
			int[] result = new int[32];
			for(int i = 0; i < 32; i++) {
				String a = intToString(lBlock[i]);
				String e = intToString(afterRRound[i]);
				result[i] = getIntFromString(XOR(a, e));
			}
			return result;
		}
		
		private int[] fFunktionS(int[] input, int round) {
			int x = getOuterBits(input);
			int y = getCenterBits(input);
			//System.out.println("Gefundene Koordinaten:" + x + ", " + y);
			int decimal = this.DES_SBOXES[round][x][y];
			//System.out.println("Gefundener Dezimalwert: " + decimal);
			String binary = DecimalToBinary(decimal);
			//System.out.println("Dies ist in binär: " + binary);
			return BinaryStringToIntArray(binary);
		}

		private int[] shiftBy(int[] input, int n) {
			int[] result = new int[input.length];
			int k = 1;
			try {
				for(int i = 0; i < input.length; i++) {
					result[i] = input[i+n];
					k++;
				}
			}
			catch (java.lang.ArrayIndexOutOfBoundsException e) {
				for (int i = 0; i < n; i++) {
					result[k-1+i] = input[i];
				}
			}
			return result;
		}

		private int[][] buildPC1() {
			int[][] p0 = new int[8][8];
			int k = 1;
			int[][] cBlock = new int[4][8];
			int[][] dBlock = new int[4][8];
			
			// Initialize 
			for(int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					p0[i][j] = k;
					k++;
				}
			}
			
			
			
			// C-Block Generation
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < cBlock.length; j++) {
					cBlock[i][j] = p0[7-j][i];
				}
			}
			for(int i = 0; i < 3; i++) {
				for(int j = 4; j < 8; j++) {
					cBlock[i][j] = p0[7-j][i];
				}
			}
			
			// D-Block Generation
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < dBlock.length; j++) {
					dBlock[i][j] = p0[7-j][6-i];
				}
			}
			
			for(int i = 0; i < 4; i++) {
				for(int j = 4; j < 8; j++) {
					dBlock[i][j] = p0[7-j][6-i];
				}
			}
			
			//Writing Permutation 
			int[][] result = new int[8][7];
			for(int i = 0; i < 8; i++) {
				//First Row C-Block
				if(i == 0) {
					for(int j = 0; j < 7; j++) {
						result[i][j] = cBlock[i][j];
					}
				}
				else if (i > 0 && i < 4) {
					try {
						result[i][0] = cBlock[i-1][8-i];
						for(int j = i; j < 7; j++) {
							result[i][j] = cBlock[i][j-i];
						}
						for(int j = 1; j < i; j++) {
							result[i][j] = cBlock[i-1][8-i+j];
						}
					}
					catch(java.lang.ArrayIndexOutOfBoundsException e) {
						
						}
					}
				//First Row D-Block
				else if(i == 4) {
					for(int j = 0; j < 7; j++) {
						result[i][j] = dBlock[4-i][j];
					}
				}
					
				else {
					try {
					result[i][0] = dBlock[i-5][12-i];
					for(int j = i-4; j < 7; j++) {
						result[i][j] = dBlock[i-4][j+4-i];
						}
					for(int j = 1; j < i-4; j++) {
						result[i][j] = dBlock[i-5][12-i+j];	
						}
					
				
					}
					catch(java.lang.ArrayIndexOutOfBoundsException e) {
						
						}
					for(int j = 3; j < 7; j++) {
						result[7][j] = dBlock[i-4][j+1];
					}
				}
			}
		
			/*print(p0);
			System.out.println("\n");
			System.out.println("Werte im C-Block:");
			print(cBlock);
			System.out.println("\n");
			System.out.println("Werte im D-Block:");
			print(dBlock);
			System.out.println("Permutationstabelle:");
			print(result);
			System.out.println("\n");
			*/
			return result;
		}
		
		private String roundToKey(int[] c, int[] d) {
			/*System.out.println("----------------------Generierung der Rundenschlüssel----------------------");
			System.out.println("Länge des Arrays C: " + c.length);
			System.out.println("Länge des Arrays D: " + d.length);
			System.out.println("Runde Nr. " + DESROUND);
			*/
			int[] temp = new int[c.length + d.length];
			int[][] temp2 = copyPC2();
			int[] res = new int[c.length+d.length-8];
			for(int i = 0; i < c.length; i++) {
				temp[i] = c[i];
			}
			for(int k = 0; k < d.length; k++) {
				temp[k+d.length] = d[k];
			}
			//System.out.println("Eingabeschlüssel der Länge:" + temp.length + "\n" + Arrays.toString(temp));
			
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 6; j++) {
					int t = temp2[i][j];
					temp2[i][j] = temp[t-1];
				}
			}
			//System.out.println("Nach der Permutation");
			//print(temp2);
			int w = 0;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 6; j++) {
					res[w] = temp2[i][j];
					w++;
				}
			}
			
		
			return IntArrayToString(res);
			
		}
		
		private int getOuterBits(int[] input) {
			int a = input[0];
			int b = input[input.length-1];
			if(a == 0 && b == 0) {
				return 0;
			}
			else if(a == 0 && b == 1) {
				return 1;
			}
			else if(a == 1 && b == 0) {
				return 2;
			}
			else {
				return 3;
			}
		}

		private int getCenterBits(int[] input) {
			String center = "";
			for (int i = 1; i < input.length-1; i++) {
				center += intToString(input[i]);
			}
			
			return findBinaryString(center);
		}

		private int[][] copyPC2() {
			int[][] res = new int[DES_PC2.length][DES_PC2[0].length];
			for(int i = 0; i < res.length; i++) {
				for(int j = 0; j < res[0].length; j++) {
					res[i][j] = DES_PC2[i][j];
				}
			}
			return res;
		}

		public String makeDESRoundOut(int r, String key, int[] lBlock, int[] rBlock) {
			int[] lBlockOut = new int[32];
			int[] rBlockOut = new int[32];
			int[] Out = new int[64];
			
			
			//Generating Roundkeys
			this.generateDESRoundKeys(key);
			
			//Getting the Roundkey for the wanted round
			int[] currRblock = rBlock;
			int[] currLblock = lBlock;
			int[] currRoukey = BinaryStringToIntArray(DESKEYS[r]);
			
			rBlockOut = this.functionF(currLblock, currRblock, currRoukey); 
			lBlockOut = currRblock;
			
			
			//Building the Output Array
			for(int i = 0; i < lBlockOut.length; i++) {
				Out[i] = lBlockOut[i];
			}
			for(int i = 0; i < rBlockOut.length; i++) {
				Out[i + lBlockOut.length] = rBlockOut[i];
			}
			
			
			return IntArrayToString(Out);
		}
}
