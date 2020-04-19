package es.alejandro12120.igor.news;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import net.dv8tion.jda.core.entities.TextChannel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class NewsManager {

	private static NewsManager instance;

	public NewsManager() {
		instance = this;
	}

	public static NewsManager getNewsManager() {
		return instance;
	}

	private JsonArray getNews() {
		try {
			String key = "95101467fadf6a73024b0aba98c46d02c266b2b665050b75922c7c073393e81e";
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("https://fnapi.me/api/news/");
			httpGet.addHeader("Authorization", key);

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
			JsonObject rootobj = root.getAsJsonObject();
			return rootobj.get("data").getAsJsonArray();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void createImage() {
		try {
			BufferedImage img = ImageIO.read(new File("news_background.png"));
			BufferedImage temp = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);

			Graphics graphics = temp.getGraphics();
			graphics.drawImage(img, 0, 0, null);

			for (int i = 0; i < getNews().size(); i++) {
				if (i == 0) {
					JsonObject object = getNews().get(i).getAsJsonObject();
					String title = object.get("title").getAsString();
					String description = object.get("body").getAsString();

					String image_url = object.get("image").getAsString();
					URL url = new URL(image_url);
					URLConnection image_connection = url.openConnection();
					image_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage image = ImageIO.read(image_connection.getInputStream());
					Image image_resized = image.getScaledInstance(570, 290, Image.SCALE_DEFAULT);

					BufferedImage adspace = ImageIO.read(new File("adspace.png"));
					Image adspace_resized = adspace.getScaledInstance(130, 55, Image.SCALE_DEFAULT);

					graphics.drawRect(30, 440, 600, 450);
					graphics.setColor(new Color(255, 255, 255));
					graphics.fillRect(30, 440, 600, 450);

					graphics.setColor(new Color(0, 0, 0));
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 40));
					graphics.drawString(title.toUpperCase(), 45, 780);
					graphics.setColor(new Color(135, 206, 235));
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 27));
					if (description.length() > 52) {
						graphics.drawString(description.substring(0, 57), 45, 815);
						if(description.length() > 57 && description.length() > 117) {
							graphics.drawString(description.substring(57, 117), 45, 835);
							if(description.length() > 118) {
								graphics.drawString(description.substring(118, description.length()), 45, 855);
							}
						}else{
							graphics.drawString(description.substring(57, description.length()), 45, 835);
						}
					} else {
						graphics.drawString(description, 45, 815);
					}

					graphics.drawImage(image_resized, 46, 450, null);

					if (!object.get("adspace").isJsonNull()) {
						graphics.drawImage(adspace_resized, 23, 420, null);
						graphics.setColor(new Color(255, 255, 255));
						graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 40));
						graphics.drawString(object.get("adspace").getAsString(), 50, 460);
					}
				}
				if (i == 1) {
					JsonObject object = getNews().get(i).getAsJsonObject();
					String title = object.get("title").getAsString();
					String description = object.get("body").getAsString();

					String image_url = object.get("image").getAsString();
					URL url = new URL(image_url);
					URLConnection image_connection = url.openConnection();
					image_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage image = ImageIO.read(image_connection.getInputStream());
					Image image_resized = image.getScaledInstance(570, 290, Image.SCALE_DEFAULT);

					BufferedImage adspace = ImageIO.read(new File("adspace.png"));
					Image adspace_resized = adspace.getScaledInstance(130, 55, Image.SCALE_DEFAULT);

					graphics.drawRect(645, 440, 600, 450);
					graphics.setColor(new Color(255, 255, 255));
					graphics.fillRect(645, 440, 600, 450);

					graphics.setColor(new Color(0, 0, 0));
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 40));
					graphics.drawString(title.toUpperCase(), 660, 780);
					graphics.setColor(new Color(135, 206, 235));
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 27));
					if (description.length() > 52) {
						graphics.drawString(description.substring(0, 62), 660, 815);
						if(description.length() > 62 && description.length() > 131) {
							graphics.drawString(description.substring(62, 132), 660, 835);
							if(description.length() > 132) {
								graphics.drawString(description.substring(133, description.length()), 655, 855);
							}
						}else{
							graphics.drawString(description.substring(62, description.length()), 660, 835);
						}
					} else {
						graphics.drawString(description, 660, 815);
					}

					graphics.drawImage(image_resized, 661, 450, null);

					if (!object.get("adspace").isJsonNull()) {
						graphics.drawImage(adspace_resized, 620, 420, null);
						graphics.setColor(new Color(255, 255, 255));
						graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 40));
						graphics.drawString(object.get("adspace").getAsString(), 648, 460);
					}
				}
				if (i == 2) {
					JsonObject object = getNews().get(i).getAsJsonObject();
					String title = object.get("title").getAsString();
					String description = object.get("body").getAsString();

					String image_url = object.get("image").getAsString();
					URL url = new URL(image_url);
					URLConnection image_connection = url.openConnection();
					image_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage image = ImageIO.read(image_connection.getInputStream());
					Image image_resized = image.getScaledInstance(570, 290, Image.SCALE_DEFAULT);

					BufferedImage adspace = ImageIO.read(new File("adspace.png"));
					Image adspace_resized = adspace.getScaledInstance(130, 55, Image.SCALE_DEFAULT);

					graphics.drawRect(1260, 440, 600, 450);
					graphics.setColor(new Color(255, 255, 255));
					graphics.fillRect(1260, 440, 600, 450);

					graphics.setColor(new Color(0, 0, 0));
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 40));
					graphics.drawString(title.toUpperCase(), 1275, 780);
					graphics.setColor(new Color(135, 206, 235));
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 27));
					if (description.length() > 52) {
						graphics.drawString(description.substring(0, 56), 1275, 815);
						if(description.length() > 56 && description.length() > 111) {
							graphics.drawString(description.substring(56, 112), 1275, 840);
							if(description.length() > 112) {
								graphics.drawString(description.substring(112, description.length()), 1275, 865);
							}
						}else{
							graphics.drawString(description.substring(56, description.length()), 1275, 840);
						}
					} else {
						graphics.drawString(description, 1275, 815);
					}

					graphics.drawImage(image_resized, 1277, 450, null);

					if (!object.get("adspace").isJsonNull()) {
						graphics.drawImage(adspace_resized, 1235, 420, null);
						graphics.setColor(new Color(255, 255, 255));
						graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 40));
						graphics.drawString(object.get("adspace").getAsString(), 1265, 460);
					}
				}
			}

			/* Save the image */
			graphics.dispose();

			File f = new File("news/in-game-news.png");
			ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

			ImageOutputStream ios = ImageIO.createImageOutputStream(f);
			writer.setOutput(ios);
			writer.write(temp);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void sendNews(TextChannel channel) {
		channel.sendFile(new File("news/in-game-news.png")).queue();
	}

}
