package org.lwjl.loops;

import java.util.ArrayList;

public class Loop {

    private final long id;
    private ArrayList<SubLoop> loops;

    public Loop(long id) {
        this.id = id;
        this.loops = new ArrayList<>();
    }

    public void addLoop(SubLoop loop) {
        if (!hasId(loop.getId())) this.loops.add(loop);
        else throw new RuntimeException(String.format("Loop with id: %d already exists", id));
    }

    public void removeLoop(long id) {
        for (SubLoop loop : loops) {
            if (loop.getId() == id) {
                this.loops.remove(loop);
                return;
            }
        }
        throw new RuntimeException(String.format("Loop with id: %d does not exist", id));
    }

    public boolean hasId(long id) {
        if (this.getId() == id) return true;
        for (SubLoop loop : loops) {
            if (loop.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void run(int contraint, boolean verbose) {
        if (contraint == 0) runInternal(RunType.INFINITE, contraint, verbose);
        else if (contraint >= 1) runInternal(RunType.FINITE, contraint, verbose);
        else runInternal(RunType.DO_ONCE, contraint, verbose);
    }

    public void run(int contraint) {
        run(contraint, false);
    }

    public void run() {
        run(0, false);
    }

    private void runInternal(RunType runType, int contraint, boolean verbose) {
        if (contraint == 0 && runType == RunType.INFINITE) {
            int i = 0;
            while (contraint == 0) {
                for (SubLoop loop : loops) {
                    if (verbose) System.out.print(i + ": ");
                    loop.run();
                }
                if (verbose) i++;
            }
        }

        if (contraint >= 1 && runType == RunType.FINITE) {
            for (int i = 0; i < contraint; i++) {
                for (SubLoop loop : loops) {
                    if (verbose) System.out.print(i + ": ");
                    loop.run();
                }
            }
        }

        if (runType == RunType.DO_ONCE) {
            int i = 0;
            for (SubLoop loop : loops) {
                if (verbose) System.out.print(i + ": ");
                loop.run();
            }
        }
    }

    public long getId() {
        return id;
    }

    public enum RunType {
        DO_ONCE,
        FINITE,
        INFINITE,
    }

}
