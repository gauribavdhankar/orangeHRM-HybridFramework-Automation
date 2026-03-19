package utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonDataReader {

	public static JSONArray getJsonData(String path) {
		
		try {
			String content = new String(
					Files.readAllBytes(Paths.get("src/test/resources/" + path)
							)
					);
			
			return new JSONArray(content);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to read JSON file: " + path);
		}
	}
	
	public static Object[][] getJsonDataAs2DArray(String path) {
		
		JSONArray data = getJsonData(path);
		
		Object[][] result = new Object[data.length()][2];
		
		for (int i = 0; i < data.length(); i++) {
			
			result[i][0] = data.getJSONObject(i).get("username");
			result[i][1] = data.getJSONObject(i).get("password");
			
		}
		
		return result;
		
	}
}
