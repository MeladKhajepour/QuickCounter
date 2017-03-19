package utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

import qc.main.QCTileService;
import qc.main.R;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by Melad Khajepour
 */

public class NotificationUtils {

    private final int notificationId = 0;
    private final Context context;
    private final NotificationManager nm;

    public NotificationUtils(QCTileService tileService) {

        context = tileService.getApplicationContext();
        nm = (NotificationManager) tileService.getSystemService(NOTIFICATION_SERVICE);
    }

    public void createNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(R.drawable.ic_refresh_black_24dp)
                .setContentTitle("Expand for actions")
                .setContentText("Counter is at " + TileUtils.getCount() + ".")
                .addAction(changeOrResetLabelAction())
                .addAction(resetCounterAction())
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true);

        nm.notify(notificationId, builder.build());
    }

    public void dismissNotification() {
        nm.cancel(notificationId);
    }

    private NotificationCompat.Action changeOrResetLabelAction() {

        return new NotificationCompat.Action.Builder(
                R.drawable.ic_refresh_black_24dp, "Change or Reset Label",
                pendingIntent("label_action")).addRemoteInput(
                new RemoteInput.Builder(Receivers.REMOTE_INPUT_ID).setLabel(
                        "Enter new label or leave blank to reset").build()
        ).build();
    }

    private NotificationCompat.Action resetCounterAction() {

        return new NotificationCompat.Action.Builder(
                R.drawable.ic_refresh_black_24dp, "Reset Counter",
                pendingIntent("reset_action")).build();
    }

    private PendingIntent pendingIntent(String activity) {
        return PendingIntent.getBroadcast(context, 0, new Intent(activity), 0);
    }
}
