package com.sorting.Algorithms1Week3Assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

	private final List<LineSegment> segments;

	public BruteCollinearPoints(Point[] points) {

		validateAruguments(points, "Point Array");
		checkForDuplicates(points);

		this.segments = new ArrayList<LineSegment>();

		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < points.length; k++) {
					for (int l = k + 1; l < points.length; l++) {
						Point p = points[i], q = points[j], r = points[k], s = points[l];
						if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
							Point[] temp = { p, q, r, s };
							Arrays.sort(temp);
							LineSegment tempLineSegment = new LineSegment(temp[0], temp[temp.length - 1]);
							this.segments.add(tempLineSegment);
						}
					}
				}
			}
		}
	}

	private void checkForDuplicates(Point[] points) {
		List<Point> list = new ArrayList<>();
		for (int i = 0; i < points.length; i++) {
			if(points[i] == null) throw new IllegalArgumentException("Invalid argument");
			if (!list.contains(points[i])) {
				list.add(points[i]);
			} else {
				throw new IllegalArgumentException("Duplicate points not allowed");
			}
		}
	}

	public int numberOfSegments() {
		return this.segments.size();
	}

	private void validateAruguments(Object argument, Object argumentName) {
		if (argument == null)
			throw new IllegalArgumentException("Invalid argument type for " + argumentName);

	}

	public LineSegment[] segments() {
		LineSegment[] segments = new LineSegment[this.segments.size()];
		for (int i = 0; i < this.segments.size(); i++) {
			segments[i] = this.segments.get(i);
		}
		return segments;
	}

	public static void main(String[] args) {

		// read the n points from a file
		In in = new In("Resources" + File.separator + "collinear" + File.separator + "input200.txt");
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();

	}

}
