package es.alejandro12120.igor.listeners;

import es.alejandro12120.igor.updates.UpdateManager;
import es.alejandro12120.igor.utils.Common;
import java.util.TimerTask;

public class VersionChangeHandler extends TimerTask {

	String currentVersion;
	boolean broadcasted;

	public VersionChangeHandler() {
		this.currentVersion = Common.getFortniteWindowsVersion();
		System.out.println(currentVersion);
		this.broadcasted = false;
	}

	@Override
	public void run() {
		if (!currentVersion.equalsIgnoreCase(Common.getFortniteWindowsVersion()) && !broadcasted) {
			broadcasted = true;
			if (UpdateManager.getUpdateManager().isInUpdateMode()) {
				return;
			}
			UpdateManager.getUpdateManager().update();
		}
	}

}
