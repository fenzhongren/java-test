package com.zejunx.test;

import java.util.ArrayList;

public class SimpleDotCom {
	
	public void setLocationCells(ArrayList<String> loc) {
		locationCells = loc;
	}
	
	public String checkIfHit(String userInput) {
		String result = "miss";
		
		int index = locationCells.indexOf(userInput);
		if(index >= 0) {
			locationCells.remove(index);
			if(locationCells.isEmpty()) {
				result = "kill";
			} else {
				result = "hit";
			}
		}

		return result;
	}
	
	private ArrayList<String> locationCells;
}
