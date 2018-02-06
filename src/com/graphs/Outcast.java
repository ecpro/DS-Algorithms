package com.graphs;

/**
 * Created by eccspro on 05/02/18.
 */
public final class Outcast {

    private final WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        validateObjectArguments(wordnet, "wordnet");
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        String outCast = null;
        int maxDist = Integer.MIN_VALUE;
        for(int i = 0; i < nouns.length; i++) {
            int currDist = 0;
            for(int j = 0; j < nouns.length; j++) {
                currDist += wordNet.distance(nouns[i], nouns[j]);
            }
            if(currDist > maxDist) outCast = nouns[i];
        }
        return outCast;
    }

    private void validateObjectArguments(Object object, String argumentName) {
        if(object == null) throw new IllegalArgumentException(argumentName + " must not be " + null);
    }
}
