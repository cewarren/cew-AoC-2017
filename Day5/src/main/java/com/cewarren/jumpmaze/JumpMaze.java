package com.cewarren.jumpmaze;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JumpMaze {

	public static void main(String[] args) throws IOException {
		int index = 0;
		int count = 0;
		List<Integer> jumpList = new ArrayList<Integer>();
		Stream<String> lines = Files.lines(Paths.get(URI.create(JumpMaze.class.getResource("resources/input.txt").toString())));
		
		lines.forEach(s -> jumpList.add(Integer.parseInt(s)));
		lines.close();
		//List<Integer> jumpList = Arrays.asList(0,3,0,1,-3);
		
		while(index < jumpList.size())
		{
			//System.out.println("BEFORE\nIndex: " + String.valueOf(index) + " | Value: " + jumpList.get(index).toString());
			int prevIndex = index;
			index += jumpList.get(index);
			jumpList.set(prevIndex, jumpList.get(prevIndex) >= 3 ? jumpList.get(prevIndex) - 1 : jumpList.get(prevIndex) + 1);
			//System.out.println("AFTER\nIndex: " + String.valueOf(index) + " | New Value: " + jumpList.get(prevIndex).toString());
			count++;
		}
		
		System.out.println("Number of Jumps: " + String.valueOf(count));

	}

}
