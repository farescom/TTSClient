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

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import tts.model.ConectionToTTSServerFailed;

/**
 * <p> SoundPlayer is a simple mp3 player. You could use this class to make your application play mp3 files.<br>
 * To use <b>SoundPlayer</b> you need to follow these two simple steps:</p>
 * <ol>
 * <li> First you need to create an instance of SoundPlayer class passing mp3 file path to it's constructor</li>
 * <li> While you have done the first step, you can use {@link SoundPlayer#play(boolean)} method.
 * The given parameter tells SoundPlayer whether to repeat the sound or not. True will make it play the sound file forever, while False causes only one play.
 * </li>
 * If you want to stop playing the sound, just use {@link SoundPlayer#stop()} method.
 * You could also add listeners to SoundPlayer to be notified when playing the sound is started or stopped. To learn more about this please check {@link PlayerListener} interface.
 *
 * @author jakub_fara@hotmail.com
 */
public class SoundPlayer implements Runnable
{
    private ArrayList<PlayerListener> listeners;
    
    private String filePath;
    private String playerName;
    
    private AdvancedPlayer player;
    private Thread playerThread;   
        
    private boolean repeat;
    private int repeatCounter = 0;
    private boolean stop = false;
    
    EventHandler eventHandler = new EventHandler();
    
     /**
     * If you use this contructor SoundPlayer name will be set to the value of filePath
     * @param filePath the path to mp3 file
     */
    public SoundPlayer(String filePath)
    {
        this.filePath = filePath;
        this.playerName = "Player:"+filePath;
        listeners = new  ArrayList<PlayerListener>();
    }
     /**
     * @param filePath the path to mp3 file
     * @param playerName SoundPlayer name (You can specify any name you like)
     */
    public SoundPlayer(String filePath, String playerName)
    {
        this(filePath);
        this.playerName = playerName;
    }
     
     /**
     * Gets player's file path. 
     * @return player's file path. 
     */
    public String getFilePath()
    {
        return filePath;
    }
     /**
     * Gets player's name. 
     * @return player's name.
     */
    public String getPlayerName()
    {
        return playerName;
    }
     /**
     * Checks if the sound playing is repeated
     * @return true - if sound is repeated, false - otherwise.
     */
    public boolean isRepeated()
    {
        return repeat;
    }
        
     /**
     * Adding listener object (whose class implements PlayerListener interface) to the SoundPlayer.
     * See {@link PlayerListener} for more details.
     */
    public void addLiestener(PlayerListener listener)
    {
        listeners.add(listener);
    }
     /**
     * Removing listener object from SoundPlayer.
     * See {@link PlayerListener} for more details.
     */
    public void deleteListener(PlayerListener listener)
    {
        listeners.remove(listener);
    }
     /**
     * Removing all listener objects from SoundPlayer.
     * See {@link PlayerListener} for more details.
     */
    public void deleteAll()
    {
        for (PlayerListener listener : listeners)
            listeners.remove(listener);
    }
        
    // Runnable method
    public void run()
    {
        try
        {
            if (this.player != null)
                this.player.play();
        }
        catch (javazoom.jl.decoder.JavaLayerException ex)
        {
            ex.printStackTrace();
        }
        catch (java.lang.NullPointerException e)
        {
            this.player.close();
        }
    }
    
     /**
     * Plays mp3 sound.
     * @param repeat
     */
    public void play(boolean repeat)
    {
        this.repeat = repeat;
        play();
    }

    // Stop method
    public void stop()
    {
        if (player != null)
        {
            player.close();
        }
    }

    // private methods
    private void play()
    {
        try
        {
            String urlAsString = 
                "file:///" 
                + new java.io.File(".").getCanonicalPath() 
                + "/" 
                + this.filePath;
            this.player = new AdvancedPlayer
            (
                new java.net.URL(urlAsString).openStream(),
                javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
            );
            this.player.setPlayBackListener(eventHandler);
            this.playerThread = new Thread(this, playerName);
            this.playerThread.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void playerStarted()
    {
        //System.out.println("Mp3Start()");
        for (PlayerListener listener : listeners)
        {
            listener.playerStarted(this);
        }
    }
    
    private void playerFinished()
    {
        //System.out.println("Mp3Stop()");
        for (PlayerListener listener : listeners)
        {
            listener.playerFinished(this);
        }
    }
    
    private class EventHandler extends PlaybackListener 
    {
        // PlaybackListenerImplementation members
        public void playbackStarted(PlaybackEvent playbackEvent)
        {
            if (repeat)
            {
                if (repeatCounter == 0)
                {
                    playerStarted();
                    repeatCounter++;
                }
            }
            else
            {
                playerStarted();
            }
        }
        public void playbackFinished(PlaybackEvent playbackEvent)
        {
            if (repeat)
            {
                play();
            }
            else 
            {
                try {
                    playerFinished();
                } catch (ConectionToTTSServerFailed ex) {
                    Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }      
    }
}