package com.substring;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String retainedWord = " ";
        String retainedWordReverse = " ";
        long tempHash = 0;
        long hashvalue = 0;

        while (true) {
            System.out.println("Please enter the next character of your word, if you have finished please enter '1' ");
            String nextCharacter = scanner.nextLine();
            if(nextCharacter.equals("1")) {
                break;
            }
             retainedWord = retainedWord+nextCharacter;
             retainedWordReverse = nextCharacter + retainedWordReverse;
             tempHash = RabinKarp.hash(nextCharacter);
             hashvalue += tempHash;

             if(RabinKarp.hash(retainedWordReverse) == hashvalue) {
                 System.out.println(retainedWord + " yes");
             } else {
                 System.out.println(retainedWord + " no");
             }
        }

    }

//    static void checkIfPalindrome(String word)
//    {
//        int n = word.length();
//
//        if (n == 1) {
//            System.out.println(word.charAt(0) + " yes");
//            return;
//        }
//
//        //check if odd or even
//        if(n % 2 == 0) {
//            //if even, obtain both halves
//            String firstHalf = word.substring(0, n/2);
//            String secondHalf = word.substring(n/2);
//            //reverse second string
//            String secondHalfReversed = new StringBuilder(secondHalf).reverse().toString();
//            //once done, check if they are equal using hashing
//
//            RabinKarp rk = new RabinKarp(firstHalf);
//            int result = rk.search(secondHalfReversed);
//            if(result == secondHalfReversed.length()) {
//                System.out.println(word + " no");
//            } else {
//                System.out.println(word + " yes");
//            }
//
//        } else {
//            //if odd, obtain both halves
//            String firstHalf = word.substring(0, n/2);
//            String secondHalf = word.substring(n/2+1);
//            //reverse second string
//            String secondHalfReversed = new StringBuilder(secondHalf).reverse().toString();
//            //once done, check if they are equal using hashing
//            RabinKarp rk = new RabinKarp(firstHalf);
//            int result = rk.search(secondHalfReversed);
//            if(result == secondHalfReversed.length()) {
//                System.out.println(word + " no");
//            } else {
//                System.out.println(word + " yes");
//            }
//        }
//    }
  }

/*
Output:
Please enter the next character of your word, if you have finished please enter '1'
w
w yes
Please enter the next character of your word, if you have finished please enter '1'
o
wo no
Please enter the next character of your word, if you have finished please enter '1'
w
wow yes
Please enter the next character of your word, if you have finished please enter '1'
1

============

Please enter the next character of your word, if you have finished please enter '1'
y
y yes
Please enter the next character of your word, if you have finished please enter '1'
a
ya no
Please enter the next character of your word, if you have finished please enter '1'
y
yay yes
Please enter the next character of your word, if you have finished please enter '1'

===========

Please enter the next character of your word, if you have finished please enter '1'
s
s yes
Please enter the next character of your word, if you have finished please enter '1'
u
su no
Please enter the next character of your word, if you have finished please enter '1'
s
sus yes
Please enter the next character of your word, if you have finished please enter '1'
h
sush no
Please enter the next character of your word, if you have finished please enter '1'
i
sushi no
Please enter the next character of your word, if you have finished please enter '1'
1

 */