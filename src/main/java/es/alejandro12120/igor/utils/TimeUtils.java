package es.alejandro12120.igor.utils;

public class TimeUtils {
	
	public static String convert(int seconds) {
		int h = seconds / 3600;
		int i = seconds - h * 3600;
		int m = i / 60;
		int s = i - m * 60;
		String timeF = "";
		if (h > 0) {
			if (h < 10) {
				timeF = String.valueOf(String.valueOf(timeF)) + "0";
			}
			timeF = String.valueOf(String.valueOf(timeF)) + h + ":";
		}
		if (m < 10) {
			timeF = String.valueOf(String.valueOf(timeF)) + "0";
		}
		timeF = String.valueOf(String.valueOf(timeF)) + m + ":";
		if (s < 10) {
			timeF = String.valueOf(String.valueOf(timeF)) + "0";
		}
		timeF = String.valueOf(String.valueOf(timeF)) + s;
		return timeF;
	}
	
}
