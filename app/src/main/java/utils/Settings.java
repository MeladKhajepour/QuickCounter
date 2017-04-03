package utils;

import android.content.SharedPreferences;

/**
 * Created by Melad Khajepour
 */

public class Settings {

    public static long notifTimeout;
    public static boolean notificationsEnabled;
    public static boolean resetLabelAction;
    public static boolean resetAllAction;
    public static boolean resetCounterAction;
    public static boolean dismissOnAction;
    public static Integer count;
    public static String label;

    public static final String PREFS = "prefs";
    public static final String NOTIF_TIMEOUT_VALUE = "notifTimeout";
    public static final String NOTIFICATIONS_ENABLED_SWITCH = "notificationsEnabled";
    public static final String DISMISS_ON_ACTION_SWITCH = "dismissOnAction";
    public static final String RESET_LABEL_ACTION_SWITCH = "resetLabelAction";
    public static final String RESET_ALL_ACTION_SWITCH = "resetAllAction";
    public static final String RESET_COUNTER_ACTION_SWITCH = "resetCounterAction";
    public static final String COUNT = "count";
    public static final String LABEL = "label";

    public Settings(SharedPreferences prefs) {

        if(!prefs.getBoolean("set", false)) {
            prefs.edit()
                    .putLong(NOTIF_TIMEOUT_VALUE, 3000)
                    .putBoolean(NOTIFICATIONS_ENABLED_SWITCH, true)
                    .putBoolean(RESET_LABEL_ACTION_SWITCH, true)
                    .putBoolean(RESET_ALL_ACTION_SWITCH, true)
                    .putBoolean(RESET_COUNTER_ACTION_SWITCH, true)
                    .putBoolean(DISMISS_ON_ACTION_SWITCH, true)
                    .putBoolean("set", true).apply();
        } else {
            notifTimeout = prefs.getLong(NOTIF_TIMEOUT_VALUE, 3000);
            notificationsEnabled = prefs.getBoolean(NOTIFICATIONS_ENABLED_SWITCH, true);
            resetLabelAction = prefs.getBoolean(RESET_LABEL_ACTION_SWITCH, true);
            resetAllAction = prefs.getBoolean(RESET_ALL_ACTION_SWITCH, true);
            resetCounterAction = prefs.getBoolean(RESET_COUNTER_ACTION_SWITCH, true);
            dismissOnAction = prefs.getBoolean(DISMISS_ON_ACTION_SWITCH, true);
        }
    }
}