package com.MochiJump;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ImportLevelReader {

	
	 public void ReadRest () {
		ObjectMapper mapper = new ObjectMapper();
        String output = null;
        String output2 = null;
		
          try {

            URL url = new URL("http://mochijump.com/test/returnAll");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
		  // this is strange this while statement:
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                output2 = output;
            }
        conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        
          
        // okay this works great! I can get the information for all of the levels produced here:
        // NOTE: importing everything from getAll is extremely inefficient, better to build a specific API for this on MJLE
  	    try {
  			Level[] level = mapper.readValue(output2, Level[].class);
  			LevelMap lm = new LevelMap();
  			System.out.println(level[level.length-1].getStartX());
  			ArrayList <Rectangle> platforms = new ArrayList<>();
  			for (int i=0; i<level[level.length-1].getStartX().size(); i++) {
  				platforms.add(new Rectangle ((int)(level[level.length-1].getStartX().get(i)), (int)(level[level.length-1].getStartY().get(i)), (int)(level[level.length-1].getWidth().get(i)), (int)(level[level.length-1].getHeight().get(i))));
  			}
  			System.out.println(platforms);
  			lm.useWebImport(platforms);
  			} catch (JsonMappingException e) {
  			    e.printStackTrace();
  			} catch (JsonGenerationException e) {
  			    e.printStackTrace();
  			} catch (IOException e) {
  			    e.printStackTrace();
  			}
  }
}