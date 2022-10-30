package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
 

public class Log {

	private static final Logger Log = Logger.getLogger(Log.class.getName());

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

	public static void configureLogger() {
		//Инициализация Конфигурации LOG4j
		Properties logProperties = new Properties();
		try {
			logProperties.load(new FileInputStream("configs/log4j.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();
		logProperties.setProperty("log4j.appender.file.File",
				System.getProperty("user.dir") + "\\logs\\"
						+ new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(calendar.getTime()) + ".log");

		PropertyConfigurator.configure(logProperties);

	}

}
