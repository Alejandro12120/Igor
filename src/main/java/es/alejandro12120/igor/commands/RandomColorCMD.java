package es.alejandro12120.igor.commands;

import es.alejandro12120.igor.Igor;
import java.util.Random;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class RandomColorCMD extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "randomcolor") || args[0].equalsIgnoreCase(Igor.getIgor().prefix + "color")) {
			EmbedBuilder embd = new EmbedBuilder();
			Random obj = new Random();
			int rand_num = obj.nextInt(0xffffff + 1);
			
			embd.setTitle("Random Color");
			embd.setColor(Integer.valueOf(rand_num));
			embd.setDescription("`"+rand_num+"`");
			e.getChannel().sendMessage(embd.build()).queue();
		}
	}

}
