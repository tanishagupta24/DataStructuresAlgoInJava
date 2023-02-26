package com.spellcheck;

import java.io.*;
import java.util.*;

public class HashTableDictionary {
    String[] correctWords;
    int count = 0;
    //constructor - read each line, make the appropriate sized array, put the value in place
    public HashTableDictionary(String dictFName) {
        try {
            Scanner reader = new Scanner(new File(dictFName));
            int numWords = reader.nextInt();
            count = numWords;
            correctWords = new String[(int)(numWords*1.2)];
            while (numWords > 0) {
                String word  = reader.next().trim().toLowerCase();
                int hashValue = hash(word);
                put(word);
                numWords--;
            }

            reader.close();
        }
        catch (IOException e) {
            System.out.println("error in reading english words from file");
        }
        System.out.println(dictFName +" loaded containing " + count + " words");
    }

//    public int hashCode(String word) {
//        int hash = 0;
//        for (int i = 0; i < word.length(); i++)
//            hash = (10 * hash + word.charAt(i)) % count;
//        return hash;
//    }

    private int hash(String key)
    { return (key.hashCode() & 0x7fffffff) % correctWords.length; }

    //put value into place using linear probing
    public void put(String val)
    {
        int i;
        i = hash(val);
        while(correctWords[i] != null) {
            i = (i + 1) % correctWords.length;
        }
        correctWords[i] = val;
    }
    //search for word
    public boolean searchWord(String s) {
        int i;
        i = hash(s);
        while(i != correctWords.length-1 && correctWords[i] != null) {
            if(correctWords[i].equals(s)) {
                return true;
            }
            i = (i + 1) % correctWords.length;

        }
        return false;
    }
    //return string to the speller
    public String get(String s) {
        int i;
        i = hash(s);
        while(i != correctWords.length-1 && correctWords[i] != null) {
            if(correctWords[i].equals(s)) {
                return correctWords[i];
            }
            i = (i + 1) % correctWords.length;

        }
        return "Not found...";
    }
    //size of dicitonary
    public int size() {
        return count;
    }
    //is dict empty
    public boolean isEmpty() {
        return count == 0;
    }
    //print dictionary
    public void printArray() {
        for(int i = 0; i < correctWords.length; i++) {
            System.out.println(correctWords[i]);
        }
    }

    public static void main(String[] args) {
        HashTableDictionary ed = new HashTableDictionary("./src/com/spellcheck/words");
        ed.printArray();
        System.out.println(ed.searchWord("xylophones"));
        System.out.println(ed.searchWord("of"));

    }
}