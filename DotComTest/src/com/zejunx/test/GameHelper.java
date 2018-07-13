package com.zejunx.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHelper {
	private ArrayList<String> totalCells = new ArrayList<String>();

	public String getUserInput(String prompt) {
		String inputLine = null;
		
		System.out.print(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if(inputLine.length() == 0) {
				return null;
			}
		} catch(IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine;
	}
	
	public ArrayList<String> placeDotCom(int numOfCells) {
		ArrayList<String> result = new ArrayList<String>();
		
		int currentNumOfCells = 0;
		boolean isComplete = false;
		String input;
		while(!isComplete ) {
			input = getUserInput("Enter a cell:");
			if(!isValidInput(input))
				continue;
			if(!doesConflictWithOtherCells(input)) {
				result.add(input);
				totalCells.add(input);
				currentNumOfCells++;
				if(currentNumOfCells == numOfCells)
					isComplete = true;
			}

		}
		return result;
	}
	
	private boolean isValidInput(String input) {
		if(input.length() != 2) {
			System.out.println("Format error: " + input);
			return false;
		}

		return isInScope(input);
	}
	
	private boolean isInScope(String input) {
		char firstChar = input.charAt(0);
		char lastChar = input.charAt(1);
		
		boolean inScope = true;
		if(firstChar<'A' || firstChar>'G' || lastChar<'0' || lastChar>'6') {
			inScope = false;
			System.out.println("Scope Error: " + input);
		}
		return inScope;
	}
	
	private boolean doesConflictWithOtherCells(String input) {
		if(totalCells.contains(input)) {
			System.out.println("Your input conflict with cells, please enter other cell!");
			return true;
		} else {
			return false;
		}
	}
}
