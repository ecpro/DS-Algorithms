package com.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
		return (p1, p2) -> {
            double slopeToP1 = p.slopeTo(p1);
            double slopeToP2 = p.slopeTo(p2);
            return (int)(slopeToP1 - slopeToP2);
        };
	}

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static void main(String [] args) {
        Point p1 = new Point(1,2);
        Point p2 = new Point(3,1);
        Point p3 = new Point(4,4);
        Point p4 = new Point(-2,-5);
        Point p5 = new Point(1,5);
        Point p6 = new Point(1,-5);
        List<Point> points = new ArrayList<>();
        //points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);

        Collections.sort(points, p1.slopeOrder());

        System.out.println(points);
    }
}
