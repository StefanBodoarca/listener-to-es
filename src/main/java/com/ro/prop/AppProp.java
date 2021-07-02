package com.ro.prop;

import java.io.*;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public final class AppProp {

    private static Logger logger = LogManager.getLogger(AppProp.class);

    private static InputStream inputStream;
    public static String ELASTIC_SEARCH_URL = "";
    public static String DOMAIN = "";
    public static int PORT = -1;
    public static String PROTOCOL = "";
    private AppProp() {}

    public static void loadConfig() throws IOException {
        try {
            Properties prop = new Properties();

            inputStream = AppProp.class
                    .getClassLoader().getResourceAsStream("config.properties");


            Reader reader = new InputStreamReader(inputStream, "UTF-8");

            if (reader != null) {
                prop.load(reader);
            } else {
                throw new FileNotFoundException("property file not found in the classpath");
            }
            ELASTIC_SEARCH_URL = prop.getProperty("ELASTIC_SEARCH_URL");
            DOMAIN = prop.getProperty("DOMAIN");
            PORT = Integer.parseInt(prop.getProperty("PORT"));
            PROTOCOL = prop.getProperty("PROTOCOL");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            inputStream.close();
        }
    }
}
