package org.lwjl.loops;

public abstract class SubLoop {

    private final long id;

    public SubLoop(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract int run();

}
