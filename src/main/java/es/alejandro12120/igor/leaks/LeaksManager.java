package es.alejandro12120.igor.leaks;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.utils.Common;
import es.alejandro12120.igor.utils.JSON;
import java.awt.Font;
import java.awt.Graphics;
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

public class LeaksManager {

	private static LeaksManager instance;

	public LeaksManager() {
		this.instance = this;
	}

	public static LeaksManager getLeaksManager() {
		return instance;
	}

	public void createImage() {
		try {
			int defaultwidth = 4176;
			int defaultheight = 522;
			if (getUpcoming().size() <= 8) {
				BufferedImage temp = new BufferedImage(defaultwidth, defaultheight, BufferedImage.TYPE_INT_ARGB);

				Graphics graphics = temp.getGraphics();

				for (int i = 0; i < getUpcoming().size(); i++) {
					//Draw all
				}

				graphics.dispose();

				File f = new File("leaks/" + Common.getFortniteVersion() + "_cosmetics.png");
				ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

				ImageOutputStream ios = ImageIO.createImageOutputStream(f);
				writer.setOutput(ios);
				writer.write(temp);

			} else if (getUpcoming().size() > 8) {
				if (getUpcoming().size() <= 16) {
					defaultheight = defaultheight * 2;
					BufferedImage temp = new BufferedImage(defaultwidth, defaultheight, BufferedImage.TYPE_INT_ARGB);

					Graphics graphics = temp.getGraphics();

					for (int i = 0; i < getUpcoming().size(); i++) {
						//Draw all
					}

					graphics.dispose();

					File f = new File("leaks/" + Common.getFortniteVersion() + "_cosmetics.png");
					ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

					ImageOutputStream ios = ImageIO.createImageOutputStream(f);
					writer.setOutput(ios);
					writer.write(temp);
				}
				if (getUpcoming().size() <= 24) {
					defaultheight = defaultheight * 3;
					BufferedImage temp = new BufferedImage(defaultwidth, defaultheight, BufferedImage.TYPE_INT_ARGB);

					Graphics graphics = temp.getGraphics();

					for (int i = 0; i < getUpcoming().size(); i++) {
						//Draw all
					}

					graphics.dispose();

					File f = new File("leaks/" + Common.getFortniteVersion() + "_cosmetics.png");
					ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

					ImageOutputStream ios = ImageIO.createImageOutputStream(f);
					writer.setOutput(ios);
					writer.write(temp);
				}
				if (getUpcoming().size() <= 32) {
					defaultheight = defaultheight * 4;
					BufferedImage temp = new BufferedImage(defaultwidth, defaultheight, BufferedImage.TYPE_INT_ARGB);

					Graphics graphics = temp.getGraphics();

					for (int i = 0; i < getUpcoming().size(); i++) {
						//Draw all
					}

					graphics.dispose();

					File f = new File("leaks/" + Common.getFortniteVersion() + "_cosmetics.png");
					ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

					ImageOutputStream ios = ImageIO.createImageOutputStream(f);
					writer.setOutput(ios);
					writer.write(temp);
				}
				if (getUpcoming().size() <= 40) {
					defaultheight = defaultheight * 5;
					BufferedImage temp = new BufferedImage(defaultwidth, defaultheight, BufferedImage.TYPE_INT_ARGB);

					Graphics graphics = temp.getGraphics();

					for (int i = 0; i < getUpcoming().size(); i++) {
						//Draw all
					}

					graphics.dispose();

					File f = new File("leaks/" + Common.getFortniteVersion() + "_cosmetics.png");
					ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

					ImageOutputStream ios = ImageIO.createImageOutputStream(f);
					writer.setOutput(ios);
					writer.write(temp);
				}
				if (getUpcoming().size() <= 48) {
					defaultheight = defaultheight * 6;
					BufferedImage temp = new BufferedImage(defaultwidth, defaultheight, BufferedImage.TYPE_INT_ARGB);

					Graphics graphics = temp.getGraphics();

					for (int i = 0; i < getUpcoming().size(); i++) {
						if (i == 0) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 1, 1, null);
							graphics.drawImage(icon, 35, 1, null);
							graphics.drawImage(rectangle, 1, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 20, 200);
						}
						if(i == 1) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 258, 1, null);
							graphics.drawImage(icon, 280, 1, null);
							graphics.drawImage(rectangle, 258, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 270, 200);
						}
						if(i == 2) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 515, 1, null);
							graphics.drawImage(icon, 525, 1, null);
							graphics.drawImage(rectangle, 515, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 570, 200);
						}
						if(i == 3) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 772, 1, null);
							graphics.drawImage(icon, 790, 1, null);
							graphics.drawImage(rectangle, 772, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 820, 200);
						}
						if(i == 4) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 1029, 1, null);
							graphics.drawImage(icon, 1070, 1, null);
							graphics.drawImage(rectangle, 1029, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 1100, 200);
						}
						if(i == 5) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 1286, 1, null);
							graphics.drawImage(icon, 1310, 1, null);
							graphics.drawImage(rectangle, 1286, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 1350, 200);
						}
						if(i == 6) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 1543, 1, null);
							graphics.drawImage(icon, 1580, 1, null);
							graphics.drawImage(rectangle, 1543, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 1590, 200);
						}
						if(i == 7) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 1800, 1, null);
							graphics.drawImage(icon, 1820, 1, null);
							graphics.drawImage(rectangle, 1800, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 1850, 200);
						}
						if(i == 8) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 2057, 1, null);
							graphics.drawImage(icon, 2085, 1, null);
							graphics.drawImage(rectangle, 2057, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 2125, 200);
						}
						/*if(i == 8) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 2057, 1, null);
							graphics.drawImage(icon, 2085, 1, null);
							graphics.drawImage(rectangle, 2057, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 2125, 200);
						}
						if(i == 8) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 2057, 1, null);
							graphics.drawImage(icon, 2085, 1, null);
							graphics.drawImage(rectangle, 2057, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 2125, 200);
						}
						if(i == 8) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 2057, 1, null);
							graphics.drawImage(icon, 2085, 1, null);
							graphics.drawImage(rectangle, 2057, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 2125, 200);
						}
						if(i == 8) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 2057, 1, null);
							graphics.drawImage(icon, 2085, 1, null);
							graphics.drawImage(rectangle, 2057, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 2125, 200);
						}
						if(i == 8) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 2057, 1, null);
							graphics.drawImage(icon, 2085, 1, null);
							graphics.drawImage(rectangle, 2057, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 2125, 200);
						}
						if(i == 8) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 2057, 1, null);
							graphics.drawImage(icon, 2085, 1, null);
							graphics.drawImage(rectangle, 2057, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 2125, 200);
						}
						if(i == 8) {
							JsonObject object = getUpcoming().get(i).getAsJsonObject();
							JsonObject images = object.getAsJsonObject("images");
							
							BufferedImage rectangle = ImageIO.read(new File("leaks/rarities/rectangle.png"));
							BufferedImage background = getRarityBackGround(object.get("rarity").getAsString().toLowerCase());
							String name = object.get("name").getAsString();
							String icon_string = (images.get("featured").getAsString().equalsIgnoreCase("false") ? images.get("icon").getAsString().replace(".png", "_192.png") : images.get("featured").getAsString().replace(".png", "_192.png"));
							
							URL icon_url = new URL(icon_string);
							URLConnection icon_connection = icon_url.openConnection();
							icon_connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

							BufferedImage icon = ImageIO.read(icon_connection.getInputStream());
							
							graphics.drawImage(background, 2057, 1, null);
							graphics.drawImage(icon, 2085, 1, null);
							graphics.drawImage(rectangle, 2057, 1, null);
							

							graphics.setFont(new Font("Burbank Big Cd Bd", Font.BOLD, 35));
							graphics.drawString(name, 2125, 200);
						}*/
					}

					graphics.dispose();

					File f = new File("leaks/" + Common.getFortniteVersion() + "_cosmetics.png");
					ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

					ImageOutputStream ios = ImageIO.createImageOutputStream(f);
					writer.setOutput(ios);
					writer.write(temp);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void sendLeaks() {
		for (int i = 0; i < JSON.getLeaksArray().size(); i++) {
			TextChannel channel = Igor.jda.getTextChannelById(JSON.getLeaksArray().get(i).getAsString());
			channel.sendFile(new File("leaks/" + Common.getFortniteVersion() + "_cosmetics.png")).queue();
		}
	}

	private JsonArray getUpcoming() {
		try {
			String token = "90213c32-6025-41f8-8eec-6ad027dfe23d";
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("https://fnbr.co/api/upcoming");
			httpGet.addHeader("x-api-key", token);

			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
			JsonObject rootobj = root.getAsJsonObject();
			JsonArray array = rootobj.getAsJsonArray("data");
			return array;
		} catch (IOException ex) {
			return null;
		}
	}

	private BufferedImage getRarityBackGround(String rarity) {
		try {
			switch (rarity) {
				case "legendary": {
					return ImageIO.read(new File("leaks/rarities/legendary.png"));
				}
				case "dark": {
					return ImageIO.read(new File("leaks/rarities/dark.png"));
				}
				case "epic": {
					return ImageIO.read(new File("leaks/rarities/epic.png"));
				}
				case "marvel": {
					return ImageIO.read(new File("leaks/rarities/marvel.png"));
				}
				case "dc": {
					return ImageIO.read(new File("leaks/rarities/dc.png"));
				}
				case "rare": {
					return ImageIO.read(new File("leaks/rarities/rare.png"));
				}
				case "uncommon": {
					return ImageIO.read(new File("leaks/rarities/uncommon.png"));
				}
				case "common": {
					return ImageIO.read(new File("leaks/rarities/common.png"));
				}
				default: {

				}
			}
		} catch (IOException e) {

		}
		return null;
	}

}
