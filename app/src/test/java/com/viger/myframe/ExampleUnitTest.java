package com.viger.myframe;

import android.util.ArrayMap;
import android.util.SparseArray;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        Hashtable<String, String> hashtable = new Hashtable<>();

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        Map<String, String> safeHashMap = Collections.synchronizedMap(new HashMap<String, String>());

        SparseArray<Object> array = new SparseArray<>();
        array.put(0,"");

        ArrayMap<Integer, String> arrayMap = new ArrayMap<>();
        array.put(0,"");

    }



}