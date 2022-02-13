package com.minholee93.margincallcalculator.util;

import java.util.*;

public class ValueUtils {

    public static <E> Collection<E> getOrEmpty(Collection<E> collection) {
        return collection == null ? Collections.emptyList() : collection;
    }

    public static <K, V> Map<K, V> getOrEmpty(Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    public static <E> Set<E> getOrEmpty(Set<E> set) {
        return set == null ? Collections.emptySet() : set;
    }

    public static <E> List<E> getOrEmpty(List<E> list) {
        return list == null ? Collections.emptyList() : list;
    }
}
