package yellow.sausages.com.exam;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorsActivity extends AppCompatActivity implements SensorEventListener {

    private TextView sensorListText;
    private TextView tempText;
    private TextView lightText;
    private ImageView compassImageView;

    private SensorManager sm;
    private Sensor temp;
    private Sensor light;
    private Sensor accelerometer;
    private Sensor magnetometer;

    private float currentCompassAngle = 0;
    private float[] readingMagnetometer = new float[3];
    private float[] readingAccelerometer = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        sensorListText = findViewById(R.id.sensorListText);
        tempText = findViewById(R.id.tempText);
        lightText = findViewById(R.id.lightText);
        compassImageView = findViewById(R.id.compassImageView);

        //Sensor list
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s : sensorList) {
            sensorListText.append(s.getName() + "\n");
        }

        //SENSOR THINGS
        temp = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(temp != null) {
            sm.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(light != null) {
            sm.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(accelerometer != null) {
            sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(magnetometer != null) {
            sm.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == temp) {
            tempText.setText(event.values[0] + "");
        }

        if(event.sensor == light) {
            lightText.setText(event.values[0] + "");
        }

        float[] rotation = new float[9];
        float[] orientation = new float[3];

        if(event.sensor == accelerometer) {
            readingAccelerometer[0] = event.values[0];
            readingAccelerometer[1] = event.values[1];
            readingAccelerometer[2] = event.values[2];
        }
        if(event.sensor == magnetometer) {
            readingMagnetometer[0] = event.values[0];
            readingMagnetometer[1] = event.values[1];
            readingMagnetometer[2] = event.values[2];
        }

        SensorManager.getRotationMatrix(rotation, null, readingAccelerometer, readingMagnetometer);
        SensorManager.getOrientation(rotation, orientation);
        float azimuthRadians = orientation[0];
        float azimuthDegrees = -(float) (Math.toDegrees(azimuthRadians) + 360)%360;

        makeAnimation(currentCompassAngle, azimuthDegrees, compassImageView);
        currentCompassAngle = azimuthDegrees;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void makeAnimation(float from, float to, View rotateme) {
        RotateAnimation ra = new RotateAnimation(from, to, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(200);
        ra.setFillAfter(true);
        rotateme.startAnimation(ra);
    }
}
