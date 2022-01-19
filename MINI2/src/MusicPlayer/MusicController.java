package MusicPlayer;

import javazoom.jl.player.MP3Player;

public class MusicController {
	
static MP3Player mp3 = new MP3Player();
	
	public static void HomeRunPlay() {
		mp3.play("Homerun");
		
	}
	
	public static void HitPlay() {
		mp3.play("Hit");
	}
	
	public static void StrikePlay() {
		mp3.play("Strike");
	}
	
	public static void Intro() {
		mp3.play("Intro");
	}
	
	public static void stop() {
		mp3.stop();
	}

}
