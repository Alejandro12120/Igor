package es.alejandro12120.igor.commands;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.utils.Status;
import java.util.Date;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class StatusCMD extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if(args[0].equalsIgnoreCase(Igor.getIgor().prefix+"status")) {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(Status.getGeneralStatus());
			
			embed.addField("Fortnite: ", Status.getFortniteStatus(), true);
			
			embed.setColor(Status.isOperational() ? 0x00ff00 : 0xff0000);
			embed.setFooter("Generated by Igor.", "https://upload.wikimedia.org/wikipedia/commons/a/a7/Epic_Games_logo.png");
			embed.setTimestamp(new Date().toInstant());
			
			e.getMessage().getChannel().sendMessage(embed.build()).queue();
		}
	}
	
}
