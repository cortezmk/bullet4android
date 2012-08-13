package org.bulletSamples;

public class Cube extends Mesh {
	
    public Cube(float width, float height, float depth) {
        width  /= 2;
        height /= 2;
        depth  /= 2;
 
        float vertices[] = { -width, -height, -depth, // 0
        		width, -height, -depth, // 1
        		width, height, -depth, // 2
        		-width, height, -depth, // 3
        		-width, -height, depth, // 4
        		width, -height, depth, // 5
        		width, height, depth, // 6
        		-width, height, depth, // 7
        		};
 
        short indices[] = {
        		0, 4, 5, //bottom 0, -1, 0
        		0, 5, 1,
        		1, 5, 6, //right 1, 0, 0
        		1, 6, 2,
        		2, 6, 7, //top 0, 1, 0
        		2, 7, 3,
        		3, 7, 4, //left -1, 0, 0
        		3, 4, 0,
        		4, 7, 6, //front 0, 0, 1
        		4, 6, 5,
        		3, 0, 1, //back 0, 0, -1
        		3, 1, 2,
        		};
        
        /*float[] normals = {
        	0, -1, 0,
        	0, -1, 0,
        	1, 0, 0,
        	1, 0, 0,
        	0, 1, 0,
        	0, 1, 0,
        	-1, 0, 0,
        	-1, 0, 0,
        	0, 0, -1,
        	0, 0, -1,
        	0, 0, 1,
        	0, 0, 1,
        };*/
        
        float l = (float) Math.sqrt(Math.sqrt(2) + 1);
        
        float[] normals = { -l, -l, -l, // 0
        		l, -l, -l, // 1
        		l, l, -l, // 2
        		-l, l, -l, // 3
        		-l, -l, l, // 4
        		l, -l, l, // 5
        		l, l, l, // 6
        		-l, l, l, // 7
        		};
 
        setIndices(indices);
        setVertices(vertices);
        //calculateNormals();
        setNormals(normals);
    }
}
