package com.hussainaaliya.stringmaze;
import java.util.*;

public class Maze {
    private int rows;
    private int cols;
    private Cell[][] grid;
    private String[][] maze_mat;
    private int rcur;
    private int ccur;
    private int rtar;
    private int ctar;

    public Maze(int nrows, int ncols){
        rows = nrows;
        cols = ncols;
        grid = new Cell[rows][cols];
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                grid[r][c] = new Cell(r, c);
    }

    public ArrayList<Cell> getNeighbors(Cell c){
        ArrayList<Cell> neighbors = new ArrayList<>();
        int crow = c.getRow();
        int ccol = c.getCol();
        if(crow > 0)
            neighbors.add(grid[crow-1][ccol]);
        if(crow < rows-1)
            neighbors.add(grid[crow+1][ccol]);
        if(ccol > 0)
            neighbors.add(grid[crow][ccol-1]);
        if(ccol < cols-1)
            neighbors.add(grid[crow][ccol+1]);
        return neighbors;
    }

    public void generate(){
        int rrow = (int)(Math.random()*rows);
        int rcol = (int)(Math.random()*cols);
        Stack<Cell> stack = new Stack<>();
        Cell cur = grid[rrow][rcol];
        int visited = 1;

        while(visited < rows*cols){
            ArrayList<Cell> neighbors = getNeighbors(cur);
            ListIterator<Cell> iter = neighbors.listIterator();
            while(iter.hasNext()){
                if(!iter.next().unVisited()){
                    iter.remove();
                }
            }
            if(neighbors.size() > 0){
                int ran = (int)(Math.random()*neighbors.size());
                Cell rn = neighbors.get(ran);
                cur.breakWall(rn);
                stack.add(cur);
                cur = rn;
                visited++;
            }
            else
                cur = stack.pop();
        }
    }

    public void setMazeMatrix(){
        String[][] rep = new String[rows*2+1][cols*2+1];
        for(int r = 0; r < rep.length; r++)
            for(int c = 0; c < rep[0].length; c++)
                rep[r][c] = "#";
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++){
                Cell cur = grid[r][c];
                int rrow = cur.getRow()*2+1;
                int rcol = cur.getCol()*2+1;
                rep[rrow][rcol] = " ";
                HashSet<String> cwalls = cur.getWalls();
                if(!cwalls.contains("T") && (rrow > 1))
                    rep[rrow-1][rcol] = " ";
                if(!cwalls.contains("B") && (rrow < rep.length-2))
                    rep[rrow+1][rcol] = " ";
                if(!cwalls.contains("R") && (rcol < rep[0].length-2))
                    rep[rrow][rcol+1] = " ";
                if(!cwalls.contains("L") && (rcol > 1))
                    rep[rrow][rcol-1] = " ";
            }
        maze_mat = rep;
    }

    public String[][] getMazeMatrix(){
        return maze_mat;
    }

    public void startMaze(){
        int sr = 0;
        int sc = 0;
        while (maze_mat[sr][sc].equals("#")){
            sr = (int)(Math.random()*rows+1);
            sc = (int)(Math.random()*cols+1);
        }
        maze_mat[sr][sc] = "%";
        rcur = sr;
        ccur = sc;
        while (maze_mat[sr][sc].equals("%") || maze_mat[sr][sc].equals("#")){
            sr = (int)(Math.random()*rows+1);
            sc = (int)(Math.random()*cols+1);
        }
        rtar = sr;
        ctar = sc;
        maze_mat[sr][sc] = "$";
    }

    public int getCurRow(){
        return rcur;
    }

    public int getCurCol(){
        return ccur;
    }

    public void moveUp(){
        if(!maze_mat[rcur-1][ccur].equals("#")){
            maze_mat[rcur-1][ccur] = "%";
            maze_mat[rcur][ccur] = " ";
            rcur--;
        }
    }

    public void moveDown(){
        if(!maze_mat[rcur+1][ccur].equals("#")){
            maze_mat[rcur+1][ccur] = "%";
            maze_mat[rcur][ccur] = " ";
            rcur++;
        }
    }

    public void moveLeft(){
        if(!maze_mat[rcur][ccur-1].equals("#")){
            maze_mat[rcur][ccur-1] = "%";
            maze_mat[rcur][ccur] = " ";
            ccur--;
        }
    }

    public void moveRight(){
        if(!maze_mat[rcur][ccur+1].equals("#")){
            maze_mat[rcur][ccur+1] = "%";
            maze_mat[rcur][ccur] = " ";
            ccur++;
        }
    }

    public boolean isOver(){
        return (rcur == rtar && ccur == ctar);
    }

    public String getMazeString(){
        StringBuilder ret = new StringBuilder();
        for(int r = 0; r < maze_mat.length; r++)
            for(int c = 0; c < maze_mat[0].length; c++){
                ret.append(maze_mat[r][c]);
                if (c == maze_mat[0].length-1)
                    ret.append("\n");
            }
        return ret.toString();
    }

    private class Cell{
        private int row;
        private int col;
        private HashSet<String> walls;

        public Cell(int nrow, int ncol){
            row = nrow;
            col = ncol;
            walls = new HashSet<>();
            walls.add("T");
            walls.add("B");
            walls.add("R");
            walls.add("L");
        }

        public int getRow() {
            return row;
        }

        public void setRow(int r) {
            row = r;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int c) {
            col = c;
        }

        public HashSet<String> getWalls(){
            return walls;
        }

        public void removeWall(String dir){
            walls.remove(dir);
        }

        public void breakWall(Cell neighbor){
            int nrow = neighbor.getRow();
            int ncol = neighbor.getCol();
            int drow = row-nrow;
            int dcol = col-ncol;
            if (Math.abs(drow) <= 1 && Math.abs(dcol) <= 1){
                if (dcol == 1){
                    removeWall("L");
                    neighbor.removeWall("R");
                }
                if (dcol == -1){
                    removeWall("R");
                    neighbor.removeWall("L");
                }
                if (drow == 1){
                    removeWall("B");
                    neighbor.removeWall("T");
                }
                if (drow == -1){
                    removeWall("T");
                    neighbor.removeWall("B");
                }
            }
        }

        public boolean unVisited(){
            return walls.size() == 4;
        }
    }

}
