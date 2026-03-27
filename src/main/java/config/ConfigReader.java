package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
		
		private static Properties properties = new Properties(); //read container's key-values

		//static block for load once
		static {
			try {
				InputStream input = ConfigReader.class
						.getClassLoader()
						.getResourceAsStream("config.properties"); //Find config.properties from classpath.
				
				properties.load(input); //This loads all key-value pairs into memory.
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//Generic getter (imp for flexibility)
		public static String get(String key) {
			return properties.getProperty(key);
		}
		public String getExecutionMode() {
			return properties.getProperty("execution.mode");
		}
		public String getGridUrl() {
			return properties.getProperty("grid.url");
		}
		public String getUrl() {
			return properties.getProperty("url");
		}
		public String getBrowser() {
			return properties.getProperty("browser");
		}
		public String getUsername() {
			return properties.getProperty("username");
		}
		public String getPassword() {
			return properties.getProperty("password");
		}
		public int getTimeout() {
			return Integer.parseInt(properties.getProperty("timeout"));
		}
		public boolean isHeadless() {
			return Boolean.parseBoolean(properties.getProperty("headless"));
		}
}
