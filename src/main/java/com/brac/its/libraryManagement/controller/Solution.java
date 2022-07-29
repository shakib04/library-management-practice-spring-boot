package com.brac.its.libraryManagement.controller;

import java.io.*;
import java.util.*;

public class Solution {

    static boolean isAnagram(String a, String b) {

        a = a.toUpperCase();
        b = b.toUpperCase();

        Hashtable<Character,Integer> firstTable = new Hashtable<>();
        for(Character c: a.toCharArray()){
            //System.out.println(c);
            if (firstTable.contains(c)){
                int previousValue = firstTable.get(c);
                firstTable.put(c, ++previousValue);
            }else {
                firstTable.put(c, 1);
            }
        }

        Hashtable<Character,Integer> secondTable = new Hashtable<>();
        for(Character c: a.toCharArray()){
            //System.out.println(c);
            if (secondTable.contains(c)){
                int previousValue = secondTable.get(c);
                secondTable.put(c, ++previousValue);
            }else {
                secondTable.put(c, 1);
            }
        }

        if (firstTable.size() != secondTable.size()) return false;

        for (Character key: firstTable.keySet()){
            if (! firstTable.get(key)
                    .equals(
                            secondTable.get(key))
            ){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}

