package com.MochiJump;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionChecker {
	
	public String version = "0.2.4";
	
	
	public String checkVersion() {
		String output;
		String output2 = "";
	
		try {
			
			URL url = new URL("http://mochijump.com/test/version");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
            
		}
		catch (Exception e) {
			return "unable to check new releases due to connection";
		}
		if (output2.equalsIgnoreCase(version)) {
  			 return "version is current";
  		 } else {
  			return "new version has been released! download at mochijump.com!"; 
  		 }
		
		
	}

}

