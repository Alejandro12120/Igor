package es.alejandro12120.igor.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import es.alejandro12120.igor.Igor;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStreamReader;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class CosmeticsLeaksCMD extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if(args[0].equalsIgnoreCase(Igor.getIgor().prefix+"delete")) {
			if(args.length == 3) {
				TextChannel channel = e.getMessage().getMentionedChannels().get(0);
				String id = args[2];
				channel.getMessageById(id).complete().delete().queue();
				e.getMessage().delete().queue();
			}
		}
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "4k")) {
			if (args.length == 1) {
				try {
					DefaultHttpClient httpclient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=4k");
					httpGet.addHeader("Content-Type", "application/json");

					HttpResponse response = httpclient.execute(httpGet);
					HttpEntity entity = response.getEntity();

					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
					JsonObject rootobj = root.getAsJsonObject();
					String url = rootobj.get("message").getAsString();
					EmbedBuilder embed = new EmbedBuilder();
					embed.setColor(Color.RED);
					embed.setImage(url);
					embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

					e.getChannel().sendMessage(embed.build()).queue();
				} catch (IOException ex) {
					e.getChannel().sendMessage("ERROR").queue();
				}
			}
			if (args.length == 2) {
				int amount = Integer.parseInt(args[1]);
				for (int i = 0; i < amount; i++) {
					try {
						DefaultHttpClient httpclient = new DefaultHttpClient();
						HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=4k");
						httpGet.addHeader("Content-Type", "application/json");

						HttpResponse response = httpclient.execute(httpGet);
						HttpEntity entity = response.getEntity();

						JsonParser jp = new JsonParser();
						JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
						JsonObject rootobj = root.getAsJsonObject();
						String url = rootobj.get("message").getAsString();
						EmbedBuilder embed = new EmbedBuilder();
						embed.setColor(Color.RED);
						embed.setImage(url);
						embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

						e.getChannel().sendMessage(embed.build()).queue();
					} catch (IOException ex) {
						e.getChannel().sendMessage("ERROR").queue();
					}
				}
			}
		}
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "anal")) {
			if (args.length == 1) {
				try {
					DefaultHttpClient httpclient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=anal");
					httpGet.addHeader("Content-Type", "application/json");

					HttpResponse response = httpclient.execute(httpGet);
					HttpEntity entity = response.getEntity();

					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
					JsonObject rootobj = root.getAsJsonObject();
					String url = rootobj.get("message").getAsString();
					EmbedBuilder embed = new EmbedBuilder();
					embed.setColor(Color.RED);
					embed.setImage(url);
					embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

					e.getChannel().sendMessage(embed.build()).queue();
				} catch (IOException ex) {
					e.getChannel().sendMessage("ERROR").queue();
				}
			}
			if (args.length == 2) {
				int amount = Integer.parseInt(args[1]);
				for (int i = 0; i < amount; i++) {
					try {
						DefaultHttpClient httpclient = new DefaultHttpClient();
						HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=anal");
						httpGet.addHeader("Content-Type", "application/json");

						HttpResponse response = httpclient.execute(httpGet);
						HttpEntity entity = response.getEntity();

						JsonParser jp = new JsonParser();
						JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
						JsonObject rootobj = root.getAsJsonObject();
						String url = rootobj.get("message").getAsString();
						EmbedBuilder embed = new EmbedBuilder();
						embed.setColor(Color.RED);
						embed.setImage(url);
						embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

						e.getChannel().sendMessage(embed.build()).queue();
					} catch (IOException ex) {
						e.getChannel().sendMessage("ERROR").queue();
					}
				}
			}
		}
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "hentai")) {
			if (args.length == 1) {
				try {
					DefaultHttpClient httpclient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=hentai");
					httpGet.addHeader("Content-Type", "application/json");

					HttpResponse response = httpclient.execute(httpGet);
					HttpEntity entity = response.getEntity();

					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
					JsonObject rootobj = root.getAsJsonObject();
					String url = rootobj.get("message").getAsString();
					EmbedBuilder embed = new EmbedBuilder();
					embed.setColor(Color.RED);
					embed.setImage(url);
					embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

					e.getChannel().sendMessage(embed.build()).queue();
				} catch (IOException ex) {
					e.getChannel().sendMessage("ERROR").queue();
				}
			}
			if (args.length == 2) {
				int amount = Integer.parseInt(args[1]);
				for (int i = 0; i < amount; i++) {
					try {
						DefaultHttpClient httpclient = new DefaultHttpClient();
						HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=hentai");
						httpGet.addHeader("Content-Type", "application/json");

						HttpResponse response = httpclient.execute(httpGet);
						HttpEntity entity = response.getEntity();

						JsonParser jp = new JsonParser();
						JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
						JsonObject rootobj = root.getAsJsonObject();
						String url = rootobj.get("message").getAsString();
						EmbedBuilder embed = new EmbedBuilder();
						embed.setColor(Color.RED);
						embed.setImage(url);
						embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

						e.getChannel().sendMessage(embed.build()).queue();
					} catch (IOException ex) {
						e.getChannel().sendMessage("ERROR").queue();
					}
				}
			}
		}
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "pussy")) {
			if (args.length == 1) {
				try {
					DefaultHttpClient httpclient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=pussy");
					httpGet.addHeader("Content-Type", "application/json");

					HttpResponse response = httpclient.execute(httpGet);
					HttpEntity entity = response.getEntity();

					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
					JsonObject rootobj = root.getAsJsonObject();
					String url = rootobj.get("message").getAsString();
					EmbedBuilder embed = new EmbedBuilder();
					embed.setColor(Color.RED);
					embed.setImage(url);
					embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

					e.getChannel().sendMessage(embed.build()).queue();
				} catch (IOException ex) {
					e.getChannel().sendMessage("ERROR").queue();
				}
			}
			if (args.length == 2) {
				int amount = Integer.parseInt(args[1]);
				for (int i = 0; i < amount; i++) {
					try {
						DefaultHttpClient httpclient = new DefaultHttpClient();
						HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=pussy");
						httpGet.addHeader("Content-Type", "application/json");

						HttpResponse response = httpclient.execute(httpGet);
						HttpEntity entity = response.getEntity();

						JsonParser jp = new JsonParser();
						JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
						JsonObject rootobj = root.getAsJsonObject();
						String url = rootobj.get("message").getAsString();
						EmbedBuilder embed = new EmbedBuilder();
						embed.setColor(Color.RED);
						embed.setImage(url);
						embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

						e.getChannel().sendMessage(embed.build()).queue();
					} catch (IOException ex) {
						e.getChannel().sendMessage("ERROR").queue();
					}
				}
			}
		}
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "ass")) {
			if (args.length == 1) {
				try {
					DefaultHttpClient httpclient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=ass");
					httpGet.addHeader("Content-Type", "application/json");

					HttpResponse response = httpclient.execute(httpGet);
					HttpEntity entity = response.getEntity();

					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
					JsonObject rootobj = root.getAsJsonObject();
					String url = rootobj.get("message").getAsString();
					EmbedBuilder embed = new EmbedBuilder();
					embed.setColor(Color.RED);
					embed.setImage(url);
					embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

					e.getChannel().sendMessage(embed.build()).queue();
				} catch (IOException ex) {
					e.getChannel().sendMessage("ERROR").queue();
				}
			}
			if (args.length == 2) {
				int amount = Integer.parseInt(args[1]);
				for (int i = 0; i < amount; i++) {
					try {
						DefaultHttpClient httpclient = new DefaultHttpClient();
						HttpGet httpGet = new HttpGet("https://nekobot.xyz/api/image?type=ass");
						httpGet.addHeader("Content-Type", "application/json");

						HttpResponse response = httpclient.execute(httpGet);
						HttpEntity entity = response.getEntity();

						JsonParser jp = new JsonParser();
						JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
						JsonObject rootobj = root.getAsJsonObject();
						String url = rootobj.get("message").getAsString();
						EmbedBuilder embed = new EmbedBuilder();
						embed.setColor(Color.RED);
						embed.setImage(url);
						embed.setFooter("Pedido por: " + e.getAuthor().getName(), e.getAuthor().getAvatarUrl());

						e.getChannel().sendMessage(embed.build()).queue();
					} catch (IOException ex) {
						e.getChannel().sendMessage("ERROR").queue();
					}
				}
			}
		}
	}

}
