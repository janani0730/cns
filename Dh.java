import java.io.*;
import java.math.*;
import java.util.*;

public class Dh {	
 static int power(int x, int y, int p) {   
   int res = 1; 
   x = x % p;    
   while (y > 0) { 
     if ((y & 1) == 1) 
          res = (res * x) % p;
     y = y >> 1; 
     x = (x * x) % p; 
   } 
 return res; 
 } 

 static boolean miillerTest(int d, int n)
 {           
   int a = 2 + (int)(Math.random() % (n - 4)); 
   int x = power(a,d,n);
   if (x == 1 || x == n - 1) 
        return true; 
   while (d != n - 1) { 
        x = (x * x) % n; 
        d *= 2;      
        if (x == 1) 
            return false; 
        if (x == n - 1) 
            return true; 
        } 
        return false; 
 }

static boolean isPrime(int n, int k) { 
   if (n <= 1 || n == 4) 
            return false; 
   if (n <= 3) 
            return true; 
   int d = n - 1; 
   while (d % 2 == 0) 
            d /= 2; 
   for (int i = 0; i < k; i++) 
            if (!miillerTest(d, n)) 
                return false; 
   return true; 
} 

public static void main(String args[] )
{
	BigInteger p,g,xa,xb,ya,yb,ka,kb;
	int p1;
	Scanner in=new Scanner(System.in);
	System.out.println("Input prime number and primitive root : ");
	p1=in.nextInt();
	g=in.nextBigInteger();
	int k=4;			
        while(!isPrime(p1, k)) {
            	System.out.println("p is not prime, enter prime : ");
            	p1=in.nextInt();
            }
        p=BigInteger.valueOf(p1);
	System.out.println("Input public keys of a and b : ");
	xa=in.nextBigInteger();
	xb=in.nextBigInteger();
	ya=g.modPow(xa,p);
	yb=g.modPow(xb,p);
	ka=ya.modPow(xb,p);
	kb=yb.modPow(xa,p);
	System.out.println("Private Keys:\nya : "+ya+"\tyb : "+yb);
	System.out.println("The shared key is :\nka:"+ka+"\tkb:"+kb);
	}
}
