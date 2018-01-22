package yellow.sausages.com.exam;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IntentActivity extends AppCompatActivity {

    static final int REQUEST_SELECT_PHONE_NUMBER = 1;

    private TextView answerText;
    private TextView contactText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        //init the frontend stuff
        contactText = findViewById(R.id.contactName);
        answerText = findViewById(R.id.intentString);

        //get extra and place it on xml
        Bundle bundle = getIntent().getExtras();
        final String answer = bundle.getString("examEx");
        answerText.setText(answer);


    }

    public void selectContact(View view) {
        //start the contact (implicit) intent
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);

            if(cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(nameIndex);
                contactText.setText(name);
            }
        }
    }

    public void startNewActivity(View view) {
        Intent intent = new Intent(IntentActivity.this, ResponsiveLayoutsActivity.class);
        startActivity(intent);
    }

    public void startDRBrowser(View view) {
        Uri webpage = Uri.parse("https://www.dr.dk");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
