package com.sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eccspro on 15/02/18.
 */
public class FastCollinearPoints {

    private final Point[] points;
    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        validateAruguments(points, "Point Array");
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            validateAruguments(points[i], "Point Object in points Array");
            Point p = new Point(points[i].x, points[i].y);
            this.points[i] = p;
        }
        Arrays.sort(points);
        this.segments = new ArrayList<>();

        for(int i = 0; i < this.points.length; i++) {
            Point temp = this.points[i];
            Arrays.sort(this.points, temp.slopeOrder());
            double prevSlope = Double.MIN_VALUE;
            int slopeCounter = 0;
            Point start = null, end = null;
            for(int j = 0; j < points.length; j++) {
                if(points[j].compareTo(temp) == 0) continue;
                double slope = temp.slopeTo(points[i]);
                if(slope == prevSlope) {
                    slopeCounter++;
                }
                else {
                    if(slopeCounter >= 3) {
                        end = points[j - 1];
                        LineSegment segment = new LineSegment(start, end);
                        this.segments.add(segment);
                    }
                    start = points[j];
                    prevSlope = slope;
                    slopeCounter = 1;
                }
                if(j == points.length - 1 && temp.compareTo(points[j]) == prevSlope && slopeCounter >=3) {
                    end = points[j];
                    LineSegment segment = new LineSegment(start, end);
                    this.segments.add(segment);
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
        LineSegment [] segments = new LineSegment[this.segments.size()];
        for(int i = 0; i < this.segments.size(); i++) {
            int px = this.segments.get(i).p.x;
            int py = this.segments.get(i).p.y;
            Point pp = new Point(px, py);

            int qx = this.segments.get(i).q.x;
            int qy = this.segments.get(i).q.y;
            Point qq = new Point(qx, qy);

            LineSegment segment = new LineSegment(pp, qq);
            segments[i] = segment;
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
