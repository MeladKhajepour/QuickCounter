package handlers;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import qc.main.R;
import utils.Counter;

/**
 * Created by Melad Khajepour
 */

public class ButtonHandler implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.applyBtn:
                Counter.setLabel(((EditText) v.getRootView().findViewById(R.id.labelField)).getText().toString());
                break;
            case R.id.resetBtn:
                Counter.resetCount();
                break;
            case R.id.timeoutDisabled:
                Spinner notifTimeoutSpinner = (Spinner) v.getRootView().findViewById(R.id.notifTimeout);
                Switch timeoutDisabledSwitch = (Switch) v;

                if(timeoutDisabledSwitch.isChecked()) {
                    notifTimeoutSpinner.setEnabled(false);
                } else {
                    notifTimeoutSpinner.setEnabled(true);
                }
                break;
        }
    }
}
