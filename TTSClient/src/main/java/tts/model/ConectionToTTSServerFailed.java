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
 * An exceptin thrown when a Speaker can not access TTS server through the internet (No internet connection).
 * 
 * @author jakub_fara@hotmail.com
 */
public class ConectionToTTSServerFailed extends Error {

    /**
     * Creates a new instance of
     * <code>TooLongString</code> without detail message.
     */
    public ConectionToTTSServerFailed() {
    }

    /**
     * Constructs an instance of
     * <code>TooLongString</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ConectionToTTSServerFailed(String msg) {
        super(msg);
    }
}
