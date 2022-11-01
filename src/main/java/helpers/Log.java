package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
public class Log {

	private static final Logger Log = LogManager.getLogger();

	public static void info(String message) {
		Log.info(message);
		System.out.println(message);
	}

	public static void error(String message) {
		Log.error(message);
		System.out.println(message);
	}

	public static void logToFile(String message) {
		Log.info(message);
	}
  
}
