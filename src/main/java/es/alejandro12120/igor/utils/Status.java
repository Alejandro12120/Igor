package es.alejandro12120.igor.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Status {

	public static boolean isOperational() {
		try {
			URL url = new URL("https://status.epicgames.com/index.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(reader);
			JsonObject obj = root.getAsJsonObject();
			JsonArray array = obj.get("components").getAsJsonArray();
			JsonObject arrayobj = array.get(0).getAsJsonObject();
			String status = arrayobj.get("status").getAsString();
			return status.equalsIgnoreCase("operational");
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static String getGeneralStatus() {
		try {
			URL url = new URL("https://status.epicgames.com/index.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(reader);
			JsonObject obj = root.getAsJsonObject();
			JsonObject status = obj.get("status").getAsJsonObject();
			String general = status.get("description").getAsString();
			return general;
		} catch (IOException ex) {
			ex.printStackTrace();
			return "STATUS NOT FOUND";
		}
	}
	
	public static String getFortniteStatus() {
		try {
			URL url = new URL("https://status.epicgames.com/index.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(reader);
			JsonObject obj = root.getAsJsonObject();
			JsonArray array = obj.get("components").getAsJsonArray();
			JsonObject arrayobj = array.get(0).getAsJsonObject();
			String status = arrayobj.get("status").getAsString();
			String statusc = status.substring(0, 1).toUpperCase() + status.substring(1);
			return statusc;
		}catch(IOException ex) {
			ex.printStackTrace();
			return "UNABLE TO ACCESS DATA";
		}
	}

}
