package es.alejandro12120.igor.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSON {
	
	public static JsonArray getShopArray() {
		try {
			JsonParser jp = new JsonParser();
			JsonReader reader = new JsonReader(new FileReader("configuration/shop.json"));
			JsonElement element = jp.parse(reader);
			JsonObject object = element.getAsJsonObject();
			JsonArray array = object.get("shop").getAsJsonArray();
			return array;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JsonArray getUpdatesArray() {
		try {
			JsonParser jp = new JsonParser();
			JsonReader reader = new JsonReader(new FileReader("configuration/updates.json"));
			JsonElement element = jp.parse(reader);
			JsonObject object = element.getAsJsonObject();
			JsonArray array = object.get("updates").getAsJsonArray();
			return array;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JsonArray getLeaksArray() {
		try {
			JsonParser jp = new JsonParser();
			JsonReader reader = new JsonReader(new FileReader("configuration/leaks.json"));
			JsonElement element = jp.parse(reader);
			JsonObject object = element.getAsJsonObject();
			JsonArray array = object.get("leaks").getAsJsonArray();
			return array;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JsonObject getData() {
		try {
			JsonParser jp = new JsonParser();
			JsonReader reader = new JsonReader(new FileReader("configuration/data.json"));
			JsonElement element = jp.parse(reader);
			JsonObject object = element.getAsJsonObject();
			return object;
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
