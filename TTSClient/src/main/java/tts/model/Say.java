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
package tts.model;

import tts.SoundPlayer;

/**
 *
 * @author jakub_fara@hotmail.com
 */
public class Say {
    private String text;
    private String language;
    private String speechRate;
    private String audioFormat;
    private boolean store;
    private SoundPlayer soundPlayer;

    public Say(String text, String language, String speed, String audioFormat, boolean store) {
        this.text = text;
        this.language = language;
        this.speechRate = speed;
        this.audioFormat = audioFormat;
        this.store = store;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSpeed() {
        return speechRate;
    }

    public void setSpeed(String speed) {
        this.speechRate = speechRate;
    }

    public String getAudioFormat() {
        return audioFormat;
    }

    public boolean isStoreMode() {
        return store;
    }
        
    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }
        
    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    public void setInStoreMode(boolean store) {
        this.store = store;
    }
    
    public void setSoundPlayer(SoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
    }
}