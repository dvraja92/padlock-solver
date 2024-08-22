package com.cleverthis.interview.padlock.cracker;

import com.cleverthis.interview.padlock.PadlockImpl;

import java.util.HashSet;
import java.util.Set;


public abstract class AbstractPadlockSolution {

    protected final PadlockImpl padlock;

    protected AbstractPadlockSolution(PadlockImpl padlock) {
        this.padlock = padlock;
    }

    public void solve() {
        Integer[] solution = new Integer[padlock.getNumpadSize()];
        if (fillAndCheck(0, solution, new HashSet<>())) {
            return;
        }

        throw new RuntimeException("can't solve");
    }

    private boolean fillAndCheck(int position, Integer[] solution, Set<Integer> usedDigits) {
        if (position == getNumpadSize()) {
            return isPasscodeCorrect();
        }

        for (int digit : getDigits()) {
            if (!usedDigits.contains(digit)) {
                executeWriteInputBuffer(position, digit);
                solution[position] = digit;
                usedDigits.add(digit);

                if (fillAndCheck(position + 1, solution, usedDigits)) {
                    return true;
                }

                usedDigits.remove(digit);
            }
        }
        return false;
    }


    protected abstract Iterable<Integer> getDigits();

    protected abstract void executeWriteInputBuffer(int position, int digit);

    protected abstract boolean isPasscodeCorrect();

    protected abstract int getNumpadSize();

}
