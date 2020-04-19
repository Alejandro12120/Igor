package es.alejandro12120.igor;

import es.alejandro12120.igor.commands.AdminToolsCMD;
import es.alejandro12120.igor.commands.AesCMD;
import es.alejandro12120.igor.commands.CosmeticsLeaksCMD;
import es.alejandro12120.igor.commands.ExportCMD;
import es.alejandro12120.igor.commands.InfoCMD;
import es.alejandro12120.igor.commands.RandomColorCMD;
import es.alejandro12120.igor.commands.ReloadCMD;
import es.alejandro12120.igor.commands.SacCMD;
import es.alejandro12120.igor.commands.SearchItemCMD;
import es.alejandro12120.igor.commands.SetChannelCMD;
import es.alejandro12120.igor.commands.ShopOpinionCMD;
import es.alejandro12120.igor.commands.StatusCMD;
import es.alejandro12120.igor.commands.TrendingTopicsCMD;
import es.alejandro12120.igor.commands.TweetShopCMD;
import es.alejandro12120.igor.leaks.LeaksCMD;
import es.alejandro12120.igor.leaks.LeaksManager;
import es.alejandro12120.igor.listeners.AdminToolsListener;
import es.alejandro12120.igor.listeners.TrendingTopicsListener;
import es.alejandro12120.igor.listeners.VersionChangeHandler;
import es.alejandro12120.igor.news.NewsCMD;
import es.alejandro12120.igor.news.NewsManager;
import es.alejandro12120.igor.others.EventCountdown;
import es.alejandro12120.igor.shop.ShopCMD;
import es.alejandro12120.igor.shop.ShopManager;
import es.alejandro12120.igor.shop.ShopScheduler;
import es.alejandro12120.igor.updates.UpdateManager;
import es.alejandro12120.igor.utils.Common;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Igor {

	private static Igor instance;
	public String prefix = "-";
	private static Twitter shoptwitter;
	private static Twitter trendingtwitter;

	public static JDA jda;

	/* Things to check:
		- News
		- Leaks
	 */
	public static void main(String[] args) {
		instance = new Igor();
		try {
			jda = new JDABuilder(AccountType.BOT).setToken("NjEzMDExMTkyOTk4NTI2OTg2.XVqt3g.mWgC09aAusTnuC8ymNu2RTTKpN0").build();
		} catch (LoginException e) {
			System.out.println("ERROR LOGGING WITH TOKEN");
			return;
		}

		new ShopManager(); //for register instance
		new UpdateManager(); // for register instance
		new NewsManager(); //for register instance
		new LeaksManager(); //for register instance
		registerShopTwitter(); //daily shop in twitter
		registerTrendingTwitter(); //obtain trending topics of twitter
		registerFont(); // for register fonts
		/* Making the bot useful */
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setGame(Game.watching("The Seven | " + Common.getFortniteVersion() + "v"));
		jda.addEventListener(new AesCMD());
		jda.addEventListener(new InfoCMD());
		jda.addEventListener(new ShopCMD());
		jda.addEventListener(new AdminToolsCMD());
		jda.addEventListener(new ReloadCMD());
		jda.addEventListener(new SetChannelCMD());
		jda.addEventListener(new AdminToolsListener());
		jda.addEventListener(new TweetShopCMD());
		jda.addEventListener(new CosmeticsLeaksCMD());
		jda.addEventListener(new StatusCMD());
		jda.addEventListener(new SearchItemCMD());
		jda.addEventListener(new TrendingTopicsCMD());
		jda.addEventListener(new TrendingTopicsListener());
		jda.addEventListener(new ShopOpinionCMD());
		jda.addEventListener(new NewsCMD());
		jda.addEventListener(new LeaksCMD());
		jda.addEventListener(new ExportCMD());
		jda.addEventListener(new RandomColorCMD());
		jda.addEventListener(new SacCMD());
		/* Starting Runnables */
		Timer timer = new Timer();
		timer.schedule(new ShopScheduler(), 0, 1000);
		timer.schedule(new Common(), 60000, 60000);
		timer.schedule(new VersionChangeHandler(), 60000, 60000);
		timer.schedule(new EventCountdown(), 0, 1000);
	}

	public static Igor getIgor() {
		return instance;
	}

	private static void registerShopTwitter() {
		try {
			shoptwitter = new TwitterFactory(new ConfigurationBuilder().setDebugEnabled(true)
					.setOAuthConsumerKey("mZe8ugiqXB48tZ1x4PNYbQEhj")
					.setOAuthConsumerSecret("OaHqe2FkwhxkLdlBl2WH7HHRHO8d5da4NQqgU1yWFUgpeXdA7B")
					.setOAuthAccessToken("2747889790-lIvBmjxXeXPQbWbgxtd1SUsLHJDIH9EzjNpnZO6")
					.setOAuthAccessTokenSecret("3ASOuMHKJZEQZi3HqBG4cS3VqvRvaeWV6vVeMXRXfqiDx").build()).getInstance();
			System.out.println("[Shop-Twitter] Successfully logged on " + shoptwitter.getScreenName());
		} catch (TwitterException ex) {
			if (ex.getStatusCode() == 200) {
				System.out.println("[Shop-Twitter] Please check that you setup correctly your oauth's keys correctly.");
			} else {
				System.out.println("[Shop-Twitter] An error occurred while logging in.");
			}
		}
	}

	private static void registerTrendingTwitter() {
		try {
			trendingtwitter = new TwitterFactory(new ConfigurationBuilder().setDebugEnabled(true)
					.setOAuthConsumerKey("z3OiQOyksRylSulFDFPJuKnNi")
					.setOAuthConsumerSecret("gzptUWw0qfaxyRkNHhm6FVSiYZHVy3z1D3CtkEsLT71MBoIl9I")
					.setOAuthAccessToken("2747889790-YSxvaDRA6nuauPaHwVc34jUpsYjdjZbfjAJQXUR")
					.setOAuthAccessTokenSecret("7N9VoJZewASdfsUv4XKGkhpcZahao19miC2qUnBuOe7gk").build()).getInstance();
			System.out.println("[TT-Twitter] Successfully logged on " + trendingtwitter.getScreenName());
		} catch (TwitterException ex) {
			if (ex.getStatusCode() == 200) {
				System.out.println("[TT-Twitter] Please check that you setup correctly your oauth's keys correctly.");
			} else {
				System.out.println("[TT-Twitter] An error occurred while logging in.");
			}
		}
	}

	private static void registerFont() {
		try {
			InputStream fontStream = new BufferedInputStream(new FileInputStream("font.ttf"));
			GraphicsEnvironment ge
					= GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontStream));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	public Twitter getShopTwitter() {
		return this.shoptwitter;
	}

	public Twitter getTrendingTwitter() {
		return this.trendingtwitter;
	}
	
	
}
