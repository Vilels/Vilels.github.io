package tf;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound{
	
	public Sound() {
		
	}
	
 public static void main(String[] args) {
    soundtrackLoop("music/music.wav ", true);
}

public static void soundtrackLoop (String soundName, boolean loop) {
    while(loop) {
        soundPlay(soundName);
    }
}

public static void soundPlay(String soundName) { 
    try {
        AudioInputStream audioInputStream; 
        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(Exception error) {           
        System.out.println("Error with playing sound."+error);
    }
}
}