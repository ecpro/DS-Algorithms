package com.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

	private final Point[] points;
	private List<LineSegment> segments;

	public BruteCollinearPoints(Point[] points) {
		validateAruguments(points, "Point Array");
		this.points = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			validateAruguments(points[i], "Point Object in points Array");
			Point p = new Point(points[i].x, points[i].y);
			this.points[i] = p;
		}
		
		this.segments = new ArrayList<LineSegment>();
		for (int i = 0; i < this.points.length; i++) {
			for (int j = 1; j < this.points.length; j++) {
				for (int k = 2; k < this.points.length; k++) {
					for (int l = 3; l < this.points.length; l++) {
						Point p = points[i], q = points[j], r = points[k], s = points[l];
						if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
							Point [] temp  = {p, q, r, s};
							Arrays.sort(temp);
							LineSegment tempLineSegment = new LineSegment(temp[0], temp[temp.length - 1]);
							this.segments.add(tempLineSegment);
						}
					}
				}
			}
		}
	}

	public int numberOfSegments() {
		return this.segments.size();
	}
	
	private void validateAruguments(Object argument, Object argumentName) {
		if(argument == null) throw new IllegalArgumentException("Invalid argument type for " + argumentName);
		
	}
	
	public LineSegment[] segments() {
		return (LineSegment[]) this.segments.toArray();
	}
}
