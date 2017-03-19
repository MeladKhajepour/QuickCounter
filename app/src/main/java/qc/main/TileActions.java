package qc.main;

import utils.NotificationUtils;
import utils.TileUtils;

/**
 * Created by Melad Khajepour
 */

class TileActions {

    private QCTileService ts;

    TileActions(QCTileService tileService) {
        ts = tileService;
    }

    void onClick() {

        TileUtils.incrementCount();
        new NotificationUtils(ts).createNotification();
        updateTile();
    }

    void onStartListening() {
        updateTile();
    }

    void onTileAdded() {
        updateTile();
    }

    void updateTile() {

        ts.getSharedPreferences("prefs", 0).edit()
                .putInt("count", TileUtils.getCount())
                .putString("label", TileUtils.getLabel())
                .apply();

        ts.getQsTile().setLabel(TileUtils.getLabel());
        ts.getQsTile().setIcon(TileUtils.createIcon());
        ts.getQsTile().updateTile();
    }
}
