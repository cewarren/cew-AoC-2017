package com.cew.spiralmemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static final int UP = 0;
	public static void main(String[] args) {
		//int input = 277678;
		int input = 277678;
		int maxVal = 0;
		int maxIndex = 0;
		int distance = 0;
		int[] pos = new int[2];
		int midpoint = 0;
		boolean isCornerFound = false;
		int rslt = 0;
		int i = 1;
		
		while(!isCornerFound) {
			maxVal = i % 2 != 0 ? (int) Math.pow(i, 2) >= input ? (int) Math.pow(i, 2) : 0 : 0;
			if(maxVal > 0) isCornerFound = true; else i++;
		}
		
		maxIndex = (int) Math.sqrt((double) maxVal) - 1;
		distance = maxVal - input;
		midpoint = maxIndex / 2;
		
		rslt = calcManhattanDistance(getCoords(distance, maxIndex), midpoint);
		
		System.out.println("Distance: " + String.valueOf(rslt));
		
	}
	
	private static int[] getCoords(int difference, int size) {
		int[] coordinate = new int[2];
		//bottom right corner is always the point of a perfect square.
		//so the value is defined as row^2 or column^2. the inverse being
		//the sqrt of the value at that index being the size of both dimensions an the 2d array.
		
		
		if(difference <= size) { //if the value diff is less than or equal to the size of the row
			coordinate[0] = size; // x = last row
			coordinate[1] = size - difference; // y = row size - the difference b/w the 2 max and the target
		} else { //if the diff is greater than the row, start going up the first column
			difference -= size;
			
			if(difference <= size) { //if the value diff is less than or equal to the size of the column
				coordinate[0] = size - difference; // x = column size - the difference
				coordinate[1] = 0; // y = first column
			} else { // the diff is greater than the size of the column, start going LTR on the top row
				difference -= size;
				
				if(difference <= size) {
					coordinate[0] = 0;
					coordinate[1] = size - difference;
				} else { // the diff is greater than the size of the row, it's in the last column.
					difference -= size; //the last entry of the column has already been checked
					
					coordinate[0] = size - difference;
					coordinate[1] = size;
				}
			}
		}
		
		return coordinate;
	}
	
	private static int calcManhattanDistance(int[] p, int q) {
		int result = 0;
		
		result = Math.abs(p[0] - q) + Math.abs(p[1] - q);
		
		return result;
	}
	
	private static int calcNextSeq(int input) {
		int rslt = 0;
		List<List<Integer>> spiral = new ArrayList<List<Integer>>();
		
		List<Integer> temp = new ArrayList<Integer>();
		
		temp.
		
		
		
		currRow.add(1);
		currRow.add(1);
		
		
		return rslt;
	}

}
