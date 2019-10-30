import java.util.Scanner;

class rail_fence{
  public static void main(String [] args){
    Scanner sc = new Scanner(System.in);
    String plaintext = new String();
    String encrypted_text = new String();
    String decrypted_text = new String();
    int flag = 0, index = 0, i, j=0;
    System.out.print("Enter Key: ");
    int key = sc.nextInt();
    System.out.print("Enter plain text: ");
    plaintext = sc.next();
    char rail_fence_matrix[][] = new char[key][plaintext.length()];

    for(i=0; i<key; ++i){
      for(j=0; j<plaintext.length(); ++j){
        rail_fence_matrix[i][j] = ' ';
      }
    }
    while(index < plaintext.length()){
      for(i=0 ; i<key; ++i){
       if(index < plaintext.length()){
          rail_fence_matrix[i][index] = plaintext.charAt(index);
          // System.out.println(rail_fence_matrix[i][index] + " " + i + " "+ index);
          ++index;
        }
      } //System.out.println(i);
      for( i = i-2; i>0; --i){
       if(index < plaintext.length()){
          rail_fence_matrix[i][index] = plaintext.charAt(index);
          // System.out.println(rail_fence_matrix[i][index] + " " + i + " "+ index);
          ++index;
        }
      }
    }
    for(i=0; i<key; ++i){
      for(j=0; j<plaintext.length(); ++j){
        System.out.print(rail_fence_matrix[i][j]);
      }
      System.out.println();
    }

    //Encryption
    for(i=0; i<key; ++i){
      for(j=0; j<plaintext.length(); ++j){
        if(rail_fence_matrix[i][j] != ' '){
          encrypted_text += rail_fence_matrix[i][j];
        }
      }
    }

    System.out.println("Encrypted Text: "+ encrypted_text);

    //Decryption
    char decrypted_matrix[][] =new char[key][encrypted_text.length()];
    for(i=0; i<key; ++i){
      for(j=0; j<plaintext.length(); ++j){
        decrypted_matrix[i][j] = ' ';
      }
    }


    //index=0;
    j=0;
    //Table formation
    while(j < encrypted_text.length()){
      for(i=0 ; i<key; ++i){
        if(j < encrypted_text.length()){
          decrypted_matrix[i][j] = '-'; 
          // System.out.println(rail_fence_matrix[i][index] + " " + i + " "+ index);
         // ++index;
          ++j;
        }
      }
      for( i = i-2; i>0; --i){
        if(j < encrypted_text.length()){
          decrypted_matrix[i][j] = '-'; 
          // System.out.println(rail_fence_matrix[i][index] + " " + i + " "+ index);
         // ++index;
          ++j;
        }
      }
    }

    index = 0;
    for(i =0; i<key; ++i){
      for(j=0; j<encrypted_text.length(); ++j){
        if(decrypted_matrix[i][j] == '-'){
          decrypted_matrix[i][j] = encrypted_text.charAt(index);
          ++index;
        }
      }
    }

    for(i=0; i<key; ++i){
      for(j=0; j<plaintext.length(); ++j){
        System.out.print(decrypted_matrix[i][j]);
      }
      System.out.println();
    }

    // int length = 0;
    // while(length < encrypted_text.length()){
    //   for(i=0; i<key; ++i){
    //     if((length < encrypted_text.length()))
    //       if(decrypted_matrix[i][length] != ' '){
    //         decrypted_text += decrypted_matrix[i][length];
    //         ++length;
    //       }
    //   }
    // }

  String d = "";
      for(j=0; j<plaintext.length(); ++j){
      	for(i=0; i<key; ++i){
      		if(decrypted_matrix[i][j]!=' ')
      			d+=decrypted_matrix[i][j];
       // System.out.print(decrypted_matrix[i][j]);
      }
      //System.out.println();
    }


    //System.out.println("Decrypted Text: "+ decrypted_text);
    System.out.println("Decrypted Text: "+ d);


  }
}