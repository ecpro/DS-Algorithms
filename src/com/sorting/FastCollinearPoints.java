package com.sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.*;


/**
 * Created by eccspro on 15/02/18.
 */
public class FastCollinearPoints {
    
    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        validateAruguments(points, "Point Array");
        this.segments = new ArrayList<>();
        Point [] slopeOrdered = points.clone();
        Map<Double, Set<Point>> visitedPoints = new HashMap<>();
        for(int i = 0; i < points.length; i++) {
            Point temp = points[i];
            Arrays.sort(slopeOrdered, temp.slopeOrder());
            double slopes [] = new double[slopeOrdered.length];
            for(int k = 0; k < slopes.length; k++) {
                slopes[k] = temp.slopeTo(slopeOrdered[k]);
            }
            double prevSlope = Double.MIN_VALUE;
            List<Point> collinearPoints = new ArrayList<>();
            collinearPoints.add(temp);
            for(int j = 0; j < slopeOrdered.length; j++) {
                Point curr = slopeOrdered[j];
                if(curr.equals(temp)) continue;
                double slope = temp.slopeTo(curr);
                if(slope == prevSlope) {
                    collinearPoints.add(curr);
                    if(j == slopeOrdered.length - 1 && checkForCollinearity(visitedPoints, collinearPoints, slope)) {
                        LineSegment seg = new LineSegment(collinearPoints.get(0), collinearPoints.get(collinearPoints.size() - 1));
                        if(!visitedPoints.containsKey(slope)) {
                            Set<Point> visited = new HashSet<>();
                            visited.add(collinearPoints.get(collinearPoints.size() - 1));
                            visitedPoints.put(slope, visited);
                        }
                        else {
                           Set<Point> visited =  visitedPoints.get(slope);
                           visited.add(collinearPoints.get(collinearPoints.size() - 1));
                        }
                        this.segments.add(seg);
                    }
                }
                else {
                    if(checkForCollinearity(visitedPoints, collinearPoints, prevSlope)) {
                        LineSegment seg = new LineSegment(collinearPoints.get(0), collinearPoints.get(collinearPoints.size() - 1));
                        if(!visitedPoints.containsKey(prevSlope)) {
                            Set<Point> visited = new HashSet<>();
                            visited.add(collinearPoints.get(collinearPoints.size() - 1));
                            visitedPoints.put(prevSlope, visited);
                        }
                        else {
                            Set<Point> visited =  visitedPoints.get(prevSlope);
                            visited.add(collinearPoints.get(collinearPoints.size() - 1));
                        }
                        this.segments.add(seg);
                    }
                    prevSlope = slope;
                    collinearPoints = new ArrayList<>();
                    collinearPoints.add(curr);
                    collinearPoints.add(temp);
                }
            }
        }
    }

    private boolean checkForCollinearity(Map<Double, Set<Point>> visitedPoints, List<Point> collinearPoints, double slope) {
        Collections.sort(collinearPoints);
        return collinearPoints.size() >= 4 && (!visitedPoints.containsKey(slope) || (visitedPoints.containsKey(slope) && !visitedPoints.get(slope).contains(collinearPoints.get(collinearPoints.size() - 1))));
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
