package CATests.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static{
        reload();
    }

    public static void reload(){
        try(FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)){
            properties.load(fis);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
