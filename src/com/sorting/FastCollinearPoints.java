package com.sorting;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by eccspro on 15/02/18.
 */
public class FastCollinearPoints {

	private final List<LineSegment> segments;

	public FastCollinearPoints(Point[] points) {
		validateAruguments(points, "Point Array");
		checkForDuplicates(points);
		this.segments = new ArrayList<>();
		List<Point> visited = new ArrayList<>();
		Point[] slopeOrdered = points.clone();
		for (int i = 0; i < points.length; i++) {
			Point temp = points[i];
			Arrays.sort(slopeOrdered, temp.slopeOrder());
			double slopes[] = new double[slopeOrdered.length];
			for (int k = 0; k < slopes.length; k++) {
				slopes[k] = temp.slopeTo(slopeOrdered[k]);
			}
			double prevSlope = Double.NEGATIVE_INFINITY;
			List<Point> collinearPoints = new ArrayList<>();
			for (int j = 0; j < slopeOrdered.length; j++) {
				Point curr = slopeOrdered[j];
				// if(curr.equals(temp)) continue;
				double slope = temp.slopeTo(curr);
				if (slope == prevSlope) {
					collinearPoints.add(curr);
					if (j == slopeOrdered.length - 1 && checkForCollinearity2(collinearPoints, slope)) {
						if (!isSegmentDuplicate(collinearPoints, temp, slope, visited)) {
							LineSegment seg = new LineSegment(collinearPoints.get(0), collinearPoints.get(collinearPoints.size() - 1));
							this.segments.add(seg);
							visited.add(collinearPoints.get(collinearPoints.size() - 1));
						}
					}

				} else {
					if (checkForCollinearity2(collinearPoints, prevSlope)) {
						if (!isSegmentDuplicate(collinearPoints, temp, prevSlope, visited)) {
							LineSegment seg = new LineSegment(collinearPoints.get(0), collinearPoints.get(collinearPoints.size() - 1));
							this.segments.add(seg);
							visited.add(collinearPoints.get(collinearPoints.size() - 1));
						}
					}
					prevSlope = slope;
					collinearPoints = new ArrayList<>();
					collinearPoints.add(curr);
					collinearPoints.add(temp);
				}
			}
			if (i == points.length - 1) {
				System.out.println("faf");
			}
		}

	}

	private boolean isSegmentDuplicate(List<Point> collinear, Point temp, double slope, List<Point> visited) {
		Point max = collinear.get(collinear.size() - 1);
		for (Point p : visited) {
			int maxCompareP = max.compareTo(p);
			if (maxCompareP == 0) {
				if (max.compareTo(temp) == 0) {
					max = collinear.get(collinear.size() - 2);
				}
				double slopeWithP = temp.slopeTo(max);
				if (slopeWithP == slope) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * private boolean checkForCollinearity(Map<Double, List<Point>>
	 * visitedPoints, List<Point> collinearPoints, double slope) {
	 * Collections.sort(collinearPoints); return collinearPoints.size() >= 4 &&
	 * (!visitedPoints.containsKey(slope) || (visitedPoints.containsKey(slope)
	 * &&
	 * !visitedPoints.get(slope).contains(collinearPoints.get(collinearPoints.
	 * size() - 1)))); }
	 */

	private boolean checkForCollinearity2(List<Point> collinearPoints, double slope) {
		Collections.sort(collinearPoints);
		return collinearPoints.size() >= 4;
	}

	private void checkForDuplicates(Point[] points) {
		List<Point> list = new ArrayList<>();
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null)
				throw new IllegalArgumentException("Invalid array element");
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
		In in = new In("Resources" + File.separator + "collinear" + File.separator + "grid4x4.txt");
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
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
