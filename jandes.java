import java.io.*;
import java.util.*;
import java.lang.Math;
import java.math.*;

class jandes{

public static String[] keys = new String[16];

private static int[] ip = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36,
            28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32,
            24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
            11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
    private static int[] ip_inverse = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47,
            15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13,
            53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51,
            19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17,
            57, 25 };
    private static int[][][] s_table = {
            { 		{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                    { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                    { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                    { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
            },
            { 		{ 15, 1, 8, 14, 6, 11, 3, 2, 9, 7, 2, 13, 12, 0, 5, 10 },
                    { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                    { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                    { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
            },
            { 		{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                    { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                    { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                    { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
            },
            { 		{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                    { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                    { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                    { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
            },
            { 		{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                    { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                    { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                    { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }
            },
            { 		{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                    { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                    { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                    { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }

            },
            { 		{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                    { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                    { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                    { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
            },
            { 		{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                    { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                    { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                    { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }

            } };
            private static int[] pc1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34,
            26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63,
            55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53,
            45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
            private static int[] pc2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29,
            32 };
            private static int[] e_table = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8,
            9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,
            1 };
            private static int[] p = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5,
            18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4,
            25};
            private static int[] keyShift = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2,
            2, 1 }; 

public static String toBinStr(String str)
{
String res = "";
for(int i =0;i<str.length();i++)
{int x = (int)str.charAt(i);
String temp = Integer.toBinaryString(x);
while(temp.length()%8!=0)
 temp = "0" + temp;
res+= temp;
}

while(res.length()%64!=0)
res = "0" + res;

return res;
}


public static String ip(String str)
{
char[] newar = new char[64];
for(int i =0; i<64;i++)
{
newar[i] = str.charAt(ip[i] -1);
}
String res = new String(newar);
return res;
} 

public static String inv_ip(String str)
{
char[] newar = new char[64];
for(int i =0; i<64;i++)
{
newar[i] = str.charAt(ip_inverse[i] -1);
}
String res = new String(newar);
return res;
} 

public static String pbox(String str)
{
char[] newar = new char[32];
for(int i =0; i<32;i++)
{
newar[i] = str.charAt(p[i] -1);
}
String res = new String(newar);
return res;
} 

public static String exp(String str)
{
char[] newar = new char[48];
for(int i =0; i<48;i++)
{
newar[i] = str.charAt(e_table[i] -1);
}
String res = new String(newar);
return res;
} 

public static String pc1(String str)
{
char[] newar = new char[56];
for(int i =0; i<56;i++)
{
newar[i] = str.charAt(pc1[i] -1);
}
String res = new String(newar);
return res;
} 

public static String pc2(String str)
{
char[] newar = new char[48];
for(int i =0; i<48;i++)
{
newar[i] = str.charAt(pc2[i] -1);
}
String res = new String(newar);
return res;
} 

 public static String xor(String str1, String str2)
            {
                if(str1.length()!=str2.length())
                {
                    System.out.println("Not equal strings");
                    return null;
                }
                String ret_string = "";
                for(int i=0;i<str1.length();i++)
                {
                    if(str1.charAt(i)=='0' && str2.charAt(i)=='0')
                        ret_string = ret_string + "0";
                    else if(str1.charAt(i)=='1' && str2.charAt(i)=='1')
                        ret_string = ret_string + "0";
                    else if(str1.charAt(i)=='1' && str2.charAt(i)=='0')
                        ret_string = ret_string + "1";
                    else if(str1.charAt(i)=='0' && str2.charAt(i)=='1')
                        ret_string = ret_string + "1";
                }
                return ret_string;
}

public static String rotate(String str)
{
String temp = str.charAt(0)+ ""; String res = "";
for(int i =1; i<str.length(); i++)
{
 res+= str.charAt(i);
}

res+=temp;

return res;
}

public static String rot(String s, int i)
{
for(int j=0; j<i;j++)
{
s = rotate(s);
}

return s;
}
public static String sboxx(String s, int i)
{
String  rowString = "" + s.charAt(0) + s.charAt(5);
String  colString = "" + s.charAt(1) + s.charAt(2) + s.charAt(3) + s.charAt(4);
int row = Integer.parseInt(rowString, 2);
int col = Integer.parseInt(colString, 2);
int val = s_table[i][row][col];
String r = Integer.toBinaryString(val);
while(r.length()%4!=0)
r = "0" + r;

return r;
}

public static String sbox(String input)
{int value = 0;
                String[] words = new String[8];
                String ret_string = "";

                for(int i=0;i<8;i++)
                {
                    words[i] = input.substring(0 + value,6 + value);
                    value = value + 6;
                }
                for(int i = 0;i<8;i++)
                {
                    ret_string = ret_string + sboxx(words[i],i);
                }

                return ret_string;
}


public static String encryption(String s, String key)
{

String str = s;System.out.println("S - 64:" + str.length() + " " + str);
String k = key;
String key_l = "", key_r = "", sl = "", sr = "", temp ="";
str = ip(str);
k = pc1(k); System.out.println("K - 56:" + k.length() + " " + k);
for(int i =0; i<16;i++)
{
System.out.println("Round"+i);
key_l = k.substring(0,28); //System.out.println("K - 28:" + key_l.length() + " " + key_l);
key_r = k.substring(28,56);// System.out.println("K - 28:" + key_r.length() + " " + key_r);
key_l = rot(key_l, keyShift[i]);//System.out.println("K - 28:" + key_l.length() + " " + key_l);
key_r = rot(key_r, keyShift[i]);//System.out.println("K - 28:" + key_r.length() + " " + key_r);
String k2 = key_l + key_r;// System.out.println("K - 56:" + k.length() + " " + k);
k2 = pc2(k2); //System.out.println("K - 48:" + k2.length() + " " + k2);
keys[i]=k2;

sl = str.substring(0,32); System.out.println("S - 32:" + sl.length() + " " + sl);
sr = str.substring(32,64); System.out.println("S - 32:" + sr.length() + " " + sr); temp = sr;
sr = exp(sr); System.out.println("S - 48:" + sr.length() + " " + sr);
sr = xor(sr, k2); System.out.println("S - 48:" + sr.length() + " " + sr);
sr = sbox(sr);System.out.println("S - 32:" + sr.length() + " " + sr);
sr = pbox(sr);System.out.println("S - 32:" + sr.length() + " " + sr);
sr = xor(sr, sl);System.out.println("S - 32:" + sr.length() + " " + sr);
sl  = temp; System.out.println("S - 32:" + sl.length() + " " + sl);
str = sl + sr; System.out.println("S - 64:" + str.length() + " " + str);
}

sl = str.substring(0,32);
sr = str.substring(32,64);
str = sr + sl;
str = inv_ip(str);
return str;
}

public static String decryption(String s)
{

String str = s;
//String k = key;
String  sl = "", sr = "";
str = ip(str);
//k = pc1(k);
for(int i =0; i<16;i++)
{
sl = str.substring(0,32);
sr = str.substring(32,64);
String temp = sr;
sr = exp(sr);
sr = xor(sr, keys[15 - i]);
sr = sbox(sr);
sr = pbox(sr);
sr = xor(sr, sl);
sl  = temp;
str = sl + sr;
}

sl = str.substring(0,32);
sr = str.substring(32,64);
str = sr + sl;
str = inv_ip(str);
return str;
}


public static void main(String[] args)
{
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the msg: ");
	String msg = sc.nextLine();
	System.out.println("Enter the key: ");
	long key = sc.nextLong();
	String bitkey = Long.toBinaryString(key);
	while(bitkey.length()%64!=0)
	bitkey = "0" + bitkey;
	String bitmsg = toBinStr(msg);
	String e = encryption(bitmsg,bitkey);
	String d = decryption(e);
	
BigInteger enc = new BigInteger(e, 2);
System.out.println("Enc msg : " + enc.toString(16));
enc = new BigInteger(d, 2);
System.out.println("Dec msg : " + enc.toString(16));
	


}

}
