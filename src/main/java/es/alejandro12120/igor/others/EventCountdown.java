package es.alejandro12120.igor.others;

import es.alejandro12120.igor.Igor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class EventCountdown extends TimerTask {
	
	
	@Override
	public void run() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String time = dateFormat.format(date);
		if(time.equalsIgnoreCase("2019-12-31 at 15:00:00")) {
			startCountdown();
		}
	}
	
	public static void startCountdown() {
		System.out.println("Countdown has started!");
		Timer timer = new Timer();
		timer.schedule(new Countdown(), 0, 1000);
	}
	
}
