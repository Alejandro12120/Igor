package es.alejandro12120.igor.shop;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.utils.JSON;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;
import net.dv8tion.jda.core.entities.TextChannel;

public class ShopScheduler extends TimerTask {

	@Override
	public void run() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String time = dateFormat.format(date);
		if (time.equalsIgnoreCase("00:01:00")) {
			/* Creates the shop image */
			try {
				if (!ShopManager.getShopManager().shopExists()) {
					ShopManager.getShopManager().createNewShopImage();
				}
			} catch (Exception ex) {
				return;
			}

			/* Send the shop image to all servers */
			for (int i = 0; i < JSON.getShopArray().size(); i++) {
				String id = JSON.getShopArray().get(i).getAsString();
				TextChannel channel = Igor.jda.getTextChannelById(id);
				ShopManager.getShopManager().sendShop(channel);
			}

			/* Tweet the shop */
			ShopManager.getShopManager().tweetShop();
		}
	}

}
