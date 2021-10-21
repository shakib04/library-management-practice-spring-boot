package com.brac.its.libraryManagement.basicOperation;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@Log
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
        int count = 10;
        ArrayList<Integer> nums = fibonacciSeries.getFibonacciOfANumber(count);
        log.info(nums.toString());
        Assert.assertEquals(java.util.Optional.of(nums.get(count - 1)), java.util.Optional.of(nn[count - 1]));
        Assert.assertEquals(nn.length, nums.toArray().length);
    }
}