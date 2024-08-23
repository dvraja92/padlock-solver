package com.cleverthis.interview.padlock.cracker;

import com.cleverthis.interview.padlock.PadlockImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This abstract class defines the overall solution that uses the the backtracking process.
 * It uses abstract methods to delegate the specific details of digit selection, command execution like write & size of numpad,
 * and passcode validation to subclasses.
 *
 * The subclasses can define specific behaviour how they pull the details from padlock.
 * {@link ConcretePadlockSolution} -> provides actual implementation to pull details from plan java padlock object .
 * {@link TcpSocketPadlockSolution} -> a placeholder class to demonstrate if the padlock communicates via a tcp socket.
 *
 */
public abstract class AbstractPadlockSolution {

    protected final PadlockImpl padlock;

    protected AbstractPadlockSolution(PadlockImpl padlock) {
        this.padlock = padlock;
    }

    /**
     * method that initiates the solution and print the solution if found on the std out.
     *
     * @return String of passcode
     * @throws IllegalArgumentException when the padlock is not solvable. Ideally this should not be the case.
     */
    public String solve() {
        Integer[] solution = new Integer[padlock.getNumpadSize()];
        if (backtrack(0, solution, new HashSet<>())) {
            return Stream.of(solution).map(String::valueOf).collect(Collectors.joining());
        }

        // Should not reach here if the padlock is solvable
        throw new IllegalArgumentException("Given padlock is not solvable");
    }

    /**
     * Recursive method that applies the alogithm (refer README.md for the algorithm details)
     * in simple words, it tries to fill all the positions and verifies the passcode.
     * if correct then return else removes last added digit and try with another combination.
     *
     * @param position   The current position (0 to numpadSize-1).
     * @param solution   solution array.
     * @param usedDigits Hashset of used digits.
     * @return true if valid passcode, false otherwise.
     */
    private boolean backtrack(int position, Integer[] solution, Set<Integer> usedDigits) {
        if (position == getNumpadSize()) {
            return isPasscodeCorrect();
        }

        for (int digit : getDigits()) {
            if (!usedDigits.contains(digit)) {
                executeWriteInputBuffer(position, digit);
                solution[position] = digit;
                usedDigits.add(digit);

                if (backtrack(position + 1, solution, usedDigits)) {
                    return true;
                }

                // Backtrack
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
