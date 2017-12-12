package com.cewarren.hepassphrase;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		// check list of passphrases for passphrase validity.
		// validity is determined by there being no duplicate words in the passphrase.
		// determine how many passphrases are invalid.
		
		int result = 0;
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<ArrayList<String>> passList = new ArrayList<ArrayList<String>>();
		Path path = Paths.get(URI.create(Main.class.getResource("resources/input.txt").toString()));
		
		Stream<String> lines = Files.lines(path);
		lines.forEach(line -> {
			data.addAll(Arrays.asList(line.toString().split(" ")));
			passList.add(new ArrayList<String>(data));
			data.clear();
		});
		lines.close();
		
		result = calcInvalidPassphrases(passList);
		
		System.out.println("Number of Invalid Phrases: " + String.valueOf(result));
		

	}
	
	private static int calcInvalidPassphrases(ArrayList<ArrayList<String>> list) {
		//loop through each AL within the main AL and compare the words contained within
		int rslt = 0;
		boolean isInvalid = false;
		List<String> reversed = new ArrayList<String>();
		
		for(ArrayList<String> currRow : list) {
			reversed.addAll(currRow);
			Collections.reverse(reversed);
			//System.out.println("Current Row: " + currRow.toString());
			for(String currItem : currRow) {
				reversed.remove(reversed.size() - 1);
				for(String compItem : reversed) {
					//System.out.println(currItem + " : "  + compItem + " :: " + String.valueOf(currItem.equals(compItem)));
					if(currItem.equals(compItem)) {
						isInvalid = true;
						break;
					} else {
						isInvalid = false;
					}
				}
				if(isInvalid) break;
			}
			if(!isInvalid) rslt += 1;
			//System.out.println(String.valueOf(rslt));
			reversed.clear();
			
		}
		
		return rslt;
	}

}
