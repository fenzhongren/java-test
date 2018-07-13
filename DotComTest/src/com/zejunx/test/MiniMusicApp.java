package com.zejunx.test;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMusicApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiniMusicApp musicApp = new MiniMusicApp();
		musicApp.play();
	}

	public void play() {
		try {
			Sequencer player = MidiSystem.getSequencer();
			player.open();
	
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, 50, 100);
			MidiEvent  noteOn = new MidiEvent(a, 1);
			track.add(noteOn);

			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, 44, 100);
			MidiEvent noteOff = new MidiEvent(b, 3);
			track.add(noteOff);

			player.setSequence(seq);
			player.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
