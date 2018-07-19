package com.zejunx.swing;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestCheckBox {
	
	private static final String[] buttonArray = {"Start", "Stop", "Tempo Up", "Tempo Down"};
	private static final String[] itemArray = 
		{"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare",
		 "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo",
		 "Maracas", "Whistle", "Low Conga", "Cowbell",
		 "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestCheckBox gui = new TestCheckBox();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		JPanel backgroundPanel = new JPanel();
		
		for(String name : itemArray) {
			JPanel itemPanel = new JPanel();
			JTextField field = new JTextField(name);
			itemPanel.add(field);
			for(int j=0; j<16; j++) {
				JCheckBox box = new JCheckBox();
				box.addItemListener(new BoxItemListener(String.format("%s-%d", name, j), box));
				itemPanel.add(box);
			}
			backgroundPanel.add(itemPanel);
		}
		frame.getContentPane().add(BorderLayout.CENTER , backgroundPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(800, 700);
		frame.setVisible(true);
	}
	
	class BoxItemListener implements ItemListener {
		
		private String name;
		JCheckBox box;
		
		public BoxItemListener(String aName, JCheckBox aBox) {
			name = aName;
			box = aBox;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String onOrOff = "off";
			if(box.isSelected() == true) {
				onOrOff = "on";
			}
			
			System.out.println("Check box " + name + " is " + onOrOff);
		}
		
	}
}
