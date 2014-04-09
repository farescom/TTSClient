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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tts.SoundManipulator;
import tts.Speaker;
import tts.model.AudioFormat;
import tts.model.StoreMode;

public class TTSTest {
	public static void main(String[] args) throws InterruptedException {

//		SoundManipulator.concatenate(
//				"sounds/system/intro.mp3",
//				"sounds/system/pause.mp3",
//				"sounds/system/final.mp3");
//				
//		List<String> list = new ArrayList<String>();
//		list.add("sounds/system/pause.mp3");
//		list.add("sounds/system/silence_4.mp3");
//		list.add("sounds/system/pause.mp3");
//		list.add("sounds/system/intro.mp3");
//		list.add("sounds/system/Bryan adams.mp3");
//		boolean status = SoundManipulator.concatenate(list, "sounds/system/haha.mp3");
//		System.out.println("Status: "+status);
		
		String key = "e113a69fbf0240c09441d4755a2bdfe9";
		Speaker speaker = Speaker.getSpeaker(key);
		speaker.setStoreMode(StoreMode.store);
        speaker.setAudioFormat(AudioFormat._48khz_16bit_stereo);
//		speaker.speak("We will open sound file from: sounds/system/intro.mp3");
//		speaker.play("sounds/system/final.mp3");
        speaker.speak("Application recording.");
        
        List<String> tab = new ArrayList<String>();
        //tab.add("sounds/16_16_s.mp3");
        //tab.add("sounds/16_16_m.mp3");
        //tab.add("sounds/16_8_s.mp3");
        //tab.add("sounds/16_8_m.mp3");
        tab.add("sounds/system/pause_converted.mp3");
        tab.add("sounds/1.mp3");
        tab.add("sounds/system/intro_converted.mp3");
        
        SoundManipulator.concatenate( tab, "sounds/result.mp3");
        
       
        
//        Connection c = null;
//        Statement stmt = null;
//        try {
//          Class.forName("org.sqlite.JDBC");
//          c = DriverManager.getConnection("jdbc:sqlite:test.db");
//          System.out.println("Opened database successfully");
//
//          stmt = c.createStatement();
//          String sql = "CREATE TABLE COMPANY " +
//                       "(ID INT PRIMARY KEY     NOT NULL," +
//                       " NAME           TEXT    NOT NULL, " + 
//                       " AGE            INT     NOT NULL, " + 
//                       " ADDRESS        CHAR(50), " + 
//                       " SALARY         REAL)"; 
//          stmt.executeUpdate(sql);
//          stmt.close();
//          c.close();
//        } catch ( Exception e ) {
//          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//          System.exit(0);
//        }
//        System.out.println("Table created successfully");
        
        
        
//        Connection c = null;
//        Statement stmt = null;
//        try {
//          Class.forName("org.sqlite.JDBC");
//          c = DriverManager.getConnection("jdbc:sqlite:test.db");
//          c.setAutoCommit(false);
//          System.out.println("Opened database successfully");
//
//          stmt = c.createStatement();
//          String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//                       "VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
//          stmt.executeUpdate(sql);
//
//          sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//                "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
//          stmt.executeUpdate(sql);
//
//          sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//                "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
//          stmt.executeUpdate(sql);
//
//          sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//                "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
//          stmt.executeUpdate(sql);
//
//          stmt.close();
//          c.commit();
//          c.close();
//        } catch ( Exception e ) {
//          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//          System.exit(0);
//        }
//        System.out.println("Records created successfully");
        
        
        
        
        
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
          while ( rs.next() ) {
             int id = rs.getInt("id");
             String  name = rs.getString("name");
             int age  = rs.getInt("age");
             String  address = rs.getString("address");
             float salary = rs.getFloat("salary");
             System.out.println( "ID = " + id );
             System.out.println( "NAME = " + name );
             System.out.println( "AGE = " + age );
             System.out.println( "ADDRESS = " + address );
             System.out.println( "SALARY = " + salary );
             System.out.println();
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Operation done successfully");
	}
}