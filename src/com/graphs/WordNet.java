package com.graphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        this.numVertices = this.idSynsetMap.size();
        this.digraph = new Digraph(numVertices);
        loadHypernyms(hypernyms);

        validateTwoRoots();
        validateCycle();
    }

    private void validateCycle() {
        DirectedCycle dc = new DirectedCycle(this.digraph);
        if(dc.hasCycle()) throw new IllegalArgumentException("given directed graph has a cycle");
    }

    private void validateTwoRoots() {
        int numVerticesWithMoreThan1OutDegree = 0;
        for(int i = 0; i < numVertices; i++) {
            if(this.digraph.outdegree(i) == 0) {
            	//System.out.println("vertex " + i + "outdegree " + digraph.outdegree(i));
            	numVerticesWithMoreThan1OutDegree++;
            }
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

    // do unit testing of this class
    public static void main(String[] args) throws ParseException {
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String monthStartDate = "06/01/2017";
		Date startDate = df.parse(monthStartDate);
		for(int i = 0; i < 10; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
			String monthEndDate = df.format(cal.getTime());
			System.out.println("start date " + startDate + " end date" + cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			startDate = cal.getTime();
		}
		
    }
}
