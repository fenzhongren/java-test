package com.zejunx.music;

import javax.sound.midi.*;


public class MiniMusicPlayerV1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiniMusicPlayerV1 player = new MiniMusicPlayerV1();
		player.go();
	}
	
	public void go() {
		
		int[] eventsIWant = {127};
		
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
		
			sequencer.addControllerEventListener(new MusicControllerEventListener(), eventsIWant);
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			for(int i=40; i<66; i++) {
				track.add(makeEvent(144, 1, i, 127, i));
				track.add(makeEvent(128, 1, i, 100, i+3));
			}
			
			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	class MusicControllerEventListener implements ControllerEventListener {

		@Override
		public void controlChange(ShortMessage event) {
			// TODO Auto-generated method stub
			System.out.println("Change happens!");
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
