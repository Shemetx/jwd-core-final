package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public enum ApplicationProperties {
    //todo
    INSTANCE;

    public static ApplicationProperties getSingleton() { // static getter
        return INSTANCE;
    }

    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;

    private final Integer fileRefreshRate;
    private final String dateTimeFormat;

    ApplicationProperties() {
        this.inputRootDir = PropertyReaderUtil.inputRootDir();
        this.outputRootDir = PropertyReaderUtil.outputRootDir();
        this.crewFileName = PropertyReaderUtil.crewFileName();
        this.missionsFileName = PropertyReaderUtil.missionsFileName();
        this.spaceshipsFileName = PropertyReaderUtil.spaceshipsFileName();
        this.fileRefreshRate = Integer.parseInt(PropertyReaderUtil.fileRefreshRate());
        this.dateTimeFormat = PropertyReaderUtil.dateTimeFormat();
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }
}
