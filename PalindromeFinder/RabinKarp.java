package com.substring;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RabinKarp {
    private String pat;
    private long patHash;
    private int M;
    private static long Q = longRandomPrime();
    private static int R = 256;
    private long RM;

    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= M - 1; i++)
            RM = (R * RM) % Q;
        patHash = hash(pat);
    }

    public boolean check(int i) {
        return true;
    }

    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static long hash(String key) {
        long h = 0;
        for (int j = 0; j < key.length(); j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    public int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt);
        if (patHash == txtHash) return 0;
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                if (check(i - M + 1)) return i - M + 1;
        }
        return N;
    }

    public long getPatHash() {
        return patHash;
    }

    public static void main(String[] args) {

        //taking a different approach from last time, this time hashing string forward and hashing backwards to see if
        // they are the same
        Scanner scanner = new Scanner(System.in);
        String retainedWord = "";
        String retainedWordReverse = "";
        long hashvalue = 0;

        while (true) {
            //get characters
            System.out.println("Please enter the next character of your word, if you have finished please enter '1' ");
            String nextCharacter = scanner.nextLine();
            //break character
            if(nextCharacter.equals("1")) {
                break;
            }
            //retained word
            retainedWord = retainedWord+nextCharacter;
            //retained word in revers
            retainedWordReverse = nextCharacter + retainedWordReverse;
            //add new character to retained word hash
            hashvalue = (R * hashvalue + nextCharacter.charAt(0)) % Q;
            //find hash of retainedWordinReverse
            long hashOfRetainedWord = hash(retainedWordReverse);

            //check if equal
            if(hashOfRetainedWord == hashvalue) {
                System.out.println(retainedWord + " yes");
            } else {
                System.out.println(retainedWord + " no");
            }
        }

    }
}

/*
Please enter the next character of your word, if you have finished please enter '1'
t
t yes
Please enter the next character of your word, if you have finished please enter '1'
a
ta no
Please enter the next character of your word, if you have finished please enter '1'
c
tac no
Please enter the next character of your word, if you have finished please enter '1'
o
taco no
Please enter the next character of your word, if you have finished please enter '1'
c
tacoc no
Please enter the next character of your word, if you have finished please enter '1'
a
tacoca no
Please enter the next character of your word, if you have finished please enter '1'
t
tacocat yes
Please enter the next character of your word, if you have finished please enter '1'
1

Process finished with exit code 0

===================================================================================

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
1

Process finished with exit code 0

 */