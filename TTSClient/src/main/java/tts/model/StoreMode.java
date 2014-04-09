/*
 * Copyright (C) 2013 www.LearnProgrammingNow.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tts.model;

import tts.Speaker;

/**
 * StoreMode is used to specify whether the speaker will store sound files locally or not.
 * <p> Choosing StoreMode.store will cause that each time the speaker gets
 * new sound file, it will keep it locally to benefit from it in the future
 * when we ask him to speak the same text again. In this way you can build an application, that does not
 * require internet connection to speack. (After running it for the first time, Speaker will download all
 * sound files and save them in <b>sounds</b> folder).
 * </p>
 * 
 * @author jakub_fara@hotmail.com
 */
public enum StoreMode {
    /**
     * The speaker will not store any sound files locally. Each time we call {@link Speaker#speak(java.lang.String) speak} method it will covenrt the text to sound using remote TTS server (this needs internet connection!).
     */
    none,
    /**
     * The speaker will store sound files locally in <b>sound</b> folder. Each time we call {@link Speaker#speak(java.lang.String) speak} method the {@link Speaker} will try first to locate the appropriate file locally before it use remote TTS server (The speaker won't need internet connection if the file is available locally).
     */
    store
}
