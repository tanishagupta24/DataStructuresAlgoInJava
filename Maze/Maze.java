package com.maze;
import java.util.*;
 public class Maze {
     private UnionFind rooms;
     private int[] roomList;
     private ArrayList<String> walls = new ArrayList<>();

     public Maze(int n) {
         rooms = new UnionFind(n*n);
         roomList = new int[n*n];
         for(int i = 0; i < roomList.length; i++) {
             roomList[i] = i;
         }

         for(int i = 0; i < roomList.length; i++) {
             if(!(roomList[i] % n == (n-1))) {
                 int plusOne = i+1;
                 walls.add(i + "to" + plusOne);
             }
             if(roomList[i] < n*n-n) {
                 int plusN = i+n;
                 walls.add(i + "to" + plusN);
             }
         }
     }

     public void createMaze() {
         while(walls.size() > 0) {
             int i = (int)(Math.random() * (walls.size() - 1));
             int room1 = Integer.parseInt(walls.get(i).substring(0, walls.get(i).indexOf("t")));
             int room2 = Integer.parseInt(walls.get(i).substring(walls.get(i).indexOf("o")+1));

             
             if(!(rooms.locate(room1) == rooms.locate(room2))) {
                 rooms.union(room1, room2);
                     System.out.println("Breaking wall: " + walls.get(i));
                     walls.remove(i);


             } else {
                 walls.remove(i);
             }
         }
     }


     public void printBlankMaze() {
         for(int i = 0; i < roomList.length; i++) {
             System.out.println(roomList[i]);
         }

         for(int i = 0; i < walls.size(); i++) {
             System.out.println(walls.get(i));
         }
     }

     public static void main(String args[]) {
         Maze maze = new Maze(15);
         maze.createMaze();
     }


 }