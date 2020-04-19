package es.alejandro12120.igor.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import es.alejandro12120.igor.Igor;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;
import java.util.TimerTask;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;

public class Common extends TimerTask {

	@Override
	public void run() {
		try {
			URL url = new URL("http://benbotfn.tk:8080/api/status");
			URLConnection request = url.openConnection();
			request.connect();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonObject rootobj = root.getAsJsonObject();

			JsonObject newdata = new JsonObject();

			JsonElement fortniteversion = new JsonPrimitive(String.valueOf(rootobj.get("currentFortniteVersionNumber").getAsDouble()));
			JsonElement total = new JsonPrimitive(rootobj.get("totalPakCount").getAsInt());
			JsonElement fortnitewindows = new JsonPrimitive(rootobj.get("currentFortniteVersion").getAsString());
			JsonElement encrypted = new JsonPrimitive(rootobj.get("encryptedPakFileCount").getAsInt());

			newdata.add("currentFortniteVersionNumber", fortniteversion);
			newdata.add("totalPakCount", total);
			newdata.add("currentFortniteVersion", fortnitewindows);
			newdata.add("encryptedPakFileCount", encrypted);

			URL url2 = new URL("http://benbotfn.tk:8080/api/aes");
			URLConnection request2 = url2.openConnection();
			request.connect();

			JsonParser jp2 = new JsonParser();
			JsonElement element = jp2.parse(new InputStreamReader((InputStream) request2.getContent()));
			JsonObject obj = element.getAsJsonObject();

			JsonElement aes = new JsonPrimitive(obj.get("mainKey").getAsString());

			newdata.add("aesKey", aes);

			JsonObject additionalObject = obj.get("additionalKeys").getAsJsonObject();
			newdata.add("additionalKeys", additionalObject);

			if (!additionalObject.isJsonNull()) {
				Set<String> paks = Common.getAdditionalKeys().keySet();
				Set<String> newpaks = additionalObject.keySet();
				if (paks.size() != newpaks.size()) {
					for(String pak : newpaks) {
						if(!paks.contains(pak)) {
							EmbedBuilder embd = new EmbedBuilder();
							embd.addField("Pak File Name", pak, true);
							embd.addField("AES Key", additionalObject.get(pak).getAsString(), false);
							embd.setColor(6054868);
							for(int i = 0; i<JSON.getUpdatesArray().size(); i++) {
								TextChannel channel = Igor.jda.getTextChannelById(JSON.getUpdatesArray().get(i).getAsString());
								channel.sendMessage(embd.build()).queue();
							}
						}
					}
				}

			}
			
			

			Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			try (FileWriter file = new FileWriter("configuration/data.json")) {
				file.write(gson.toJson(newdata));
				file.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static int getTotalPakCount() {
		return JSON.getData().get("totalPakCount").getAsInt();
	}

	public static int getEncryptedPakCount() {
		return JSON.getData().get("encryptedPakFileCount").getAsInt();
	}

	public static String getFortniteVersion() {
		return JSON.getData().get("currentFortniteVersionNumber").getAsString();
	}

	public static String getFortniteWindowsVersion() {
		return JSON.getData().get("currentFortniteVersion").getAsString();
	}

	public static String getAESKey() {
		return JSON.getData().get("aesKey").getAsString();
	}

	public static JsonObject getAdditionalKeys() {
		return JSON.getData().get("additionalKeys").getAsJsonObject();
	}

}
