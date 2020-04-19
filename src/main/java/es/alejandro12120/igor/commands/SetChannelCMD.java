package es.alejandro12120.igor.commands;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.utils.JSON;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SetChannelCMD extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		Message message = e.getMessage();
		if (args[0].equalsIgnoreCase(Igor.getIgor().prefix + "setchannel")) {
			if (e.getMember().getUser().equals(e.getJDA().getSelfUser())) {
				return;
			}
			if (message.getMember().hasPermission(Permission.ADMINISTRATOR) || message.getMember().getUser().getId().equalsIgnoreCase("305715994935033857")) {
				switch (args.length) {
					case 1:
						e.getChannel().sendMessage("Available args: shop, updates, leaks.").queue();
						break;
					case 2:
						e.getChannel().sendMessage("Example: -setchannel shop #daily-shop").queue();
						break;
					case 3:
						if (args[1].equalsIgnoreCase("shop")) {
							TextChannel mentionedchannel = message.getMentionedChannels().get(0);
							if (mentionedchannel == null) {
								e.getChannel().sendMessage("You must type a valid text channel.").queue();
								return;
							}
							JsonObject object = new JsonObject();
							JsonArray shops = new JsonArray();

							for (int i = 0; i < JSON.getShopArray().size(); i++) {
								String id = JSON.getShopArray().get(i).getAsString();
								shops.add(id);
							}

							shops.add(mentionedchannel.getId());

							object.add("shop", shops);

							Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
							try (FileWriter file = new FileWriter("configuration/shop.json")) {
								file.write(gson.toJson(object));
								file.flush();
								EmbedBuilder embed = new EmbedBuilder();
								embed.setTitle(":white_check_mark: Success!");
								embed.setDescription("Successfully set shop messages to #" + mentionedchannel.getName() + " (" + mentionedchannel.getId() + ").");
								embed.setFooter("Set by: " + message.getMember().getUser().getName() + "#" + message.getMember().getUser().getDiscriminator(), message.getMember().getUser().getAvatarUrl());
								embed.setTimestamp(new Date().toInstant());
								embed.setColor(0x00FF00);
								e.getChannel().sendMessage(embed.build()).queue();
							} catch (IOException ex) {
								EmbedBuilder embd = new EmbedBuilder();
								embd.setTitle(":x: ERROR :x:");
								embd.setDescription("Check console for logs.");
								embd.setColor(0xff0000);

								e.getChannel().sendMessage(embd.build()).queue();
								ex.printStackTrace();
							}
						} else if (args[1].equalsIgnoreCase("updates") || args[1].equalsIgnoreCase("update")) {
							TextChannel mentionedchannel = message.getMentionedChannels().get(0);
							if (mentionedchannel == null) {
								e.getChannel().sendMessage("You must type a valid text channel.").queue();
								return;
							}
							JsonObject object = new JsonObject();
							JsonArray updates = new JsonArray();

							for (int i = 0; i < JSON.getUpdatesArray().size(); i++) {
								String id = JSON.getUpdatesArray().get(i).getAsString();
								updates.add(id);
							}

							updates.add(mentionedchannel.getId());

							object.add("updates", updates);

							Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
							try (FileWriter file = new FileWriter("configuration/updates.json")) {
								file.write(gson.toJson(object));
								file.flush();
								EmbedBuilder embed = new EmbedBuilder();
								embed.setTitle(":white_check_mark: Success!");
								embed.setDescription("Successfully set updates messages to #" + mentionedchannel.getName() + " (" + mentionedchannel.getId() + ").");
								embed.setFooter("Set by: " + message.getMember().getUser().getName() + "#" + message.getMember().getUser().getDiscriminator(), message.getMember().getUser().getAvatarUrl());
								embed.setTimestamp(new Date().toInstant());
								embed.setColor(0x00FF00);
								e.getChannel().sendMessage(embed.build()).queue();
							} catch (IOException ex) {
								EmbedBuilder embd = new EmbedBuilder();
								embd.setTitle(":x: ERROR :x:");
								embd.setDescription("Check console for logs.");
								embd.setColor(0xff0000);

								e.getChannel().sendMessage(embd.build()).queue();
								ex.printStackTrace();
							}

						} else if (args[1].equalsIgnoreCase("leaks") || args[1].equalsIgnoreCase("leak")) {
							TextChannel mentionedchannel = message.getMentionedChannels().get(0);
							if (mentionedchannel == null) {
								e.getChannel().sendMessage("You must type a valid text channel.").queue();
								return;
							}
							JsonObject object = new JsonObject();
							JsonArray leaks = new JsonArray();

							for (int i = 0; i < JSON.getLeaksArray().size(); i++) {
								String id = JSON.getLeaksArray().get(i).getAsString();
								leaks.add(id);
							}

							leaks.add(mentionedchannel.getId());

							object.add("leaks", leaks);
							Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
							try (FileWriter file = new FileWriter("configuration/leaks.json")) {
								file.write(gson.toJson(object));
								file.flush();
								EmbedBuilder embed = new EmbedBuilder();
								embed.setTitle(":white_check_mark: Success!");
								embed.setDescription("Successfully set leaks messages to #" + mentionedchannel.getName() + " (" + mentionedchannel.getId() + ").");
								embed.setFooter("Set by: " + message.getMember().getUser().getName() + "#" + message.getMember().getUser().getDiscriminator(), message.getMember().getUser().getAvatarUrl());
								embed.setTimestamp(new Date().toInstant());
								embed.setColor(0x00FF00);
								e.getChannel().sendMessage(embed.build()).queue();
							} catch (IOException ex) {
								EmbedBuilder embd = new EmbedBuilder();
								embd.setTitle(":x: ERROR :x:");
								embd.setDescription("Check console for logs.");
								embd.setColor(0xff0000);

								e.getChannel().sendMessage(embd.build()).queue();
								ex.printStackTrace();
							}
						} else {
							e.getChannel().sendMessage("Available args: shop, updates, leaks.").queue();
							break;
						}
						break;
					default:
						e.getChannel().sendMessage("Available args: shop, updates, leaks.").queue();
						break;
				}
			} else {
				message.addReaction(":x:");
			}
		}
	}

}
