package es.alejandro12120.igor.commands;

import es.alejandro12120.igor.Igor;
import java.awt.Image;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ExportCMD extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "export") || args[0].equalsIgnoreCase(Igor.getIgor().prefix + "exportAsset")) {
			if (args.length == 1) {
				e.getChannel().sendMessage("You must type something to search");
			} else {
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < args.length; ++i) {
					sb.append(" ").append(args[i]);
				}
				String search = sb.toString().substring(1);
				if(!search.contains(".uasset")) {
					search = search+".uasset";
				}
				try {
					URL url = new URL("http://benbotfn.tk:8080/api/exportAsset?file="+search);
					URLConnection connection = url.openConnection();
					connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
					e.getChannel().sendFile(connection.getInputStream(), "exportedByIgor"+ (isImage("http://benbotfn.tk:8080/api/exportAsset?file="+search) ? ".png" : ".ogg")).queue();
				} catch (Exception ex) {
					e.getChannel().sendMessage("No results found for: '"+search+"'").queue();
				}
			}
		}
	}
	
	private boolean isImage(String url) {
		try {
			Image image = ImageIO.read(new URL(url));
			return (image != null);
		}catch(Exception ex) {
			return false;
		}
	}

}
