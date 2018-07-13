package com.zejunx.test;

import java.util.ArrayList;

public class SimpleDotComTestDrive {

	public static void main(String[] args) {
		ArrayList<String> cells = new ArrayList<String>();
		SimpleDotCom dotCom = new SimpleDotCom();
		GameHelper helper = new GameHelper();

		cells.add("5");
		cells.add("8");
		cells.add("3");
		dotCom.setLocationCells(cells);
		
		boolean isComplete = false;
		String resultMsg = null;
		String userInput = null;
		int gameCount = 0;
		while(!isComplete) {
			userInput = helper.getUserInput("Enter a number:");
			gameCount++;
			resultMsg = dotCom.checkIfHit(userInput);
			if(resultMsg.equals("hit")) {
				System.out.println("Hit number " + userInput);
			} else if(resultMsg.equals("miss")) {
				System.out.println("Miss");
			} else if(resultMsg.equals("kill")) {
				System.out.println("Killed, game over, you have tried " + gameCount + "times");
				isComplete = true;
			} else {
				System.out.println("Error, unknown input: " + userInput);
			}
		}
	}

}
