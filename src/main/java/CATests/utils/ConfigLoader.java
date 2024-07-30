package CATests.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader
{
    private Properties properties = new Properties();
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static ConfigLoader instance;

    public ConfigLoader(){
        reload();
    }

    public void reload(){
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)){
            properties.load(fis);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }


    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value){
        properties.setProperty(key, value);
        saveProperties();
    }

    private void saveProperties(){
        try(FileOutputStream fos = new FileOutputStream(CONFIG_FILE_PATH)){
            properties.store(fos, null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
