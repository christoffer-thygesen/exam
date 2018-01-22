package yellow.sausages.com.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GoogleFirebaseActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ArrayAdapter<String> categoryAdapter;
    private EditText editText;
    private Button submitButton;
    private ListView listView;
    private ArrayList<String> firebaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_firebase);

        firebaseList = new ArrayList<>();
        editText = findViewById(R.id.inputText);
        submitButton = findViewById(R.id.submitButton);
        listView = findViewById(R.id.firebaseListview);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String line = dataSnapshot.getValue(String.class);

                firebaseList.add(line);
                listView.setAdapter(categoryAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.push().setValue(editText.getText().toString());
            }
        });

        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, firebaseList);
        listView.setAdapter(categoryAdapter);
    }
}
