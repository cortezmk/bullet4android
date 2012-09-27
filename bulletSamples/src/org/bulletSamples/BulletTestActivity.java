package org.bulletSamples;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnTouchListener;

public class BulletTestActivity extends Activity implements SensorEventListener {
	public OpenGLRenderer renderer;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
	private OnTouchListener touchListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			renderer.onTouch(event);
			return true;
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)
        GLSurfaceView view = new GLSurfaceView(this);
        view.setOnTouchListener(touchListener);
        renderer = new OpenGLRenderer();
   		view.setRenderer(renderer);
   		setContentView(view);
   		System.loadLibrary("LinearMath");
   		System.loadLibrary("BulletCollision");
   		System.loadLibrary("BulletDynamics");
   		System.loadLibrary("bulletWrapper");
   		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }
    
    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}

	public void onSensorChanged(SensorEvent event)
	{
		//Accelerometer.gravity.x = event.values[0];
		//Accelerometer.gravity.y = -event.values[2];
		//Accelerometer.gravity.z = event.values[1];
		Accelerometer.gravity.x = event.values[0];
		Accelerometer.gravity.y = event.values[1];
		Accelerometer.gravity.z = event.values[2];
	}
}