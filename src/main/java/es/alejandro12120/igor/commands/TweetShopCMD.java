package es.alejandro12120.igor.commands;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.shop.ShopManager;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class TweetShopCMD extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if(args[0].equalsIgnoreCase(Igor.getIgor().prefix+"tweetshop")) {
			if(e.getAuthor().getId().equalsIgnoreCase("305715994935033857")) {
				ShopManager.getShopManager().tweetShop();
				e.getMessage().addReaction("✅").queue();
			}else{
				e.getMessage().addReaction("❌").queue();
			}
		}
	}
	
}
