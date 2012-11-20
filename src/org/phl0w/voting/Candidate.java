package org.phl0w.voting;

public class Candidate implements Comparable<Candidate> {

    private int votes;
    private String name = "";

    public Candidate(final String name, final int votes) {
        this.votes = votes;
        this.name = name;
    }

    public int getVotes() {
        return this.votes;
    }

    public String getName() {
        return this.name;
    }

    public int compareTo(final Candidate t) {
        return this.votes < t.getVotes() ? -1 : 1;
    }
}