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
 * An error thrown in Speaker's method ({@link tts.Speaker#speak(java.lang.String)}) when the length of text parameter passed to the method is longer that 1000 chars.
 * @author jakub_fara@hotmail.com
 */
public class TooLongText extends Error {

    /**
     * Creates a new instance of
     * <code>TooLongString</code> without detail message.
     */
    public TooLongText() {
    }

    /**
     * Constructs an instance of
     * <code>TooLongString</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public TooLongText(String msg) {
        super(msg);
    }
}
