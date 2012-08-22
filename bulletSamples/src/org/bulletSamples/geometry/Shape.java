package org.bulletSamples.geometry;

public enum Shape
{
	Box(0),
	Sphere(1);
	private final int index;   
    Shape(int index) { this.index = index;}
    public int index() { return index; }
}
