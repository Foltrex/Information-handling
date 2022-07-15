package com.epam.infohandling.data;

import com.epam.infohandling.exception.DataException;
import com.epam.infohandling.exception.InformationHandlingException;
import com.epam.infohandling.logic.ExpressionCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DataReader {
    private static final Logger LOGGER = LogManager.getLogger(DataReader.class);

    private static final String LINE_FEED = "\n";

    public String read(String filePath) throws DataException {
        LOGGER.debug("Reading from file: " + filePath);

        StringBuilder text = new StringBuilder();

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                text.append(line).append(LINE_FEED);
            }

        } catch (IOException ex) {
            throw new DataException("File reading exception", ex);
        }

        return String.valueOf(text);
    }
}
