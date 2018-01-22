package yellow.sausages.com.exam;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView categoriesListView;
    private ArrayList<String> categories;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        //init arraylist
        categories = new ArrayList<>();
        categories.add("In case they think you can remember everything");
        categories.add("Responsive Layouts");
        categories.add("Activities");
        categories.add("Intents");
        categories.add("Resources");
        categories.add("AdapterViews");
        categories.add("Animations");
        categories.add("Navigation");
        categories.add("Google Firebase");
        categories.add("Data Storage");
        categories.add("Networking");
        categories.add("Sensors");
        categories.add("External Hardware");

        //init arrayAdapter
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);

        //find listview
        categoriesListView = findViewById(R.id.categoryListView);

        //set adapter to listview
        categoriesListView.setAdapter(categoryAdapter);

        //set onclick on listview
        categoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = categories.get(position);
                Intent intent = null;

                switch (name) {
                    case "In case they think you should remember everything":
                        setContentView(R.layout.einstein);
                        break;
                    case "Responsive Layouts":
                        intent = new Intent(context, ResponsiveLayoutsActivity.class);
                        break;
                    case "Activities":
                        intent = new Intent(context, ActivitiesActivity.class);
                        //put extra for the new activity
                        intent.putExtra("examEx", "Hello, I come from a previous Activity");
                        break;
                    case "Intents":
                        intent = new Intent(context, IntentActivity.class);
                        intent.putExtra("examEx", "I was supposed to be put here");
                        break;
                    case "Resources":
                        intent = new Intent(context, ResourcesActivity.class);
                        break;
                    case "AdapterViews":
                        intent = new Intent(context, AdapterViewsActivity.class);
                        break;
                    case "Animations":
                        intent = new Intent(context, AnimationsActivity.class);
                        break;
                    case "Navigation":
                        intent = new Intent(context, NavigationActivity.class);
                        break;
                    case "Google Firebase":
                        intent = new Intent(context, GoogleFirebaseActivity.class);
                        break;
                    case "Data Storage":
                        intent = new Intent(context, DataStorageActivity.class);
                        break;
                    case "Networking":
                        intent = new Intent(context, NetworkingActivity.class);
                        break;
                    case "Sensors":
                        intent = new Intent(context, SensorsActivity.class);
                        break;
                    case "External Hardware":
                        intent = new Intent(context, ExternalHardwareActivity.class);
                        break;
                }

                if(intent == null) {
                    //do nothing
                } else {
                    startActivity(intent);
                }
            }
        });
    }
}
