import java.io.*;
import java.util.*;

public class MD5
{
  	private static final int INIT_A = 0x67452301;
  	private static final int INIT_B = (int)0xEFCDAB89L;
  	private static final int INIT_C = (int)0x98BADCFEL;
  	private static final int INIT_D = 0x10325476;
 
  	private static final int[] SHIFT_AMTS = {
    	7, 12, 17, 22,
    	5,  9, 14, 20,
    	4, 11, 16, 23,
    	6, 10, 15, 21
  	};
 
  	private static final int[] TABLE_T = new int[64];
  	static
  	{
    	for (int i = 0; i < 64; i++)
    		TABLE_T[i] = (int)(long)((1L << 32) * Math.abs(Math.sin(i + 1)));
  	}
 
  	public static byte[] computeMD5(byte[] message)
  	{
    	int msgBytes = message.length;
    	int numBlocks = ((msgBytes + 8) >>> 6) + 1;
    	int totalLen = numBlocks * 64; // #blocks * 64
    	byte[] paddingBytes = new byte[totalLen - msgBytes];
    	paddingBytes[0] = (byte)0x80; //1000000
    	long msgBits = (long)msgBytes * 8; // bit =  byte * 8

    	for (int i = 0; i < 8; i++)
    	{
    	  	paddingBytes[paddingBytes.length - 8 + i] = (byte)msgBits; // -8 is to omit the length bytes at  end
      		msgBits >>>= 8;
    	}
 
 
    	int a = INIT_A;
    	int b = INIT_B;
    	int c = INIT_C;
    	int d = INIT_D;
    	int[] X = new int[16];
    	for (int i = 0; i < numBlocks; i ++)
    	{
    	  	int index = i * 64; 
      		for (int j = 0; j < 64; j++, index++)
          X[j * 4] = ((int)((index < msgBytes) ? message[index] : paddingBytes[index - msgBytes]) << 24) | (X[j *4] >>> 8);

        
      		int originalA = a;
      		int originalB = b;
      		int originalC = c;
      		int originalD = d;
      		for (int j = 0; j < 64; j++)
      		{
        		int div16 = j/16;
        		int f = 0;
        		int k = j;
        		switch (div16)
        		{
          			case 0:
            			f = (b & c) | (~b & d);
            			break;
 
          			case 1:
            			f = (b & d) | (c & ~d);
            			k = (k * 5 + 1) & 0x0F;
            			break;
 
          			case 2:
            			f = b ^ c ^ d;
            			k = (k * 3 + 5) & 0x0F;
            			break;
 
          			case 3:
            			f = c ^ (b | ~d);
            			k = (k * 7) & 0x0F;
            			break;
        		}
        		int temp = b + Integer.rotateLeft(a + f + X[k] + TABLE_T[j], SHIFT_AMTS[(div16 * 4) | (j & 3)]);
        		a = d;
        		d = c;
        		c = b;
        		b = temp;
      		}
 
		    a += originalA;
      		b += originalB;
      		c += originalC;
      		d += originalD;
    	}
 
     	byte[] md5 = new byte[16];
     	int count = 0;
   // "\n16 Round Compressions:\n"
     	for (int i = 0; i < 4; i++)
    	{
      		int n = (i == 0) ? a : ((i == 1) ? b : ((i == 2) ? c : d));
       		for (int j = 0; j < 4; j++)
       		{
         		md5[count++] = (byte)n;
         		n >>>= 8;
       		}
     	}
    	return md5;
  	 }
 
  	public static String toHexString(byte[] b)
  	{
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < b.length; i++)
    	{
      		sb.append(String.format("%02X", b[i] & 0xFF));
    	}
    	return sb.toString();
  	}
 
  	public static void main(String[] args)
  	{
  		String plaintext = "";

  		Scanner sc = new Scanner(System.in);

  		while(true)
  		{
	  		System.out.println("\nEnter Message: ");
  			plaintext = sc.nextLine();
  			if(plaintext.equals(""))
  			{
  				System.out.println("\nMD5 Hash of "+"' '"+" is "+toHexString(computeMD5(plaintext.getBytes())));
  				break;
 			}
 			else
 			{
 				System.out.println("\nMD5 Hash of "+plaintext+" is "+toHexString(computeMD5(plaintext.getBytes())));
  			}
 		}
  	}	
}