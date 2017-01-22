package com.ironyard;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static final int SIZE = 10;

    public static ArrayList<ArrayList<Room>> createRooms(){
        ArrayList<ArrayList<Room>> rooms = new ArrayList<>();

        for(int row = 0; row < SIZE; row++){
            ArrayList<Room> roomRow = new ArrayList<>();
            for(int col=0; col<SIZE; col++){
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

        while(createMaze(rooms, nextRoom)); //new maze with wall "torn down" (minus 1 wall)

        return true;
    }//end createMaze()

    public static void main(String[] args) {
        ArrayList<ArrayList<Room>> rooms = createRooms();
        createMaze(rooms, rooms.get(0).get(0));
        for(ArrayList<Room> row : rooms) {
            System.out.print(" _"); //prints top line
        }

        System.out.println();
        for(ArrayList<Room> row : rooms) {
            System.out.print("|"); //prints left "wall"

            for(Room room : row) {
                if(room.hasBottom){
                    System.out.print("_"); //draws bottom

                    } else {
                    System.out.print(" "); //no bottom, a space
                }

                if (room.hasRight){
                    System.out.print("|"); //have a right wall?  draws wall
                } else {
                    System.out.print(" "); // no wall, prints a space
                }
            }
            System.out.println();

        }//end grid

    }//end main()

}//end class Main
