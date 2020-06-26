package de.unidue.iem.tdr.nis.client.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RSA extends Helper{
//-----------------------------------------------------------------------------------------------------------------------
	 int RSA_n;
	 int RSA_e;
	 int RSA_d;
	 int RSA_EUKLID;
	 int EuclidStepCount = 1;
	 int p;
	 int q;
	 Random rand = new Random();
	 int[] primes = generatePrimes(100);
//-----------------------------------------------------------------------------------------------------------------------
	public String[] generatePublicKey() {
		this.generateRSA_Key();
		String n = "" + RSA_n;
		String e = "" + RSA_e;
		return new String[] {n, e};
	}
	 
	private int[] ExtEuclid(int a, int b) {
		//Self written based on Pseudocode
		int s = 0; 			int old_s = 1;
		int r = b;			int old_r = a;
		int q = 0;			int r0;
		int s0;				int t;
	while (r != 0) {
		q = (int) old_r/ r;
		r0 = r;
		s0 = s;
		r = old_r - q * r;
		old_r = r0;
		s = old_s - q * s;
		old_s = s0;
	}
	if(b != 0) {
		int n = old_r - old_s * a;
		t = n / b;
	}
	else {
		t = 0;
	}
	return new int[] {old_r, old_s, t};
	}
	
	private boolean isMirrored(int a, int b) {
		if(a < 10 || b < 10 || a > 99 || b > 99) {
			return false;
		}
		else {
			String sA ="";
			sA += a;
			String sB = "";
			sB += b;
			if(sA.charAt(0) == sB.charAt(1) && sA.charAt(1) == sB.charAt(0)) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public int[] generatePrimes(int boundary) {
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
	
	
	public void generateRSA_Key() {
		//selecting 2 random prime Numbers
		int p = 0;
		while(p < 10) {
		p = primes[rand.nextInt(primes.length)];
		}
		this.p = p;
		int q = 0;
		while (q >= p || q == 0) {
		q = primes[rand.nextInt(primes.length)];
		}
		this.q = q;
		
		//Calculating N
		this.RSA_n = p * q;
		
		//Calculating EUKLID
		this.RSA_EUKLID = (p-1) * (q - 1);
		
		//Choosing random small int 
		int x = rand.nextInt(RSA_EUKLID);
		while(EuclidGGT(x, this.RSA_EUKLID) != 1) {
			x = rand.nextInt(RSA_EUKLID);
		}
		this.RSA_e = x;
		
		
		//Calculate d
		this.RSA_d = this.RSA_EUKLID + ExtEuclid(this.RSA_e, this.RSA_EUKLID)[1];
		
		if(RSA_e == RSA_d) {
			while(RSA_e == RSA_d) {
				int f = rand.nextInt(RSA_EUKLID);
				while(EuclidGGT(f, this.RSA_EUKLID) != 1) {
					f = rand.nextInt(RSA_EUKLID);
				}
				this.RSA_e = f;
				this.RSA_d = this.RSA_EUKLID + ExtEuclid(this.RSA_e, this.RSA_EUKLID)[1];
			}
		}
		
		if(modulo(RSA_e*RSA_d, RSA_EUKLID) != 1) {
			this.generateRSA_Key();
		}
	}
	
	public void setRSAKey(int n, int e) {
		this.RSA_n = n;
		this.RSA_e = e;
		
	}
	
	public void setRSAKey(int n, int e, int d, int euklid) {

		this.RSA_n = n;
		this.RSA_e = e;
		this.RSA_d = d;
		this.RSA_EUKLID = euklid;
	}
	
	public int[] encryptRSA(int[] e) {
		DFH d = new DFH();
		int[] result = new int[e.length];
		for(int i = 0; i < e.length; i++) {
			result[i] = (int) d.bigModulo(e[i], this.RSA_e, this.RSA_n);
		}
		return result;
	}
	
	public int[] decryptRSA(String s) {
		DFH d = new DFH();
		//Chop the String
				int[] temp = new int[s.length()];
				for(int i = 0; i < temp.length; i++) {
					if(s.charAt(i) == '_') {
						temp[i] = -1;
					}
					else {
						switch(s.charAt(i)) {
						case '0':
							temp[i] = 0;
							break;
						case '1':
							temp[i] = 1;
							break;
						case '2':
							temp[i] = 2;
							break;
						case '3':
							temp[i] = 3;
							break;
						case '4':
							temp[i] = 4;
							break;
						case '5':
							temp[i] = 5;
							break;
						case '6':
							temp[i] = 6;
							break;
						case '7':
							temp[i] = 7;
							break;
						case '8':
							temp[i] = 8;
							break;
						case '9':
							temp[i] = 9;
							break;
						}
						}
					}
				
				int zz = 1;
				for(int i = 0; i < temp.length; i++) {
					if(temp[i] == -1) {
						zz++;
					}
					else {
						continue;
					}
				}
				int kk = 0;
				int[] mInt = new int[zz];
				for(int i = 0; i < temp.length; i++) {
					String sz = "";
					while (i < temp.length && temp[i] != -1)  {
						sz+= temp[i];
						i++;
					}
					
					mInt[kk] = Integer.parseInt(sz);
					kk++;
							}

					
		int[] result = new int[mInt.length];
		for(int i = 0; i < mInt.length; i++) {
			result[i] = (int) d.bigModulo(mInt[i], this.RSA_d, this.RSA_n);
		}
				
			return result;		
	}
}
