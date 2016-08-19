package gui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * A class controlling the audio in the game.
 *
 * @author Aden Kenny and Simon Pope.
 *
 */

public class Audio {

	String soundName;
	AudioInputStream audioInputStream;
	Clip clip;

	/**
	 * Mutes the audio in the game.
	 */

	public void muteAudio() {

		if(this.clip != null) { //Check to make sure we have sound.
			this.clip.close(); //Close the audio.
			this.clip = null; //Set to no music.
		}
	}

	/**
	 * Unmutes the audio in the game.
	 */

	public void unmuteAudio() {


		if(this.clip == null) { //Checks to make sure we don't already have sound.
			playAudio();
		}

		//playAudio(); //Uncomment this for a great bug. Press the play audio button a few times.

	}

	/**
	 * Restarts the audio in the game.
	 */

	private void playAudio() {

		this.soundName = "assets/audio/music.wav";
		this.audioInputStream = null;

		try {
			this.audioInputStream = AudioSystem.getAudioInputStream(new File(this.soundName).getAbsoluteFile());
		}
		catch (UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		}

		try {
			this.clip = AudioSystem.getClip();
			this.clip.open(this.audioInputStream);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY); //Set music to loop.
			this.clip.start();
		}

		catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
