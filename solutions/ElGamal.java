package de.unidue.iem.tdr.nis.client.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ElGamal extends Helper{


	/**
	 * Diese Klasse stellt Konstanten und Methoden für die ElGamal-Verschlüsselung bereit
	 */

//-----------------------------------------------------------------------------------------------------------------------
	
	private Random r = new Random();
	private int a;
	private int[] publicKey = new int[3];
	private int encryptionKey;
	private int k = -1;
	private int decryptionKey;
	
//-----------------------------------------------------------------------------------------------------------------------
	
	//Mathematical Methods
	
	private int[] generatePrimes(int boundary) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < boundary; i++) {
			if(isPrime(i) == true) {
				list.add(i);
			}
			else {
				continue;
			}
		}
		int[] result = new int[list.size()-2];
		for(int i = 0; i < result.length; i++) {
			result[i] = list.get(2+i);
		}
		return result;
	}
	
	@Override
	public String primeFactors(int n) {
		int[] PrimeNumbers = generatePrimes(n);
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
		
		return PrimeFactors;
		
	}
	
	public int calculatePrimitiveRoot(int n) {
		int x = n-1;
		int[] factors = factorize(x);
		for(int i = 0; i < n; i++) {
			int[] temp = new int[factors.length];
			//Calculate for every i  exp(g, phi(n)/p)
			for(int k = 0; k < temp.length; k++) {
				temp[k] = (int) bigModulo(i, x / factors[k], n);
			}
			//check whether a 1 was calculated
			boolean b  = false;
			for(int k = 0; k < temp.length; k++) {
				if(temp[k] != 1) {
					continue;
				}
				else {
					b = true;
					break;
				}
			}
			if(i == 0) {
				continue;
			}	
			else if(b == false) {
				return i;
			}
				
		}
		return 0;
	}

	private int[] factorize(int n) {
		int x = n;
		int[] primes = generatePrimes(n);
		ArrayList<Integer> l = new ArrayList<Integer>();
		for(int i = 0; i < primes.length; i++) {
		if(modulo(x, primes[i]) == 0) {
		l.add(primes[i]);
		x = x / primes[i];
		i--;
		}
		else {
			
			}
		}
			int[] result = new int[l.size()];
			for(int i = 0; i < result.length; i++) {
				result[i] = l.get(i);
			}
			return result;
	}
	
	private String binaryArrayToHex(int[] bin) {
		int[] temp;
		if(modulo(bin.length, 4) != 0) {
			int x = 0;
			while(modulo(bin.length + x , 4) != 0) {
				x++;
			}
			temp = new int[bin.length + x];
			for(int i = 0; i < x; i++) {
				temp[i] = 0;
			}
			for(int i = x; i < temp.length; i++) {
				temp[i] = bin[i-x];
			}
		}
		else {
			temp = bin;
		}
		String result = "";
		for(int i = 0; i < temp.length/4; i++) {
			int[] temp2 = new int[4];
			for(int j = 0; j < 4; j++) {
				temp2[j] = temp[4*i+j];
			}
			int x = findBinaryString(IntArrayToString(temp2));
			result += this.HexValues[x];
		}
		
		return result;
	}
	
//------------------------------------------------------------------------------------------------------------------------
				
	public int[] generateKeys() {
		
		//Generate and save p
		int p = r.nextInt(1000);
		while(isPrime(p) == false && isPrime(p-1/2) == false) {
			p++;
		}
		publicKey[0] = p;
		
		//Compute alpha
		int alpha = calculatePrimitiveRoot(p);
		publicKey[1] = alpha;
		
		//Selecting random int
		int a0 = 0;
		while(a0 <= 1) {
		a0 = r.nextInt(p-2);
		}
		this.a = a0;
		
		//compute beta
		int beta = (int) bigModulo(publicKey[1], this.a, publicKey[0]);
		publicKey[2] = beta;
		
		//Debug
		//System.out.println("Geählte Primzahl: " + p + "\n" + "Berechnete Primitivwurzel: " + publicKey[1] + "\n" + "Gewähltes a: " + this.a + "\n" + "Berechnetes beta: " + publicKey[2]);
	
		return this.publicKey;
	}
		
	public String[] getPublicKey() {
			return new String[] {"" + this.publicKey[0], "" + this.publicKey[1], "" + this.publicKey[2]};
		}
	
	public void setKey(int p, int alpha, int beta) {
		publicKey[0] = p;
		publicKey[1] = alpha;
		publicKey[2] = beta;
	}

	public void setRandomA(int a) {
		this.a = a;
	}
	
	public void setRandomK(int k) {
		this.k = k;
	}
	
	public String encrpyt(String s) {
		if(this.k == -1) {
		//selecting random k
		int k = 0;
		while(k <= 1 || k >= publicKey[0]-2 || EuclidGGT(k, publicKey[0]) != 1) {
		 k = r.nextInt();
			}
		this.k = k;
		}
		
		this.encryptionKey = (int) bigModulo(publicKey[2], this.k, publicKey[0]);
		String enc = "" + (int) bigModulo(publicKey[1], this.k, publicKey[0]) + "_";
		
		int[] m = plainToDecimalASCII(s);
		
		int[] temp = new int[m.length];
		for(int i = 0; i < temp.length; i++) {
			temp[i] = modulo(m[i] * this.encryptionKey, publicKey[0]);
		}
		
		String[] s0 = new String[temp.length];
		for(int i = 0; i < s0.length; i++) {
			s0[i] = binaryArrayToHex(DecimalToBinaryArray(temp[i]));
		}
		
		for(int i = 0; i < s0.length-1; i++) {
			enc += s0[i] + "_";
		}
		enc += s0[s0.length-1];
		
		return enc;
		
		
		
	}
	
	public String decrypt(String s) {
		int x = 1;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '_') {
				x++;
			}
		}
		int k = 0;
		String[] s0 = new String[x];
		for(int i = 0; i < s.length(); i++) {
			x = 0;
			while(x+1 != s.length()-1) {
				
				if(s.charAt(i + x) != '_' && i + x < s.length()-1) {
					x++;
				}
				else if(i + x >= s.length()-1) {
					x = s.length()-i - 1;
					break;
				}
				else {
					break;
				}
			}
			if(i + x < s.length()-1) {
			s0[k] = s.substring(i, i + x);
			i = i + x;
			k++;
			}
			else {
				s0[k] = s.substring(i, i + x+1);
				break;
			}
		}
		System.out.println(Arrays.toString(s0));
		
		
		int[] m = new int[s0.length];
		for(int i = 0; i < s0.length; i++) {
			m[i] = binaryArrayToInt(BinaryStringToIntArray(HexToBinary(s0[i])));
		}
		
		//System.out.println(Arrays.toString(m));
		
		
		//calculate K^-1
		decryptionKey = (int) bigModulo(m[0], publicKey[0] - 1 - this.a, publicKey[0]);
		
		int[] mD = new int[m.length];
		for(int i = 0; i < mD.length; i++) {
			mD[i] = modulo(m[i] * this.decryptionKey, publicKey[0]);
		}
		
		int[] mD0 = new int[mD.length-1];
		for(int i = 0; i < mD0.length; i++) {
			mD0[i] = mD[i+1];
		}
		
		char[] c = ASCIIToClear(mD0);
		
		
		//System.out.println(Arrays.toString(mD0));
		
		return charArrayToString(c);
		
		
	}
	
}
