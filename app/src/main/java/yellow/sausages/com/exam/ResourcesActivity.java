package yellow.sausages.com.exam;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ResourcesActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);

        //not much going on here
        //look in styles.xml for some fancy background action
        //want to translate your .xml source?
        //https://translate.google.com/manager/android_studio/?asVer=3.0.1.0&pkgName=yellow_sausages_com_exam&appId=yellow_sausages_com_exam&apkVer=1

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isTrue = sharedPreferences.getBoolean("pref_key_auto_delete", false);
        String list_preference_1 = sharedPreferences.getString("list_preference_1", "");
        String example_text = sharedPreferences.getString("example_text", getString(R.string.pref_default_display_name));

        text1.setText(isTrue.toString());
        text2.setText(list_preference_1);
        text3.setText(example_text);
    }

    public void openPreference(View view) {
        Intent intent = new Intent(ResourcesActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openHMSettings(View view) {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new HMSettingsActivity())
                .commit();
    }
}
