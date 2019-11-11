package com.backtracking;

import java.util.*;

public class RatMaze {

    static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coordinate)) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static Stack<Coordinate> solveMaze(int[][]maze, int xPos, int yPos) {
        Stack<Coordinate> path = new Stack<>();
        Set<Coordinate> visited = new HashSet<>();
        if(solveMaze(maze, xPos, yPos, path, visited)){
            return path;
        }
        return null;
    }

    public static boolean solveMaze(int[][] maze, int xPos, int yPos, Stack<Coordinate> path, Set<Coordinate> visited) {
        path.push(new Coordinate(xPos, yPos));
        if(xPos == maze.length - 1 && yPos == maze[1].length - 1 && maze[xPos][yPos] == 1) {
            return true;
        }
        if(isValidMove(maze, xPos, yPos,visited)) {
            if(solveMaze(maze, xPos+1, yPos, path, visited)) {
                return true;
            }
            if(solveMaze(maze, xPos, yPos+1, path, visited)) {
                return true;
            }
        }
        if(!path.isEmpty()) {
            path.pop();
        }
        return false;
    }

    private static boolean isValidMove(int[][] maze, int xPos, int yPos, Set<Coordinate> visited) {
        if(!isVisited(xPos, yPos, visited) && xPos < maze.length && yPos < maze[0].length && maze[xPos][yPos] == 1) {
            visited.add(new Coordinate(xPos, yPos));
            return true;
        }
        return false;
    }

    private static boolean isVisited(int xPos, int yPos, Set<Coordinate> visited) {
        if(visited.contains(new Coordinate(xPos, yPos))) {
            System.out.println("already visited " + xPos + ", " + yPos);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {{1,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,1}, {1,1,1,0,1}};
        System.out.println(new Coordinate(1,2).equals(new Coordinate(1,2)));
        Stack<Coordinate> coordinates = solveMaze(maze, 0, 0);
        if(Objects.nonNull(coordinates)) {
            coordinates.forEach(coordinate -> {
                System.out.format("(%s,%s)\n", coordinate.x,coordinate.y);
            });
        }

    }

}
