package com.jking.mergeback.merge;

import com.jking.mergeback.client.response.ResultsItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by john on 3/31/17.
 */
public abstract class Merger<T> {

    public List<T> merge(List<T> from, List<T> to) {
        return mergeList(to, from.stream()
                .map(fromItem ->
                        to.stream()
                                .filter(toItem -> fromItem.equals(toItem))
                                .findAny()
                                .map(toItem -> merge(fromItem, toItem))
                                .orElseGet(() -> fromItem)

                ).collect(Collectors.toList()));
    }

    <Y> List<Y> mergeList(List<Y> from, List<Y> to){
        return Stream.concat(to.stream(),
                from.stream()
                        .filter(fromItem -> to.stream()
                                .noneMatch(toItem -> toItem.equals(fromItem))))
                .collect(Collectors.toList());
    }

    String mergeString(String from, String to){
        return to == null ? from : to;
    }

    public abstract T merge(T from, T to);
}
