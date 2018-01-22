package yellow.sausages.com.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitiesActivity extends AppCompatActivity {

    private ImageView cycleImage;
    private TextView answerText;
    private Button toastButton;
    private ProgressBar proBar;
    private int theProgress = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        //init progressbar
        proBar = findViewById(R.id.proBar);
        proBar.setProgress(theProgress);
        proBar.setMax(40);

        //logging example (flip phone for results)
        if(savedInstanceState != null) {
            Log.d("examLog", "Hello, you restored me from a previous state");
        } else {
            Log.d("examLog", "Hello, you started a new activity");
        }

        //get extra and place it on xml
        Bundle bundle = getIntent().getExtras();
        final String answer = bundle.getString("examEx");
        answerText = findViewById(R.id.answerText);
        answerText.setText(answer);

        cycleImage = findViewById(R.id.cycleImage);
        cycleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("examLog", "You clicked on me!");
            }
        });

        toastButton = findViewById(R.id.toastButton);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivitiesActivity.this, "here is your toast", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OnProgress(View view) {

        switch (view.getId()) {
            case R.id.add:
                theProgress++;
                break;
            case R.id.subtract:
                theProgress--;
                break;
        }
        proBar.setProgress(theProgress);
    }
}
