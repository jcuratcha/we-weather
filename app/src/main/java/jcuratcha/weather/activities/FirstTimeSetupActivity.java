package jcuratcha.weather.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import jcuratcha.weather.R;

public class FirstTimeSetupActivity extends AppCompatActivity {

    SharedPreferences prefs = null;

    EditText mLocationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setup);

        mLocationName = (EditText)findViewById(R.id.current_location_name);

        prefs = getSharedPreferences("com.doorcrasher.WeWeather", MODE_PRIVATE);
    }

    public void onClickSaveButton(View view) {

        if (mLocationName != null) {
            String location = mLocationName.getText().toString();
            prefs.edit().putString("currentLocation", location).apply();
        }

        finish();
    }
}
