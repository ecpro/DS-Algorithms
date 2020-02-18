package com.backtracking;

import java.util.*;

public class WordBreak {

    Set<String> words;

    public WordBreak(Set<String> words) {
     this.words = new HashSet<>(words);
    }

    public void findSentences(String input, Integer start, Map<String,List<String>> output, String found) {
        for(int j = start + 1; j <= input.length(); j++) {
            String word = input.substring(start, j);
            if (words.contains(word)) {
                    if (Objects.nonNull(found)) {
                    if (output.containsKey(found)) {
                        output.get(found).add(word);
                    } else {
                        List<String> vals = new ArrayList<>();
                        vals.add(word);
                        output.put(found, vals);
                    }
                }
                findSentences(input, j, output, word);
            }
        }
    }

    public static void main(String[] args) {

        Set<String> words = new HashSet<>(Arrays.asList( "i", "like", "sam", "sung", "samsung", "mobile", "ice",
            "cream", "icecream", "man", "go", "mango", "and"));
        WordBreak wordBreak = new WordBreak(words);

        Map<String, List<String>> map = new LinkedHashMap<>();
        wordBreak.findSentences("ilikeicecreamandmango", 0, map, null);
        System.out.println(map);
    }
}
