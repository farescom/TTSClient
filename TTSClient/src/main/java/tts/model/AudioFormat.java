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

/**
 * AudioFormat is used to specify the format of sound files.
 * <p>There are many formats, which differ from each other in sound quality.
 * If your internet connection is good, then we advise you to use audio formats of higher quality like <b>_16khz_16bit_stereo</b>.</p>
 * 
 * @author jakub_fara@hotmail.com
 */
public enum AudioFormat {
    _8khz_8bit_mono,        //8 kHz, 8 Bit, Mono
    _8khz_8bit_stereo,  	//8 kHz, 8 Bit, Stereo
    _8khz_16bit_mono,       //8 kHz, 16 Bit, Mono
    _8khz_16bit_stereo, 	//8 kHz, 16 Bit, Stereo
    _11khz_8bit_mono,       //11 kHz, 8 Bit, Mono
    _11khz_8bit_stereo, 	//11 kHz, 8 Bit, Stereo
    _11khz_16bit_mono,      //11 kHz, 16 Bit, Mono
    _11khz_16bit_stereo,	//11 kHz, 16 Bit, Stereo
    _12khz_8bit_mono,       //12 kHz, 8 Bit, Mono
    _12khz_8bit_stereo,     //12 kHz, 8 Bit, Stereo
    _12khz_16bit_mono,      //12 kHz, 16 Bit, Mono
    _12khz_16bit_stereo,	//12 kHz, 16 Bit, Stereo
    _16khz_8bit_mono,       //16 kHz, 8 Bit, Mono
    _16khz_8bit_stereo,     //16 kHz, 8 Bit, Stereo
    _16khz_16bit_mono,      //16 kHz, 16 Bit, Mono
    _16khz_16bit_stereo,	//16 kHz, 16 Bit, Stereo
    _22khz_8bit_mono,       //22 kHz, 8 Bit, Mono
    _22khz_8bit_stereo,     //22 kHz, 8 Bit, Stereo
    _22khz_16bit_mono,      //22 kHz, 16 Bit, Mono
    _22khz_16bit_stereo,	//22 kHz, 16 Bit, Stereo
    _24khz_8bit_mono,       //24 kHz, 8 Bit, Mono
    _24khz_8bit_stereo,     //24 kHz, 8 Bit, Stereo
    _24khz_16bit_mono,      //24 kHz, 16 Bit, Mono
    _24khz_16bit_stereo,	//24 kHz, 16 Bit, Stereo
    _32khz_8bit_mono,       //32 kHz, 8 Bit, Mono
    _32khz_8bit_stereo,     //32 kHz, 8 Bit, Stereo
    _32khz_16bit_mono,      //32 kHz, 16 Bit, Mono
    _32khz_16bit_stereo,	//32 kHz, 16 Bit, Stereo
    _44khz_8bit_mono,       //44 kHz, 8 Bit, Mono
    _44khz_8bit_stereo,     //44 kHz, 8 Bit, Stereo
    _44khz_16bit_mono,      //44 kHz, 16 Bit, Mono
    _44khz_16bit_stereo,	//44 kHz, 16 Bit, Stereo
    _48khz_8bit_mono,       //48 kHz, 8 Bit, Mono
    _48khz_8bit_stereo,     //48 kHz, 8 Bit, Stereo
    _48khz_16bit_mono,      //48 kHz, 16 Bit, Mono
    _48khz_16bit_stereo,	//48 kHz, 16 Bit, Stereo
    _alaw_8khz_mono,       //ALaw8 kHz, Mono
    _alaw_8khz_stereo,      //ALaw, 8 kHz, Stereo
    _alaw_11khz_mono,       //ALaw, 11 kHz, Mono
    _alaw_11khz_stereo,     //ALaw, 11 kHz, Stereo
    _alaw_22khz_mono,       //ALaw, 22 kHz, Mono
    _alaw_22khz_stereo,     //ALaw, 22 kHz, Stereo
    _alaw_44khz_mono,       //ALaw, 44 kHz, Mono
    _alaw_44khz_stereo, 	//ALaw, 44 kHz, Stereo
    _ulaw_8khz_mono,        //uLaw8 kHz, Mono
    _ulaw_8khz_stereo,      //uLaw, 8 kHz, Stereo
    _ulaw_11khz_mono,       //uLaw, 11 kHz, Mono
    _ulaw_11khz_stereo,     //uLaw, 11 kHz, Stereo
    _ulaw_22khz_mono,       //uLaw, 22 kHz, Mono
    _ulaw_22khz_stereo,     //uLaw, 22 kHz, Stereo
    _ulaw_44khz_mono,       //uLaw, 44 kHz, Mono
    _ulaw_44khz_stereo, 	//uLaw, 44 kHz, Stereo
}
