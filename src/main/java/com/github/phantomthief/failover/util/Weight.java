/**
 * 
 */
package com.github.phantomthief.failover.util;

import static com.google.common.collect.Range.closedOpen;
import static com.google.common.collect.TreeRangeMap.create;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.RangeMap;

/**
 * 带权重的树
 * 
 * @author w.vela
 * @param <T>
 */
public class Weight<T> {

    private final Map<T, Long> weightMap = new HashMap<>();
    private final RangeMap<Long, T> nodes = create();
    private long maxWeight = 0;

    public Weight<T> add(T node, long weight) {
        if (weight > 0) {
            weightMap.put(node, weight);
            nodes.put(closedOpen(maxWeight, maxWeight + weight), node);
            maxWeight += weight;
        }
        return this;
    }

    public T get() {
        if (isEmpty()) {
            return null;
        }
        long resultIndex = nextLong(0, maxWeight);
        return nodes.get(resultIndex);
    }

    public boolean isEmpty() {
        return maxWeight == 0;
    }

    @Override
    public String toString() {
        return nodes.toString();
    }
}