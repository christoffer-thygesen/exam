package yellow.sausages.com.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResponsiveLayoutsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsive_layouts);
    }

    public void goToLinearLayout(View view) {
        setContentView(R.layout.responsive_layouts2);
    }

    public void goToRelativeLayout(View view) {
        setContentView(R.layout.responsive_layouts3);
    }
}
