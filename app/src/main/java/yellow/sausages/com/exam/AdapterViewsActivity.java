package yellow.sausages.com.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AdapterViewsActivity extends AppCompatActivity {

    private Spinner mySpinner;
    private ListView examListview;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_views);

        names = new ArrayList<>();
        names.add("Rebeca Capozzoli");
        names.add("Richard Knipe");
        names.add("Shasta Widger");
        names.add("Jody Schmitt");
        names.add("Socorro Wicklund");
        names.add("Elease Curtis");
        names.add("Aura Palka");
        names.add("Brian Masson");
        names.add("Craig Okada");
        names.add("Yulanda Ledwell");
        names.add("Pia Hefner");
        names.add("Weston Westbrooks");
        names.add("Karina Capers");
        names.add("Leonia Lango");
        names.add("Joann Sunde");
        names.add("Shani Lares");
        names.add("Jeri Haris");
        names.add("Eloisa Hymas");

        mySpinner = findViewById(R.id.mySpinner);
        examListview = findViewById(R.id.examListview);

        //setup for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

//        //setup custom listview
        ArrayAdapter examAdapter = new ExamListAdapter(this, names);
        examListview.setAdapter(examAdapter);
    }

    public void goToRecyclerActivity(View view) {
        Intent intent = new Intent(AdapterViewsActivity.this, RecyclerViewActivity.class);
        startActivity(intent);
    }
}
