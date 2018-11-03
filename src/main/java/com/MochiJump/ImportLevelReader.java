package com.MochiJump;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ImportLevelReader {
		LevelMap lMap;
		Level[] level;
		ArrayList <Rectangle> platforms = new ArrayList<>();
		ArrayList <String> names = new ArrayList<>();

	
	 public void ReadRest (LevelMap lm) {
		ObjectMapper mapper = new ObjectMapper();
        String output = null;
        String output2 = null;
		
          try {

            URL url = new URL("https://mochijump.com/test/returnAll");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                output2 = output;
            }
        conn.disconnect();

        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Unable to connect! Are you connected to"
        			+ " the internet? Press ESC and select Start to run"
        			+ " without custom levels.", "Unable to connect to MochiJump.com", 
        			JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception in NetClientGet:- " + e);
        }
        
          
    try {
  	    	lMap = lm;
  			level = mapper.readValue(output2, Level[].class);
  			} catch (JsonMappingException e) {
  			    e.printStackTrace();
  			} catch (JsonGenerationException e) {
  			    e.printStackTrace();
  			} catch (IOException e) {
  			    e.printStackTrace();
  			}
	 }
	 
	 public void getLevelNames() {
		 for (int i=0; i<level.length; i++) {
			 names.add(level[i].getlevelName());
			 System.out.println(names);
		 }
	 }
	 
	 public void importLevel(int index) {
			for (int i=0; i<level[index].getStartX().size(); i++) {
  				platforms.add(new Rectangle ((int)(level[index].getStartX().get(i)), 
  						(int)(level[index].getStartY().get(i)), (int)(level[index].getWidth().get(i)), 
  						(int)(level[index].getHeight().get(i))));
  			}
			for (int i=0; i<level[index].getHairClipStartX().size(); i++) {
				lMap.addHairClipNPC(level[index].getHairClipStartX().get(i), 
						level[index].getHairClipStartY().get(i));
			}
			if (!(level[index].getGooseStartX()==null)) {
				for(int i=0; i<level[index].getGooseStartX().size(); i++) {
					lMap.addGooseNPC(level[index].getGooseStartX().get(i), 
							level[index].getGooseStartY().get(i));
				}
			}
			
			lMap.addGoalNPC(level[index].getGoalStartX(), level[index].getGoalStartY());
			
			lMap.addYLimiter();
			
			lMap.addMochi(level[index].getMochiStartX(), level[index].getMochiStartY());
			
			lMap.useWebImport(platforms);
	 }
}