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
package tts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SoundManipulator is used to concatenate different sound files into one file.<br>
 * It could be used to produce mp3 file based on different small sound files. To
 * use <b>SoundManipulator</b> choose one of its two methods:
 * </p>
 * <ol>
 * <li>{@link SoundManipulator#concatenate(String, String, String)} - if you
 * need to concatenate two files</li>
 * <li>{@link SoundManipulator#concatenate(List, String)} - if you need to
 * concatenate more files</li>
 * 
 * @author jakub_fara@hotmail.com
 */
public class SoundManipulator {

	public SoundManipulator() {

	}

	/**
	 * Concatenates two mp3 sound files, and save them in the given destinations
	 * file.
	 * 
	 * @param soundPath1
	 *            the path to first mp3 sound file.
	 * @param soundPath2
	 *            the path to second mp3 sound file.
	 * @param resultSoundPath
	 *            the path to destination file, which will include the
	 *            concatenated sounds (if file exists, it will be overwritten).
	 * @return returns True - if succeeded, otherwise - False. If any of the
	 *         files does not exist, the whole process will fail, and false will
	 *         be returned.
	 */
	public static boolean concatenate(String soundPath1, String soundPath2,
			String resultSoundPath) {

		ArrayList<String> list = new ArrayList<String>();
		list.add(soundPath1);
		list.add(soundPath2);
		return concatenate(list, resultSoundPath);
	}

	/**
	 * Concatenates a list of mp3 sound files, and save them in the given
	 * destinations file.
	 * 
	 * @param soundPaths
	 *            a list of paths to all mp3 sound files, that we want to
	 *            concatenate.
	 * @param resultSoundPath
	 *            the path to destination file, which will include the
	 *            concatenated sounds (if file exists, it will be overwritten).
	 * @return returns True - if succeeded, otherwise - False. If any of the
	 *         files does not exist, the whole process will fail, and false will
	 *         be returned.
	 */
	public static boolean concatenate(List<String> soundPaths,
			String resultSoundPath) {

		File result = new File(resultSoundPath);
		OutputStream os = null;
		try {
			os = new FileOutputStream(result, false);
			byte[] buffer = new byte[1 << 20]; // loads 1 MB of the file
			for (int i = 0; i < soundPaths.size(); i++) {
				InputStream in = new FileInputStream(soundPaths.get(i));
				int count;
				while ((count = in.read(buffer)) != -1) {
					os.write(buffer, 0, count);
					os.flush();
				}
				in.close();
			}
			os.close();
			return true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			try {
				os.close();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			boolean s = result.delete();
			// System.out.println(s);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			try {
				os.close();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			boolean s = result.delete();
			// System.out.println(s);
			return false;
		}
	}
}
