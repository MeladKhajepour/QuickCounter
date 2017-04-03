package qc.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Melad Khajepour
 */

public class ErrorPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_activity);

        ((TextView) findViewById(R.id.errorBox)).setText(getIntent().getStringExtra("error"));
    }
}
