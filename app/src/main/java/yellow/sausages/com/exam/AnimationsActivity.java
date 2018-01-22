package yellow.sausages.com.exam;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AnimationsActivity extends AppCompatActivity {

    private TextView moveText;
    private Float offset = 0f;

    private Scene mAScene;
    private Scene mAnotherScene;
    private ViewGroup mSceneRoot;
    private boolean isClicked;
    private Button buttonMoveLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);

        buttonMoveLeft = findViewById(R.id.buttonMoveLeft);
        moveText = findViewById(R.id.moveTextView);
        mSceneRoot = findViewById(R.id.content);
        mSceneRoot = findViewById(R.id.scene_root);

        mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.a_scene, this);
        mAnotherScene = Scene.getSceneForLayout(mSceneRoot, R.layout.another_scene, this);
    }

    public void onMove(View view) {

        ObjectAnimator animator;

        switch (view.getId()) {
            case R.id.buttonMoveLeft:
                offset-= 100f;
                animator = ObjectAnimator.ofFloat(moveText, "translationX", offset);
                break;
            case R.id.buttonMoveRight:
                offset+= 100f;
                animator = ObjectAnimator.ofFloat(moveText, "translationX", offset);
                break;
            default:
                animator = null;
        }

        animator.setDuration(1000);
        animator.start();
    }

    public void playAnimation(View view) {
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade_transition);
        if(!isClicked) {
            TransitionManager.go(mAnotherScene, fade);
            isClicked = true;
        } else {
            TransitionManager.go(mAScene, fade);
            isClicked = false;
        }
    }

    public void startAnimationWOScenes(View view) {
        Intent intent = new Intent(AnimationsActivity.this, AnimationWithoutScenesActivity.class);
        startActivity(intent);
    }
}
