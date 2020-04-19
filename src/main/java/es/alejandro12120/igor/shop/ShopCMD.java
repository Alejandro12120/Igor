package es.alejandro12120.igor.shop;

import es.alejandro12120.igor.Igor;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ShopCMD extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "shop")) {
			if(!ShopManager.getShopManager().shopExists()) {
				ShopManager.getShopManager().createNewShopImage();
			}
			ShopManager.getShopManager().sendShop(e.getChannel());
		}
	}
}
