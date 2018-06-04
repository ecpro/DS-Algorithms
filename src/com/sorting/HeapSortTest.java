package com.sorting;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;

public class HeapSortTest {

    public void testSwimMethod() throws Exception {
        Integer[] input = {5,6,1,4,3,0,9};
        Class<?> heapSortClass = Class.forName("com.sorting.HeapSort");
        Object object = heapSortClass.newInstance();
        Optional<Method> optionalMethod = Arrays.stream(
                heapSortClass.getDeclaredMethods())
                .filter(x -> x.getName().equals("swim") && x.getModifiers() == Modifier.PRIVATE).findFirst();

        if(optionalMethod.isPresent()) {
            Method method = optionalMethod.get();
            method.setAccessible(true);
            method.invoke(object, input, 5);
            Arrays.stream(input).forEach(x -> System.out.print(x + " "));
        }

    }

    public static void main(String[] args) throws Exception {
        HeapSortTest test = new HeapSortTest();
        //test.testSwimMethod();
        Integer[] input = {5,6,1,4,3,0,9};
        HeapSort.minHeap(input);
        Arrays.stream(input).forEach(x -> System.out.print(x + " "));
    }
}
