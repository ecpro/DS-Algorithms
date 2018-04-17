package com.hackerrank;

import java.util.Arrays;

/**
 * Created by eccspro on 27/03/18.
 */
public class SparshArray {
    public static int findSuffix(String[] collections, String queryString) {
        return (int) Arrays.stream(collections).filter(collection -> collection.equals(queryString)).count();
    }
}
