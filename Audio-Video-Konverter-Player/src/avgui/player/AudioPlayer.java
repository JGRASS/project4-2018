package avgui.player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioPlayer implements PlayerInterface {

	public Player player;
	FileInputStream fis;
	BufferedInputStream bis;

	public long pauseLocation;
	public long songTotalLength;
	public int rewindLocation;
	public String fileLocation;

	@Override
	public void playA(String path) {
		try {
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);

			songTotalLength = fis.available(); // imamo punu duzinu pesme

			fileLocation = path + "";
		} catch (FileNotFoundException e1) {

		} catch (JavaLayerException e1) {

		} catch (IOException e) {

		}

		new Thread() {
			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException e) {

				}

			}

		}.start();

	}
//+1000000
	@Override
	public void rewind(long offset) {
		pause();
		resume(offset);

	}
	


	@Override
	public void pause() {
		if (player != null) {

			try {

				pauseLocation = fis.available();
				player.close();

			} catch (IOException e) {

			}

		}

	}

	@Override
	public void stop() {
		if (player != null) {
			player.close();
			pauseLocation = 0;
			songTotalLength = 0;

		}

	}

	@Override
	public void resume(long offset) {
		try {
			fis = new FileInputStream(fileLocation);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);

			fis.skip(songTotalLength - pauseLocation + offset);
		} catch (FileNotFoundException e1) {

		} catch (JavaLayerException e1) {

		} catch (IOException e) {

		}

		new Thread() {
			@Override
			public void run() {
				try {
					player.play();
				} catch (JavaLayerException e) {

				}

			}

		}.start();

	}

	@Override
	public void playV(JFrame f, String path, JPanel p) {
		// TODO Auto-generated method stub

	}

}
