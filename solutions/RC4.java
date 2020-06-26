package de.unidue.iem.tdr.nis.client.solutions;

public class RC4 extends Helper{
		
//----------------------------------------------------------------------------------------------------------------------
		
		//Necessary Methods ONLY for the RC4-Tasks
		public int getStateLength(String s) {
			int c = 0;
			for(int i = 0; i < s.length(); i++) {
				if(i < s.length()-1) {
				if(s.charAt(i) != '_' && s.charAt(i+1) == '_' ) {
					c++;
				}
				else if(s.charAt(i) != '_' && s.charAt(i+1) != '_') {
					c++;
					i += 1;
				}
			}
			
			else {
				c++;
				break;
			}
			}
			return c;
		}
		
		public int[] StateStringToArray(String s) {
			int[] result = new int[getStateLength(s)];
			int k = 0;
			for(int i = 0; i < s.length(); i++) {
			if(i == s.length() - 1) {
				String s0 = "0";
				s0 += s.charAt(i);
				result[k] = dualStringToInt(s0);
			}
			else {
				if(s.charAt(i) == '_') {
					
				}
				else if(s.charAt(i) != '_') {
					if(s.charAt(i+1) == '_') {
						String s0 = "0";
						s0 += s.charAt(i);
						result[k] = dualStringToInt(s0);
						k++;
					}
					else {
						String s0 = "";
						s0 += s.charAt(i);
						s0+= s.charAt(i+1);
						result[k] = dualStringToInt(s0);
						k++;
						i++;
					}
					
				}
			}
			}
			return result;
		}
		
		public int[] XOR2(int[] a, int[] b) {
			int[] result = new int[a.length];
			for(int i = 0; i < a.length; i++) {
				if(a[i] == 0 && b[i] == 0) {
					result[i] = 0;
				}
				else if(a[i] == 1 && b[i] == 1) {
					result[i] = 0;
				}
				else {
					result[i] = 1;
				}
			}
			return result;
		}
	//----------------------------------------------------------------------------------------------------------------------
		
		public String RC4PRGA(int[] STable, String t) {
			int[] S = STable;
			int i = 0;
			int j = 0;
			String result = "";
			int k = 0;
			
			while (k < t.length()){
				i = modulo((i+1), S.length);
				j = modulo((j+ S[i]), S.length);
				
				int a = S[i];
				S[i] = S[j];
				S[j] = a;
				
				int b = S[i] + S[j];
				
				int p = S[modulo(b, S.length)];
				result += p;	
				k++;
			}
			return result;
		}
	
		public int[] RC4KSA(int[] Key) {
			//Initialize S-Table
			int[] s = new int[Key.length];
			for(int i = 0; i < s.length; i++) {
				s[i] = i;
			}
			
			int j = 0;
			
			for(int i = 0; i < Key.length; i++) {
				int K_INDEX = modulo(i, Key.length);
				j = modulo((j + Key[K_INDEX] + s[i]), Key.length);
				int a = s[i];
				s[i] = s[j];
				s[j] = a;
			}
			return s;
		}
		
		public int[] RC4PRGA2(int[] STable, String t) {
			int[] result = new int[t.length()];
			int[] S = STable;
			int i = 0;
			int j = 0;
			int k = 0;
			
			while (k < t.length()){
				i = modulo((i+1), S.length);
				j = modulo((j+ S[i]), S.length);
				
				int a = S[i];
				S[i] = S[j];
				S[j] = a;
				
				int b = S[i] + S[j];
				
				int p = S[modulo(b, S.length)];
				result[k] = p;	
				k++;
			}
			return result;
		}

		public String[] RC4Encryption(int[] key, String plain) {
			int[] k = RC4KSA(key);
			int[] s = RC4PRGA2(k, plain);
			String[] message = plainToHex(plain);
			
			for(int i = 0; i < message.length; i++) {
				int[] currBinary = BinaryStringToIntArray(HexToBinary(message[i]));
				int[] rN = DecimalToBinaryArray(s[i]);

					int[] t = new int[8];
					int o = rN.length-1;
					for(int j = 0; j < rN.length; j++) {
						t[7-j] = rN[o];
						o = o-1;
					}
				int[] r = XOR2(t, currBinary);
				message[i] = binaryToHex(r);
				
				
				}
				
			return message;
			
			
		
		}
}

