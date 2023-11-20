package be.uantwerpen.fti.ei.gamelogic.Helpers;

import java.io.*;
import java.util.*;

import static java.lang.Double.parseDouble;

public class Constants {

    static Properties prop = new Properties();

    /**
     * loads the values in the properties into the properties of the game
     * @throws IOException
     */
    public static void loadProperties() throws IOException {
        InputStream input = new FileInputStream("src/resources/ConfigFile.properties");
        prop.load(input);
    }

    /**
     * Getter Properties
     * @return the properties of the game
     */
    public static Properties getConstants() {
        return prop;
    }
}
