package CATests.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ConfigUpdater {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    public static void updateConfig(Map<String, String> data){
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)){
            properties.load(fis);
        } catch (IOException e){
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE_PATH)){
            for (Map.Entry<String, String> entry : data.entrySet()){
                properties.setProperty(entry.getKey(), entry.getValue());
            }

            properties.store(fos, null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
