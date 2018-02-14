package com.sorting;

import java.util.Comparator;

public final class Point implements Comparable<Point>{
	
	public final int x, y;
	public Point(int x , int y) {
		this.x = x;
		this.y = y;
	}
	
	public double slopeTo(Point that) {
		if(this.x == that.x) {
			if(this.y == that.y) return 0;
			return Double.MAX_VALUE;
		}
		return this.y - that.y / (double)(this.x - that.x);
	}
	
	@Override
	public int compareTo(Point that) {
		if(this.y < that.y) return -1;
		if(this.y > that.y) return 1;
		if(this.x < that.x) return -1;
		if(this.x > that.y) return 1;
		return 0;	
	}
	
	public Comparator<Point> slopeOrder() {
		Point p = this;
		return new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				double slopeToP1 = p.slopeTo(p1);
				double slopeToP2 = p.slopeTo(p2);
				
			}
		};
	}

}
