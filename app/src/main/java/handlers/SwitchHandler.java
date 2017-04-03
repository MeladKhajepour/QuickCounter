package handlers;

import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import qc.main.MainActivity;
import qc.main.R;

import static utils.Settings.DISMISS_ON_ACTION_SWITCH;
import static utils.Settings.NOTIFICATIONS_ENABLED_SWITCH;
import static utils.Settings.NOTIF_TIMEOUT_VALUE;
import static utils.Settings.PREFS;
import static utils.Settings.RESET_ALL_ACTION_SWITCH;
import static utils.Settings.RESET_COUNTER_ACTION_SWITCH;
import static utils.Settings.RESET_LABEL_ACTION_SWITCH;
import static utils.Settings.dismissOnAction;
import static utils.Settings.notifTimeout;
import static utils.Settings.notificationsEnabled;
import static utils.Settings.resetAllAction;
import static utils.Settings.resetCounterAction;
import static utils.Settings.resetLabelAction;

/**
 * Created by Melad Khajepour
 */

public class SwitchHandler implements CompoundButton.OnCheckedChangeListener {

    private MainActivity main;
    private SharedPreferences prefs;

    public SwitchHandler(MainActivity m) {
        main = m;
        prefs = m.getSharedPreferences(PREFS,0);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int id = buttonView.getId();

        switch (id) {
            case R.id.notifSwitch:
                notificationsEnabled = isChecked;
                prefs.edit().putBoolean(NOTIFICATIONS_ENABLED_SWITCH, isChecked).apply();
                toggleNotifs(isChecked);
                break;
            case R.id.actionLabel:
                resetLabelAction = isChecked;
                prefs.edit().putBoolean(RESET_LABEL_ACTION_SWITCH, isChecked).apply();
                break;
            case R.id.actionReset:
                resetAllAction= isChecked;
                prefs.edit().putBoolean(RESET_ALL_ACTION_SWITCH, isChecked).apply();
                break;
            case R.id.actionCounter:
                resetCounterAction = isChecked;
                prefs.edit().putBoolean(RESET_COUNTER_ACTION_SWITCH, isChecked).apply();
            case R.id.dismissOnAction:
                dismissOnAction = isChecked;
                prefs.edit().putBoolean(DISMISS_ON_ACTION_SWITCH, isChecked).apply();
                break;
            case R.id.timeoutDisabled:
                notifTimeout = -1;
                prefs.edit().putLong(NOTIF_TIMEOUT_VALUE, notifTimeout).apply();
                break;
        }
    }

    private void toggleNotifs(boolean state) {
        ViewGroup root = (ViewGroup) main.findViewById(R.id.rootLayout);
        View view;

        for(int i = 0; i < root.getChildCount(); i++) {
            view = root.getChildAt(i);

            if((view instanceof Switch || view instanceof Spinner) && view.getId() != R.id.notifSwitch) {
                view.setEnabled(state);
            }
        }
    }
}