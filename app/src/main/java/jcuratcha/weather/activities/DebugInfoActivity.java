package jcuratcha.weather.activities;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jcuratcha.weather.R;

public class DebugInfoActivity extends Activity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_info);

        layout = (LinearLayout) findViewById(R.id.activity_debug_info);

        loadPreferences();
    }

    private void loadPreferences() {

        Map<String, ?> prefs =
                PreferenceManager.getDefaultSharedPreferences(this)
                .getAll();

        int numPrefs = prefs.size();

        List<TextView> prefTextViews = new ArrayList<>();

        for (String key : prefs.keySet()) {
            Object pref = prefs.get(key);
            String printVal = "";
            if (pref instanceof Boolean) {
                printVal =  key + " : " + (Boolean) pref;
            }
            if (pref instanceof Float) {
                printVal =  key + " : " + (Float) pref;
            }
            if (pref instanceof Integer) {
                printVal =  key + " : " + (Integer) pref;
            }
            if (pref instanceof Long) {
                printVal =  key + " : " + (Long) pref;
            }
            if (pref instanceof String) {
                printVal =  key + " : " + (String) pref;
            }
            if (pref instanceof Set<?>) {
                printVal =  key + " : " + (Set<String>) pref;
            }

            TextView prefTextView = new TextView(this);
            prefTextView.setText(key + " : " + printVal);
            layout.addView(prefTextView);
            prefTextViews.add(prefTextView);
        }
    }
}
