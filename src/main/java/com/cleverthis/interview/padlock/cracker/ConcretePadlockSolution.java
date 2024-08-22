package com.cleverthis.interview.padlock.cracker;

import com.cleverthis.interview.padlock.PadlockImpl;

import java.util.ArrayList;
import java.util.List;


public class ConcretePadlockSolution extends AbstractPadlockSolution {
    public ConcretePadlockSolution(PadlockImpl padlock) {
        super(padlock);
    }

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
        padlock.writeInputBuffer(position, digit);
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
