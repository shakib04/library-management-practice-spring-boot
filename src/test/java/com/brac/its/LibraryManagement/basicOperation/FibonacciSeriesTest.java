package com.brac.its.LibraryManagement.basicOperation;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FibonacciSeriesTest {

    @Test
    void getFibonacciOfANumber() {

        int[] nn = {
                0,
                1,
                1,
                2,
                3,
                5,
                8,
                13,
                21,
                34
        };
        FibonacciSeries fibonacciSeries = new FibonacciSeries();
        ArrayList<Integer> nums = fibonacciSeries.getFibonacciOfANumber(10);
        Assert.assertEquals(nn.length, nums.toArray().length);
    }
}