package es.alejandro12120.igor.listeners;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.commands.AdminToolsCMD;
import es.alejandro12120.igor.shop.ShopManager;
import es.alejandro12120.igor.updates.UpdateManager;
import es.alejandro12120.igor.utils.JSON;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class AdminToolsListener extends ListenerAdapter {

	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
		if (!e.getMember().getUser().equals(e.getJDA().getSelfUser())) {
			if (e.getMember().getUser().getId().equalsIgnoreCase("305715994935033857") && e.getMessageId().equalsIgnoreCase(AdminToolsCMD.embed_id)) {
				if (e.getReactionEmote().getName().equals("⛔")) {
					e.getReaction().removeReaction().queue();
					e.getChannel().getMessageById(e.getMessageId()).complete().delete().queue();
				} else if (e.getReactionEmote().getName().equals("🔄")) {
					e.getReaction().removeReaction().queue();
					ShopManager.getShopManager().createNewShopImage();
				} else if (e.getReactionEmote().getName().equals("📄")) {
					e.getReaction().removeReaction().queue();
					for (int i = 0; i < JSON.getShopArray().size(); i++) {
						String id = JSON.getShopArray().get(i).getAsString();
						TextChannel channel = Igor.jda.getTextChannelById(id);
						ShopManager.getShopManager().sendShop(channel);
					}

				} else if (e.getReactionEmote().getName().equals("⏰")) {
					e.getReaction().removeReaction().queue();
					UpdateManager.getUpdateManager().update();
				} else {
					e.getReaction().removeReaction().queue();
				}
			}

		}
	}

}
