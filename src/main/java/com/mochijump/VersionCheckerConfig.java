package com.mochijump;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionCheckerConfig {
    private static final Logger LOG = LogManager.getLogger(VersionCheckerConfig.class);
    private static final String DEFAULT_IMPORT_URL = "https://mochijump.com/test/version";
    private static final Path DEFAULT_CONFIG_LOCATION = Paths.get(".", "VersionUrl");
    private String importURL;

    public VersionCheckerConfig() {
        // TODO figure out how you want to handle importing configs. (i.e. inside Jar won't work cleanly, should this
        //  be an external config?)
        try {
            BufferedReader reader = Files.newBufferedReader(DEFAULT_CONFIG_LOCATION);
            this.importURL = reader.readLine();
        }catch (IOException npe){
            if (LOG.isDebugEnabled()){
                LOG.debug("Version checker config either failed to load configuration file or was unable to read it");
            }
            this.importURL = DEFAULT_IMPORT_URL;
        }
    }

    public String getImportURL() {
        return this.importURL;
    }
}
