package daar.front;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Application {
	
	private final static Logger logger = Logger.getLogger(Application.class);
	public static Properties properties = new Properties();
	
	static {
		try {
			InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("config.properties");
			properties.load(inputStream);
		} catch(Exception e) {
			logger.error(e);
		}
	}

}
