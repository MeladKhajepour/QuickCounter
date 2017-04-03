package qc.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import handlers.ButtonHandler;
import handlers.NotifTimeoutHandler;
import handlers.SwitchHandler;
import utils.Counter;
import utils.Settings;

import static utils.Settings.PREFS;

public class MainActivity extends Activity {

    private SharedPreferences prefs;
    private ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(PREFS, 0);
        root = (ViewGroup) findViewById(R.id.rootLayout);
        setupHandlers(root);

        new Settings(prefs);
    }

    @Override
    public void onResume() {
        super.onResume();

        restoreViewStates(root);
    }

    @Override
    public void onStop() {
        super.onStop();

        saveViewStates(root);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    private void saveViewStates(View v) {
        ViewGroup root = (ViewGroup) v;
        View view;

        for(int i = 0; i < root.getChildCount(); i++) {
            view = root.getChildAt(i);

            if(view instanceof Switch) {
                prefs.edit().putBoolean("" + view.getId(), ((Switch) view).isChecked()).apply();

            } else if (view instanceof Spinner) {
                prefs.edit().putInt("" + view.getId(), ((Spinner) view).getSelectedItemPosition()).apply();

            } else if(view instanceof LinearLayout) {
                saveViewStates(view);
            }
        }
    }

    private void setupHandlers(View v) {
        ViewGroup root = (ViewGroup) v;
        SwitchHandler switchHandler = new SwitchHandler(this);
        ButtonHandler buttonHandler = new ButtonHandler();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.change_notifs_dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        View view;

        for(int i = 0; i < root.getChildCount(); i++) {
            view = root.getChildAt(i);

            if(view instanceof Switch) {
                ((Switch) view).setOnCheckedChangeListener(switchHandler);
                if(view.getId() == R.id.timeoutDisabled) {
                    view.setOnClickListener(buttonHandler);
                }

            } else if (view instanceof Spinner) {
                ((Spinner) view).setAdapter(adapter);
                ((Spinner) view).setOnItemSelectedListener(new NotifTimeoutHandler(prefs));

            } else if(view instanceof Button) {
                view.setOnClickListener(buttonHandler);

            } else if(view instanceof LinearLayout) {
                setupHandlers(view);
            }
        }
    }

    private void restoreViewStates(View v) {
        ViewGroup root = (ViewGroup) v;
        View view;

        for(int i = 0; i < root.getChildCount(); i++) {
            view = root.getChildAt(i);

            if(view instanceof Switch && view.getId() == R.id.timeoutDisabled) {
                ((Switch) view).setChecked(prefs.getBoolean("" + view.getId(), false));

            } else if(view instanceof Switch) {
                ((Switch) view).setChecked(prefs.getBoolean("" + view.getId(), true));

            } else if (view instanceof Spinner) {
                ((Spinner) view).setSelection(prefs.getInt("" + view.getId(), 0));

            } else if(view instanceof EditText){
                ((EditText) view).setText(Counter.getLabel());

            } else if(view instanceof LinearLayout) {
                restoreViewStates(view);
            }
        }
    }
}