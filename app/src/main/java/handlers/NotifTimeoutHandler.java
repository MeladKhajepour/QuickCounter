package handlers;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;

import static utils.Settings.NOTIF_TIMEOUT_VALUE;
import static utils.Settings.notifTimeout;

/**
 * Created by Melad Khajepour
 */

public class NotifTimeoutHandler implements AdapterView.OnItemSelectedListener {

    private SharedPreferences prefs;

    public NotifTimeoutHandler(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (pos) {
            case 0:
                notifTimeout = 3000;
                break;
            case 1:
                notifTimeout = 5000;
                break;
            case 2:
                notifTimeout = 8000;
                break;
        }

        prefs.edit().putLong(NOTIF_TIMEOUT_VALUE, notifTimeout).apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
