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
		
		result = calcInvalidPassphrases(passList, false);
		
		System.out.println("Number of Valid Phrases: " + String.valueOf(result));
		
		result = 0;
		result = calcInvalidPassphrases(passList, true);
		
		System.out.println("Number of Valid Phrases (Anagram): " + String.valueOf(result));
		

	}
	
	private static int calcInvalidPassphrases(ArrayList<ArrayList<String>> list, boolean checkAnagram) {
		//loop through each AL within the main AL and compare the words contained within
		int rslt = 0;
		boolean isInvalid = false;
		List<String> reversed = new ArrayList<String>();
		
		for(ArrayList<String> currRow : list) {
			reversed.addAll(currRow);
			Collections.reverse(reversed);
			for(String currItem : currRow) {
				reversed.remove(reversed.size() - 1);
				for(String compItem : reversed) {
					if (!checkAnagram) {
						if (currItem.equals(compItem)) { isInvalid = true; break; } 
						else isInvalid = false;
					} else {
						isInvalid = isInvalidWithAnagram(currItem, compItem);
						if(isInvalid) break;
					}
				}
				if(isInvalid) break;
			}
			if(!isInvalid) rslt += 1;
			reversed.clear();
			
		}
		
		return rslt;
	}
	
	private static boolean isInvalidWithAnagram(String firstWord, String secondWord) {
		//same as above, except now a passphrase is invalid if there are any anagrams.
		//anagrams have all the same letters.
		//you can take each work and do to char array, sort the char array, and then compare.
		boolean isInvalid = false;
		List<String> firstList = new ArrayList<String>();
		List<String> secondList = new ArrayList<String>();
		
		firstList.addAll(Arrays.asList(firstWord.split("")));
		secondList.addAll(Arrays.asList(secondWord.split("")));
		
		firstList.sort(null);
		secondList.sort(null);
		
		if(firstList.equals(secondList)) isInvalid = true;
		else isInvalid = false;
		
		
		return isInvalid;
	}

}
