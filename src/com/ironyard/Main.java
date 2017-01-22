package com.ironyard;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static final int SIZE = 10;
    public static boolean firstPath = true;
    public static boolean firstStop = true;

    public static Random r = new Random();

    public static int startingX = r.nextInt(SIZE);
    public static int startingY = r.nextInt(SIZE);


    public static ArrayList<ArrayList<Room>> createRooms(){
        //Room[][] rooms = new Room[SIZE][SIZE];
        ArrayList<ArrayList<Room>> rooms = new ArrayList<>();

        for(int row = 0; row < SIZE; row++){

            ArrayList<Room> roomRow = new ArrayList<>();

            for(int col=0; col<SIZE; col++){
                //rooms[row][col] = new Room(row,col);
                roomRow.add(new Room(row,col));
            }
            rooms.add(roomRow);
        }
        return rooms;
    }//end createRooms()

    //checking borders for possible "neighbors"
    public static ArrayList<Room> possibleNeighbors(ArrayList<ArrayList<Room>> rooms, int row, int col){
        ArrayList<Room> neighbors = new ArrayList<>();

        if(row > 0) neighbors.add(rooms.get(row-1).get(col));
        if(row < SIZE-1) neighbors.add(rooms.get(row+1).get(col));
        if(col > 0) neighbors.add(rooms.get(row).get(col-1));
        if(col < SIZE-1) neighbors.add(rooms.get(row).get(col+1));

        neighbors = neighbors.stream()          //convert neighbors to stream
                .filter(room -> {               //filter OUT rooms that have been visited
                    return !room.wasVisited;
                })
                .collect(Collectors.toCollection(ArrayList<Room>::new)); //new ArrayList will NOT include rooms that have been visited

        return neighbors;
    }//end possibleNeighbors()

    public static Room randomNeighbor(ArrayList<ArrayList<Room>> rooms, int row, int col){
        ArrayList<Room> neighbors = possibleNeighbors(rooms, row, col);

        if(neighbors.size() > 0) {
            Random r = new Random();
            int index = r.nextInt(neighbors.size());
            return neighbors.get(index);
        } else {
            if (firstStop == true){
                //rooms[row][col].setIsEnd(true);
                firstStop = false;
            }
        }
        return null;
    }//end randomNeighbor()

    public static void tearDownWall(Room oldRoom, Room newRoom) {
        // going up
        if (newRoom.row < oldRoom.row) {
            newRoom.hasBottom = false;
        }
        // going down
        else if (newRoom.row > oldRoom.row) {
            oldRoom.hasBottom = false;
        }
        // going left
        else if (newRoom.col < oldRoom.col) {
            newRoom.hasRight = false;
        }
        // going right
        else if (newRoom.col > oldRoom.col) {
            oldRoom.hasRight = false;
        }
    }//end tearDownWall()

    //RECURSIVE METHOD (calling the method in that method)
    public static boolean createMaze(ArrayList<ArrayList<Room>> rooms, Room room) {

        room.wasVisited = true;

        Room nextRoom = randomNeighbor(rooms, room.row, room.col);
        if (nextRoom == null) {
            return false; //end the method
        }

        tearDownWall(room, nextRoom);

        //new maze with wall "torn down" (minus 1 wall)
        while(createMaze(rooms, nextRoom)) {
        }
        if(firstPath == false){
            //rooms[startingX][startingY].setIsStart(true);
            firstPath = true;
        }

        return true;
    }//end createMaze()

    public static void main(String[] args) {

        ArrayList<ArrayList<Room>> rooms = createRooms();

        System.out.println("Starting index positions: " + startingX + ":" + startingY);
        System.out.println("The starting and ending position lack Underscores.");
        //rooms[startingX][startingY].setIsStart(true);

        createMaze(rooms, rooms.get(0).get(0));

        for(ArrayList<Room> row : rooms) {
            System.out.print(" _"); //prints top line
        }

        System.out.println();

        for(ArrayList<Room> row : rooms) {
            System.out.print("|"); //prints left "wall"

            for(Room room : row) {
                if (room.oStart) {
                    System.out.print(room.oStart ? "O" : "");
                    System.out.print(room.hasRight ? "|" : " ");
                } else if (room.xEnd) {
                    System.out.print(room.xEnd ? "X" : "");
                    System.out.print(room.hasRight ? "|" : " ");

                } else {
                    System.out.print(room.hasBottom ? "_" : " ");  //inline conditional - if the room has a bottom print one otherwise print a space
                    System.out.print(room.hasRight ? "|" : " ");

                }
            }
            System.out.println();

        }//end grid

    }//end main()

}//end class Main
