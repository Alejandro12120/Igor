package es.alejandro12120.igor.commands;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.shop.ShopManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ShopOpinionCMD extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "opinion")) {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Shop's Opinion - " + ShopManager.getShopManager().getDate());
			embed.setDescription(":smile: » " + ShopManager.getShopManager().getUpVotes() + " \n:neutral_face: » " + ShopManager.getShopManager().getNeutralVotes() + " \n:angry: » " + ShopManager.getShopManager().getDownVotes() + " \nGeneral Opinion » " + switchNames(getGeneralOpinion()));
			embed.setColor(0xE59400);
			embed.setFooter("Grabbed from fnbr.co", "https://image.fnbr.co/logo/fnbr.png");
			e.getChannel().sendMessage(embed.build()).queue();
			embed.clear();
		}
	}

	private String switchNames(String opinion) {
		if (opinion.equalsIgnoreCase("up")) {
			return ":smile:";
		} else if (opinion.equalsIgnoreCase("neutral")) {
			return ":neutral_face";
		} else if (opinion.equalsIgnoreCase("down")) {
			return ":angry:";
		} else {
			return ":x:";
		}
	}

	private String getGeneralOpinion() {
		int x = ShopManager.getShopManager().getUpVotes();
		int y = ShopManager.getShopManager().getNeutralVotes();
		int z = ShopManager.getShopManager().getDownVotes();

		if (x > y && x > z) {
			return "up";
		} else if (y > x && y > z) {
			return "neutral";
		} else if (z > x && z > y) {
			return "down";
		} else {
			return "neutral";
		}
	}
}
