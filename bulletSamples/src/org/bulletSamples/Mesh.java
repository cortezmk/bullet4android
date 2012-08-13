package org.bulletSamples;

import java.nio.*;
import javax.microedition.khronos.opengles.*;

import org.bulletSamples.physics.CollisionShape;

public class Mesh {
	
	// Translate params.
    public float x = 0;
    public float y = 0;
    public float z = 0;
 
    // Rotate params.
    public float rx = 0;
    public float ry = 0;
    public float rz = 0;
    public float ra = 0;
    
    // Our vertex buffer.
    private FloatBuffer verticesBuffer = null;
 
    // Our index buffer.
    private ShortBuffer indicesBuffer = null;
    
    private FloatBuffer normalBuffer = null;
 
    // The number of indices.
    private int numOfIndices = -1;
 
    // Flat Color
    private float[] rgba = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
 
    // Smooth Colors
    private FloatBuffer colorBuffer = null;
    
    private Vector3 getVectorAt(int index)
    {
    	index *= 3;
    	return new Vector3(verticesBuffer.get(index), verticesBuffer.get(index + 1), verticesBuffer.get(index + 2));
    }
    
    protected void setNormals(float[] normals) {
		// a float is 4 bytes, therefore we multiply the number if
		// vertices with 4.
		ByteBuffer vbb = ByteBuffer.allocateDirect(normals.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		normalBuffer = vbb.asFloatBuffer();
		normalBuffer.put(normals);
		normalBuffer.position(0);
    }
    
    public void calculateNormals()
    {
    	if((verticesBuffer == null) || (indicesBuffer == null)) return;
    	ByteBuffer idd = ByteBuffer.allocateDirect(indicesBuffer.capacity() * 4);
    	idd.order(ByteOrder.nativeOrder());
    	normalBuffer = idd.asFloatBuffer();
    	float[] buff = new float[indicesBuffer.capacity()];
    	for(int i = 0; i <= indicesBuffer.capacity() - 3; i += 3)
    	{
    		Vector3 v1 = getVectorAt(indicesBuffer.get(i));
    		Vector3 v2 = getVectorAt(indicesBuffer.get(i + 1));
    		Vector3 v3 = getVectorAt(indicesBuffer.get(i + 2));
    		Vector3 w1 = new Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    		Vector3 w2 = new Vector3(v1.x - v3.x, v1.y - v3.y, v1.z - v3.z);
    		buff[i] = -((w1.y * w2.z) - (w1.z * w2.y));
    		buff[i + 1] = -((w1.z * w2.x) - (w1.x * w2.z));
    		buff[i + 2] = -((w1.x * w2.y) - (w1.y * w2.x));
    	}
    	normalBuffer.put(buff);
    	normalBuffer.position(0);
    }
    
    public void draw(GL10 gl) {
    	gl.glLoadIdentity();
        gl.glTranslatef(x, y, z);
        //gl.glRotatef(rx, 1, 0, 0);
        //gl.glRotatef(ry, 0, 1, 0);
        //gl.glRotatef(rz, 0, 0, 1);
        gl.glRotatef(ra, rx, ry, rz);
	        // Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CW);
		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE);
		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK);
		// Enabled the vertices buffer for writing and to be used during
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		if(normalBuffer != null)
		{
			gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
		}
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        // Set flat color
        gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
        // Smooth color
        if ( colorBuffer != null ) {
            // Enable the color array buffer to be used during rendering.
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            // Point out the where the color buffer is.
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        }
		gl.glDrawElements(GL10.GL_TRIANGLES, numOfIndices,
			GL10.GL_UNSIGNED_SHORT, indicesBuffer);
		// Disable the vertices buffer.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		if(normalBuffer != null) gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		// Disable face culling.
		gl.glDisable(GL10.GL_CULL_FACE);
    }
    
    protected void setVertices(float[] vertices) {
		// a float is 4 bytes, therefore we multiply the number if
		// vertices with 4.
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		verticesBuffer = vbb.asFloatBuffer();
		verticesBuffer.put(vertices);
		verticesBuffer.position(0);
    }
 
    protected void setIndices(short[] indices) {
		// short is 2 bytes, therefore we multiply the number if
		// vertices with 2.
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indicesBuffer = ibb.asShortBuffer();
		indicesBuffer.put(indices);
		indicesBuffer.position(0);
		numOfIndices = indices.length;
    }
 
    protected void setColor(float red, float green, float blue, float alpha) {
        // Setting the flat color.
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alpha;
    }
 
    protected void setColors(float[] colors) {
		// float has 4 bytes.
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
    }
    
    public void setTransform(Vector3 vector3)
    {
    	x = vector3.x;
    	y = vector3.y;
    	z = vector3.z;
    }
    
    public void setTransform(Vector3 vector3, Quaternion quat)
    {
    	x = vector3.x;
    	y = vector3.y;
    	z = vector3.z;
    	rx = quat.axis.x;
    	ry = quat.axis.y;
    	rz = quat.axis.z;
    	ra = quat.angle * (180.0f / 3.14f);
    }
    
    public void applyTransform(CollisionShape body)
    {
    	Vector3 vec = new Vector3();
    	Quaternion quat = new Quaternion();
    	body.getTransform(vec, quat);
    	setTransform(vec, quat);
    }
}
