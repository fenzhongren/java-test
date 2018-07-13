package com.zejunx.dotcom;

import java.util.ArrayList;

import com.zejunx.test.GameHelper;

public class DotComBust {
	private GameHelper helper;
	private ArrayList<DotCom> dotComsList;
	private int numOfGuesses;
	

	public static void main(String[] args) {
		DotComBust dotComBust = new DotComBust();
		
		dotComBust.setUpGame();
		dotComBust.startPlaying();
		dotComBust.finishGame();
	}

	private void setUpGame() {
		helper = new GameHelper();
		dotComsList = new ArrayList<DotCom>();
		numOfGuesses=0;
		
		dotComsList.add(new DotCom("baidu.com"));
		dotComsList.add(new DotCom("163.com"));
		dotComsList.add(new DotCom("sina.com"));
		
		for(DotCom dt : dotComsList) {
			ArrayList<String> loc = helper.placeDotCom(3);
			dt.setLocationCells(loc);
		}
		for(DotCom dt : dotComsList) {
			dt.showLocationCells();
		}
	}
	
	private void startPlaying() {
		while(!dotComsList.isEmpty()) {
			String userInput = helper.getUserInput("Enter a guess:");
			numOfGuesses++;
			checkUserGuess(userInput);
		}
	}
	
	private void checkUserGuess(String userInput) {
		int size = dotComsList.size();
		int index;
		for(index = 0; index<size; index++) {
			String resultMsg = dotComsList.get(index).checkIfHit(userInput);
			if(resultMsg.equals("hit")) {
				System.out.println("Hit " + dotComsList.get(index).getDotComName() + " " + userInput);
				break;
			} else if(resultMsg.equals("kill")) {
				System.out.println(dotComsList.get(index).getDotComName() + " is killed!");
				dotComsList.remove(index);
				break;
			}
		}
		if(index == size) {
			System.out.println("miss");
		}
	}

	
	private void finishGame() {
		System.out.println("You have killed all dotcoms with " + numOfGuesses + " guesses!");
	}
}
