package utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.app.RemoteInput;

import qc.main.MainActivity;
import qc.main.QCTileService;
import qc.main.R;

import static android.content.Context.NOTIFICATION_SERVICE;
import static utils.Settings.notifTimeout;
import static utils.Settings.resetAllAction;
import static utils.Settings.resetCounterAction;
import static utils.Settings.resetLabelAction;


/**
 * Created by Melad Khajepour
 */

public class NotificationUtils {

    private final int notificationId = 0;
    private final Context ctx;
    private final NotificationManager nm;
    private boolean handlerSet = false;

    public NotificationUtils(QCTileService ts) {

        ctx = ts.getApplicationContext();
        nm = (NotificationManager) ts.getSystemService(NOTIFICATION_SERVICE);
    }

    public void createNotification() {

        Notification.Builder builder = new Notification.Builder(ctx);

        builder.setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(Counter.createIcon())
                .setContentTitle("Expand for actions. Counter is at " + Counter.getCount() + ".")
                .setAutoCancel(true)
                .setContentIntent(activityPendingIntent(MainActivity.class));

        if(resetLabelAction) {
            builder.addAction(changeOrResetLabelAction());
        }
        if(resetAllAction) {
            builder.addAction(resetAllAction());
        }
        if(resetCounterAction) {
            builder.addAction(resetCounterAction());
        }

        nm.notify(notificationId, builder.build());

        if(notifTimeout > 0 && !handlerSet) {
            Handler h = new Handler();
            handlerSet = true;
            h.postDelayed(new Runnable() {
                public void run() {
                    dismissNotification();
                    handlerSet = false;
                }
            }, notifTimeout);
        }
    }

    public void dismissNotification() {
        nm.cancel(notificationId);
    }

    private Notification.Action changeOrResetLabelAction() {

        return new Notification.Action.Builder(
                Icon.createWithResource(ctx, R.drawable.ic_refresh_black_24dp),
                "Change Label",
                broadcastPendingIntent("label_action")).addRemoteInput(
                    new RemoteInput.Builder(Receivers.REMOTE_INPUT_ID).setLabel(
                        "Enter new label or leave blank to reset").build()).build();
    }

    private Notification.Action resetAllAction() {

        return new Notification.Action.Builder(
                Icon.createWithResource(ctx, R.drawable.ic_refresh_black_24dp),
                "Reset All",
                broadcastPendingIntent("reset_all_action")).build();
    }

    private Notification.Action resetCounterAction() {

        return new Notification.Action.Builder(
                Icon.createWithResource(ctx, R.drawable.ic_refresh_black_24dp),
                "Reset Count",
                broadcastPendingIntent("reset_action")).build();
    }

    private PendingIntent activityPendingIntent(Class activity) {

        return PendingIntent.getActivity(ctx, 0, new Intent(ctx, activity), 0);
    }

    private PendingIntent broadcastPendingIntent(String activity) {

        return PendingIntent.getBroadcast(ctx, 0, new Intent(activity), 0);
    }
}
