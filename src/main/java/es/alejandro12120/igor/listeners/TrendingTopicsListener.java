package es.alejandro12120.igor.listeners;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.commands.TrendingTopicsCMD;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import twitter4j.StatusUpdate;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterException;

public class TrendingTopicsListener extends ListenerAdapter {

	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
		if (!e.getMember().getUser().equals(e.getJDA().getSelfUser())) {
			if (e.getMember().getUser().getId().equalsIgnoreCase("305715994935033857")) {
				if (e.getMessageId().equalsIgnoreCase(TrendingTopicsCMD.trending_id)) {
					if (e.getReactionEmote().getName().equals("⬆")) {
						e.getReaction().removeReaction().queue();
						e.getChannel().getMessageById(e.getMessageId()).complete().addReaction("❌").queue();

						try {
							Trends trends = Igor.getIgor().getTrendingTwitter().getPlaceTrends(TrendingTopicsCMD.code);
							int count = 0;
							String tweet = "";
							for (Trend trend : trends.getTrends()) {
								if (count != 0 && count < 6) {
									tweet = tweet + count + ". " + trend.getName() + " \n";
								}
								count++;
							}
							StatusUpdate status = new StatusUpdate(switchCode(TrendingTopicsCMD.code)+" Trends: \n"+tweet);
							Igor.getIgor().getTrendingTwitter().updateStatus(status);
						} catch (TwitterException ex) {
							System.out.println("ERROR WHILE POSTING TRENDING TOPICS TWEET");
						}
					} else if (e.getReactionEmote().getName().equals("❌")) {
						e.getChannel().getMessageById(e.getMessageId()).complete().delete().queue();
						e.getChannel().getMessageById(TrendingTopicsCMD.trending_id).complete().delete().queue();
					} else {
						e.getReaction().removeReaction().queue();
					}
				}
			} else {
				e.getReaction().removeReaction().queue();
			}
		}
	}
	
	private String switchCode(int code) {
		switch(code) {
			case 1: {
				return "Global";
			}
			case 766273: {
				return "Spain";
			}
			default: {
				return "Global";
			}
		}
	}
}
