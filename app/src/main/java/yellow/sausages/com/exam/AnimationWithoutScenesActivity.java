package yellow.sausages.com.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.transition.Fade.IN;
import static android.transition.TransitionManager.beginDelayedTransition;

public class AnimationWithoutScenesActivity extends AppCompatActivity {

    private TextView mLabelText;
    private Fade mFade;
    private ViewGroup mRootView;
    private Button animateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_without_scenes);

//
//// Get the root view and create a transition
        mRootView = findViewById(R.id.aniRootScene);
        mLabelText = new TextView(this);
        mLabelText.setText("memes for everyone!");
        animateButton = findViewById(R.id.buttonAnimate);
        mFade = new Fade(IN);

        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("aniDebug", mRootView + "");
                Log.d("aniDebug", mFade + "");
                Log.d("aniDebug", mRootView + "");

                beginDelayedTransition(mRootView, mFade);
                mRootView.addView(mLabelText);
            }
        });
    }
}
