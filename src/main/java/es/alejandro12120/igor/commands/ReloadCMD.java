package es.alejandro12120.igor.commands;

import es.alejandro12120.igor.Igor;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ReloadCMD extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if(args[0].equalsIgnoreCase(Igor.getIgor().prefix+"reload")) {
			if(e.getMember().hasPermission(Permission.ADMINISTRATOR) && !e.getAuthor().isBot()) {
				e.getMessage().addReaction("✔").queue();
				System.exit(0);
			}else{
				e.getMessage().addReaction("❌").queue();
			}
		}
	}
}
