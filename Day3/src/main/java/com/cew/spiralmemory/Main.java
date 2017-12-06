package com.cew.spiralmemory;

public class Main {

	public static final int UP = 0;
	public static void main(String[] args) {
		//int input = 277678;
		int input = 1024;
		int indexSize = (int) Math.ceil(Math.sqrt((double) input)) - 1; 
		int tblMaxVal = (int) Math.pow(Math.ceil(Math.sqrt((double) input)), 2);
		int midpoint = indexSize / 2;
		int inputMaxValDiff = tblMaxVal - input;
		int[] destination = {midpoint, midpoint};
		
		int distance = calcManhattanDistance(getCoords(inputMaxValDiff, indexSize), destination);
		
		System.out.println(String.valueOf(distance));
		
	}
	
	private static int[] getCoords(int difference, int size) {
		int[] coordinate = new int[2];
		//bottom right corner is always the point of a perfect square.
		//so the value is defined as row^2 or column^2. the inverse being
		//the sqrt of the value at that index being the size of both dimensions an the 2d array.
		
		System.out.println("Difference: " + String.valueOf(difference) + " Size: " + String.valueOf(size));
		
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
	
	private static int calcManhattanDistance(int[] p, int[] q) {
		int result = 0;
		
		System.out.println("Start: [" + String.valueOf(p[0])+ ", " + String.valueOf(p[1]) +
		"] End: [" + String.valueOf(q[0]) + ", " + String.valueOf(q[1]) + "]");
		
		result = Math.abs(p[0] - q[0]) + Math.abs(p[1] - q[1]);
		
		return result;
	}

}
