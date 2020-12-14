package com.hussein.um;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: ArrayListStream</p>
 * <p>Description: unmodifiable object design mode</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/2 5:07 PM
 */
public class ArrayListStream {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Thread", "Concurrency", "Scala");
        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
        list.forEach(System.out::println);
    }
}
