package org.bulletSamples;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnTouchListener;

public class BulletTestActivity extends Activity {
	public OpenGLRenderer renderer;
	
	/*private OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			return true;
		}
	};*/
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)
        GLSurfaceView view = new GLSurfaceView(this);
        //view.setOnTouchListener(touchListener);
        renderer = new OpenGLRenderer();
   		view.setRenderer(renderer);
   		setContentView(view);
   		System.loadLibrary("LinearMath");
   		System.loadLibrary("BulletCollision");
   		System.loadLibrary("BulletDynamics");
   		System.loadLibrary("bulletWrapper");
    }
}