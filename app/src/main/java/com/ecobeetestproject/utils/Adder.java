package com.ecobeetestproject.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.*;

public class Adder  implements  Runnable{

    public  static  List<Callable<Integer>> functionList;
    public  static Consumer<Integer> onSumChanged;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) throws Exception {
        Callable<Integer> expensiveFunction = () -> (int) IntStream.range(0, 200000000).count();
        Callable<Integer> cheapFunction = () -> (int) IntStream.range(0, 10000000).count();
        functionList= Arrays.asList(expensiveFunction, cheapFunction);
        onSumChanged = (sum) -> System.out.println("Current result: " + sum);

        // Computationally expensive functions need more time than cheaper functions.
        // Because of this, computationally cheaper functions, when run in parallel,
        // should be summed up before more expensive functions.
        // Expected output:
        // Current result: 10000000
        // Current result: 210000000
        // Final result: 210000000

    }

    @Override
    public void run() {
        int result = 0;
        try {
            result = sum(functionList, onSumChanged);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + result);
    }

    public static int sum(List<Callable<Integer>> functions,
                          Consumer<Integer> onSumChanged) throws Exception {
        throw new Exception("Waiting to be implemented.");
    }

}
