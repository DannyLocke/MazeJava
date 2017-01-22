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

    public Room (int row, int col){
        this.row = row;
        this.col = col;
    }
}
