package com.mochijump;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author andrew
 */
public class VersionChecker {
    private final static Logger LOG = LogManager.getLogger(VersionChecker.class);
    // TODO this should be set elsewhere
    public String version = "0.2.5";

	/**
	 * Currently unconfigurabled, calls a server to get a string which is a version number and then compares that String
	 * to the application's version number.
	 *
	 * @return The string corresponding to outcome of call to server
	 */
	public String checkVersion() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("attempting to contact server to get latest version number");
        }
        String output;
        String output2 = "";

        try {
            // TODO could be configurable
            URL url = new URL("https://mochijump.com/test/version");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            while ((output = br.readLine()) != null) {
                output2 = output;
            }

        } catch (Exception e) {
            LOG.info("Unable to access server to check version number");
            return "unable to check new releases due to connection";
        }
        if (output2.equalsIgnoreCase(version)) {
            return "version is current";
        } else {
            return "new version has been released! download at mochijump.com!";
        }
    }
}

