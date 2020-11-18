package api.ws.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertiesUtil {
	
	private static AppPropertiesUtil instance = null;
	
	private AppPropertiesUtil(){}
	
	private String apiresturl;
	
	public static AppPropertiesUtil getInstance(){
		
		if(instance==null){

			try {
				File configDir = new File(System.getProperty("catalina.base"), "conf");
				File configFile = new File(configDir, "app.properties");
				InputStream stream = new FileInputStream(configFile);
				Properties props = new Properties();
				props.load(stream);
				System.out.println(props.getProperty("apiresturl"));
				instance.setApiresturl(props.getProperty("apiresturl"));
			} catch (FileNotFoundException e) {
				System.out.println("PropertiesUtil FileNotFoundException");
			} catch (IOException e) {
				System.out.println("PropertiesUtil IOException");
			}
			
		}
		return instance;
		
	}

	public String getApiresturl() {
		return apiresturl;
	}

	public void setApiresturl(String apiresturl) {
		this.apiresturl = apiresturl;
	}
	
}
