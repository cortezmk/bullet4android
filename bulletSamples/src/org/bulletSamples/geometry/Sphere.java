package org.bulletSamples.geometry;

import java.util.ArrayList;

public class Sphere extends Mesh {
	private float radius = 0;
	
	public float getRadius() {
		return radius;
	}

	public static float[] vertices;
	public static short[] indices;
	
	public Sphere(float radius)
	{
		int rings = 8, sectors = 8;
		float R = 1.f/(float)(rings-1);
		float S = 1.f/(float)(sectors-1);
		int r, s;
		this.radius = radius;
		int vc = 0, nc = 0;
		vertices = new float[rings * sectors * 3];
		float[] normals = new float[rings * sectors * 3];
		for(r = 0; r < rings; r++) for(s = 0; s < sectors; s++)
		{
			float y = (float)Math.sin(Math.PI/2.0 + Math.PI * r * R);
			float x = (float)Math.cos(2 * Math.PI * s * S) * (float)Math.sin(Math.PI * r * R);
			float z = (float)Math.sin(2 * Math.PI * s * S) * (float)Math.sin(Math.PI * r * R);
			vertices[vc++] = x * radius;
			vertices[vc++] = y * radius;
			vertices[vc++] = z * radius;
			normals[nc++] = x;
			normals[nc++] = y;
			normals[nc++] = z;
		}
		indices = new short[rings * sectors * 6];
		int ic = 0;
		for(r = 0; r < (rings-1); r++) for(s = 0; s < (sectors-1); s++)
		{
			//indices[ic++] = (short)((r+1) * sectors + (s+1));
			indices[ic++] = (short)(r * sectors + s);
			indices[ic++] = (short)((r+1) * sectors + s);
			indices[ic++] = (short)(r * sectors + (s+1));
			
			indices[ic++] = (short)((r+1) * sectors + (s+1));
			indices[ic++] = (short)(r * sectors + (s+1));
			indices[ic++] = (short)((r+1) * sectors + s);
		}
        setIndices(indices);
        setVertices(vertices);
        setNormals(normals);
	}
}
