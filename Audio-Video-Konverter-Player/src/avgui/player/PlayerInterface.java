package avgui.player;

import javax.swing.JFrame;
import javax.swing.JPanel;

public interface PlayerInterface {

public void playA(String path);
public void rewind(long offset);
public void pause();
public void stop();
public void resume(long offset);
public void playV(JFrame f, String path, JPanel p);
	

	
}
