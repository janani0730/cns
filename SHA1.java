import java.io.*;
import java.util.*;

public class SHA1 
{
	private List<byte[]> inputDataList;
	
	public SHA1() 
	{
		this.inputDataList = new ArrayList<byte[]>();
	}

	private static int rol(int num, int cnt) 
	{
		return (num << cnt) | (num >>> (32 - cnt));
	}
	
	public void update(byte[] data) 
	{
		inputDataList.add(data);
	}
	
	public byte[] digest() 
	{
		int totalNumBytes = 0;
		for (byte[] chunk : inputDataList) 
		{
			totalNumBytes += chunk.length;
		}
		byte[] allData = new byte[totalNumBytes];
		int off = 0;
		for (byte[] chunk : inputDataList) 
		{
			System.arraycopy(chunk, 0, allData, off, chunk.length);
			off += chunk.length;
		}
		return digest(allData);
	}

	public byte[] digest(byte[] x) 
	{
		int[] blks = new int[(((x.length + 8) >> 6) + 1) * 16];
		int i;

		for(i = 0; i < x.length; i++) 
		{
			blks[i >> 2] |= x[i] << (24 - (i % 4) * 8);
		}

		blks[i >> 2] |= 0x80 << (24 - (i % 4) * 8);
		blks[blks.length - 1] = x.length * 8;

		int[] w = new int[80];

		int a =  0x67452301;
		int b =  (int)0xEFCDAB89L;
		int c =  (int)0x98BADCFEL;
		int d =  0x10325476;
		int e =  (int)0xc3d2e1f0L;

		System.out.println("\nInitial Buffers: \nA: "+String.format("%x",a)+"\nB: "+String.format("%x",b)+"\nC: "+String.format("%x",c)+"\nD: "+String.format("%x",d)+"\nE: "+String.format("%x",e));
		for(i = 0; i < blks.length; i += 16) 
		{
			int olda = a;
			int oldb = b;
			int oldc = c;
			int oldd = d;
			int olde = e;

			System.out.println("\n80 Round Compressions: ");
			for(int j = 0; j < 80; j++) 
			{
				System.out.println("Round "+j+"- A: "+a+" B: "+b+" C: "+c+" D: "+d+" E: "+e);

				w[j] = (j < 16) ? blks[i + j] : ( rol(w[j-3] ^ w[j-8] ^ w[j-14] ^ w[j-16], 1) );

				int t = rol(a, 5) + e + w[j] + ( (j < 20) ?  (int)0x5A827999 + ((b & c) | ((~b) & d))
								: (j < 40) ?  (int)0x6ED9EBA1 + (b ^ c ^ d) : (j < 60) ? (int)0x8F1BBCDC + ((b & c) | (b & d) | (c & d))
												: (int)0xCA62C1D6 + (b ^ c ^ d) );
				e = d;
				d = c;
				c = rol(b, 30);
				b = a;
				a = t;
			}

			a = a + olda;
			b = b + oldb;
			c = c + oldc;
			d = d + oldd;
			e = e + olde;
		}

		byte[] digest = new byte[20];
		fill(a, digest, 0);
		fill(b, digest, 4);
		fill(c, digest, 8);
		fill(d, digest, 12);
		fill(e, digest, 16);

		return digest;
	}

	private void fill(int value, byte[] arr, int off) 
	{
		arr[off + 0] = (byte) ((value >> 24) & 0xff);
		arr[off + 1] = (byte) ((value >> 16) & 0xff);
		arr[off + 2] = (byte) ((value >> 8) & 0xff);
		arr[off + 3] = (byte) ((value >> 0) & 0xff);
	}

	public String toHexString(byte[] b)
  	{
    	StringBuilder sb = new StringBuilder();
    	System.out.println("\nSHA1 Hash being generated....");
    	for (int i = 0; i < b.length; i++)
    	{
    		System.out.println("Hash "+(i+1)+": "+String.format("%02X", b[i] & 0xFF));
      		sb.append(String.format("%02X", b[i] & 0xFF));
    	}
    	return sb.toString();
  	}

	public static void main(String[] args)
	{
		SHA1 s = new SHA1();
		Scanner sc = new Scanner(System.in);
		String plaintext = "";

		while(true)
  		{
	  		System.out.println("\nEnter Message: ");
  			plaintext = sc.nextLine();
  			if(plaintext.equals(""))
  			{
  				System.out.println("\nSHA1 Hash of "+"' '"+" is "+s.toHexString(s.digest(plaintext.getBytes())));
  				break;
 			}
 			else
 			{
 				System.out.println("\nSHA1 Hash of "+plaintext+" is "+s.toHexString(s.digest(plaintext.getBytes())));
  			}
 		}
	}
}