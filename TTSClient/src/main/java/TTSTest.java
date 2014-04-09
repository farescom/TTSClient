import java.util.ArrayList;
import java.util.List;

import tts.SoundManipulator;
import tts.Speaker;
import tts.model.AudioFormat;
import tts.model.StoreMode;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class TTSTest {
	public static void main(String[] args) throws InterruptedException {

//		SoundManipulator.concatenate(
//				"sounds/system/intro.mp3",
//				"sounds/system/pause.mp3",
//				"sounds/system/final.mp3");
//				
//		List<String> list = new ArrayList<String>();
//		list.add("sounds/system/pause.mp3");
//		list.add("sounds/system/silence_4.mp3");
//		list.add("sounds/system/pause.mp3");
//		list.add("sounds/system/intro.mp3");
//		list.add("sounds/system/Bryan adams.mp3");
//		boolean status = SoundManipulator.concatenate(list, "sounds/system/haha.mp3");
//		System.out.println("Status: "+status);
		
		String key = "e113a69fbf0240c09441d4755a2bdfe9";
		Speaker speaker = Speaker.getSpeaker(key);
		speaker.setStoreMode(StoreMode.store);
        speaker.setAudioFormat(AudioFormat._48khz_16bit_stereo);
//		speaker.speak("We will open sound file from: sounds/system/intro.mp3");
//		speaker.play("sounds/system/final.mp3");
        speaker.speak("Application recording.");
        
        List<String> tab = new ArrayList<String>();
        //tab.add("sounds/16_16_s.mp3");
        //tab.add("sounds/16_16_m.mp3");
        //tab.add("sounds/16_8_s.mp3");
        //tab.add("sounds/16_8_m.mp3");
        tab.add("sounds/system/pause_converted.mp3");
        tab.add("sounds/1.mp3");
        tab.add("sounds/system/intro_converted.mp3");
        
        SoundManipulator.concatenate( tab, "sounds/result.mp3");
	}
}