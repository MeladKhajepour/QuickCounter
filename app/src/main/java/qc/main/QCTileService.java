package qc.main;

import android.content.Intent;
import android.service.quicksettings.TileService;

import utils.NotificationUtils;

/**
 * Created by Melad Khajepour.
 */

public class QCTileService extends TileService {


    @Override
    public void onCreate() {
        super.onCreate();
        try {
            new TileActions(this).onCreate();
        } catch (Throwable e) {
            startActivity(new Intent(this, ErrorPage.class).putExtra("error", e.toString()));
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
try {
        //startService intents from Receivers
        if(intent.getBooleanExtra("update", false)) {
            new TileActions(this).updateTile();
        }
        if(intent.getBooleanExtra("dismiss", false)) {
            new NotificationUtils(this).dismissNotification();
        }
        if(intent.getBooleanExtra("notification", false)) {
            new NotificationUtils(this).createNotification();
        }
    } catch (Throwable e) {
        startActivity(new Intent(this, ErrorPage.class).putExtra("error", e.toString()));
    }

        return START_STICKY;
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
try{
        new TileActions(this).onStartListening();
    } catch (Throwable e) {
        startActivity(new Intent(this, ErrorPage.class).putExtra("error", e.toString()));
    }
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
try{
        new TileActions(this).onStopListening();
    } catch (Throwable e) {
        startActivity(new Intent(this, ErrorPage.class).putExtra("error", e.toString()));
    }
    }

    @Override
    public void onClick() {
        super.onClick();
        try{
        new TileActions(this).onClick();
    } catch (Throwable e) {
        startActivity(new Intent(this, ErrorPage.class).putExtra("error", e.toString()));
    }
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        try{
        new TileActions(this).onTileAdded();
    } catch (Throwable e) {
        startActivity(new Intent(this, ErrorPage.class).putExtra("error", e.toString()));
    }
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        try{
        new TileActions(this).onTileRemoved();
    } catch (Throwable e) {
        startActivity(new Intent(this, ErrorPage.class).putExtra("error", e.toString()));
    }
    }
}
