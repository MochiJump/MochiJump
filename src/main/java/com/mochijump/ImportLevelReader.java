package com.mochijump;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ImportLevelReader {
    private static final Logger LOG = LogManager.getLogger(ImportLevelReader.class);
    private LevelMap lMap;
    private Level[] level;
    private ArrayList<Rectangle> platforms = new ArrayList<>();
    public ArrayList<String> names = new ArrayList<>();
    private final ImportLevelConfig config = new ImportLevelConfig();

    public void ReadRest(LevelMap lm) {
    	if (LOG.isDebugEnabled()){
    		LOG.debug("Attempting to import levels from: {}", config.getImportURL());
		}
        ObjectMapper mapper = new ObjectMapper();
        String output;
        String output2 = null;

        try {
            //TODO make configurable.
            URL url = new URL(this.config.getImportURL());
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
            	LOG.error("unable to connect to host for level import: {}", e.getMessage());
            	e.printStackTrace();
        }
        try {
            lMap = lm;
            level = mapper.readValue(output2, Level[].class);
        } catch (IOException e) {
        	LOG.error("Unable to convert imported data to JSON. Tried to convert: {}", output2);
            e.printStackTrace();
        }
    }

    public void getLevelNames() {
        for (int i = 0; i < level.length; i++) {
            names.add(level[i].getlevelName());
            System.out.println(names);
        }
    }

    public void importLevel(int index) {
        for (int i = 0; i < level[index].getStartX().size(); i++) {
            platforms.add(new Rectangle((int) (level[index].getStartX().get(i)),
                    (int) (level[index].getStartY().get(i)), (int) (level[index].getWidth().get(i)),
                    (int) (level[index].getHeight().get(i))));
        }
        for (int i = 0; i < level[index].getHairClipStartX().size(); i++) {
            lMap.addHairClipNPC(level[index].getHairClipStartX().get(i),
                    level[index].getHairClipStartY().get(i));
        }
        if (level[index].getGooseStartX() != null) {
            for (int i = 0; i < level[index].getGooseStartX().size(); i++) {
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