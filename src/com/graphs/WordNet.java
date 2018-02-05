package com.graphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by eccspro on 02/02/18.
 */
public final class WordNet {

    private final Map<String, Set<Integer>> nounsSynsetMap = new HashMap<>();
    private final Map<Integer, String> idSynsetMap = new HashMap<>();
    private final Digraph digraph;
    private final int numVertices;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

        // validate the arguments
        validateObjectArguments(synsets, "Synsets filename");
        validateObjectArguments(hypernyms, "Hypernyms filename");

        // load synsets and hypernyms from files
        loadSynsets(synsets);
        this.numVertices = nounsSynsetMap.size();
        this.digraph = new Digraph(numVertices);
        loadHypernyms(hypernyms);

        boolean flag = isDAG(digraph);

        if(flag) throw new IllegalArgumentException("graph is not DAG");

        //validateTwoRoots(digraph);
        validateCycle(digraph);
    }

    private void validateCycle(Digraph digraph) {
        DirectedCycle dc = new DirectedCycle(this.digraph);
        if(dc.hasCycle()) throw new IllegalArgumentException("given directly graph has a cycle");
    }

    private void validateTwoRoots(Digraph digraph) {
        int numVerticesWithMoreThan1OutDegree = 0;
        for(int i = 0; i < numVertices; i++) {
            if(digraph.outdegree(i) == 0) numVerticesWithMoreThan1OutDegree++;
        }
        if(numVerticesWithMoreThan1OutDegree > 1) throw new IllegalArgumentException("Graph must not have two roots");
    }

    private void loadSynsets(String synsets) {
        List<String[]> rows = this.readCSV(synsets);
        for(String [] s : rows) {
            int v = Integer.parseInt(s[0]); // synset id
            this.idSynsetMap.put(v, s[1]);
            String [] nouns = s[1].split(" "); // nouns space separated
            for(String noun : nouns) {
                if(!nounsSynsetMap.containsKey(noun)) {
                    Set<Integer> ids = new HashSet<>();
                    ids.add(v);
                    nounsSynsetMap.put(noun, ids);
                }
                else {
                    nounsSynsetMap.get(noun).add(v);
                }
            }
        }
    }

    private List<String[]> readCSV(String fileNname) {
        List<String[]> rows = new ArrayList<>();
        In in = new In(fileNname);
        while(in.hasNextLine()) {
            String s[] = in.readLine().split(",");
            rows.add(s);
        }
        return rows;
    }

    private void loadHypernyms(String hypernyms) {
        List<String[]> rows = this.readCSV(hypernyms);
        for(String [] row: rows) {
            int vertex = Integer.parseInt(row[0]);
            for(int i = 1; i < row.length; i++) {
                    digraph.addEdge(Integer.parseInt(row[0]), Integer.parseInt(row[i]));
            }
        }
    }

    private void validateObjectArguments(Object object, String argumentName) {
        if(object == null) throw new IllegalArgumentException(argumentName + " must not be " + null);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return this.nounsSynsetMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        this.validateObjectArguments(word, "word");
        return nounsSynsetMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if(!this.isNoun(nounA) && !this.isNoun(nounB)) throw new IllegalArgumentException("nouns not present in word net");
        SAP sap = new SAP(this.digraph);
        return sap.length(this.nounsSynsetMap.get(nounA), this.nounsSynsetMap.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if(!this.isNoun(nounA) && !this.isNoun(nounB)) throw new IllegalArgumentException("nouns not present in word net");
        SAP sap = new SAP(this.digraph);
        int ancestor = sap.ancestor(nounsSynsetMap.get(nounA), nounsSynsetMap.get(nounB));
        if(ancestor != -1) return idSynsetMap.get(ancestor);
        return null;
    }

    @Override
    public String toString() {
        return "WordNet{" +
                "nounsSynsetMap=" + nounsSynsetMap +
                ", \n digraph=" + digraph +
                ", numVertices=" + numVertices +
                '}';
    }

    private boolean dfs(int v, Digraph G, Map<Integer, Boolean> visit,
                        Stack<Integer> S) {
        visit.put(v, true);
        S.push(v);
        for (int w : G.adj(v)) {
            if (S.contains(w))
                return false;
            if (!visit.containsKey(w))
                if (!dfs(w, G, visit, S))
                    return false;
        }
        S.pop();
        return true;
    }

    private boolean isDAG(Digraph G) {
        Map<Integer, Boolean> visit = new HashMap<Integer, Boolean>();
        Stack<Integer> S = new Stack<Integer>();
        for (int i = 0; i < G.V(); i++) {
            if (!visit.containsKey(i))
                if (!dfs(i, G, visit, S))
                    return false;
        }
        // check multiple roots
        int cnt = 0;
        for (int i = 0; i < G.V(); i++) {
            int n = 0;
            Iterator<Integer> it = G.adj(i).iterator();
            while (it.hasNext()) {
                n += 1;
                it.next();
            }
            if (n == 0)
                cnt += 1;
        }
        if (cnt != 1)
            return false;
        return true;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet net = new WordNet("/Users/eccspro/Documents/version-control/DS-Algorithms/Resources/wordnet/synsets6.txt",
                "/Users/eccspro/Documents/version-control/DS-Algorithms/Resources/wordnet/hypernyms6InvalidTwoRoots.txt");
        System.out.println(net);
    }
}
