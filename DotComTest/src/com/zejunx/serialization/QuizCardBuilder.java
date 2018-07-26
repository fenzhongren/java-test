package com.zejunx.serialization;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizCardBuilder {

	public QuizCardBuilder() {
		frame = new JFrame("Quiz Card Builder");
		frame.setBounds(400, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JTextArea questionArea = new JTextArea(100, 10);
		questionArea.setName("Question");
		JScrollPane questionScroller = new JScrollPane(questionArea);
		questionArea.setLineWrap(true);
		questionScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		questionScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//questionScroller.setBounds(10, 10, 10, 10);
		panel.add(questionScroller);
		
		JTextArea answerArea = new JTextArea(100, 10);
		answerArea.setName("Answer");
		JScrollPane answerScroller = new JScrollPane(answerArea);
		answerArea.setLineWrap(true);
		answerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		answerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		answerScroller.setName("Answer");
		panel.add(answerScroller);
		
		JButton nextCard = new JButton("Next Card");
		panel.add(nextCard);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		QuizCardBuilder card = new QuizCardBuilder();
	}
	
	private JFrame frame;
}
