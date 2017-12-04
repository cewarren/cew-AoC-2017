package com.cewarren.corrupchecksum;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> intMatrix = new ArrayList<ArrayList<Integer>>();
		Path path = Paths.get(URI.create(Main.class.getResource("/resources/input.txt").toString()));
		
		Stream<String> lines = Files.lines(path);
		lines.forEach(line -> data.add(line));
		lines.close();
		
		intMatrix = getIntMatrix(data);
		
		int result = calcChecksum(intMatrix);
		
		System.out.println(String.valueOf(result));

	}
	
	private static ArrayList<ArrayList<Integer>> getIntMatrix(ArrayList<String> data){
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		ArrayList<String> currList = new ArrayList<String>();
		ArrayList<Integer> currIntList = new ArrayList<Integer>();
		
		for(String array : data) {
			//System.out.println("Array string: " + array);
			currList.addAll(Arrays.asList(array.split("\t")));
			//System.out.println("ArrayList: " + currList);
			
			for(String num : currList) {
				//System.out.println("Number: " + num);
				currIntList.add(Integer.parseInt(num));
				//System.out.println("Int Array: " + currIntList.toString());
			}
			
			matrix.add(new ArrayList<Integer>(currIntList));
			//System.out.println("Matrix: " + matrix.toString());
			currList.clear();
			currIntList.clear();
			//System.out.println("Matrix2: " + matrix.toString());
			
		}
		
		return matrix;
	}
	
	private static int calcChecksum(ArrayList<ArrayList<Integer>> matrix) {
		//ArrayList<Integer> currIntList = new ArrayList<Integer>();
		int checksum = 0;

		for(ArrayList<Integer> currRow : matrix) {
			currRow.sort(null);
			checksum += (currRow.get(currRow.size() - 1) - currRow.get(0));
			
		}
		
		return checksum;
	}
	

}
