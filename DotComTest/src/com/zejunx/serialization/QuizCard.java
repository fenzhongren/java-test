package com.zejunx.serialization;

import java.io.Serializable;

public class QuizCard implements Serializable {
	
	public QuizCard(String q, String a) {
		question = q;
		answer = a;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	private String question;
	private String answer;
	
}
