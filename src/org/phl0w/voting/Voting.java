package org.phl0w.voting;

public class Voting {

    private static Candidate[] candidates = new Candidate[4];

    public static void main(String... args) {
        candidates[0] = new Candidate("Barack Obama", 100);
        candidates[1] = new Candidate("Mitt Romney", 510);
        candidates[2] = new Candidate("Ron Paul", 2024);
        candidates[3] = new Candidate("Rick Santorum", 124);
        System.out.println("And the winner is: " + getMostVoted().getName() + " with " + getMostVoted().getVotes() + " votes!");
    }

    private static Candidate getMostVoted() {
        Candidate t = candidates[3];
        for (Candidate c : candidates) {
            if (c.compareTo(t) == 1) {
                t = c;
            }
        }
        return t;
    }
}
