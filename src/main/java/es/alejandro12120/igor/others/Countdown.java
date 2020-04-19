package es.alejandro12120.igor.others;

import es.alejandro12120.igor.Igor;
import es.alejandro12120.igor.utils.Common;
import es.alejandro12120.igor.utils.TimeUtils;
import java.util.TimerTask;
import net.dv8tion.jda.core.entities.Game;

public class Countdown extends TimerTask {

	private int secondsleft = 28800;

	@Override
	public void run() {
		if (secondsleft == 0) {
			Igor.jda.getPresence().setGame(Game.watching("NEW YEAR"));
		}else{
			Igor.jda.getPresence().setGame(Game.watching(TimeUtils.convert(secondsleft) + " | " + Common.getFortniteVersion()));
			secondsleft--;
		}
		

	}

}
