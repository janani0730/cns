import java.io.*;
import java.lang.*;
import java.util.*;

public class Ex3 {

  static void printMat(char[][] mat,int m,int n)
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

  static void func1() //Rail cipher encryption
  {
    String str;
    int key;
    int i,j,t;
    int temp;
    System.out.println("Enter the text: "); //
    Scanner sc=new Scanner(System.in);
    str=sc.nextLine();
    int len=str.length();
    char[] str2=str.toCharArray();
    System.out.println("Enter the key(Number of Levels): "); //
    key=sc.nextInt();
    String result="";
    int cols=0;
    if(len%key==0)
    {
      cols=len/key;
    }
    else
    {
      cols=len/key+1;
    }
    // System.out.println(key+" "+cols);
    // System.out.println();
    char[][] res=new char[cols][key];
    int counter=0;

    for(i=0;i<cols;i++)
    {
      for(j=0;j<key;j++)
      {
        //System.out.println(i+" "+j+" "+counter);
        if(counter==len)
          break;
        res[i][j]=str2[counter];
        ++counter;
      }
    }
    char res2[][]=new char[key][cols];
    for(i=0;i<key;i++) for(j=0;j<cols;j++) res2[i][j]=res[j][i];
   
    // System.out.println();
    // printMat(res,cols,key);
    // System.out.println();
    printMat(res2,key,cols);
    System.out.println();
    for(i=0;i<key;i++)
      for(j=0;j<cols;j++)
        result+=res2[i][j];
    System.out.println(result);  
  }


  static void func2() //Rail cipher decryption
  {
    String str;
    int key;
    int i,j,t;
    int temp;
    System.out.println("Enter the Cipher text: "); //
    Scanner sc=new Scanner(System.in);
    str=sc.nextLine();
    int len=str.length();
    char[] str2=str.toCharArray();
    System.out.println("Enter the key(Number of Levels): "); //
    key=sc.nextInt();
    String result="";
    int cols=key;
    int rows=0;
    int counter=0;
    if(len%key==0)
    {
      rows=len/key;
    }
    else
    {
      rows=len/key+1;
    }
     char[][] res=new char[cols][rows];
    // System.out.println(key+" "+cols);
    // System.out.println();
    for(i=0;i<cols;i++)
    {
      for(j=0;j<rows;j++)
      {
        //System.out.println(i+" "+j+" "+counter);
        if(counter==len)
          break;
        res[i][j]=str2[counter];
        ++counter;
      }
    }
    char res2[][]=new char[rows][cols];
    for(i=0;i<rows;i++) for(j=0;j<cols;j++) res2[i][j]=res[j][i];
   
    // System.out.println();
    // printMat(res,cols,key);
    // System.out.println();
    printMat(res,cols,rows);
    System.out.println();
    for(i=0;i<rows;i++)
      for(j=0;j<cols;j++)
        result+=res2[i][j];
    System.out.println(result);  
  }

  static void func3() //Row-Column cipher encryption
  {
    String str, key;
    int i,j,t;
    int temp;
    System.out.println("Enter the text: "); // attackpostponeduntiltwoam
    Scanner sc=new Scanner(System.in);
    str=sc.nextLine();
    int len=str.length();
    char[] str2=str.toCharArray();
    System.out.println("Enter the key: "); // 2413756
    key=sc.next();
    int len_key=key.length();
    char[] key_arr=key.toCharArray();
    int[] key_arr2=new int[len_key];
    //int sum=0;
    for(i=0;i<len_key;i++)
    {
      key_arr2[i]=key_arr[i]-'0';
      //sum+=key_arr2[i];
    }
    //System.out.println(sum);
    int rows,cols;
    cols=len_key;
    if(len%cols==0)
      rows=len/cols;
    else
      rows=len/cols+1;
    char[][] matrix=new char[rows][cols];
    int len2=len;
    t=0;
    for(i=0;i<rows;i++)
    {
      for(j=0;j<cols;j++)
      {
        if(len2>0)
          {
            matrix[i][j]=str2[t];
            ++t;
            --len2;
          }
        else
        {
          matrix[i][j]='x';
        }
      }
    }

    printMat(matrix,rows,cols);
    System.out.println();
    int[] order=new int[len_key];
    int find=1;
    for(i=0;i<len_key;i++)
    {
      for(j=0;j<len_key;j++)
      {
        if(key_arr2[j]==find)
        {
          order[i]=j;
          ++find;
          break;
        }
      }
    }

    // for(i=0;i<len_key;i++)
    //   System.out.println(order[i]+" ");
    System.out.println("Encrypted text is: "); // ttnaaodwaptmtsuoknlxpetxcoix
    for(i=0;i<len_key;i++)
    {
      for(j=0;j<rows;j++)
        System.out.print(matrix[j][order[i]]);
    }
    System.out.println();


  }

  static void func4() //Row-Column cipher decryption
  {
    String str, key;
    int i,j,t;
    int temp;
    System.out.println("Enter the text: "); // ttnaaodwaptmtsuoknlxpetxcoix
    Scanner sc=new Scanner(System.in);
    str=sc.nextLine();
    int len=str.length();
    char[] str2=str.toCharArray();
    System.out.println("Enter the key: "); // 2413756
    key=sc.next();
    int len_key=key.length();
    char[] key_arr=key.toCharArray();
    int[] key_arr2=new int[len_key];
    //int sum=0;
    for(i=0;i<len_key;i++)
    {
      key_arr2[i]=key_arr[i]-'0';
      //sum+=key_arr2[i];
    }
    //System.out.println(sum);
    int rows,cols;
    cols=len_key;
    if(len%cols==0)
      rows=len/cols;
    else
      rows=len/cols+1;
    char[][] matrix=new char[rows][cols];
    int len2=len;
    int[] order=new int[len_key];
    int find=1;
    for(i=0;i<len_key;i++)
    {
      for(j=0;j<len_key;j++)
      {
        if(key_arr2[j]==find)
        {
          order[i]=j;
          ++find;
          break;
        }
      }
    }

    t=0;
    for(i=0;i<len_key;i++)
    {
      for(j=0;j<rows;j++)
      {
        matrix[j][order[i]]=str2[t];
        ++t;
      }
    }

    char[] result=new char[len];
    //printMat(matrix,rows,cols);
    t=0;
    for(i=0;i<rows;i++)
      for(j=0;j<cols;j++)
        if(matrix[i][j]!='x')
          result[t++]=matrix[i][j];
     
    System.out.println(result);
  }


  public static void main(String[] args) {
    int choice;
    System.out.println("1)Rail Cipher Encryption\n2)Rail Cipher Decryption\n3)Row-Column Cipher Encryption\n4)Row-Column Cipher Decryption\n");
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
        case 3: func3();
        break;
        case 4: func4();
        break;
        default: System.out.println("Invalid Choice");
        System.exit(0);
    }
    System.out.println();
    }
  }
}
