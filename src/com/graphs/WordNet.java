package com.graphs;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by eccspro on 02/02/18.
 */
public final class WordNet implements Iterable<String>{

    private final Map<Integer, Set<String>> vertices;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        validateObjectArguments(synsets, "Synsets filename");
        validateObjectArguments(hypernyms, "Hypernyms filename");
        vertices = new HashMap<>();
        // read synsets into an input stream
        loadSynsets(synsets);
    }

    private void loadSynsets(String synsets) {
        In in = new In(synsets);
        String [] s = null;
        while(in.hasNextLine()) {
            s = in.readLine().split(",");
            int v = Integer.parseInt(s[0]); // synset id
            String [] nouns = s[1].split(" "); // nouns space separated
            Set<String> synset = new LinkedHashSet<>();
            for(String noun : nouns) {
                synset.add(noun);
            }
            this.vertices.put(v, synset);
        }
    }

    private void validateObjectArguments(Object object, String argumentName) {
        if(object == null) throw new IllegalArgumentException(argumentName + " must not be " + null);
    }


    // returns all WordNet nouns
    @Override
    public Iterator<String> iterator() {
        return new NounIterator();
    }

    class NounIterator implements Iterator<String>{

        private int currentKey;
        private Iterator<String> nounIterator;

        public NounIterator() {
            if(vertices != null && !vertices.isEmpty()) {
                nounIterator = vertices.get(0).iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if(currentKey >= vertices.size() - 1 && !nounIterator.hasNext()) return false;
            return true;
        }

        @Override
        public String next() {
            if(!nounIterator.hasNext()) {
                currentKey++;
                nounIterator = vertices.get(currentKey).iterator();
            }
            return nounIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        for(Integer key : vertices.keySet()) {
            if(vertices.get(key).contains(word)) return true;
        }
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet net = new WordNet("/Users/eccspro/Documents/version-control/DS-Algorithms/Resources/wordnet/synsets5000-subgraph.txt", "abc");
        Iterator<String> iterator = net.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
