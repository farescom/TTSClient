/*
 * Copyright (C) 2014 www.LearnProgrammingNow.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU LESSER GENERAL PUBLIC LICENSE as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU LESSER GENERAL PUBLIC LICENSE for more details.
 *
 * You should have received a copy of the GNU LESSER GENERAL PUBLIC LICENSE
 * along with this program.  If not, see <http://www.gnu.org/licenses/lgpl-3.0.txt>.
 */

import java.util.ArrayList;
import java.util.List;

import tts.SoundManipulator;
import tts.Speaker;
import tts.model.AudioFormat;
import tts.model.StoreMode;

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