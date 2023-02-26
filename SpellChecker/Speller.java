package com.spellcheck;

import java.io.*;
import java.util.Scanner;

public class Speller  {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        HashTableDictionary ed = new HashTableDictionary("./src/com/spellcheck/words");
        System.out.println("Please enter your word: ");
        String word = scanner.nextLine().trim().toLowerCase();
        //check if correctly spelled
        if(ed.searchWord(word)) {
            System.out.println("That is the correct spelling!");
        } else {
            boolean flag = false;
            //check if added to the end
            if(ed.searchWord(word.substring(0, word.length()-1))) {
                System.out.println("Did you mean: " + ed.get(word.substring(0, word.length()-1)));
                flag = true;
            }
            //check if added to the front
            if(ed.searchWord(word.substring(1))) {
                System.out.println("Did you mean: " + ed.get(word.substring(1)));
                flag = true;
            }

            String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
            for(int i = 0; i < arr.length; i++) {
                //check if deleted from the front
                if(ed.searchWord(arr[i] + word)) {
                    System.out.println("Did you mean: " + ed.get(arr[i] + word));
                    flag = true;
                }
                //check if deleted from the end
                if(ed.searchWord(word+arr[i])) {
                    System.out.println("Did you mean: " + ed.get(word+arr[i]));
                    flag = true;
                }
            }
            //check if swapped characters
            for(int i = 0; i < word.length()-1; i++) {
                if(ed.searchWord(word.substring(0,i) + word.charAt(i+1) + word.charAt(i) + word.substring(i+2))) {
                    System.out.println("Did you mean: " + word.substring(0,i) + word.charAt(i+1) + word.charAt(i) + word.substring(i+2));
                    flag = true;
                }
            }
            //word not incorrectly spelled, nor correctly spelled; not included in dictionary
            if(!flag) {
                System.out.println("I did not recognize your word.");
            }
        }
    }
}