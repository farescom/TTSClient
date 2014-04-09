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

import tts.model.ConectionToTTSServerFailed;

/**
 * This interface can be used to implement event handler for {@link SoundPlayer}. 
 * </br>It defines two methods:
 * <ul>
 * <li>{@link #playerStarted(tts.SoundPlayer) playerStarted}: a method called by SoundPlayer when it starts playing sound file.</li>
 * <li>{@link #playerFinished(tts.SoundPlayer) playerFinished}: a method called by SoundPlayer when it finishes playing sound file.</br>
 * Please notice that stopping SoundPlayer (using {@link SoundPlayer#stop()} method will not cause invoking this method.
 * playerFinished method is invoked only when SoundPlayer finishes playing naturally.
 * </li>
 * While implementing an event handler for a SoundPlayer don't forget to add event handlar class as an oberver to SoundPlayer object
 * by calling method: {@link SoundPlayer#addLiestener(tts.PlayerListener)}.
 * <ul>
 * @author jakub_fara@hotmail.com
 */
public interface PlayerListener {
    /**
     * method called by SoundPlayer when it starts playing sound file.
     * 
     * @param notifier SoundPlayer object, that called this method
     */
    public void playerStarted(SoundPlayer notifier);

    /**
     * method called by SoundPlayer when it finishes playing sound file.</br>
     * Please notice that stopping SoundPlayer (using {@link SoundPlayer#stop()} method will not cause invoking this method.
     * <b>playerFinished</b> method is invoked only when SoundPlayer finishes playing sound file naturally.
     * 
     * @param notifier SoundPlayer object, that called this method
     */
    public void playerFinished(SoundPlayer notifier);
}