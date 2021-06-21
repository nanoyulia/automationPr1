package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	public static String readKey(String key) {
		String value = "";
		try (InputStream input = new FileInputStream("./src/test/resources/data/configuration.properties")) {
			Properties prop = new Properties();
			prop.load(input);

			value = prop.getProperty(key);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return value;
	}
}
