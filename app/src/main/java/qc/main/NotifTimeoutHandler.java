package qc.main;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import static utils.NotificationUtils.notifTimeout;

/**
 * Created by Melad Khajepour
 */

public class NotifTimeoutHandler implements AdapterView.OnItemSelectedListener {

    private Context ctx;

    public NotifTimeoutHandler(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        Toast.makeText(ctx, parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();

        switch (pos) {
            case 0:
                notifTimeout = 2000;
                break;
            case 1:
                notifTimeout = 3000;
                break;
            case 2:
                notifTimeout = 5000;
                break;
            case 3:
                notifTimeout = 10000;
                break;
            case 4:
                notifTimeout = -1;
        }

        ctx.getSharedPreferences("prefs", 0).edit().putInt("timeout", notifTimeout).apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
