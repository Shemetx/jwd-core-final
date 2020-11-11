package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();
    private static Logger logger = Logger.getLogger(PropertyReaderUtil.class.getName());

    private PropertyReaderUtil() {
    }

    public static Properties getInstance() {
        return properties;
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() throws IOException {
        logger.info("Start to fill properties from src/main/resources/application.properties");
        final String propertiesFileName = "src/main/resources/application.properties";
        try (InputStream inputStream = new FileInputStream(propertiesFileName)) {
            properties.load(inputStream);
            logger.info("End of filling properties from src/main/resources/application.properties");
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File not found: src/main/resources/application.properties");
            e.printStackTrace();
        }
    }

    public static String inputRootDir() {
        return properties.getProperty("inputRootDir");
    }

    public static String outputRootDir() {
        return properties.getProperty("outputRootDir");
    }

    public static String crewFileName() {
        return properties.getProperty("crewFileName");
    }

    public static String missionsFileName() {
        return properties.getProperty("missionsFileName");
    }

    public static String spaceshipsFileName() {
        return properties.getProperty("spaceshipsFileName");
    }

    public static String fileRefreshRate() {
        return properties.getProperty("fileRefreshRate");
    }

    public static String dateTimeFormat() {
        return properties.getProperty("dateTimeFormat");
    }


}
