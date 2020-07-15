package com.voicezen.subsequence;

import java.util.Random;

public class CharacterUtils {

	public static String generateRandomCharacter(){
		
		Random r = null;
		char c ;
		StringBuffer sb =  new StringBuffer();
		for(int i=0; i <3000 ; i++){
			r = new Random();
			c = (char)(r.nextInt(26) + 'a');
			sb.append(c);
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {

		System.out.println(generateRandomCharacter());
	}
	
}
