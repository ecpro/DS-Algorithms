package com.sorting;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
		List<String> visited = new ArrayList<>();
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
					if (j == slopeOrdered.length - 1 && checkForCollinearity(collinearPoints, slope)) {
						LineSegment seg = new LineSegment(collinearPoints.get(0),
								collinearPoints.get(collinearPoints.size() - 1));
                        if(!visited.contains(seg.toString())) {
                            this.segments.add(seg);
                            visited.add(seg.toString());
                        }
					}

				} else {
					if (checkForCollinearity(collinearPoints, prevSlope)) {
						LineSegment seg = new LineSegment(collinearPoints.get(0),
								collinearPoints.get(collinearPoints.size() - 1));
						if(!visited.contains(seg.toString())) {
                            this.segments.add(seg);
                            visited.add(seg.toString());
                        }
					}
					prevSlope = slope;
					collinearPoints = new ArrayList<>();
					collinearPoints.add(curr);
					collinearPoints.add(temp);
				}
			}
			if(i == points.length - 1) {
			    System.out.println("faf");
            }
			if (i == points.length - 1) {
				System.out.println("faf");
			}
		}

	}

	private boolean checkForCollinearity(List<Point> collinearPoints, double slope) {
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
