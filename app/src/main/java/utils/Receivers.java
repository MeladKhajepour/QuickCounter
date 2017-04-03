package utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

import qc.main.QCTileService;

import static utils.Settings.dismissOnAction;

/**
 * Created by Melad Khajepour
 */

class Receivers {

    static final String REMOTE_INPUT_ID = "remoteInput";

    public static class UpdateLabel extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Counter.setLabel(getNewLabel(intent));
            sendIntent(context, "Label Updated");
        }
    }

    public static class ResetAll extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Counter.resetCount();
            Counter.resetLabel();
            sendIntent(context, "Tile Reset");
        }
    }

    public static class ResetCounter extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Counter.resetCount();
            sendIntent(context, "Counter Reset");
        }
    }

    private static void sendIntent(Context context, String toastText) {

        Intent intent = new Intent(context, QCTileService.class).putExtra("update", true);

        if(dismissOnAction) {
            intent.putExtra("dismiss", true);
        } else {
            intent.putExtra("notification", true);
        }

        context.startService(intent);
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
    }

    private static String getNewLabel(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getString(REMOTE_INPUT_ID);
        }
        return null;
    }
}