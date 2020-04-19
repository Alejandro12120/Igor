package es.alejandro12120.igor.shop;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import es.alejandro12120.igor.Igor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import net.dv8tion.jda.core.entities.TextChannel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import twitter4j.StatusUpdate;

public class ShopManager {

	private static ShopManager instance;

	public ShopManager() {
		instance = this;
	}

	public static ShopManager getShopManager() {
		return instance;
	}

	public String getDate() {
		try {
			String token = "90213c32-6025-41f8-8eec-6ad027dfe23d";
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("https://fnbr.co/api/shop");
			httpGet.addHeader("x-api-key", token);

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
			JsonObject rootobj = root.getAsJsonObject();
			String date = rootobj.getAsJsonObject("data").get("date").getAsString().split("T")[0];
			String[] spplited = date.split("-");

			return spplited[2] + "/" + spplited[1] + "/" + spplited[0];
		} catch (Exception e) {
			e.printStackTrace();
			return "24/02/2005";
		}
	}

	public String getBruteDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String time = dateFormat.format(date);
		return time;
	}

	public JsonArray getFeaturedArray() {
		try {
			String token = "90213c32-6025-41f8-8eec-6ad027dfe23d";
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("https://fnbr.co/api/shop");
			httpGet.addHeader("x-api-key", token);

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
			JsonObject rootobj = root.getAsJsonObject();
			JsonArray array = rootobj.getAsJsonObject("data").getAsJsonArray("featured");
			return array;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public JsonArray getDailyArray() {
		try {
			String token = "90213c32-6025-41f8-8eec-6ad027dfe23d";
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("https://fnbr.co/api/shop");
			httpGet.addHeader("x-api-key", token);

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
			JsonObject rootobj = root.getAsJsonObject();
			JsonArray array = rootobj.getAsJsonObject("data").getAsJsonArray("daily");
			return array;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void createNewShopImage() {
		try {
			BufferedImage img = ImageIO.read(new File("shop_background.png"));
			BufferedImage temp = new BufferedImage(3840, 2160, BufferedImage.TYPE_INT_RGB);

			Graphics graphics = temp.getGraphics();
			graphics.drawImage(img, 0, 0, null);

			graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 120));
			graphics.drawString(ShopManager.getShopManager().getDate(), 1760, 650);

			System.out.println("Retrieving shop info...");
			System.out.println(" ");
			System.out.println("Featured: ");
			for (int i = 0; i < getFeaturedArray().size(); i++) {
				System.out.println("Name: "+getFeaturedArray().get(i).getAsJsonObject().get("name").getAsString() + " | Description: "+getFeaturedArray().get(i).getAsJsonObject().get("description").getAsString() + " | Price: " + getFeaturedArray().get(i).getAsJsonObject().get("price").getAsString() + " | Last Seen: "+getFeaturedArray().get(i).getAsJsonObject().get("history").getAsJsonObject().get("lastSeen").getAsString().split("T")[0]);
				if (i == 0) {
					JsonObject element = getFeaturedArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 90, 760, null);

					graphics.drawImage(icon_resized, 95, 745, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 150, 1245);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 260, 1305);
					graphics.drawImage(vbucks_resized, 180, 1248, null);
				}

				if (i == 1) {
					JsonObject element = getFeaturedArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 570, 760, null);

					graphics.drawImage(icon_resized, 575, 745, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 620, 1245);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 740, 1305);
					graphics.drawImage(vbucks_resized, 660, 1248, null);
				}
				if (i == 2) {
					JsonObject element = getFeaturedArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 1050, 760, null);

					graphics.drawImage(icon_resized, 1055, 745, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 1110, 1245);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 1220, 1305);
					graphics.drawImage(vbucks_resized, 1140, 1248, null);
				}
				if (i == 3) {
					JsonObject element = getFeaturedArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +	
					graphics.drawImage(background_resized, 90, 1340, null);

					graphics.drawImage(icon_resized, 95, 1325, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 150, 1825);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 260, 1885);
					graphics.drawImage(vbucks_resized, 180, 1828, null);
				}
				if (i == 4) {
					JsonObject element = getFeaturedArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +	
					graphics.drawImage(background_resized, 570, 1340, null);

					graphics.drawImage(icon_resized, 575, 1325, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 650, 1825);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 740, 1885);
					graphics.drawImage(vbucks_resized, 660, 1828, null);
				}
				if (i == 5) {
					JsonObject element = getFeaturedArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +	
					graphics.drawImage(background_resized, 1050, 1340, null);

					graphics.drawImage(icon_resized, 1055, 1325, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 1110, 1825);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 1220, 1885);
					graphics.drawImage(vbucks_resized, 1140, 1828, null);
				}
			}
			System.out.println(" ");
			System.out.println("Daily: ");
			for (int i = 0; i < getDailyArray().size(); i++) {
				System.out.println("Name: "+getDailyArray().get(i).getAsJsonObject().get("name").getAsString() + " | Description: "+getDailyArray().get(i).getAsJsonObject().get("description").getAsString() + " | Price: " + getDailyArray().get(i).getAsJsonObject().get("price").getAsString() + " | Last Seen: "+getDailyArray().get(i).getAsJsonObject().get("history").getAsJsonObject().get("lastSeen").getAsString().split("T")[0]);
				if (i == 0) {
					JsonObject element = getDailyArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 2325, 760, null);

					graphics.drawImage(icon_resized, 2340, 745, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 2340, 1245);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 2490, 1305);
					graphics.drawImage(vbucks_resized, 2410, 1248, null);
				}
				if (i == 1) {
					JsonObject element = getDailyArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 2805, 760, null);

					graphics.drawImage(icon_resized, 2820, 745, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 2820, 1245);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 2970, 1305);
					graphics.drawImage(vbucks_resized, 2890, 1248, null);
				}
				if (i == 2) {
					JsonObject element = getDailyArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 3285, 760, null);

					graphics.drawImage(icon_resized, 3300, 745, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 3300, 1245);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 3450, 1305);
					graphics.drawImage(vbucks_resized, 3370, 1248, null);
				}
				if (i == 3) {
					JsonObject element = getDailyArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 2325, 1340, null);

					graphics.drawImage(icon_resized, 2340, 1325, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 2340, 1825);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 2490, 1885);
					graphics.drawImage(vbucks_resized, 2410, 1828, null);
				}
				if (i == 4) {
					JsonObject element = getDailyArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 2805, 1340, null);

					graphics.drawImage(icon_resized, 2820, 1325, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 2820, 1825);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 2970, 1885);
					graphics.drawImage(vbucks_resized, 2890, 1828, null);
				}
				if (i == 5) {
					JsonObject element = getDailyArray().get(i).getAsJsonObject();
					JsonObject images = element.getAsJsonObject("images");

					String rarity = element.get("rarity").getAsString().toLowerCase();
					String name = element.get("name").getAsString();
					String[] names = name.split(" ");
					String price = element.get("price").getAsString();
					String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString() : images.get("featured").getAsString().replace(".png", "_512.png"));
					URL icon_url = new URL(icon_string);
					URLConnection icon_connection = icon_url.openConnection();
					icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

					BufferedImage vbucks = ImageIO.read(new File("icon_vbucks.png"));
					Image vbucks_resized = vbucks.getScaledInstance(65, 65, Image.SCALE_DEFAULT);

					BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
					Image icon_resized = icon.getScaledInstance(420, 420, Image.SCALE_DEFAULT);

					BufferedImage background = getRarityBackGround(rarity);
					Image background_resized = background.getScaledInstance(457, 557, Image.SCALE_DEFAULT);

					//width, height
					//-                    +
					//         -
					//     
					//         +
					graphics.drawImage(background_resized, 3285, 1340, null);

					graphics.drawImage(icon_resized, 3300, 1325, null);

					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, (names.length == 2) ? 70 : 60));
					graphics.drawString(name, 3300, 1825);
					graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 65));
					graphics.drawString(price, 3450, 1885);
					graphics.drawImage(vbucks_resized, 3370, 1828, null);
				}
			}

			graphics.dispose();

			File f = new File("shops/" + ShopManager.getShopManager().getBruteDate() + ".png");
			ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

			ImageOutputStream ios = ImageIO.createImageOutputStream(f);
			writer.setOutput(ios);
			writer.write(temp);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void sendShop(TextChannel channel) {
		channel.sendFile(new File("shops/" + ShopManager.getShopManager().getBruteDate() + ".png")).queue();
	}

	public File getShopFile() {
		return new File("shops/" + ShopManager.getShopManager().getBruteDate() + ".png");
	}

	public boolean shopExists() {
		return new File("shops/" + ShopManager.getShopManager().getBruteDate() + ".png").exists();
	}

	public BufferedImage getRarityBackGround(String rarity) {
		try {
			switch (rarity) {
				case "legendary": {
					return ImageIO.read(new File("rarities/legendary.png"));
				}
				case "dark": {
					return ImageIO.read(new File("rarities/dark.png"));
				}
				case "epic": {
					return ImageIO.read(new File("rarities/epic.png"));
				}
				case "marvel": {
					return ImageIO.read(new File("rarities/marvel.png"));
				}
				case "dc": {
					return ImageIO.read(new File("rarities/dc.png"));
				}
				case "icon": {
					return ImageIO.read(new File("rarities/icon.png"));
				}
				case "rare": {
					return ImageIO.read(new File("rarities/rare.png"));
				}
				case "uncommon": {
					return ImageIO.read(new File("rarities/uncommon.png"));
				}
				case "common": {
					return ImageIO.read(new File("rarities/common.png"));
				}
				default: {
					return ImageIO.read(new File("rarities/uncommon.png"));
				}
			}
		} catch (IOException e) {

		}
		return null;
	}

	public void tweetShop() {
		if (!shopExists()) {
			createNewShopImage();
		}
		StatusUpdate status = new StatusUpdate("#Fortnite Item Shop for " + getDate() + " | Use creator code 'Alejandro12120' to support.");
		status.media(ShopManager.getShopManager().getShopFile());
		try {
			Igor.getIgor().getShopTwitter().updateStatus(status);
		} catch (Exception e) {
			System.out.println("ERROR WHILE POSTING SHOP TWEET");
		}
	}

	public int getUpVotes() {
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("https://tyo-lb.fnbr.co/api/shop/votes");

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
			JsonObject rootobj = root.getAsJsonObject().get("data").getAsJsonObject();
			return rootobj.get("up").getAsInt();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int getNeutralVotes() {
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("https://tyo-lb.fnbr.co/api/shop/votes");

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
			JsonObject rootobj = root.getAsJsonObject().get("data").getAsJsonObject();
			return rootobj.get("neutral").getAsInt();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int getDownVotes() {
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("https://tyo-lb.fnbr.co/api/shop/votes");

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
			JsonObject rootobj = root.getAsJsonObject().get("data").getAsJsonObject();
			return rootobj.get("down").getAsInt();
		} catch (Exception e) {
			return 0;
		}
	}

}
