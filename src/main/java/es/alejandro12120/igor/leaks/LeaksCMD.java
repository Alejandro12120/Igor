package es.alejandro12120.igor.leaks;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.utils.Common;
import java.io.File;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class LeaksCMD extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Igor.getIgor().prefix+"sendleaks")) {
			LeaksManager.getLeaksManager().createImage();
			e.getChannel().sendFile(new File("leaks/" + Common.getFortniteVersion() + "_cosmetics.png")).queue();
		}
	}
	
}
