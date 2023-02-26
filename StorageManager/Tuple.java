package com.storage;

import java.sql.SQLOutput;

//Tuple base class
public class Tuple {
    private int arrIndex;
    private int storageBlocks;

    public Tuple(int arrIndex, int storageBlocks) {
        this.arrIndex = arrIndex;
        this.storageBlocks = storageBlocks;
    }

    public void setArrIndex(int arrIndex) {
        this.arrIndex = arrIndex;
    }

    public void setStorageBlocks(int storageBlocks) {
        this.storageBlocks = storageBlocks;
    }

    public int getArrIndex() {
        return arrIndex;
    }

    public int getStorageBlocks() {
        return storageBlocks;
    }

    public String print() {
        return "(" + arrIndex + ", " + storageBlocks + ")";
    }
}
