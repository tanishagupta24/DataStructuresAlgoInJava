package com.storage;

import java.util.LinkedList;
import java.util.ListIterator;

public class StorageManager{
    //instance variables
    public static int STORAGE_SIZE = 200;
    private int[] storageArray;
    private LinkedList<Tuple> allocationList = new LinkedList<>();
    private LinkedList<Tuple> freeList = new LinkedList<>();

    //constructor initialize the lists
    public StorageManager(int size) {
        storageArray = new int[size];
        freeList.add(new Tuple(0, size));
    }

    //allocate function
    private void requestStorage(int n) {
        int difference;
        int index = -1;
        int currLowestDifference = Integer.MAX_VALUE;
        int removeIndex = -1;
        boolean flag = false;
        ListIterator<Tuple> freeListIterator = freeList.listIterator();
        //loop through free list to find space
        while (freeListIterator.hasNext()) {
            Tuple next = freeListIterator.next();
            int numBlocks = next.getStorageBlocks();
            //check if there is enough space in the block
            if (numBlocks >= n) {
                difference = numBlocks - n;
                flag = true;
                //check if this block is smaller than the last smallest block
                if (difference < currLowestDifference) {
                    index = next.getArrIndex();
                    currLowestDifference = difference;
                    removeIndex = freeListIterator.nextIndex()-1;
                }
            }
        }
        //if there's no space for the allocation, say so
        if(!flag) {
            System.out.println("No space for the Allocation, Sorry!");
        //otherwise, add to the allocation list, fix the free list, and fix the array
        } else {
            allocationList.add(new Tuple(index, n));
            freeList.remove(removeIndex);
            int fixIndex = fixArrayAfterAllocate(index, n);
            if(currLowestDifference != 0) {
                freeList.add(new Tuple(fixIndex+1, currLowestDifference));
            }
        }
    }

    //fixes array after an allocation
    private int fixArrayAfterAllocate(int index, int n) {
        int fixIndex = 0;
        for(int i = index; i < index+n; i++) {
            storageArray[i] = 1;
            fixIndex = i;
        }
        return fixIndex;
    }

    //deallocates the storage
    private void cancelStorage(int l, int n) {
        //delete from the allocation list
        Tuple newTuple = new Tuple(l, n);
        boolean found = false;

        ListIterator<Tuple> allocationListIterator = allocationList.listIterator();
        LinkedList<Tuple> tempAllocationList = new LinkedList<>();

        while (allocationListIterator.hasNext()) {
            Tuple currentTuple = allocationListIterator.next();
            if (currentTuple.getArrIndex() == newTuple.getArrIndex() && currentTuple.getStorageBlocks() == newTuple.getStorageBlocks())
                found = true;
            else
                tempAllocationList.add(currentTuple);
        }
        //if the storage was found, proceed
        if (found) {
            allocationList = tempAllocationList;
            freeList.add(newTuple);
            for (int i = l; i < l + n; i++)
                storageArray[i] = 0;

            //find the right pointers
            int leftPtr = l - 1;
            int rightPtr = l + n;
            while (leftPtr > -1 && storageArray[leftPtr] != 1)
                leftPtr--;
            while (rightPtr < storageArray.length && storageArray[rightPtr] != 1)
                rightPtr++;

            ListIterator<Tuple> freeListIterator = freeList.listIterator();
            LinkedList<Tuple> tempFreeList = new LinkedList<>();

            //add all of the tuples outside of the range
            while (freeListIterator.hasNext()) {
                Tuple currentTuple = freeListIterator.next();
                if (currentTuple.getArrIndex() < leftPtr || currentTuple.getArrIndex() > rightPtr)
                    tempFreeList.add(currentTuple);
            }
            //conglomerate all of the tuples inside of the range and add, this effectively deletes any possible repeats
            tempFreeList.add(new Tuple(leftPtr+1, rightPtr-leftPtr-1));
            //reassign!
            freeList = tempFreeList;
        //if there's nothing in that location, say so
        } else {
            System.out.println("Nothing to Deallocate");
        }
    }

    //return allocation list
    public LinkedList<Tuple> getAllocationList() {
        return allocationList;
    }

    //print a list
    public void printList(LinkedList<Tuple> list) {
        ListIterator<Tuple> listIterator = list.listIterator(0);
        while(listIterator.hasNext()) {
            System.out.print(listIterator.next().print() + "-->");
        }
    }

    //print the allocation list
    public void printAList() {
        printList(allocationList);
    }

    //print the free list
    public void printFList() {
        printList(freeList);
    }

    //print out the array
    public void printArray() {
        System.out.print("[");
        for(int i = 0; i < storageArray.length; i++) {
            System.out.print(storageArray[i] + ", ");
        }
        System.out.print("]");
        System.out.println();
    }

    //initialize the storage hub, randomized
    public void initializeLists() {
        freeList.remove();
        int count = 0;
        //continue to alternate between allocation and free until the storage has all been account for
        while(count < STORAGE_SIZE) {
            if((STORAGE_SIZE-count) <= 10) {
                allocationList.add(new Tuple(count, STORAGE_SIZE-count));
                count = count+(STORAGE_SIZE-count);
            } else {
                int rand1 = (int)(Math.random()*(9) + 1);
                allocationList.add(new Tuple(count, rand1));
                count = count+rand1;
            }
            if(count >= STORAGE_SIZE) {
                break;
            }
            if((STORAGE_SIZE-count) <= 10) {
                freeList.add(new Tuple(count, STORAGE_SIZE-count));
                count = count+(STORAGE_SIZE-count);
            } else {
                int rand2 = (int)((Math.random()*9) + 1);
                freeList.add(new Tuple(count, rand2));
                count = count+rand2;
            }

        }
        initializeArray();
    }

    //initialize the arrays in order to initialize the storage hub
    private void initializeArray() {
        //loop through each tuple in list
        for(Tuple t : allocationList) {
            for(int i = t.getArrIndex(); i < t.getArrIndex()+t.getStorageBlocks(); i++) {
                //make sure we don't accidentally go out of bounds
                if(i < STORAGE_SIZE) {
                    //reassign allotted spaces to 1 rather than 0
                    storageArray[i] = 1;
                } else {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        StorageManager manager = new StorageManager(STORAGE_SIZE);

        System.out.print("Storage Array Before Initialize: ");
        manager.printArray();

        //Call Initialize Storage
        manager.initializeLists();

        System.out.print("Storage Array After Initialize: ");
        manager.printArray();
        System.out.print("Allocation List After Initialize: ");
        manager.printAList();
        System.out.println();
        System.out.print("Free List After Initialize: ");
        manager.printFList();
        System.out.println();


        //Pick a random number for storage block to be allocated
        int numblocks = (int)(Math.random()*(10)+1);
        System.out.println("Allocating Storage Size: " + numblocks);

        //Call Request Storage for numBlocks
        manager.requestStorage(numblocks);

        System.out.print("Storage Array After Allocation of " + numblocks + ": ");
        manager.printArray();
        System.out.print("Allocation List After Allocation of " + numblocks + ": ");
        manager.printAList();
        System.out.println();
        System.out.print("Free List After Allocation of " + numblocks + ": ");
        manager.printFList();
        System.out.println();


        //randomly select tuple to cancel storage for
        int size = manager.getAllocationList().size();
        int index = (int)(Math.random()*(size-1));
        Tuple tuple = manager.getAllocationList().get(index);
        System.out.println("Deallocating: " + tuple.print());

        //Call Cancel Storage for the randomly selected Tuple
        manager.cancelStorage(tuple.getArrIndex(), tuple.getStorageBlocks());

        System.out.print("Storage Array After Deallocation of " + tuple.print() + ": ");
        manager.printArray();
        System.out.print("Allocation List After Deallocation of " + tuple.print() + ": ");
        manager.printAList();
        System.out.println();
        System.out.print("Free List After Deallocation of " + tuple.print() + ": ");
        manager.printFList();
        System.out.println();
    }
}
