package com.maze;

public class UnionFind {

    private int[] baby;
    private int[] root;

    public UnionFind(int n) {
        baby = new int[n];
        root = new int[n];
        for (int i = 0; i < n; i++) {
            baby[i] = 0;
            root[i] = i;
        }
    }

    public int locate(int room) {
        while (room != root[room]) {
            root[room] = root[root[room]];
            room = root[room];
        }
        return room;
    }

    public void union(int room1, int room2) {
        int rootRoom1 = locate(room1);
        int rootRoom2 = locate(room2);
        if (rootRoom1 == rootRoom2) {
            return;
        }

        if (baby[rootRoom1] > baby[rootRoom2]){
            root[rootRoom2] = rootRoom1;
        }
        else if(baby[rootRoom1] < baby[rootRoom2]){
            root[rootRoom1] = rootRoom2;
        }
        else {
            root[rootRoom2] = rootRoom1;
            baby[rootRoom1]++;
        }
    }
}