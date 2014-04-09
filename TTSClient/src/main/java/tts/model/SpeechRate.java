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
 * SpeechRate is used to specify speaking speed for the Speaker.
 * <p>It privides 21 constants:<br />
 * - 10 slow constants: from <b>slow1</b> to <b>slow10</b> - the slowest speaking rate, <br />
 * - 10 fast constants: from <b>fast1</b> to <b>fast10</b> - the fastest speaking rate, <br />
 * - <b>normal</b> - is the natural rate.
 * </p>
 * 
 * @author jakub_fara@hotmail.com
 */
public enum SpeechRate {
    
    slow10,     // the slowest rate
    slow9,
    slow8,
    slow7,
    slow6,
    slow5,
    slow4,
    slow3,
    slow2,
    slow1,
    normal,     // noraml rate
    fast1,
    fast2,
    fast3,
    fast4,
    fast5,
    fast6,
    fast7,
    fast8,
    fast9,
    fast10      // the fastest rate
}
