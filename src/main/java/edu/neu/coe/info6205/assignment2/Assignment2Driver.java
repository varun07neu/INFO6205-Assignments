package edu.neu.coe.info6205.assignment2;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Run the benchmark for insertion sort
 *
 * choice indicates the order of array. 1= random, 2= ordered, 3= reverse-ordered, 4= partially ordered
 */
public class Assignment2Driver {
    private static  Config config;
    private static  int N = 500;
    public static void main(String[] args) {
        int choice = 1;
        if(choice == 1){
            for(int i = 0; i < 6; i ++){
                Helper<Integer> helper = new BaseHelper<>("insertion sort", N, config);
                Supplier<Integer[]> supplier = () -> helper.random(Integer.class, Random::nextInt);
                Assignment2Driver.benchmarkTarget(helper, supplier, i, "random");
                N*=2;
            }
        }
        else if(choice == 2) {
            Integer[] arr = new Integer[N];
            for(int i = 0; i < 6; i ++){
                Helper<Integer> helper = new BaseHelper<>("insertion sort", N, config);
                Supplier<Integer[]> supplier = () -> generateOrderedArray(N, true);
                Assignment2Driver.benchmarkTarget(helper, supplier, i, "ordered");
                N*=2;
            }
        }
        else if(choice == 3) {
            for(int i = 0; i < 6; i ++){
                Helper<Integer> helper = new BaseHelper<>("insertion sort", N, config);
                Supplier<Integer[]> supplier = () -> generateOrderedArray(N, false);
                Assignment2Driver.benchmarkTarget(helper, supplier, i, "reverse");
                N*=2;
            }
        }
        else if(choice == 4) {
            for(int i = 0; i < 6; i ++){
                Helper<Integer> helper = new BaseHelper<>("insertion sort", N, config);
                Supplier<Integer[]> supplier = () -> generatePartiallyOrderedArray(N);
                Assignment2Driver.benchmarkTarget(helper, supplier, i, "partially ordered");
                N*=2;
            }
        }

        N=100;
    }
    private static void benchmarkTarget(Helper helper, Supplier supplier, Integer runNumber, String orderType){
        final GenericSort<Integer> insertionSort = new InsertionSort<>(helper);
            final Benchmark<Integer []> benchmark = new Benchmark_Timer<>(
                    "Insertion sort run: " + (runNumber+1)+" for "+orderType+" array"+" for " + N + " integers",
                    (xs) -> Arrays.copyOf(xs, xs.length),
                    insertionSort::mutatingSort,
                    null
            );
            System.out.println(benchmark.runFromSupplier(supplier, 100)+ " ms");
    }
    private static Integer[] generateOrderedArray (int length, boolean asc) {
        Integer[] sortedArray = new Integer[length];
        if(asc){
            for (int i = 0; i< length; i++){
                sortedArray[i]=i;
            }
        }
        else{
            for (int i = 0; i< length; i++){
                sortedArray[i]=length-i-1;
            }
        }

        return  sortedArray;
    }

    private static Integer[] generatePartiallyOrderedArray (int length) {
        Integer[] partiallyOrderedArray = new Integer[length];
        Random random = new Random();
        for (int i = 0; i< length%4; i++){
                partiallyOrderedArray[i]=i;
            }
            for (int i = length%4; i< length; i++){
                partiallyOrderedArray[i]=random.nextInt(length);
            }
        return  partiallyOrderedArray;
    }
}
