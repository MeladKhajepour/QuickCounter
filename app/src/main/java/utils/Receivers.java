package utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

import qc.main.QCTileService;

import static utils.TileUtils.resetCount;
import static utils.TileUtils.setLabel;

/**
 * Created by Melad Khajepour
 */

class Receivers {

    static final String REMOTE_INPUT_ID = "remoteInput";

    private static void dismissNotificationWithToast(Context context, String toastText) {

        Intent intent = new Intent(context, QCTileService.class);
        intent.putExtra("update", true);
        intent.putExtra("dismiss",true);
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();

        context.startService(intent);
    }

    public static class UpdateLabel extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            setLabel(getResults(intent));
            dismissNotificationWithToast(context, "Label Updated");
        }
    }

    public static class ResetCounter extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            resetCount();
            dismissNotificationWithToast(context, "Counter Reset");
        }
    }

    private static String getResults(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getString(REMOTE_INPUT_ID);
        }
        return null;
    }
}