import java.io.*;
import java.lang.*;
import java.util.*;

public class Vignerecipher {

  static void printMat(int[][] mat,int m,int n)
  {
    int i,j;
    for(i=0;i<m;i++)
    {
      for(j=0;j<n;j++)
      {
        System.out.print(mat[i][j]+" ");
      }
      System.out.println();
    }
  }
  static void printMat2(char[][] mat,int m,int n)
  {
    int i,j;
    for(i=0;i<m;i++)
    {
      for(j=0;j<n;j++)
      {
        System.out.print(mat[i][j]+" ");
      }
      System.out.println();
    }
  }

  static int[][] mmul(int[][] first,int[][] second)
  {
    int c,d,k,sum=0;
    int [][] multiply=new int[3][1];
        for (c = 0; c < 3; c++)
         {
            for (d = 0; d < 1; d++)
            {  
               for (k = 0; k < 3; k++)
               {
                  sum = sum + first[c][k]*second[k][d];
               }
 
               multiply[c][d] = sum;
               sum = 0;
            }
         }
      return multiply;
  }

  static int[][] minv(int[][] mat)
  {
   
    int i, j;
    int det = 0;
    int detinv=1;
    int cof[][] = new int[3][3];
    int adj[][] = new int[3][3];
    for(i=0;i<3;i++)
    {
      for(j=0;j<3;j++)
      {
        cof[i][j]=mat[(i+1)%3][(j+1)%3]*mat[(i+2)%3][(j+2)%3]-mat[(i+2)%3][(j+1)%3]*mat[(i+1)%3][(j+2)%3]; //cofactor matrix
      }
    }
    for(i=0;i<3;i++)
    {
      for(j=0;j<3;j++)
      {
        adj[i][j]=cof[j][i]; //adjoint
      }
    }


      for(i = 0; i < 3; i++)
          det = det + (mat[0][i] * (mat[1][(i+1)%3] * mat[2][(i+2)%3] - mat[1][(i+2)%3] * mat[2][(i+1)%3]));
      det=det%26;
      if(det<0)
        det=det+26;
     
      for(i=1;i<26;i++)
        if((det*i)%26==1)
        {
          detinv=i; //(det*detinv)%26=1
          break;
        }
     
      //System.out.println(det);
      //System.out.println(detinv);

      for(i=0;i<3;i++)
        for(j=0;j<3;j++)
          adj[i][j]=(adj[i][j]*detinv)%26; //K inv matrix is adjoint*detinv % 26

      return adj;
  }
 

  static void func1() //Vigenere cipher encryption
  {
    String str,key;
    int i,j,t;
    int temp;
    System.out.println("Enter the text: "); //attackatdawn
    Scanner sc=new Scanner(System.in);
    str=sc.nextLine();
    int len=str.length();
    char[] str2=str.toCharArray();
    System.out.println("Enter the key: "); //lemon
    key=sc.next();
    char[] str_key=key.toCharArray();
    int len2=key.length();
    if(len>len2)
    {
      i=0;
      for(t=1;t<=len-len2;t++)
      {
        key+=str_key[i];
        ++i;
        if(i==len2)
          i=0;
      }
    }
    str_key=key.toCharArray();
    //System.out.println(str_key);
    Map<Character,Integer> m=new HashMap<Character,Integer>();
    Map<Integer,Character> m2=new HashMap<Integer,Character>();
    char ch='a';
    for(i=0;i<26;i++)
    {
      m.put(ch, i); //char to int
      m2.put(i,ch); //int to char
      ch=(char)((int)ch+1);
    }
    char c1,c2,c3;
    int k;
    //System.out.println(str.length());
    String result="";
    char Vigenere[][]=new char[26][26];
    for(i=0;i<26;i++)
      for(j=0;j<26;j++)
        Vigenere[i][j]=m2.get((i+j)%26);
    for(i=0;i<len;i++)
    {
      c1=str2[i];
      c2=str_key[i];
      result+=Vigenere[m.get(c1)][m.get(c2)];
    }
    System.out.println(result);
    //printMat2(Vigenere,26,26);
  }

  static void func2() //Vigenere cipher decryption
  {
    String str,key;
    int i,j,t;
    int temp;
    System.out.println("Enter the text: "); // lxfopvefrnhr
    Scanner sc=new Scanner(System.in);
    str=sc.nextLine();
    int len=str.length();
    char[] str2=str.toCharArray();
    System.out.println("Enter the key: ");
    key=sc.next();
    char[] str_key=key.toCharArray();
    int len2=key.length();
    if(len>len2)
    {
      i=0;
      for(t=1;t<=len-len2;t++)
      {
        key+=str_key[i];
        ++i;
        if(i==len2)
          i=0;
      }
    }
    str_key=key.toCharArray();
    //System.out.println(str_key);
    Map<Character,Integer> m=new HashMap<Character,Integer>();
    Map<Integer,Character> m2=new HashMap<Integer,Character>();
    char ch='a';
    for(i=0;i<26;i++)
    {
      m.put(ch, i); //char to int
      m2.put(i,ch); //int to char
      ch=(char)((int)ch+1);
    }
    char c1,c2,c3;
    int k;
    //System.out.println(str.length());
    String result="";
    char Vigenere[][]=new char[26][26];
    for(i=0;i<26;i++)
      for(j=0;j<26;j++)
        Vigenere[i][j]=m2.get((i+j)%26);
    for(i=0;i<len;i++)
    {
      c1=str2[i];
      c2=str_key[i];
      for(t=0;t<26;t++)
      {
        if(Vigenere[t][m.get(c2)]==c1)
        {
          //System.out.println("Yes");
          result+=m2.get(t);
          break;
        }
      }
    }
    System.out.println(result);
    //printMat2(Vigenere,26,26);
  }

  public static void main(String[] args) {
    int choice;
    System.out.println("1) Encryption\n2) Decryption\n");
    Scanner sc=new Scanner(System.in);
    while(true)
    {
      choice=sc.nextInt();
      switch(choice)
      {
        case 1: func1();
        break;
        case 2: func2();
        break;
       
        default: System.out.println("Invalid Choice");
        System.exit(0);
    }
    System.out.println();
    }
  }
}
