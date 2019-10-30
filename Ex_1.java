import java.io.*;
import java.lang.*;
import java.util.*;
public class Ex_1 {
static void func1() //Caesar Encryption
{
//System.out.println("Hello World");
String str;
int key,i;
int temp;
System.out.println("Enter the text: ");
Scanner sc=new Scanner(System.in);
str=sc.nextLine();
char[] str2=str.toCharArray();
System.out.println("Enter the key value: ");
key=sc.nextInt();
for(i=0;i<str.length();i++)
{
if(Character.isUpperCase(str2[i]))
{
temp=(int)str2[i]+key;
if(temp>90)
temp=(temp%91)+65;
str2[i]=(char)temp;}
else if(Character.isLowerCase(str2[i]))
{
temp=(int)str2[i]+key;
if(temp>122)
temp=(temp%123)+97;
str2[i]=(char)temp;
}
}
System.out.println(str2);
}
static void func2() //Caesar Decryption
{
//System.out.println("Hello World");
String str;
int key,i;
int temp;
System.out.println("Enter the text: ");
Scanner sc=new Scanner(System.in);
str=sc.nextLine();
char[] str2=str.toCharArray();
System.out.println("Enter the key value: ");
key=sc.nextInt();
for(i=0;i<str.length();i++)
{if(Character.isUpperCase(str2[i]))
{
temp=(int)str2[i]-key;
if(temp<65)
temp=91-(65-temp);
str2[i]=(char)temp;
}
else if(Character.isLowerCase(str2[i]))
{
temp=(int)str2[i]-key;
if(temp<97)
temp=123-(97-temp);
str2[i]=(char)temp;
}
}
System.out.println(str2);
}
static void func3() //Playfair Encryption
{
String str,key;
int i,j;
int temp;
System.out.println("Enter the text: ");
Scanner sc=new Scanner(System.in);
str=sc.nextLine();if(str.length()%2!=0)
str+="x";
//str="hammer";
char[] str2=str.toCharArray();
System.out.println("Enter the key value: ");
key=sc.nextLine();
//key="monarchy";
int len=key.length();
int len2=0;
char[] key_arr=key.toCharArray();
char[] new_key=new char[100];
Map<Character,Integer> m2=new HashMap<Character,Integer>();
Map<Character,Integer> m=new HashMap<Character,Integer>();
char ch='a';
for(i=0;i<26;i++)
{
m2.put(ch, 0);
m.put(ch,0);
ch=(char)((int)ch+1);
}
int count_var=0;
for(i=0;i<len;i++)
{
if(m2.get(key_arr[i])==0)
{
new_key[count_var]=key_arr[i];
++count_var;
m2.put(key_arr[i],1);}
}
char[] new_key2=new char[count_var];
for(i=0;i<count_var;i++)
new_key2[i]=new_key[i];
String strr=new String(new_key2);
//len=strr.length();
//System.out.println(strr);
len=strr.length();
// len=count_var;
//System.out.println(len);
len2=0;
char[][] a=new char[5][5];
for(i=0;i<5;i++)for(j=0;j<5;j++)a[i][j]=' ';
for(i=0;i<5;i++)
{
for(j=0;j<5;j++)
{
if(len<=0)
break;
a[i][j]=new_key2[len2];
m.put(new_key2[len2], 1);
++len2;
--len;
}
}char c='a';
//System.out.println(m.get('a'));
for(i=0;i<5;i++)
{
for(j=0;j<5;j++)
{
if(a[i][j]==' ')
{
while (m.get(c)==1)
{
c=(char)((int)c+1);
}
if(c=='j')
c=(char)((int)c+1);
a[i][j]=c;
c=(char)((int)c+1);
}
}
}
m.clear();
char char1,char2;
int r1,r2,c1,c2;
r1=c1=r2=c2=0;
int k;
System.out.println();for(i=0;i<5;i++)
{
for(j=0;j<5;j++)
{
System.out.print(a[i][j]+" ");
}
System.out.println();
}
for(k=0;k<str.length();k+=2)
{
char1=str2[k];
char2=str2[k+1];
if(char1=='j')
char1='i';
if(char2=='j')
char2='i';
if(char1==char2)
{
if(char1=='x')
char2='z';
else
char2='x';
}
for(i=0;i<5;i++)
{
for(j=0;j<5;j++){
if(a[i][j]==char1)
{
r1=i;
c1=j;
}
if(a[i][j]==char2)
{
r2=i;
c2=j;
}
}
}
if(r1==r2)
{
str2[k]=a[r1][(c1+1)%5];
str2[k+1]=a[r2][(c2+1)%5];
}
else if(c1==c2)
{
str2[k]=a[(r1+1)%5][c1];
str2[k+1]=a[(r2+1)%5][c2];
}
else
{
str2[k]=a[r1][c2];
str2[k+1]=a[r2][c1];
}}
System.out.println();
System.out.print("The encrypted code is: ");
System.out.println(str2);
}
static void func4() //Playfair Decryption
{
String str,key;
int i,j;
int temp;
System.out.println("Enter the text: ");
Scanner sc=new Scanner(System.in);
str=sc.nextLine();
//str="hammer";
char[] str2=str.toCharArray();
System.out.println("Enter the key value: ");
key=sc.nextLine();
//key="monarchy";
int len=key.length();
int len2=0;
char[] key_arr=key.toCharArray();
char[] new_key=new char[100];
Map<Character,Integer> m2=new HashMap<Character,Integer>();
Map<Character,Integer> m=new HashMap<Character,Integer>();
char ch='a';for(i=0;i<26;i++)
{
m2.put(ch, 0);
m.put(ch,0);
ch=(char)((int)ch+1);
}
int count_var=0;
for(i=0;i<len;i++)
{
if(m2.get(key_arr[i])==0)
{
new_key[count_var]=key_arr[i];
++count_var;
m2.put(key_arr[i],1);
}
}
char[] new_key2=new char[count_var];
for(i=0;i<count_var;i++)
new_key2[i]=new_key[i];
String strr=new String(new_key2);
//len=strr.length();
//System.out.println(strr);
len=strr.length();
// len=count_var;
//System.out.println(len);
len2=0;
char[][] a=new char[5][5];
for(i=0;i<5;i++)for(j=0;j<5;j++)a[i][j]=' ';for(i=0;i<5;i++)
{
for(j=0;j<5;j++)
{
if(len<=0)
break;
a[i][j]=new_key2[len2];
m.put(new_key2[len2], 1);
++len2;
--len;
}
}
char c='a';
//System.out.println(m.get('a'));
for(i=0;i<5;i++)
{
for(j=0;j<5;j++)
{
if(a[i][j]==' ')
{
while (m.get(c)==1)
{
c=(char)((int)c+1);
}
if(c=='j')c=(char)((int)c+1);
a[i][j]=c;
c=(char)((int)c+1);
}
}
}
m.clear();
char char1,char2;
int r1,r2,c1,c2;
r1=c1=r2=c2=0;
int k;
System.out.println();
for(i=0;i<5;i++)
{
for(j=0;j<5;j++)
{
System.out.print(a[i][j]+" ");
}
System.out.println();
}
for(k=0;k<str.length();k+=2)
{char1=str2[k];
char2=str2[k+1];
if(char1=='j')
char1='i';
if(char2=='j')
char2='i';
if(char1==char2)
{
if(char1=='x')
char2='z';
else
char2='x';
}
for(i=0;i<5;i++)
{
for(j=0;j<5;j++)
{
if(a[i][j]==char1)
{
r1=i;
c1=j;
}
if(a[i][j]==char2)
{
r2=i;
c2=j;
}}
}
if(r1==r2)
{
if(c1==0)
{str2[k]=a[r1][4];}
else
{str2[k]=a[r1][(c1-1)%5];}
if(c2==0)
{str2[k+1]=a[r1][4];}
else
{str2[k+1]=a[r1][(c2-1)%5];}
}
else if(c1==c2)
{
if(r1==0)
{str2[k]=a[4][c1];}
else
{str2[k]=a[(r1-1)%5][c1];}
if(r2==0)
{str2[k+1]=a[4][c1];}
else
{str2[k+1]=a[(r2-1)%5][c2];}
}
else
{
str2[k]=a[r1][c2];
str2[k+1]=a[r2][c1];}
if(str2[k+1]=='x')
{
if(k+1==str.length()-1)
{str2[k+1]='\0';}
else
str2[k+1]=str2[k];
}
}
System.out.println();
System.out.print("The decrypted code is: ");
System.out.println(str2);
}
public static void main(String[] args) {
int choice;
System.out.println("1)Caesar Cipher Encryption\n2)Caesar Cipher Decryption\n3)Playfair Cipher Encryption\n4)Playfair Cipher Decryption\n");
Scanner sc=new Scanner(System.in);
while(true)
{
choice=sc.nextInt();
switch(choice)
{
case 1: func1();break;
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

