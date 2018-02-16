package com.sorting;

public final class LineSegment {
	
	public final Point p, q;
	
	public LineSegment(Point p1, Point p2) {
		this.p = p1;
		this.q = p2;
	}
	
	public   void draw() {
		p.drawTo(q);
	}
	
	@Override
	
	public String toString() {
		return p + " -> " + q;
	}
}
