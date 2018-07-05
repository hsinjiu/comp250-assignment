package assignment1;

public class Message {
	
	public String message;
	public int lengthOfMessage;

	public Message (String m){
		message = m;
		lengthOfMessage = m.length();
		this.makeValid();
	}
	
	public Message (String m, boolean b){
		message = m;
		lengthOfMessage = m.length();
	}
	
	/**
	 * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
	 */
	public void makeValid(){
		//INSERT YOUR CODE HERE
		String messagebuffer="";
		message=message.toLowerCase();
		
		for(int i=0;i<this.lengthOfMessage;i++) {
			 if(message.charAt(i)<= 'z') {
				 if(message.charAt(i)>= 'a'){
					messagebuffer = messagebuffer + (char)(message.charAt(i));}}
		}
	    message= messagebuffer;
		  this.lengthOfMessage=messagebuffer.length();
	}
	
	/**
	 * prints the string message
	 */
	public void print(){
		System.out.println(message);
	}
	
	/**
	 * tests if two Messages are equal
	 */
	public boolean equals(Message m){
		if (message.equals(m.message) && lengthOfMessage == m.lengthOfMessage){
			return true;
		}
		return false;
	}
	
	/**
	 * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
	 * @param key
	 */
	public void caesarCipher(int key){
		// INSERT YOUR CODE HERE
		String buffer = ""; char chBuffer ='\0';
		for (int i = 0; i < this.lengthOfMessage; i++) {
				int keynew = key%26;
						while(message.charAt(i)+ keynew> 'z'){
								keynew= keynew - 26;
						}
						while(message.charAt(i)+ keynew< 'a'){
								keynew = keynew + 26;
						}
						chBuffer = (char)(message.charAt(i)+ keynew);
						buffer = buffer +chBuffer; 
	}
		message = buffer;
	}

	public void caesarDecipher(int key){
		this.caesarCipher(- key);
	}
	
	/**
	 * caesarAnalysis breaks the Caesar cipher
	 * you will implement the following algorithm :
	 * - compute how often each letter appear in the message
	 * - compute a shift (key) such that the letter that happens the most was originally an 'e'
	 * - decipher the message using the key you have just computed
	 */
	public void caesarAnalysis(){
		// INSERT YOUR CODE HERE
		int highestFreq = 0;
		char mostFreqChar = ' ';
		for (int i = 0; i < this.lengthOfMessage; i++)
		  {
	
			  char x = message.charAt(i);
			  int c = 0;
			  for (int j = message.indexOf(x); j != -1; j = message.indexOf(x, j + 1))
			  {
				  c++;
			  }
			  if (c > highestFreq)
			  {
				  highestFreq = c;
				  mostFreqChar = x;
			  }
		  }
		int indexMoved = mostFreqChar - 'e'; 
		//get back the character
		String buffer = "";
		char chBuffer ='\0';
		for (int i = 0; i < this.lengthOfMessage; i++) {
				int keynew = (indexMoved%26)*(-1);
						while(message.charAt(i)+ keynew>122){
												keynew= keynew - 26;
								}
						while(message.charAt(i)+ keynew<97){
												keynew = keynew + 26;
								}
								chBuffer = (char)(message.charAt(i)+ keynew);
								buffer = buffer +chBuffer; 
		}
		  message = buffer;
		 System.out.print(message);  
	}
	
	/**
	 * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
	 * @param key
	 */
	public void vigenereCipher (int[] key){
		// INSERT YOUR CODE HERE
        String buffer = ""; char chBuffer ='\0';
        for(int j = 0; j <this.lengthOfMessage; j++ ){
                int keyindex = j% key.length; 
                int keynew = key[keyindex]%26;
                while(message.charAt(j)+ keynew>'z'){
                        keynew= keynew - 26;
                }
                while(message.charAt(j)+ keynew<'a'){
                        keynew = keynew + 26;
                }
                chBuffer = (char)(message.charAt(j)+ keynew);
                buffer = buffer +chBuffer; 
        }
        message = buffer; 
	}

	/**
	 * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
	 * @param key
	 */
	public void vigenereDecipher (int[] key){
		// INSERT YOUR CODE HERE
		String buffer = ""; char chBuffer ='\0';
		for(int j = 0; j <this.lengthOfMessage; j++ ){ //this.lengthOfMessage
				int keyindex = j% key.length; 
				int keynew = (key[keyindex]%26)*(-1);
				while(message.charAt(j)+ keynew>'z'){
						keynew= keynew - 26;
				}
				while(message.charAt(j)+ keynew<'a'){
						keynew = keynew + 26;
				}
				chBuffer = (char)(message.charAt(j)+ keynew);
				buffer = buffer +chBuffer; 
		}
		message = buffer; 
	}
	
	/**
	 * transpositionCipher performs the transition cipher on the message by reorganizing the letters and eventually adding characters
	 * @param key
	 */
	
	//helper Method for trans Cipher.//
	//want to calculate # of rows needed for the trans matrix//
	private int calculateRow(int key) {
		int row;
		if(this.lengthOfMessage%key==0) {
			row=this.lengthOfMessage/key;
		}else {
			row=(this.lengthOfMessage/key)+1;
		}
		return row;
	}
	
	public void transpositionCipher (int key){
		// INSERT YOUR CODE HERE
		String messagebuufer="";
		int row; int i = 0;
		row = calculateRow(key);
		char[][] arrange = new char[row][key]; 
		

		for (int rowcount = 0; rowcount < row; rowcount++) {
			for (int columncount = 0; columncount < key; columncount++) {
				if (i < this.lengthOfMessage) { //if index smaller than msg keep filling in 
					arrange[rowcount][columncount] = message.charAt(i);
					i++;
				} 
				else {
					arrange[rowcount][columncount] = '*';
					}
				}
			}
		for(int forw=0; forw<key; forw++) { //read forward
				for(int downward=0; downward<row; downward ++) {       //read downward first 
					messagebuufer = messagebuufer +arrange[downward][forw];
				}
			}
		message = messagebuufer;
		this.lengthOfMessage=message.length();
	}
	
	/**
	 * transpositionDecipher deciphers the message given the 'key'  according to the transition cipher.
	 * @param key
	 */
	public void transpositionDecipher (int key){
		// INSERT YOUR CODE HERE
		String messagebuufer="";
		int row; 
		int i =0; 
		row = calculateRow(key);
		char[][] arrange = new char[row][key]; 
		//place back to matrix 
		for (int columncount = 0; columncount < key; columncount++) {
				for (int rowcount = 0; rowcount < row; rowcount++) {
					arrange[rowcount][columncount] = message.charAt(i);
					i++;
			} 

		}

		
		
		//read the matrix
		for (int rowcount = 0; rowcount < row; rowcount++) {
		for (int columncount = 0; columncount < key; columncount++) {
		if (arrange[rowcount][columncount] != '*') { //if index smaller than msg extract msg 
			messagebuufer = messagebuufer + arrange[rowcount][columncount];
		} 
		}
	}
	message = messagebuufer;
	this.lengthOfMessage=message.length();
		
	}
	
}
