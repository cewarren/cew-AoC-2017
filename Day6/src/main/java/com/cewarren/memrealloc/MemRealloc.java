package com.cewarren.memrealloc;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemRealloc {

	public static void main(String[] args) throws IOException {
		List<Integer> memBanks = new ArrayList<Integer>();
		Stream<String> lines = Files.lines(Paths.get(URI.create(MemRealloc.class.getResource("resources/input.txt").toString())));
		lines.forEach(s -> {
			memBanks.addAll(Stream.of(s.split("\t")).map(Integer::valueOf).collect(Collectors.toList()));
		});
		lines.close();
		
		//memBanks.addAll(Arrays.asList(0,2,7,0));
		
		int cycles = getSingleCycleCount(memBanks);
		
		System.out.println("Number of Cycles to Repeat: " + String.valueOf(cycles));
		
		int dblCycles = getDoubleCycleCount(memBanks);
		
		System.out.println("Number of Cycles to Second Repeat: " + String.valueOf(dblCycles));
			
		
	}

	private static int getSingleCycleCount(List<Integer> memBanks) {
		int cycles = 0;
		int compareVal = 0;
		List<List<Integer>> configHistory = new ArrayList<List<Integer>>();
		boolean isDistinct = true;
		
		configHistory.add(new ArrayList<Integer>(memBanks));
		
		while(isDistinct) {
			//System.out.println("Current config: " + memBanks.toString());
			compareVal = memBanks.get(0);
			for(int i = 0; i < memBanks.size(); i++) {
				if(compareVal < memBanks.get(i)) {
					compareVal = memBanks.get(i);
				}
			}
			
			int startIndex = memBanks.indexOf(compareVal);
			int blocks = memBanks.get(startIndex);
			memBanks.set(startIndex, 0);
			
			int j = startIndex + 1;
			while(blocks > 0) {
				if(j > memBanks.size() - 1) {
					j = 0;
				}
				memBanks.set(j, memBanks.get(j) + 1);
				blocks--;
				j++;
				
			}
			cycles += 1;
			
			for(int i = 0; i < configHistory.size(); i++) {
				if(memBanks.equals(configHistory.get(i))) {
					isDistinct = false;
				}
			}
			if(isDistinct) {
				configHistory.add(new ArrayList<Integer>(memBanks));
			}
		}
		
		return cycles;
	}
	
	private static int getDoubleCycleCount(List<Integer> memBanks) {
		int dblCycles = 0;
		int cycles = 0;
		int compareVal = 0;
		List<List<Integer>> configHistory = new ArrayList<List<Integer>>();

		boolean isSecondCycle = false;
		
		configHistory.add(new ArrayList<Integer>(memBanks));
		
		while(!isSecondCycle) {
			compareVal = memBanks.get(0);
			for(int i = 0; i < memBanks.size(); i++) {
				if(compareVal < memBanks.get(i)) {
					compareVal = memBanks.get(i);
				}
			}
			
			int startIndex = memBanks.indexOf(compareVal);
			int blocks = memBanks.get(startIndex);
			memBanks.set(startIndex, 0);
			
			int j = startIndex + 1;
			while(blocks > 0) {
				if(j > memBanks.size() - 1) {
					j = 0;
				}
				memBanks.set(j, memBanks.get(j) + 1);
				blocks--;
				j++;
				
			}
			if(dblCycles == 1) { cycles += 1; }
			
			for(int i = 0; i < configHistory.size(); i++) {
				if(memBanks.equals(configHistory.get(i))) {
					dblCycles += 1;
					configHistory.clear();
					configHistory.add(new ArrayList<Integer>(memBanks));
				}
			}
			
			if(dblCycles == 2) {
				isSecondCycle = true;
			}
			
		}
		
		return cycles;
	}

}
