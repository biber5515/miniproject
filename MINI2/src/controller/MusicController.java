package controller;

import java.util.ArrayList;

import Model.Music_VO;

import javazoom.jl.player.MP3Player;

public class MusicController {

	static MP3Player mp3 = new MP3Player();

//	Music_VO music1 = new Music_VO("Intro", "C:\\Users\\smhrd\\Desktop\\�̴�������Ʈ\\Intro");
//	Music_VO music2 = new Music_VO("Strike", "C:\\Users\\smhrd\\Desktop\\�̴�������Ʈ\\Strike");
//	Music_VO music3 = new Music_VO("Hit", "C:\\Users\\smhrd\\Desktop\\�̴�������Ʈ\\Hit");
//	Music_VO music4 = new Music_VO("Homerun", "C:\\Users\\smhrd\\Desktop\\�̴�������Ʈ\\Homerun");

	
	public static void Intro() {
		mp3.play("C:\\Users\\smhrd\\Desktop\\�̴�������Ʈ\\Intro");
	}

	public static void StrikePlay() {
		mp3.play("C:\\Users\\smhrd\\Desktop\\�̴�������Ʈ\\Strike");
	}
	public static void HitPlay() {
		mp3.play("C:\\Users\\smhrd\\Desktop\\�̴�������Ʈ\\Hit");
	}
	public static void HomeRunPlay() {
		mp3.play( "C:\\Users\\smhrd\\Desktop\\�̴�������Ʈ\\Homerun");

	}
	public static void stop() {
		mp3.stop();
	}

}
