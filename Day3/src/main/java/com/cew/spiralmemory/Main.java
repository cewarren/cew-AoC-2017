package com.cew.spiralmemory;

public class Main {

	public static final int UP = 0;
	public static void main(String[] args) {
		int input = 277678;
		int indexSize = (int) Math.ceil(Math.sqrt((double) input)) - 1; 
		int tblMaxVal = (int) Math.pow(Math.ceil(Math.sqrt((double) input)), 2);
		int midpoint = indexSize / 2;
		
		int inputMaxValDiff = tblMaxVal - input;
		
		int[] destination = new int[2];
		int[] startPoint = new int[2];
		
		destination[0] = midpoint;
		destination[1] = midpoint;
		
		if(inputMaxValDiff <= indexSize) {
			startPoint[0] = indexSize;
			startPoint[1] = indexSize = inputMaxValDiff;
		} else {
			for(int i = indexSize; i>= 0; i--) {
				
			}
		}
		
		
		
		
		startPoint = getCoords(input);
		
		
	}
	
	private static int[] getCoords(int val) {
		int[] coordinate = new int[2];
		
		//bottom right corner is always the point of a perfect square.
		//so the value is defined as row^2 or column^2. the inverse being
		//the sqrt of the value at that index being the size of both dimensions an the 2d array.
		
		return coordinate;
	}

}
