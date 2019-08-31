package com.mochijump;

public class ImportLevelConfig {
    public static final String DEFAULT_IMPORT_URL = "https://mochijump.com/test/returnAll";
    private String importURL;

    public ImportLevelConfig() {
        // TODO figure out how you want to handle importing configs.
        try {
            this.importURL = this.getClass().getClassLoader().getResource("/ImportUrl").getFile();
        }catch (NullPointerException npe){
            this.importURL = DEFAULT_IMPORT_URL;
        }
    }

    public String getImportURL() {
        return importURL;
    }
}
