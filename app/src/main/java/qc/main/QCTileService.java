package qc.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.service.quicksettings.TileService;

/**
 * Created by Melad Khajepour.
 */

public class QCTileService extends TileService {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        SharedPreferences prefs =
                getSharedPreferences("prefs", 0);
        TileHandlers.handleOnStartCommand(this, intent, prefs);

        return START_STICKY;
    }

    @Override
    public void onClick() {
        super.onClick();
        TileHandlers.onClick(this);
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        TileHandlers.onStartListening(this);
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        TileHandlers.onTileAdded(this);
    }
}
