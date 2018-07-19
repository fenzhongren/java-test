package com.zejunx.swing;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestCheckBox {

	private JCheckBox  checkBox;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestCheckBox gui = new TestCheckBox();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		for(int i = 0; i < 256; i++) {
			checkBox = new JCheckBox();
			checkBox.addItemListener(new BoxItemListener());
		
			panel.add(checkBox);
		}
		frame.getContentPane().add(BorderLayout.CENTER , panel);
		
		frame.setSize(350, 300);
		frame.setVisible(true);
	}
	
	class BoxItemListener implements ItemListener {
		
		private String name;
		
		public BoxItemListener(String aName) {
			name = aName;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String onOrOff = "off";
			if(checkBox.isSelected() == true) {
				onOrOff = "on";
			}
			
			System.out.println("Check box " + name + " s " + onOrOff);
		}
		
	}
}
