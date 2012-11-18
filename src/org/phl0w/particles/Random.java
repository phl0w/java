package org.phl0w.particles;

public class Random<T> {

    private T[] t;
    private final java.util.Random r = new java.util.Random();

    public Random(T... t) {
        this.t = t;
    }

    public T getRandom() {
        return t[r.nextInt(t.length)];
    }
}