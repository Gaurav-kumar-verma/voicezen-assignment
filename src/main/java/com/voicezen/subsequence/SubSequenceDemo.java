package com.voicezen.subsequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SubSequenceDemo {

	public static void main(String[] args) {

		String str = "";
		//String str = CharacterUtils.generateRandomCharacter();
		long startTime =  System.currentTimeMillis();
		System.out.println("Processing String : " + str);
		withoutIteration(str);
		System.out.println("Total time : " + (System.currentTimeMillis() -  startTime) / 1000 + "Sec");
	
		
	
	}
	
	public static void withoutIteration(String str){
			int len = str.length();
			
			if(len == 0) {
				System.out.println("You enter blank String");
				return;
			}
			
	        ArrayList<String> subSequences =  new ArrayList<String>();
	        Map<String,Integer> map =  new HashMap<>();
	        int iteration = 0;
	        int endIndex = 0;
	        
	        
	        ///////////// process String in chunks (1000 characters) and create a map of String and frequency //////////////////////////////
	        do {
	        	int startingPoint = iteration*1000;
	        	endIndex = startingPoint+999;
	        	if(endIndex >= len)
	        		endIndex = len;
		        String strToBeProcessed = str.substring(startingPoint,endIndex);
	        	int currLen = strToBeProcessed.length(); 
		        for(int i = 0; i < currLen; i++) {  
		            //This loop adds the next character every iteration for the subset to form and add it to the array  
		            for(int j = i; j < currLen; j++) {  
		            	String sub = str.substring(i, j+1);
		                subSequences.add(sub);
		                Integer freq = map.get(sub);
		                map.put(sub, freq != null ? freq + 1 : 1);
		            }  
		        }  
		        iteration++;
	        } while(len > endIndex);
	  
	        System.out.println("All subsets for given string are: " + subSequences.size());   
	        
	        /////////// Sort that map based on frequency in descending order  //////////////////////////////
	        
	        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
	        Collections.sort(list , (o1,o2) -> 
	        {
	        	Map.Entry<String, Integer>e1 = (Map.Entry<String, Integer>)o1;
	        	Map.Entry<String, Integer>e2 = (Map.Entry<String, Integer>)o2;
	        	if(e2.getValue() == e1.getValue()) {
					return e1.getKey().compareTo(e2.getKey());
				}
				return e2.getValue().compareTo(e1.getValue());
	        	
	        });
	        
	        // It will give sorted map based on values in descending order
	        System.out.println("element : " + list.get(0).getKey() + " with frequency : "+ list.get(0).getValue());
	        
	        double score = calculateScore(list.get(0));
	        System.out.println("score : " + score);
	}
	
	private static double calculateScore(Entry<String, Integer> entry) {
		
		if(entry == null) {
			return 0;
		}
		
		return Math.pow(entry.getKey().length(), 2) * Math.pow((entry.getValue() - 1), 0.33);
		
	}

	
	///////////////////////// It's not used in current program , taking too much time/////////////////////
	
	@Deprecated
	public static HashSet<String> getSequence(String sub) {

		System.out.println("processing : " + sub);
		HashSet<String> result =  new HashSet<String>();
		if (sub.length() == 0) {
			result.add(sub);
			return result;
		}

		// First character of sub
		char ch = sub.charAt(0);

		String substring = sub.substring(1);

		// Excluding first character
		System.out.println("processing again with subString : " + sub);
		HashSet<String> subSequences = getSequence(substring);
		
        for (String val : subSequences) { 
        		result.add(val); 
        		result.add(ch + val); 
        } 
		
		return result;
	}
}


//  k e ek e ek ee eek g gk ge gek ge gek gee geek 