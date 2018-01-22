package yellow.sausages.com.exam;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        categories.add("Sensors & External Hardware");

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
                    case "Responsive Layouts":
                        intent = new Intent(MainActivity.this, ResponsiveLayoutsActivity.class);
                        break;
                    case "In case they think you should remember everything":
                        setContentView(R.layout.einstein);
                        break;
                    case "Activities":
                        intent = new Intent(MainActivity.this, ActivitiesActivity.class);
                        //put extra for the new activity
                        intent.putExtra("examEx", "Hello, I come from a previous Activity");
                        break;
                    case "Intents":
                        intent = new Intent(MainActivity.this, IntentActivity.class);
                        intent.putExtra("examEx", "I was supposed to be put here");
                        break;
                    case "Resources":
                        intent = new Intent(MainActivity.this, ResourcesActivity.class);
                        break;
                    case "AdapterViews":
                        intent = new Intent(MainActivity.this, AdapterViewsActivity.class);
                        break;
                    case "Animations":
                        intent = new Intent(MainActivity.this, AnimationsActivity.class);
                        break;
                    case "Navigation":
                        intent = new Intent(MainActivity.this, NavigationActivity.class);
                        break;
                    case "Google Firebase":
                        intent = new Intent(MainActivity.this, GoogleFirebaseActivity.class);
                        break;
                    case "Data Storage":
                        intent = new Intent(MainActivity.this, DataStorageActivity.class);
                        break;
                    case "Networking":
                        intent = new Intent(MainActivity.this, NetworkingActivity.class);
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
