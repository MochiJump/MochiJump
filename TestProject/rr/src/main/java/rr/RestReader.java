package rr;
/*
* okay these test classes do what I want them to do, now I just need to combine them to the main program
*
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RestReader {

	
	public static void main(String[] args) {
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
        // NOTE: imprting everything from getAll is extremely ineffecient, better to build a specific API for this on MJLE
  	    try {
  			Level[] level = mapper.readValue(output2, Level[].class);
  			System.out.println(level[level.length-1].getStartX());
  			System.out.println(level[level.length-1].getlevelName());	  
  			} catch (JsonMappingException e) {
  			    e.printStackTrace();
  			} catch (JsonGenerationException e) {
  			    e.printStackTrace();
  			} catch (IOException e) {
  			    e.printStackTrace();
  			}
  }
}