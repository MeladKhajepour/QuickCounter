package qc.main;

import android.content.SharedPreferences;
import android.service.quicksettings.Tile;

import utils.NotificationUtils;
import utils.Settings;
import utils.Counter;

import static utils.Settings.PREFS;
import static utils.Settings.notificationsEnabled;

/**
 * Created by Melad Khajepour
 */

class TileActions {

    private QCTileService ts;
    private SharedPreferences prefs;

    TileActions(QCTileService tileService) {
        ts = tileService;
        prefs = ts.getSharedPreferences(PREFS, 0);
    }

    void onCreate() {
        new Settings(prefs);
    }

    void onStartListening() {
        updateTile();
    }

    void onStopListening() {
        Counter.saveTile(prefs);
    }

    void onClick() {

        Counter.incrementCount();

        if(notificationsEnabled) {
            new NotificationUtils(ts).createNotification();
        }

        updateTile();
    }

    void onTileAdded() {
        updateTile();
    }

    void onTileRemoved() {
        Counter.resetCount();
        Counter.resetLabel();
    }

    void updateTile() {

        Tile tile = ts.getQsTile();

        if (tile != null) {
            tile.setLabel(Counter.getLabel());
            tile.setIcon(Counter.createIcon());
            tile.updateTile();
        }
    }
}
