import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.*;
/**
 *
 * @author 4041
 */
public class HillCipher {

    /**
     * @param args the command line arguments
     */
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
   
     static void func1() //Hill cipher encryption
  {
    String str,key;
    int i,j,t;
    int temp;
    System.out.println("Enter the text: "); //attack
    Scanner sc=new Scanner(System.in);
    str=sc.nextLine();
     int len=str.length();
    if(len%3==1)
    {
     str+="xx";
    }
    if(len%3==2)
    {
     str+="x";
    }
    char[] str2=str.toCharArray();
    System.out.println("Enter the key matrix: "); // 2 4 5 9 2 1 3 17 7
    int[][] a=new int[3][3];

    for(i=0;i<3;i++) for(j=0;j<3;j++) a[i][j]=sc.nextInt();
    //printMat(a,3,3);

    Map<Character,Integer> m=new HashMap<Character,Integer>();
    Map<Integer,Character> m2=new HashMap<Integer,Character>();
    char ch='a';
    for(i=0;i<26;i++)
    {
      m.put(ch, i);
      m2.put(i,ch);
      ch=(char)((int)ch+1);
    }
    char c1,c2,c3;
    int k;
    //System.out.println(str.length());
     for(i=0;i<=str.length()+len%3-3;i+=3)
    {
      k=i;
      int[][] mat=new int[3][1];
      int[][] result=new int[3][1];
      c1=str2[i];
      c2=str2[i+1];
      c3=str2[i+2];
      mat[0][0]=m.get(c1);
      mat[1][0]=m.get(c2);
      mat[2][0]=m.get(c3);
      result=mmul(a,mat);
      for(t=0;t<3;t++)
        {
          result[t][0]=result[t][0]%26;
          //str2[k++]=m2.get(result[t][0]);
          //System.out.println(result[t][0]);
        }

      str2[i]=m2.get(result[0][0]);
      str2[i+1]=m2.get(result[1][0]);
      str2[i+2]=m2.get(result[2][0]);
    }
    System.out.println(str2);
     
   
  }


  static void func2() //Hill cipher decryption
  {
    String str,key;
    int i,j,t;
    int temp;
    int ran;
    System.out.println("Enter the text: "); //pfogoa
    Scanner sc=new Scanner(System.in);
    str=sc.nextLine();
    int len=str.length();
    char[] str2=str.toCharArray();
    System.out.println("Enter the key matrix: "); // 2 4 5 9 2 1 3 17 7
    int[][] a=new int[3][3];
    //int [][] a={{2,4,5},{9,2,1},{3,17,7}};
    int i2;
    for(i=0;i<3;i++) for(j=0;j<3;j++) a[i][j]=sc.nextInt();
    //printMat(a,3,3);

    Map<Character,Integer> m=new HashMap<Character,Integer>();
    Map<Integer,Character> m2=new HashMap<Integer,Character>();
    char ch='a';
    int t1,t2;
    for(i=0;i<26;i++)
    {
      m.put(ch, i);
      m2.put(i,ch);
      ch=(char)((int)ch+1);
    }
    char c1,c2,c3;
    int k,det=0;
    for(i=0;i<=str.length()+len%3-3;i+=3)
    {
      k=i;
      int[][] mat=new int[3][1];
      int[][] result=new int[3][1];
      c1=str2[i];
      c2=str2[i+1];
      c3=str2[i+2];
      mat[0][0]=m.get(c1);
      mat[1][0]=m.get(c2);
      mat[2][0]=m.get(c3);
      result=mmul(minv(a),mat);
      for(t=0;t<3;t++)
        {
          //System.out.print(result[t][0]+" ");
          result[t][0]=result[t][0]%26; //after multiplication again % 26
          if(result[t][0]<0) //modulo of negative number
          {
            //System.out.print("Inside for ");
            //System.out.println(result[t][0]);
            for(ran=0; ;++ran)
            {
              if(ran*26>(-result[t][0]))
              {
                result[t][0]=ran*26+result[t][0];
                break;
              }
            }
          }
          str2[k++]=m2.get(result[t][0]);
          //System.out.print(result[t][0]+" ");
        }


      str2[i]=m2.get(result[0][0]);
      str2[i+1]=m2.get(result[1][0]);
      if(m2.get(result[1][0])=='x' && i==str.length()+len%3-3)
        str2[i+1]=' ';
      str2[i+2]=m2.get(result[2][0]);
      if(m2.get(result[2][0])=='x' && i==str.length()+len%3-3)
        str2[i+2]=' ';
    }
    //printMat(minv(a));

    System.out.println(str2);
     
   
  }
     public static void main(String[] args) {
    int choice;
    System.out.println("1)Encryption\n2) Decryption\n\n");
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
