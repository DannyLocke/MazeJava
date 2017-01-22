package com.ironyard;

/**
 * Created by dlocke on 1/18/17.
 */
public class Room {

    int row;
    int col;
    boolean wasVisited = false;
    boolean hasBottom = true;
    boolean hasRight = true;

    //starting and ending positions
    String start;
    String end;
    boolean oStart = false;
    boolean xEnd = false;

    public Room (int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isWasVisited() {
        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    public boolean isHasBottom() {
        return hasBottom;
    }

    public void setHasBottom(boolean hasBottom) {
        this.hasBottom = hasBottom;
    }

    public boolean isHasRight() {
        return hasRight;
    }

    public void setHasRight(boolean hasRight) {
        this.hasRight = hasRight;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isoStart() {
        return oStart;
    }

    public void setoStart(boolean oStart) {
        this.oStart = oStart;
    }

    public boolean isxEnd() {
        return xEnd;
    }

    public void setxEnd(boolean xEnd) {
        this.xEnd = xEnd;
    }
}
