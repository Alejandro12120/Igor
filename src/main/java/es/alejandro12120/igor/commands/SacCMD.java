package es.alejandro12120.igor.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import es.alejandro12120.igor.Igor;
import java.awt.Color;
import java.io.InputStreamReader;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class SacCMD extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "sac")) {
			switch (args.length) {
				case 1: {
					e.getChannel().sendMessage(":x: Please specify a code to search! :x:").queue();
					break;
				}
				case 2: {
					try {
						DefaultHttpClient httpclient = new DefaultHttpClient();
						HttpGet httpGet = new HttpGet("https://fortnite-api.com/creatorcode/search?slug=" + args[1]);
						httpGet.addHeader("x-api-key", "LA PUTAAAA KEEYYY JODEEEER");
						httpGet.addHeader("Content-Type:" , "application/json");
						
						HttpResponse response = httpclient.execute(httpGet);
						HttpEntity entity = response.getEntity();
						
						JsonParser jp = new JsonParser();
						JsonElement root = jp.parse(new InputStreamReader(entity.getContent()));
						JsonObject obj = root.getAsJsonObject().get("data").getAsJsonObject();
						EmbedBuilder embd = new EmbedBuilder();
						embd.setTitle(":white_check_mark: Code `"+obj.get("displayName").getAsString()+"`");
						embd.addField("ID:", "`"+obj.get("id").getAsString()+"`", true);
						embd.addField("Slug:", "`"+obj.get("slug").getAsString()+"`", false);
						embd.addField("Status:", "`"+obj.get("status").getAsString()+"`", false);
						embd.addField("Verified:", (obj.get("status").getAsBoolean() ? ":white_check_mark:" : ":x:"), false);
						embd.setColor(3641159);
						e.getChannel().sendMessage(embd.build()).queue();
					} catch (Exception ex) {
						EmbedBuilder embd = new EmbedBuilder();
						embd.setTitle(":x: Code `"+args[1]+"` doesn't exist");
						embd.setDescription("The Support A Creator Code `"+args[1]+"` couldn't been found.\nError message: `"+ex.getMessage()+"`.");
						embd.setColor(0xff0000);
						e.getChannel().sendMessage(embd.build()).queue();
					}
					break;
				}
				default: {
					e.getChannel().sendMessage(":x: Please specify a code to search! :x:").queue();
					break;
				}
			}
		}
	}

}
