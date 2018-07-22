package com.zejunx.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BeatBox {
	
	private static final String[] instrumentNames = 
		{"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare",
		 "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo",
		 "Maracas", "Whistle", "Low Conga", "Cowbell",
		 "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
	
	private static final int[] instrumentNumbers = {35, 42, 46, 38, 49, 39, 
			50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
	
	private static final String[] buttonNames = {"Start", "Stop", "Tempo Up", "Tempo Down"};

	private JFrame theFrame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeatBox box = new BeatBox();
		box.buildGui();
	}
	
	public void buildGui() {
		theFrame = new JFrame();
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel backgroundPanel = new JPanel(new BorderLayout());
		backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		for(String name : buttonNames) {
			JButton button = new JButton(name);
			buttonBox.add(button);
		}
		backgroundPanel.add(BorderLayout.EAST, buttonBox);
		
		Box instrumentBox = new Box(BoxLayout.Y_AXIS);
		for(String name : instrumentNames) {
			JLabel label = new JLabel(name);
			instrumentBox.add(label);
		}
		backgroundPanel.add(BorderLayout.WEST, instrumentBox);
		
		JPanel mainPanel = new JPanel();
		int instruments = instrumentNames.length;
		mainPanel.setLayout(new GridLayout(instruments, 16));
		for(int i=0; i<instruments; i++) {
			for(int j=0; j<16; j++) {
				JCheckBox box = new JCheckBox();
				mainPanel.add(box);
			}
		}
		backgroundPanel.add(BorderLayout.CENTER, mainPanel);
		
		
		theFrame.getContentPane().add(BorderLayout.CENTER, backgroundPanel);
		theFrame.setSize(600, 400);
		theFrame.setVisible(true);
	}
	
}
