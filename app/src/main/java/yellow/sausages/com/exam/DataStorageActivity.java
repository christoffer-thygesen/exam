package yellow.sausages.com.exam;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataStorageActivity extends AppCompatActivity {

    private TextView sharedPrefText;
    private EditText sharedPrefEdit;
    private Button sharedPrefButton;
    private EditText SQLiteEditText;
    private Button SQLiteButton;
    private ListView SQLiteListView;

    private NameDbHelper mDbHelper;
    private ArrayList<String> nameList;

    private SharedPreferences sharedPreferences;
    private final static String TAG = "examEx";
    private final static String DEFAULT_VALUE = "Danny Cool";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);

        nameList = new ArrayList<>();
        sharedPrefButton = findViewById(R.id.buttonSharedPref);
        sharedPrefEdit = findViewById(R.id.editSharedPref);
        sharedPrefText = findViewById(R.id.textSharedPref);
        SQLiteEditText = findViewById(R.id.SQLiteEditText);
        SQLiteButton = findViewById(R.id.SQLitebutton);
        SQLiteListView = findViewById(R.id.SQLiteListview);

        //shared pref stuff
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        sharedPrefText.setText(sharedPreferences.getString(TAG, DEFAULT_VALUE));
        sharedPrefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = sharedPrefEdit.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TAG, input);
                editor.commit();
                sharedPrefText.setText(input);
            }
        });

        //SQLite stuff
        //Realm, Object Box, Room
        readNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        SQLiteListView.setAdapter(adapter);

        SQLiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertName(SQLiteEditText.getText().toString());
                readNames();
                SQLiteEditText.setText("");
            }
        });
    }

    public void insertName(String name) {
        NameDbHelper helper = new NameDbHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NameContract.NameEntry.COLUMN_NAME, name);

        db.insert(NameContract.NameEntry.TABLE_NAME, null, values);
        helper.close();
    }

    public void readNames() {
        NameDbHelper helper = new NameDbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(NameContract.NameEntry.TABLE_NAME, null, null,
                null, null, null, null);

        try {
            int idIndex = cursor.getColumnIndex(NameContract.NameEntry.ID);
            int nameIndex = cursor.getColumnIndex(NameContract.NameEntry.COLUMN_NAME);

            while(cursor.moveToNext()) {
                int currentID = cursor.getInt(idIndex);
                String currentName = cursor.getString(nameIndex);

                nameList.add(currentID + " - " + currentName);
            }

        } finally {
            cursor.close();
            helper.close();
        }
    }
}
