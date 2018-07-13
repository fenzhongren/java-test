package com.zejunx.dotcom;

import java.util.ArrayList;

public class DotCom {
	private ArrayList<String> locationCells;
	private String name;
	
	DotCom(String userName) {
		name = userName;
	}
	
	public String getDotComName() {
		return name;
	}
	
	
	public void setLocationCells(ArrayList<String> loc) {
		locationCells = loc;
	}
	
	public String checkIfHit(String userInput) {
		String result = "miss";
		int index = locationCells.indexOf(userInput);
		if(index >=0) {
			locationCells.remove(index);
			if(locationCells.isEmpty()) {
				result = "kill";
			} else {
				result = "hit";
			}
		}
		
		return result;
	}
	
	public void showLocationCells() {
		System.out.print(name + " is at: ");
		for(String cell: locationCells) {
			System.out.print(cell + " ");
		}
		System.out.print("\n");
	}
}
