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
package tts;

import tts.filemanagement.ModelXmlReader;
import tts.filemanagement.ModelXmlWriter;
import tts.model.AudioFormat;
import tts.model.ConectionToTTSServerFailed;
import tts.model.Language;
import tts.model.Say;
import tts.model.SoundFile;
import tts.model.SpeechRate;
import tts.model.StoreMode;
import tts.model.TooLongText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * <p> Speaker is a TTS client, which converts text to sound using a remote TTS service provided by <a href="http://www.voicerss.org">Voice RSS</a>. You could use this class to make your application speak.<br>
 * Speaker is implemented as a <i>thread-safe</i> singleton, so there is one <b>
 * Speaker</b> object, that could be accessed from everywhere in your application.<br><br> To use
 * <b>Speaker</b> you need to follow these three simple steps:</p> <ol> <li> First you need to get an instance of Speaker's class
 * using the static method {@link #getSpeaker(java.lang.String)} <p>This method
 * takes a String key as a parameter. The key represents your application on the remote TTS server and is
 * obligatory (If you use a wrong key, the Speaker will not work).<br> You can get your
 * own key: <a href="http://www.voicerss.org/login.aspx">here</a> (You will need to register before you generate your key)</li> <li>
 * While you have done the first step, you should set some settings for your
 * speaker. <br> These include: <ul> <li>setting speaker's language - {@link Speaker#setLanguage(tts.model.Language)},</li>
 * <li>speaking speed - {@link Speaker#setSpeechRate(tts.model.SpeechRate) },</li> <li>audio format - {@link Speaker#setAudioFormat(tts.model.AudioFormat)},</li> <li>and store mode - {@link Speaker#setStoreMode(tts.model.StoreMode)}.</li> </ul>
 * The default settings are: <ul> <li>British English language
 * (<b>Language.engb</b>),</li> <li>normal speaking speed
 * (<b>SpeechRate.normal</b>)</li> <li>16khz/16bit stereo audio
 * (<b>AudioFormat._16khz_16bit_stereo</b>)</li> <li>and store mode turned to
 * off (<b>StoreMode.none</b>)</li> </ul> For the meaning of each of these
 * settings, please go to appropriate setter and getter methods below. </li> <br/>
 * <li> After setting Speaker's properties, its time to call speaking method:
 * {@link #speak(java.lang.String)}. </li> </ol>
 *
 * @author jakub_fara@hotmail.com
 */
public class Speaker {

    private static Speaker reference = null;
    private SoundPlayer mp3Player;
    private String audioCodec = "MP3";
    private String site = "http://api.voicerss.org";
    private String key = "e113a69fbf0240c09441d4755a2bdfe9";
    private boolean store = false;
    private String language = "en-gb";
    private String audioFormat = "48khz_16bit_stereo"; //"16khz_16bit_stereo";
    private String speechRate = "0";
    private Queue<Say> speach = new LinkedList<Say>();
    private EventHandler eventHandler = new EventHandler();
    private Say currentSay;
    private boolean playing = false;

    public Speaker(String key) {
        this.key = key;
    }

    /**
     * Returns Speaker object.
     *
     * @param key TTS key, that that is used by the speaker to convert text to
     * sound. To get your key please visit <a
     * href="http://www.voicerss.org/login.aspx">VoiceRss.org</a>.
     * @return Speaker
     */
    public static synchronized Speaker getSpeaker(String key) {
        if (reference == null) {
            reference = new Speaker(key);
        }
        return reference;
    }

    /**
     * Sets Speaker's store mode.
     * 
     * @param sm store mode. See {@link tts.model.StoreMode} enum.
     * enum.
     */
    public void setStoreMode(StoreMode sm) {
        if (sm == StoreMode.store) {
            store = true;
        } else {
            store = false;
        }
    }

    /**
     * Sets speaker's language.
     * <p> You should set speaker's language, before you
     * call {@link #speak(java.lang.String) speak} method. Default language is
     * British English (Language.engb) </p>
     *
     * @param l speaker's language. See {@link tts.model.Language} enum.
     */
    public void setLanguage(Language l) {
        String language = l.toString();
        this.language = language.substring(0, 2) + "-" + language.substring(2, 4);
    }

    /**
     * Sets speaker's audio format.
     * 
     * @param f the audio format of speaker's sound. See {@link tts.model.AudioFormat} enum.
     */
    public void setAudioFormat(AudioFormat f) {
        String autoFormat = f.toString();
        this.audioFormat = autoFormat.substring(1);
    }

    /**
     * Sets speaker's speaking speed
     * 
     * @param r speaking speed. See {@link tts.model.SpeechRate} enum.
     */
    public void setSpeechRate(SpeechRate r) {
        if (r == r.normal) {
            this.speechRate = "0";
        } else {
            String speechRate = r.toString();
            this.speechRate = ((speechRate.substring(0, 4).equals("slow")) ? "-" : "") + speechRate.substring(4);
        }
    }

    /**
     * Checks if speaker is in store mode.
     * See {@link tts.model.StoreMode StoreMode} enum.
     *
     * @return speaking speed
     */
    public Boolean isInStoreMode() {
        return store;
    }

    /**
     * Gets speaker's language. 
     * See {@link tts.model.Language Language} enum.
     * @return speaker's language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Gets speaker's audio format. 
     * See {@link tts.model.AudioFormat AudioFormat} enum.
     * 
     * @return the audio format for speaker's sound
     */
    public String getAudioFormat() {
        return audioFormat;
    }

    /**
     * Gets speaker's speaking speed. 
     * See {@link tts.model.SpeechRate SpeechRate} enum.
     * 
     * @return speaking speed
     */
    public String getSpeechRate() {
        return speechRate;
    }

    /**
     * Orders the speaker to say the given text.
     * Speeches are scheduled in a queue. This means that If speaker is saying something when this method is called, then <b>text</b> will be added to a queue, and the speaker will say it after it finishes its current speech and all scheduled speeches.
       This mechanizm prevents simultaneous calls of <b>speak</b> method, where different texts could be said simultaneously.
     * <p>If <b>text</b> parameter is longer than 1000 chars, {@link tts.model.TooLongText TooLongText} exception will be thrown.</p> 
     * 
     * @param text text to be said by the speaker
     * @exception TooLongText An error thrown when the length of <b>text</b> parameter is longer that 1000 chars.
     * @exception ConectionToTTSServerFailed An error thrown when the Speaker can not access TTS server through the internet (No internet connection).
     */
    public synchronized void speak(String text) throws ConectionToTTSServerFailed {
        if (text.length() > 1000) {
            throw new TooLongText("The length of text (" + text.length() + ") should not be greater thatn 1000.");
        }

        speach.add(new Say(text, language, speechRate, audioFormat, store));
        if (!playing) {
            playing = true;
            say();
        }
    }

     /**
     * Orders the speaker to play the given sound.
     * Sounds to play are scheduled together with other speeches in a queue. This means that if speaker is saying something when this method is called, then mp3 sound file located in<b>filePath</b> will be added to a queue, and the speaker will play it after it finishes its current speech and all scheduled speeches / sounds.
       This mechanizm prevents simultaneous calls of <b>speak</b> and/or <b>play</b>methods.
     * 
     * @param filePath mp3 sound file path
     */
    public synchronized void play(String filePath) throws ConectionToTTSServerFailed {
        speach.add(new Say("mp3>" + filePath, null, null, null, false));
        if (!playing) {
            playing = true;
            say();
        }
    }
    
    /**
     * Removes all the scheduled speeches of the speaker (but not the current speech)
     */
    public void clear() {
        speach.clear();
    }

    /**
     * Removes all the scheduled speeches of the speaker in addition to the current speech
     * (The current speech is stopped - if any, and all scheduled speeches are removed).
     * This method is equivalent to using two methods: clear(); next();
     */
    public void clearAll() {
        clear();
        try
        {
            next();
        }
        catch (ConectionToTTSServerFailed ex) {System.out.println("Error");}
    }

    /**
     * Stops the current speech (if any) and moves to the next scheduled one (if any)
     */
    public void next() {
        if (currentSay != null) {
            currentSay.getSoundPlayer().stop();
            eventHandler.playerFinished(currentSay.getSoundPlayer());
        }
    }
    // ************************************************************************************

    private void say()  {
        if (speach.peek().equals(""))
        {
            speach.poll();
        }
        else if (speach.peek().getText().startsWith("mp3>") )
        {
            // sound not text to speak
                SoundPlayer mp3Player = new SoundPlayer(speach.peek().getText().substring(4));
                mp3Player.addLiestener(eventHandler);
                mp3Player.play(false);
                currentSay = speach.peek();
                speach.peek().setSoundPlayer(mp3Player);
        }
        else
        {
            String text = speach.peek().getText().replace(" ", "%20");
            String ttsUrl = site + "/?key=" + key
                    + "&src=" + text
                    + "&hl=" + speach.peek().getLanguage()
                    + "&r=" + speach.peek().getSpeed()
                    + "&c=" + audioCodec
                    + "&f=" + speach.peek().getAudioFormat();

            String soundFolderUrl = "sounds/"
                    + speach.peek().getLanguage() + "/"
                    + speach.peek().getSpeed() + "/"
                    + speach.peek().getAudioFormat();
            String soundMapFileUrl = soundFolderUrl + "/map.xml";
            String soundFileUrl = soundFolderUrl;

            String fileName = null;
            boolean state = false;
            if (speach.peek().isStoreMode()) {
                //System.out.println(soundMapFileUrl);
                try {
                    ModelXmlReader xmlReader = new ModelXmlReader();
                    ArrayList<SoundFile> list = xmlReader.getModelFromFile(soundMapFileUrl);

                    // map file exist, and we need to find sound file
                    int id = 0;
                    for (SoundFile s : list) {
                        int number = Integer.parseInt(s.getFile());
                        if (number > id) {
                            id = number;
                        }
                        if (s.getText().equals(text)) {
                            fileName = s.getFile();
                        }
                    }
                    if (fileName == null) {
                        // New text
                        fileName = "" + (id + 1);
                        ModelXmlWriter xmlWriter;
                        try {
                            xmlWriter = new ModelXmlWriter();
                            SoundFile soundFile = new SoundFile(text, fileName);
                            list.add(soundFile);
                            soundFileUrl += "/" + fileName + ".mp3";
                            state = copy(ttsUrl, soundFileUrl, soundFolderUrl);
                            if (state) {
                                xmlWriter.toXml(list, soundMapFileUrl);
                            }
                        } catch (ParserConfigurationException | TransformerException ex) {
                            Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        // Existing text
                        soundFileUrl += "/" + fileName + ".mp3";
                        File f = new File(soundFileUrl);        // check if file exists
                        if (!f.exists()) {
                            state = copy(ttsUrl, soundFileUrl, soundFolderUrl);
                        } else {
                            state = true;
                        }
                    }
                } catch (ParserConfigurationException | SAXException | IOException ex) {
                    // map file does not exist
                    try {
                        ModelXmlWriter xmlWriter = new ModelXmlWriter();
                        SoundFile soundFile = new SoundFile(text, "1");
                        ArrayList<SoundFile> soundFiles = new ArrayList<SoundFile>();
                        soundFiles.add(soundFile);
                        soundFileUrl += "/1" + ".mp3";
                        state = copy(ttsUrl, soundFileUrl, soundFolderUrl);
                        if (state) {
                            xmlWriter.toXml(soundFiles, soundMapFileUrl);
                        }
                    } catch (ParserConfigurationException | TransformerException ex1) {
                        Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } else {
                // We don't want to store
                soundFileUrl = "sounds/1.mp3";
                try
                {
                    state = copy(ttsUrl, soundFileUrl, "sounds");
                }
                catch(ConectionToTTSServerFailed e)
                {
                    state = false;
                }
            }

            if (state) {
                SoundPlayer mp3Player = new SoundPlayer(soundFileUrl);
                mp3Player.addLiestener(eventHandler);
                mp3Player.play(false);
                currentSay = speach.peek();
                speach.peek().setSoundPlayer(mp3Player);
            }
        }
        speach.poll();
    }

    public String getSoundPath(String text)
    {
        String t = text.replace(" ", "%20");
        text = t;
        String soundFolderUrl = "sounds/"
                + getLanguage() + "/"
                + getSpeechRate() + "/"
                + getAudioFormat();
        String soundMapFileUrl = soundFolderUrl + "/map.xml";
        String soundFileUrl = soundFolderUrl;

        String fileName = null;
        if (store) {
            try {
                ModelXmlReader xmlReader = new ModelXmlReader();
                ArrayList<SoundFile> list = xmlReader.getModelFromFile(soundMapFileUrl);

                // map file exist, and we need to find sound file
                int id = 0;
                for (SoundFile s : list) {
                    int number = Integer.parseInt(s.getFile());
                    if (number > id) {
                        id = number;
                    }
                    if (s.getText().equals(text)) {
                        fileName = s.getFile();
                    }
                }
                if (fileName == null) {
                    return "";
                } else {
                    soundFileUrl += "/" + fileName + ".mp3";
                    File f = new File(soundFileUrl);        // check if file exists
                    if (!f.exists()) {
                        return "";
                    }
                }
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                return "";
            }
        } else {
            return "";
        }
        return soundFileUrl;
    }
    
//    public boolean download(String text)  {
//        String t = text.replace(" ", "%20");
//        text = t;
//
//        String ttsUrl = site + "/?key=" + key
//                + "&src=" + text
//                + "&hl=" + getLanguage()
//                + "&r=" + getSpeechRate()
//                + "&c=" + audioCodec
//                + "&f=" + getAudioFormat();
//
//        String soundFolderUrl = "sounds/"
//                + getLanguage() + "/"
//                + getSpeechRate() + "/"
//                + getAudioFormat();
//        String soundMapFileUrl = soundFolderUrl + "/map.xml";
//        String soundFileUrl = soundFolderUrl;
//
//        String fileName = null;
//        boolean state = false;
//        
//        try {
//            ModelXmlReader xmlReader = new ModelXmlReader();
//            ArrayList<SoundFile> list = xmlReader.getModelFromFile(soundMapFileUrl);
//
//            // map file exist, and we need to find sound file
//            int id = 0;
//            for (SoundFile s : list) {
//                int number = Integer.parseInt(s.getFile());
//                if (number > id) {
//                    id = number;
//                }
//                if (s.getText().equals(text)) {
//                    fileName = s.getFile();
//                }
//            }
//            if (fileName == null) {
//                // New text
//                fileName = "" + (id + 1);
//                ModelXmlWriter xmlWriter;
//                try {
//                    xmlWriter = new ModelXmlWriter();
//                    SoundFile soundFile = new SoundFile(text, fileName);
//                    list.add(soundFile);
//                    soundFileUrl += "/" + fileName + ".mp3";
//                    state = copy(ttsUrl, soundFileUrl, soundFolderUrl);
//                    if (state) {
//                        xmlWriter.toXml(list, soundMapFileUrl);
//                    }
//                } catch (ParserConfigurationException | TransformerException ex) {
//                    Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                // Existing text
//                soundFileUrl += "/" + fileName + ".mp3";
//                File f = new File(soundFileUrl);        // check if file exists
//                if (!f.exists()) {
//                    state = copy(ttsUrl, soundFileUrl, soundFolderUrl);
//                } else {
//                    state = true;
//                }
//            }
//        } catch (ParserConfigurationException | SAXException | IOException ex) {
//            // map file does not exist
//            try {
//                ModelXmlWriter xmlWriter = new ModelXmlWriter();
//                SoundFile soundFile = new SoundFile(text, "1");
//                ArrayList<SoundFile> soundFiles = new ArrayList<SoundFile>();
//                soundFiles.add(soundFile);
//                soundFileUrl += "/1" + ".mp3";
//                state = copy(ttsUrl, soundFileUrl, soundFolderUrl);
//                if (state) {
//                    xmlWriter.toXml(soundFiles, soundMapFileUrl);
//                }
//            } catch (ParserConfigurationException | TransformerException ex1) {
//                Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//        }
//        if (state) {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
        
    private boolean copy(String ttsUrl, String filePath, String folderPath) {
        boolean success = (new File(folderPath)).mkdirs();
        try {
            URLConnection connection = new URL(ttsUrl).openConnection();
            InputStream inputStream;
            try {
                inputStream = connection.getInputStream();
            } catch (Exception e) {
                throw new ConectionToTTSServerFailed("Connection to TTS server failed. Please check if you have internet connection.");
            }
            OutputStream outputStream = new FileOutputStream(new File(filePath));
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            //System.out.println("end");
            return true;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private class EventHandler implements PlayerListener {

        @Override
        public void playerStarted(SoundPlayer notifier) {
        }

        @Override
        public void playerFinished(SoundPlayer notifier) {
            currentSay = null;
            if (speach.size() > 0) {
                say();
            } else {
                playing = false;
            }
        }
    }
}
