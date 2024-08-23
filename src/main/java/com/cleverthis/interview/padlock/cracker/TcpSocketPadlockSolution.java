package com.cleverthis.interview.padlock.cracker;

import com.cleverthis.interview.padlock.PadlockImpl;

/**
 * A placeholder implementation for the tcp socket padlock object(unimplemented) and defines the behaviour for padlock operations like
 * digit selection, write command, pulling size of numpad, passcode verification.
 */
public class TcpSocketPadlockSolution extends AbstractPadlockSolution {
    public TcpSocketPadlockSolution(PadlockImpl padlock) {
        super(padlock);
        throw new UnsupportedOperationException("TCP Padlock is not supported at the moment");
    }

    @Override
    protected Iterable<Integer> getDigits() {
        return null;
    }

    @Override
    protected void executeWriteInputBuffer(int position, int digit) {

    }

    @Override
    protected boolean isPasscodeCorrect() {
        return false;
    }

    @Override
    protected int getNumpadSize() {
        return 0;
    }
}
