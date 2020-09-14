package framework.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config extends Properties {

    public Config() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("yale/config.properties");
        try {
            this.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
