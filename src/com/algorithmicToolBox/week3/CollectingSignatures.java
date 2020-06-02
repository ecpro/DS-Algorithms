package com.algorithmicToolBox.week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectingSignatures {

    static class Segment implements Comparable<Segment> {
        Long aX;
        Long bX;

        Segment(Long ax, Long bx) {
            this.aX = ax;
            this.bX = bx;
        }

        @Override
        public int compareTo(Segment o) {
            return this.aX.compareTo(o.aX);
        }
    }

    public static List<Long> findMinIntersections(List<Segment> segments) {
        Collections.sort(segments);
        List<Long> intersectionPoints = new ArrayList<>();
        Segment prev = null;
        int index = 0;
        for (Segment segment : segments) {
            if (intersectionPoints.isEmpty()) {
                intersectionPoints.add(segment.bX);
                prev = segment;
            } else {
                if (segment.aX.compareTo(prev.bX) <= 0 && segment.bX.compareTo(prev.bX) <= 0) {
                    intersectionPoints.remove(index);
                    intersectionPoints.add(segment.bX);
                    prev = segment;
                } else if (segment.aX.compareTo(prev.bX) > 0) {
                    intersectionPoints.add(segment.bX);
                    index++;
                    prev = segment;
                }
            }
        }
        return intersectionPoints;
    }

    public static void main(String[] args) {
        Segment seg1 = new Segment(4L,7L);
        Segment seg2 = new Segment(1L,3L);
        Segment seg3 = new Segment(2L,5L);
        Segment seg4 = new Segment(5L,6L);
        List<Segment> segments = new ArrayList<>();
        segments.add(seg1);
        segments.add(seg2);
        segments.add(seg3);
        segments.add(seg4);

        List<Long> minIntersections = findMinIntersections(segments);

        System.out.println(minIntersections);

    }

}
