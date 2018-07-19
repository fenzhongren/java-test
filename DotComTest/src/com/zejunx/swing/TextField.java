package com.zejunx.swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextField {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextField gui = new TextField();
		gui.go();
	}
	
	public void go() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JTextField field1 = new JTextField(20);
		JTextField field2 = new JTextField("Your name: ");
		panel.add(field2);
		panel.add(field1);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);	
	}
	
}
