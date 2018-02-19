package com.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;

public final class Point implements Comparable<Point>{
	
	private final int x, y;
	public Point(int x , int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Finds slope of line between this and the other point
	 * @param that the other point
	 * @return
	 */
	public double slopeTo(Point that) {
		if(this.x == that.x) {
			if(this.y == that.y) return Double.NEGATIVE_INFINITY;
			return Double.POSITIVE_INFINITY;
		}
		Double slope = (this.y - that.y) / (double)(this.x - that.x);
		if(slope == -0.0) slope = 0.0;
		return slope;
	}
	
	/**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }
    
    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
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
            if(slopeToP1 > slopeToP2) return 1;
            if(slopeToP1 < slopeToP2) return -1;
            return 0;
        };
	}

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
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
