package org.bulletSamples.physics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.*;
import org.bulletSamples.geometry.*;

public class DebugDrawer {
	public static native void renewEnv();
	public static GL10 gl;
    private static FloatBuffer verticesBuffer = null;
    private static ShortBuffer indicesBuffer = null;
    private static FloatBuffer normalBuffer = null;
    private static int numOfIndices = -1;
    private static float[] rgba = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
    private static FloatBuffer colorBuffer = null;
    private static float[] colors, normals, vertices;
    private static short[] indices;
    public static Vector3 dummy1 = Vector3.zero(), dummy2 = Vector3.zero(), dummy3 = Vector3.zero(), dummy4 = Vector3.zero();
	
    protected static void setColors(float[] colors) {
    	DebugDrawer.colors = colors;
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
    }
    
    protected static void setNormals(float[] normals) {
    	DebugDrawer.normals = normals;
    	ByteBuffer vbb = ByteBuffer.allocateDirect(normals.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		normalBuffer = vbb.asFloatBuffer();
		normalBuffer.put(normals);
		normalBuffer.position(0);
    }
    
    protected static void setIndices(short[] indices) {
    	DebugDrawer.indices = indices;
    	ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indicesBuffer = ibb.asShortBuffer();
		indicesBuffer.put(indices);
		indicesBuffer.position(0);
		numOfIndices = indices.length;
    }
    
    protected static void setVertices(float[] vertices) {
    	DebugDrawer.vertices = vertices;
    	ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		verticesBuffer = vbb.asFloatBuffer();
		verticesBuffer.put(vertices);
		verticesBuffer.position(0);
    }
    
	/*public static void drawLine(Vector3 from, Vector3 to, Vector3 colorFrom, Vector3 colorTo)
	{
		System.out.println("drawLine");
		if(from.nanGuard()) return;
		if(to.nanGuard()) return;
		if(colorFrom.nanGuard()) return;
		if(colorTo.nanGuard()) return;
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		setColors( new float[] { colorFrom.x, colorFrom.y, colorFrom.z, 1, colorTo.x, colorTo.y, colorTo.z, 1 });
		setVertices( new float[] { from.x, from.y, from.z, to.x, to.y, to.z } );
		setIndices( new short[] { 0, 1 } );
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glDrawElements(GL10.GL_LINES, 2, GL10.GL_UNSIGNED_SHORT, indicesBuffer);
	}*/
    
    public static void drawLine()
	{
    	Vector3 from = dummy1, to = dummy2, colorFrom = dummy3, colorTo = dummy4;
		//System.out.println("drawLine");
		if(from.nanGuard()) return;
		if(to.nanGuard()) return;
		if(colorFrom.nanGuard()) return;
		if(colorTo.nanGuard()) return;
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		setColors( new float[] { colorFrom.x, colorFrom.y, colorFrom.z, 1, colorTo.x, colorTo.y, colorTo.z, 1 });
		setVertices( new float[] { from.x, from.y, from.z, to.x, to.y, to.z } );
		setIndices( new short[] { 0, 1 } );
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glDrawElements(GL10.GL_LINES, 2, GL10.GL_UNSIGNED_SHORT, indicesBuffer);
	}
	
	/*public void drawSphere(Vector3 p, float radius, Vector3 color)
	{
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glColor4f(color.x, color.y, color.z, 1);
		gl.glPushMatrix();
		gl.glTranslatef(p.x, p.y, p.z);
		int lats = 5;
		int longs = 5;
		int i, j;
		for(i = 0; i <= lats; i++) {
			float lat0 = (float)Math.PI * (-.5f + (float)(i - 1) / lats);
			float z0  = radius*(float)Math.sin(lat0);
			float zr0 =  radius*(float)Math.cos(lat0);

			float lat1 = (float)Math.PI * (-.5f + (float) i / lats);
			float z1 = radius*(float)Math.sin(lat1);
			float zr1 = radius*(float)Math.cos(lat1);
			float[] normals = new float[longs*2*3];
			float[] vertices = new float[longs*2*3];
			for(j = 0; j <= longs; j++) {
				float lng = 2 * (float)Math.PI * (float) (j - 1) / longs;
				float x = (float)Math.cos(lng);
				float y = (float)Math.sin(lng);
				normals[j*2+0] = x*zr0;
				normals[j*2+1] = y*zr0;
				normals[j*2+2] = z0;
				normals[j*2+3] = x*zr1;
				normals[j*2+4] = y*zr1;
				normals[j*2+5] = z1;
				vertices[j*2+0] = x*zr0;
				vertices[j*2+1] = y*zr0;
				vertices[j*2+2] = z0;
				vertices[j*2+3] = x*zr1;
				vertices[j*2+4] = y*zr1;
				vertices[j*2+5] = z1;
			}
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
			gl.glDrawElements(GL10.GL_Q, 2,
	    			GL10.GL_UNSIGNED_SHORT, indicesBuffer);
		}
		gl.glPopMatrix();
	}*/
	
	public static void drawSphere(Vector3 p, float radius, Vector3 color)
	{
		/*Sphere sphere = new Sphere(radius);
		sphere.setTransform(p);
		sphere.setColor(color.x, color.y, color.z, 1);
		sphere.render(gl);*/
	}
	
	public static void drawTriangle(Vector3 a, Vector3 b, Vector3 c, Vector3 color, float alpha)
	{
		/*gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		Vector3 normal = b.subtract(a).cross(c.subtract(a)).normalize();
		gl.glNormal3f(normal.x, normal.y, normal.z);
		gl.glColor4f(color.x, color.y, color.z, 1);
		setVertices( new float[] { a.x, a.y, a.z, b.x, b.y, b.z, c.x, c.y, c.z } );
		setIndices( new short[] { 0, 1, 2 } );
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, 3, GL10.GL_UNSIGNED_SHORT, indicesBuffer);*/
	}
	
	public static void drawContactPoint(Vector3 pointOnB, Vector3 normalOnB, float distance, int lifeTime, Vector3 color)
	{
		/*Vector3 to = pointOnB.add(normalOnB.multiply(distance));
		Vector3 from = pointOnB.clone();*/
		//drawLine(from, to, color, color);
	}
}
