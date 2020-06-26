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
	 
	//Alphabet and Hex-conversion-table
	char[] AlphabetBig = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	char[] AlphabetSmall = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	char[] HexValues = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f'};
	String[] BinaryHexValues = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "1010", "1011", "1100", "1101", "1110", "1111"}; 
	int[][] ASCII_TABLE_DECIMAL = {	{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89,90},
									{97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122}};
	String[][] ASCII_TABLE_HEX ={	{"41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58","59","5A"},
									{"61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77", "78","79","7A"}};

	//Necessary for prime factorization
	String PrimeFactors = "";
	
//-----------------------------------------------------------------------------------------------------------------------
	
	
	//Converting Methods
	
			public int[] plainToDecimalASCII(String s) {
		int[] result = new int[s.length()];
		for(int i = 0; i < result.length; i++) {
			if(isBigLetter(s.charAt(i)) == true) {
				int x = 0;
				while(s.charAt(i) != AlphabetBig[x]) {
					x++;
				}
				result[i] = ASCII_TABLE_DECIMAL[0][x];
			}
			else {
					int x = 0;
					while(s.charAt(i) != AlphabetSmall[x]) {
						x++;
					}
					result[i] = ASCII_TABLE_DECIMAL[1][x];
				}
			}
		return result;
		}

			public int getASCIIStringLength(String s) {
		int x = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '_') {
			//First Case: Placeholder
				continue;
			}
			else if(i + 2 < s.length() && s.charAt(i) != '_' && s.charAt(i+1) != '_' && s.charAt(i+2) != '_') {
				x++;
				i+=2;
			}
			else if (s.charAt(i) != '_' && s.charAt(i+1) != '_') {
				x++;
				i ++;
				continue;
			}
		}
		return x;
	}

			public int binaryToDecimal(int[] in) {
		int r = 0;
		int[] temp = new int[in.length];
		for(int i = 0; i < in.length; i++) {
			temp[in.length-1-i] = in[i];
		}
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] == 1) {
				r += Math.pow(2, i);
			}
		}
		return r;
	}

			public char[] ASCIIToClear(int[] s) {
		int y = 0;
		char[] c = new char[s.length];
		for(int i = 0; i < s.length; i++) {
			if(s[i] < 95) {
				while(s[i] != ASCII_TABLE_DECIMAL[0][y]) {
					y++;
				}
				c[i] = AlphabetBig[y];
				y = 0;
			}
			else {
				while(s[i] != ASCII_TABLE_DECIMAL[1][y]) {
					y++;
				}
				c[i] = AlphabetSmall[y];
				y = 0;
			}
		}
		return c;
	}

			public String[] clearTextToHex(String s) {
		String[] result = new String[s.length()];
		int k;
		for(int i = 0; i < result.length; i++) {
			if(isBigLetter(s.charAt(i)) == true) {
				k = 0;
				int p = 0;
				while(s.charAt(i) != AlphabetBig[p]) {
					p++;
				}
				result[i] = ASCII_TABLE_HEX[k][p];
			}
			else {
				k = 1;
				int p = 0;
				while(s.charAt(i) != AlphabetSmall[p]) {
					p++;
				}
				result[i] = ASCII_TABLE_HEX[k][p];
			}
		}
		return result;
	}

			public int dualStringToInt(String s) {
		int n = 0;
		char c = s.charAt(0); 
				switch (c) {
				case '0':
					n += 0;
					break;
				case '1':
					n += 10;
					break;
				case '2':
					n += 20;
					break;
				case '3':
					n += 30;
					break;
				case '4':
					n += 40;
					break;
				case '5':
					n += 50;
					break;
				case '6':
					n += 60;
					break;
				case '7':
					n += 70;
					break;
				case '8':
					n += 80;
					break;
				case '9':
					n += 90;
					break;
				}
		char o = s.charAt(1);
			switch (o) {
				case '0':
					n+= 0;
					break;
				case '1':
					n += 1;
					break;
				case '2':
					n += 2;
					break;
				case '3':
					n += 3;
					break;
				case '4':
					n += 4;
					break;
				case '5':
					n += 5;
					break;
				case '6':
					n += 6;
					break;
				case '7':
					n += 7;
					break;
				case '8':
					n += 8;
					break;
				case '9':
					n += 9;
					break;
		}
			return n;
	}

			public String[] plainToHex(String s) {
		//Returns a String[] with hex representation of the char
		
		int k;
		String[] result = new String[s.length()];
		for(int i = 0; i < result.length; i++) {
			if(isBigLetter(s.charAt(i)) == true) {
				k = 0;
				int c = 0;
				while (s.charAt(i) != AlphabetBig[c]) {
					c++;
				}
				result[i] = ASCII_TABLE_HEX[k][c];
			}
			else {
				k = 1;
				int c = 0;
				while (s.charAt(i) != AlphabetSmall[c]) {
					c++;
				}
				result[i] = ASCII_TABLE_HEX[k][c];
			}
		}
		return result;
		
	}

			public int[] DecimalToBinaryArray(int q) {
	ArrayList<Integer> a = new ArrayList<Integer>();
	
	int k = q;
	while(k != 0) {
		if(k != 1) {
		a.add(modulo(k, 2));
		k = k/2;
		}
		else {
			a.add(1);
			k = 0;
			break;
		}
	}
		int[] r = new int[a.size()];
		for(int i = 0; i < r.length; i++) {
			r[i] = a.get(i);
	}
	int[] n = new int[r.length];
	for(int i = 0; i < n.length; i++) {
		n[i] = r[r.length-1-i];
	}
	return n;
	
	
	}
	
			public String binaryToHex(int[] binary) {
		String result = "";
		int[] temp = new int[4];
		for(int i = 0; i < binary.length/4; i++) {
			if(i == 0) {
				for(int j = 0; j < 4; j++) {
					temp[j] = binary[i+j];
				}
				String t = IntArrayToString(temp);
				int x = findBinaryString(t);
				result += HexValues[x];
				temp = new int[4];
			}
			else {
			for(int j = 0; j < 4; j++) {
				temp[j] = binary[4*i+j];
			}
			String t = IntArrayToString(temp);
			int x = findBinaryString(t);
			result += HexValues[x];
			temp = new int[4];
			}
		}
		return result;
	}

			public String DecimalToBinary(int a) {
		if (a > 16) {
			System.out.println("Diese Zahl ist größer als der Hexadezimale Wertebereich und kann daher an dieser Stelle nicht umgewandelt werden.");
			return null;
		}
		else {
			return BinaryHexValues[a];
		}
	}

			public String charArrayToString(char[] a) {
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
	
			public char[] StringToCharArray(String input) {
	 char[] result = new char[input.length()];
	 for(int i = 0; i < result.length; i++) {
		 result[i] = input.charAt(i);
	 }
	 return result;
	}

			public String stringArrayToString(String[] in) {
		String r = "";
		for(int i = 0; i < in.length; i++) {
			r+= in[i];
		}
		return r;
	}

//-----------------------------------------------------------------------------------------------------------------------		

			//Mathematical Methods	
			
			public int EuclidGGT(int m, int n) {
				if(modulo(n, m) == 0) {
					return m;
				}
				else {
					return EuclidGGT(modulo(n, m), m);
				}
			}
			
			public boolean isPrime(int n) {
				for(int i = 2; i < n; i++) {
					if(modulo(n, i) == 0) {
						return false;
					}
				}
				return true;
			}
			
			public double moduloDouble(double a, double b) { 
				if(b < a) {
				double k = 0;
					while(true) {
						if(k > a) {
							k = k - b;
							break;
						}
						else {
							k+= b;
							continue;
						}
					}
						return a - k;
				}
				else if(a == 0) {
					return 0;
				}
				else if(a == b) {
					return 0;
				}
				else {
					return a; 
				}
			}
			
			public double bigModulo(int base, int power, int p) {
				double result  = 1;
				double pot 	= base;
				int b 	= power;
				while(b != 0) {
					if(modulo(b, 2) != 0) {
					result =  moduloDouble(result*pot, p);
					b--;
				}
					else {
						pot = moduloDouble(pot*pot, p);
						b = b/2;
					}
				}
				return result;
			}
			
			public int modulo2(int a, int b) {
				if(b < a) {
					int k = 0;
						while(true) {
							if(k > a) {
								k = k - b;
								break;
							}
							else {
								k+= b;
								continue;
							}
						}
							return a - k;
					}
					else if(a == b) {
						return 0;
					}
					else {
						return a; 
					}
				}
			
			public String HexAddition(String hex1, String hex2) {
				int[] h1 = BinaryStringToIntArray(HexToBinary(hex1));
				int[] h2 = BinaryStringToIntArray(HexToBinary(hex2));
				
				if(h1.length != h2.length) {
					return "Fehler bei der Hex-Addition: Die HexZahlen sind nicht gleich lang.";
				}
				else {
				int[] temp = BinaryAddition(h1, h2);
				return binaryToHex(temp);
				}
				
			}
			
			public int modulo(int a, int b) { 
				if(b < a) {
				int k = 0;
					while(true) {
						if(k > a) {
							k = k - b;
							break;
						}
						else {
							k+= b;
							continue;
						}
					}
						return a - k;
				}
				else if(a == 0) {
					return 0;
				}
				else if(a == b) {
					return 0;
				}
				else {
					return a; 
				}
			}
		
			public int[] BinaryAddition(int[] a, int[] b) {
 				int[] result = new int[a.length];
 				int sum;
				int over = 0;
				int c = a.length-1;
				while (c >= 0) {
						sum = a[c] + b [c] + over;
						if (sum == 0) {
							result[c] = 0;
							over = 0;
						}
						else if (sum == 1) {
							result[c] = 1;
							over = 0;
						}
						else if (sum == 2) {
							result[c] = 0;
							over = 1;
						}
						else if (sum == 3) {
							result[c] = 1;
							over = 1;
						}
						c--;
					}
				if(over > 0) {
					int[] e = {0, 0, 0, 0, 0, 0, 0, 1};
							return BinaryAddition(result, e);
						}
			else {
					return result;
				}
			}
			
			public int binaryArrayToInt(int[] bin) {
				int r = 0;
				int[] x = new int[bin.length];
				for(int i = 0; i < x.length; i++) {
					x[i] = bin[bin.length-1-i];
				}
				for(int i = 0; i < x.length; i++) {
					if(x[i] == 1) {
						r += Math.pow(2, i);
					}
				}
				return r;
			}
			
			public String XOR(String a, String b) {
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
				
				//System.out.println("Angeglichenes Array 1: " +Arrays.toString(hex1));
				//System.out.println("Angeglichenes Array 2: " +Arrays.toString(hex2));
				
				
				int[] t1 = new int[hex1.length];
				for (int i = 0; i < hex1.length; i++) {
					t1[i] = hex1[i] + hex2[i];	
				}
				//System.out.println(Arrays.toString(t1));
				
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
				while ((int) bigModulo(PrimeNumbers[pos], 1, n) != 0) {
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
			public int findHexChar(char c) {
				int d = 0;
				while(c != HexValues[d]) {
					d++;
				}
				return d;
			}
			
			public String makeHexBig(String in) {
				char[] ww = StringToCharArray(in);
				
				for(int i = 0; i < in.length(); i++) {
					if(ww[i] == 'a') {
						ww[i] = 'A';
					}
					else if (ww[i] == 'b') {
						ww[i] = 'B';
					}
					else if (ww[i] == 'c') {
						ww[i] = 'C';
					}
					else if (ww[i] == 'd') {
						ww[i] = 'D';
					}
					else if (ww[i] == 'e') {
						ww[i] = 'E';
					}
					else if (ww[i] == 'f') {
						ww[i] = 'F';
					}
				}
				return charArrayToString(ww);
			}
	
	
			public String[] chopHexStream(String input) {
				String[] result;
				input = makeHexBig(input);
				if(input.length() == 32){
				result = new String[4];
				}
				else {
				result = new String[4];
				}
				
				if(input.length() == 32) {
					String t = "";
					for(int i = 0; i < 4; i++) {
						for(int j = 0; j < 8; j++) {
					t += input.charAt(i*8+j);
					result[i] = t;
						}
						t = "";
					}
				}
				else {
					String t = "";
					for(int i = 0; i < 4; i++) {
						for(int j = 0; j < 2; j++) {
					t += input.charAt(i*2+j);
					result[i] = t;
						}
						t = "";
					}	
				}
				return result;
			}
			
		
			public int findBinaryString(String s) {
				int c = 0;
				while (c < BinaryHexValues.length) {
					if (s.equalsIgnoreCase(BinaryHexValues[c]) == true) {
						return c;
						
					}
					else {
						c++;
						continue;
					}
					
				}
				return c;
			}
			
	
			public String intToString(int i) {
				String x = "";
				x += HexValues[i];
				return x;
			}
			
	
			public int getIntFromString(String s) {
				int x = 0;
				while (x < HexValues.length) {
					if (s.charAt(0) != HexValues[x]) {
						x++;
					}
					else {
						break;
					}
				}
				return x;
			}
			
			
			public String chopLastChar(String a) {
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
				
		
			public int[] oneForward(int [] input) {
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
			
		
			public boolean isBigLetter(char c) {
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
 			 			
//-------------------------------------------------------------------------------------------------------------------------
				
				//Constructor
			public Helper() {
	
	}
}