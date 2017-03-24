package qc.main;

import android.content.Intent;
import android.content.SharedPreferences;

import utils.NotificationUtils;
import utils.TileUtils;

/**
 * Created by Melad Khajepour
 */

class TileHandlers {

    static void handleOnStartCommand(QCTileService ts, Intent intent, SharedPreferences prefs) {

        if(intent.getBooleanExtra("update", false)) {
            new TileActions(ts).updateTile();
        }
        if(intent.getBooleanExtra("dismiss", false)) {
            new NotificationUtils(ts).dismissNotification();
        }

        NotificationUtils.setPrefs(prefs);
        TileUtils.setPrefs(prefs);
    }

    static void onClick(QCTileService ts) {
        new TileActions(ts).onClick();
    }

    static void onStartListening(QCTileService ts) {
        new TileActions(ts).onStartListening();
    }

    static void onTileAdded(QCTileService ts) {
        new TileActions(ts).onTileAdded();
    }
}
