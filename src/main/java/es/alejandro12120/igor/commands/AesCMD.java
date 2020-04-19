package es.alejandro12120.igor.commands;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.utils.Common;
import java.util.Set;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class AesCMD extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");

		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "aes")) {
			String aes = Common.getAESKey();
			String version = Common.getFortniteVersion();
			if(aes.equalsIgnoreCase("Unknown")) {
				e.getChannel().sendMessage("The aes key for version "+version+" is unknown").queue();
				return;
			}
			EmbedBuilder embd = new EmbedBuilder();
			embd.setTitle("Fortnite Encryption System");
			embd.addField("Main Key", "`"+Common.getAESKey()+"`", true);
			embd.setColor(0x007481);
			if(!Common.getAdditionalKeys().isJsonNull()) {
				Set<String> paks = Common.getAdditionalKeys().keySet();
				for(String pak : paks) {
					embd.addField(pak, "`"+Common.getAdditionalKeys().get(pak).getAsString()+"`", false);
				}
			}
			e.getChannel().sendMessage(embd.build()).queue();
		}
	}

}
