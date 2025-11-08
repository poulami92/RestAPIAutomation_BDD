package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class utils {
	
	public RequestSpecification req;
	
	
	public RequestSpecification requestSpecification() throws IOException
	{
			
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));		
			req= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
	
		   return req;
		
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream("C:\\Users\\MSUSERSL123\\eclipse-workspace\\APIFramwork\\src\\test\\java\\resources\\global.properties");
		prop.load(fs);
		return prop.getProperty(key);
		
	}
	
	public Object getResponseKeyValue(String response,String key)
	{
		JsonPath js = new JsonPath(response);
		return js.get(key);
	}

}
