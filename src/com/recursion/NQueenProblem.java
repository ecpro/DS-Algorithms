package com.recursion;

public class NQueenProblem {


    public static void findValidBoardPositions(boolean[][] board, int depth, int col, int len) {

        for(int i = 0; i <= col; i++) {
            board[depth][i] = true;
            boolean flag = isSafe(board, depth, i);
            if(flag) {
               findValidBoardPositions(board, depth+1, col, len);
            }
        }
    }


    public static boolean isSafe(boolean[][] board, int row, int col) {
        for(int i = row - 1; i >= 0; i--) {
            if(board[i][col]) return false;
        }
        int i  = row - 1, j = col - 1;
        while(i >= 0 && j >= 0) {
            if(board[i][j]) return false;
            i--; j--;
        }
        i = row - 1; j = col + 1;
        while(i >= 0 && j < board[row].length) {
            if(board[i][j]) return false;
            i--; j++;
        }
        return true;
    }
    public static void main(String[] args) {
        boolean[][] board = new boolean[4][4];
        board[0][0] = true;
        board[1][3] = true;
        boolean result = isSafe(board, 1,3);
        System.out.println(result);
    }
}
