package com.brac.its.libraryManagement.basicOperation;

import java.util.ArrayList;

public class FibonacciSeries {

    public ArrayList<Integer> getFibonacciOfANumber(int count){
        int n1=0,n2=1,n3;
        ArrayList<Integer> listOfFibo = new ArrayList<>();
        if (count > 0){
            listOfFibo.add(n1);
            listOfFibo.add(n2);
            for (int i = 2; i < count; i++) {
                n3 = n1 + n2;
                listOfFibo.add(n3);
                n1 = n2;
                n2 = n3;
            }
            return listOfFibo;
        }
        return new ArrayList<>(0);

    }
}
