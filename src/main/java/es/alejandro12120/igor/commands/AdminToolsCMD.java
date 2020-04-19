package es.alejandro12120.igor.commands;

import es.alejandro12120.igor.Igor;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class AdminToolsCMD extends ListenerAdapter {
	
	public static String embed_id; 
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if(args[0].equalsIgnoreCase(Igor.getIgor().prefix+"admintools")) {
			if(e.getAuthor().getId().equalsIgnoreCase("305715994935033857")) {
				EmbedBuilder embd = new EmbedBuilder();
				
				embd.setTitle("Igor - Admin Tools :wrench:");
				embd.setDescription(":arrows_counterclockwise: To re-create the shop file. \n:page_facing_up: To re-send the shop message. \n:alarm_clock: Enter in update mode. \n:no_entry: Delete this message.");
				
				Message message = e.getChannel().sendMessage(embd.build()).complete();
				
				message.addReaction("🔄").queue();
				message.addReaction("📄").queue();
				message.addReaction("⏰").queue();
				message.addReaction("⛔").queue();
				
				e.getChannel().sendMessage(message);
				embed_id = message.getId();
				
				embd.clear();
				e.getMessage().delete().queue();
			}else{
				e.getMessage().addReaction(":x:").queue();
			}
		}
	}
}
