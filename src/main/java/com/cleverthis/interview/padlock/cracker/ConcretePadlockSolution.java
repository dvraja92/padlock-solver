package com.cleverthis.interview.padlock.cracker;

import com.cleverthis.interview.padlock.PadlockImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation for the plan java padlock object and defines the behaviour for padlock operations like
 * digit selection, write command, pulling size of numpad, passcode verification.
 */
public class ConcretePadlockSolution extends AbstractPadlockSolution {
    public ConcretePadlockSolution(PadlockImpl padlock) {
        super(padlock);
    }

    /**
     * Provides a list of possible digits for the current padlock.
     *
     * @return A list of possible digits from 0 to numpadSize-1.
     */
    @Override
    protected Iterable<Integer> getDigits() {
        List<Integer> possibleDigits = new ArrayList<>();
        for (int i = 0; i < padlock.getNumpadSize(); i++) {
            possibleDigits.add(i);
        }
        return possibleDigits;
    }

    @Override
    protected void executeWriteInputBuffer(int position, int digit) {
        padlock.writeInputBuffer(position, digit); // Expensive operation
    }

    @Override
    protected boolean isPasscodeCorrect() {
        return padlock.isPasscodeCorrect();
    }

    @Override
    protected int getNumpadSize() {
        return padlock.getNumpadSize();
    }
}
