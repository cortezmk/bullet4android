package org.bulletSamples;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.physics.*;
import org.bulletSamples.scene.*;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

import java.util.Calendar;

public class OpenGLRenderer implements Renderer {
	
	public BaseScene scene = null;
	public DynamicsWorld phys = null;
	public long lastTime = 0;
	
	private void enableLight(GL10 gl)
	{
		gl.glLightfv(0, GL10.GL_POSITION, new float[] { 0, 0, 0 }, 0);
		gl.glLightfv(0, GL10.GL_AMBIENT, new float[] { 0, 0, 0, 1 }, 0);
		gl.glLightfv(0, GL10.GL_DIFFUSE, new float[] { 1, 1, 1, 1 }, 0);
		gl.glLightfv(0, GL10.GL_SPECULAR, new float[] { 1, 1, 1, 1 }, 0);
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
		gl.glMaterialfv(GL10.GL_BACK, GL10.GL_EMISSION, new float[] { 0, 0, 1, 1 }, 0);
		gl.glMaterialfv(GL10.GL_BACK, GL10.GL_SPECULAR, new float[] { 0, 1, 0, 1 }, 0);
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		enableLight(gl);

		gl.glClearColor(1.0f, 0.0f, 0.0f, 0.5f);  
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
		lastTime = Calendar.getInstance().getTimeInMillis();
   		phys = new DynamicsWorld();
   		//scene = new NewtonPendulumScene(phys);
   		scene = new NewtonPendulumLogScene(phys);
   		//scene = new SimpleScene(phys);
   		//scene = new SimpleSphereScene(phys);
   		//scene = new SnookerScene(phys);
   		//scene = new ClosedSnookerScene(phys);
   		//scene = new ClosedSnookerScene2(phys);
   		//scene = new ClosedSnookerScene3(phys);
   		//scene = new SpringScene(phys);
   		//scene = new JellyScene(phys);
   		//scene = new RotationTestScene(phys);
   		//scene = new SimpleSnookerScene(phys);
   		//scene = new SimpleSnookerScene2(phys);
   		//scene = new SimpleSnookerSceneSeparatedEnergies(phys);
   		//scene = new SimpleSnookerSceneFts(phys);
   		//scene = new RotTestScene(phys);
   		//scene = new CylinderShapeScene(phys);
   		//scene = new InclinedPlaneScene(phys);
   		scene.create();
	}

	public void onDrawFrame(GL10 gl)
	{
		scene.update();
		long timeElapsed = Calendar.getInstance().getTimeInMillis() - lastTime;
		lastTime = Calendar.getInstance().getTimeInMillis();
		phys.stepSimulation((int)timeElapsed);
		gl.glClearColor(.5f, .7f, 1.0f, 1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		scene.render(gl);
	}
	
	public void onTouch(MotionEvent event)
	{
		scene.onTouch(event);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		BaseScene.width = width;
		BaseScene.height = height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f,
                                   (float) width / (float) height,
                                   0.1f, 100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
}
