package com.zejunx.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
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
	private ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
	private Sequencer sequencer;
	private Sequence seq;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeatBox box = new BeatBox();
		box.buildGui();
	}
	
	public void buildGui() {
		theFrame = new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel backgroundPanel = new JPanel(new BorderLayout());
		backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));		
		
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		JButton startButton = buildButton("Start", new StartButtonListener());
		buttonBox.add(startButton);
		JButton stopButton = buildButton("Stop", new StopButtonListener());
		buttonBox.add(stopButton);
		JButton tempoUpButton = buildButton("Tempo Up", new TempoUpButtonListener());
		buttonBox.add(tempoUpButton);
		JButton tempoDownButton = buildButton("Tempo Down", new TempoDownButtonListener());
		buttonBox.add(tempoDownButton);
		backgroundPanel.add(BorderLayout.EAST, buttonBox);
		
		Box instrumentBox = new Box(BoxLayout.Y_AXIS);
		for(String name : instrumentNames) {
			JLabel label = new JLabel(name);
			label.setFont(new Font("TimesRoman", Font.BOLD, 18));
			instrumentBox.add(label);
		}
		backgroundPanel.add(BorderLayout.WEST, instrumentBox);
		
		GridLayout grid = new GridLayout(16, 16);
		grid.setHgap(4);
		grid.setVgap(1);
		JPanel mainPanel = new JPanel(grid);
		int instruments = instrumentNames.length;
		for(int i=0; i<instruments; i++) {
			for(int j=0; j<16; j++) {
				JCheckBox box = new JCheckBox();
				checkBoxList.add(box);
				mainPanel.add(box);
			}
		}
		backgroundPanel.add(BorderLayout.CENTER, mainPanel);
		
		setUpMidi();
		theFrame.getContentPane().add(BorderLayout.CENTER, backgroundPanel);
		theFrame.setBounds(400, 200, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	private JButton buildButton(String aName, ActionListener anActionListener) {
		JButton button = new JButton(aName);
		button.setFont(new Font("TimesRoman", Font.BOLD, 18));
		button.addActionListener(anActionListener);
		return button;
	}
	
	private void setUpMidi(){
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			seq = new Sequence(Sequence.PPQ, 4);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	class StartButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buildTrackAndStart();
		}
		
	}
	
	class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			sequencer.stop();
		}
		
	}
	
	class TempoUpButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * 1.03));
		}
		
	}
	
	class TempoDownButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * 0.97));
		}
		
	}
	
	private void buildTrackAndStart() {
		
		int[] tickArray = null;
		Track track = seq.createTrack();

		for(int i=0; i<16; i++) {
			int instrument = instrumentNumbers[i];
			tickArray = new int[16];
			for(int j=0; j<16; j++) {
				JCheckBox box = (JCheckBox)checkBoxList.get(j+16*i);
				if(box.isSelected()) {
					tickArray[j] = instrument;
				} else {
					tickArray[j] = 0;
				}
			}
			makeTracks(tickArray, track);
			track.add(makeEvent(176, 1, 127, 0, 16));
		}
		track.add(makeEvent(192, 9, 1, 0, 15));
		try {
			sequencer.setSequence(seq);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.setTempoInBPM(120);
			sequencer.start();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void makeTracks(int[] instrumentArray, Track track) {
		for(int i=0; i<16; i++) {
			int key = instrumentArray[i];
			if(key != 0) {
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i+1));
			}
		}
	}
	
	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return event;
	}
}
