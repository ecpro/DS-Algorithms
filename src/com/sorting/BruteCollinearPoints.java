package com.sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final Point[] points;
    private final List<LineSegment> segments;

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
            for (int j = i+1; j < this.points.length; j++) {
                for (int k = j+1; k < this.points.length; k++) {
                    for (int l = k+1; l < this.points.length; l++) {
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
        In in = new In("Resources" + File.separator + "collinear" + File.separator + "input6.txt");
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


        int x = 6;

        for(int i = 0; i < x;  i++) {
            for (int j = i+1; j < x; j++) {
                for (int k = j+1; k < x; k++) {
                    for (int l = k+1; l < x; l++) {
                        System.out.println(String.format("%d %d %d %d", i, j, k, l));
                    }
                }
            }
        }
    }

}
