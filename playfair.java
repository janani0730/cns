import java.util.*;
/**
 *
 * @author Priya
 */
public class Playfair {
    public static Scanner scanner=new Scanner(System.in);
    public static char[][] matrix=new char[5][5];
   
    public static void buildMatrix(String key){
        String no_dup="";
        for(int i=0;i<key.length();i++){
            if(!no_dup.contains(key.charAt(i)+""))
                no_dup+=key.charAt(i);
        }
        String matrix_str=no_dup;
        //System.out.println(no_dup);
        String alphabet="abcdefghiklmnopqrstuvwxyz";
        for(int i=0;i<25;i++){
            if(!matrix_str.contains(alphabet.charAt(i)+""))
                matrix_str+=alphabet.charAt(i);
        }
        int k=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                matrix[i][j]=matrix_str.charAt(k);
                k++;
            }
        }
       
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(matrix[i][j]+" ");
                k++;
            }
            System.out.println("\n");
        }
    }
    public static String formatPlaintext(String plaintext){
        plaintext=plaintext.replace("j", "i").replace(" ","");
        String formatted="";
        int i=0;
        while(i<plaintext.length()-1){
            if(plaintext.charAt(i)==plaintext.charAt(i+1)){
                formatted+=plaintext.charAt(i)+"x";
                i+=1;
            }
            else{
                formatted+=""+plaintext.charAt(i)+plaintext.charAt(i+1);
                i+=2;
            }
        }
        System.out.println(formatted);
        if(i==plaintext.length()-1)
            formatted+=""+plaintext.charAt(plaintext.length()-1);
        if(formatted.length()%2!=0)
            formatted+="x";
        return formatted;
    }
   
    public static int[] findpos(char ch1,char ch2){
        int[] pos=new int[4];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(matrix[i][j]==ch1){
                    pos[0]=i;
                    pos[1]=j;
                }
                if(matrix[i][j]==ch2){
                    pos[2]=i;
                    pos[3]=j;
                }
            }
        }
        return pos;
    }
    public static void encrypt(String plaintext,String key){
        buildMatrix(key);
        String cipherText="";
        String formatted_plaintext=formatPlaintext(plaintext);
        System.out.println(formatted_plaintext);
        int r1,c1,r2,c2;
        for(int i=0;i<formatted_plaintext.length();i+=2){
            int[] pos=findpos(formatted_plaintext.charAt(i),formatted_plaintext.charAt(i+1));
            if(pos[0]==pos[2]){
                r1=pos[0];
                r2=pos[0];
                c1=(pos[1]+1)%5;
                c2=(pos[3]+1)%5;
            }
           
            else if(pos[1]==pos[3]){
                c1=pos[1];
                c2=pos[1];
                r1=(pos[0]+1)%5;
                r2=(pos[2]+1)%5;
            }
           
            else{
                r1=pos[0];
                c1=pos[3];
                r2=pos[2];
                c2=pos[1];
            }
           
            cipherText+=""+matrix[r1][c1]+matrix[r2][c2];
        }
       
        System.out.println("Ciphertext:"+cipherText);
    }
   
   
    public static void decrypt(String ciphertext,String key){
        buildMatrix(key);
        String plaintext="";
       
       
        int r1,c1,r2,c2;
        for(int i=0;i<ciphertext.length();i+=2){
            int[] pos=findpos(ciphertext.charAt(i),ciphertext.charAt(i+1));
            if(pos[0]==pos[2]){
                r1=pos[0];
                r2=pos[0];
                c1=(pos[1]-1+5)%5;
                c2=(pos[3]-1+5)%5;
            }
           
            else if(pos[1]==pos[3]){
                c1=pos[1];
                c2=pos[1];
                r1=(pos[0]-1+5)%5;
                r2=(pos[2]-1+5)%5;
            }
           
            else{
                r1=pos[0];
                c1=pos[3];
                r2=pos[2];
                c2=pos[1];
            }
           
            plaintext+=""+matrix[r1][c1]+matrix[r2][c2];
        }
       
        System.out.println("Plaintext:"+plaintext);
    }
    public static void main(String[] args) {
            System.out.println("ENCRYPTION");
            System.out.println("Enter key:");
            String key=scanner.nextLine();
            System.out.println("Enter plaintext");
            String plaintext=scanner.nextLine();
            encrypt(plaintext,key);
           
            System.out.println("DECRYPTION");
            System.out.println("Enter key:");
            key=scanner.nextLine();
            System.out.println("Enter ciphertext");
            String ciphertext=scanner.nextLine();
            decrypt(ciphertext,key);
       
       
    }
   
}