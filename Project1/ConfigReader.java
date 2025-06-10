package Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {

    public static StorageType readConfig() {
        String configFile = "config.txt";
        StorageType defaultStorage = StorageType.TABSPLIT;

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("storage=")) {
                    String storageType = line.substring(8).trim().toLowerCase();
                    switch (storageType) {
                        case "tabsplit":
                            return StorageType.TABSPLIT;
                        case "json":
                            return StorageType.JSON;
                        case "sqlite":
                            return StorageType.SQLITE;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading config file, using default storage (tabsplit)");
        }
        return defaultStorage;
    }
}
