package alex.falendish.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {

    public static Properties readProperties() {
        try (InputStream input = new FileInputStream("./src/main/resources/META-INF/application.properties")) {

            Properties props = new Properties();

            // load a properties file
            props.load(input);

            // get the property value and print it out
            System.out.println(props.getProperty("db.url"));
            System.out.println(props.getProperty("db.user"));
            System.out.println(props.getProperty("db.password"));
            return props;

        } catch (IOException ex) {
            throw new RuntimeException("Failed to read properties form file as: " + ex.getMessage());
        }
    }
}
