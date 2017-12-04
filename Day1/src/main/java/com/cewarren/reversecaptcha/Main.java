package com.cewarren.reversecaptcha;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.analysis.function.Log;

public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> accessCode = new ArrayList<Integer>();
	    int result = 0;
	    
	    String inputFile = new String(
	    		Files.readAllBytes(
	    				Paths.get(
	    						URI.create(Main.class.getResource("/resources/input.txt").toString()))));
	    
	    
	    ArrayList<String> strArray = new ArrayList<String>(Arrays.asList(inputFile.split("")));
	    strArray.removeAll(Arrays.asList(null, "\n"));
	    
	    System.out.println("String Array: \n" + strArray);
	    
	    accessCode = getIntegerArray(strArray);
	    
	    System.out.println("Int Array: \n" + accessCode.toString());
	    result = getSum(accessCode);
	    
	    System.out.println("Result: \n" + String.valueOf(result));
	    
	    
	}
	
	private static ArrayList<Integer> getIntegerArray(ArrayList<String> strArray){
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		for(String num : strArray) {
			try {
				intArray.add(Integer.parseInt(num));
			}catch(NumberFormatException e) {
				System.out.println("NumberFormat: " + num + "\n" + e.getMessage());
			}
		}
		
		return intArray;
	}
	
	private static int getSum(ArrayList<Integer> intArray) {
		int sum = 0;
		
		for(int i = 0; i < intArray.size(); i++) {
			int currVal = intArray.get(i);
			
			if(i == (intArray.size() - 1)) {
				if(currVal == intArray.get(0)) {
					sum += currVal;
				}
			} else {
				if(currVal == intArray.get(i+1)) {
					sum += currVal;
				}
			}
		}
		
		return sum;
	}
}
