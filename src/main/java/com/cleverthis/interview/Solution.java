package com.cleverthis.interview;

import com.cleverthis.interview.padlock.PadlockImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is a placeholder class showing a simple boilerplate.
 * This class is not required, so you can replace with your own architecture.
 */
public class Solution {

    private int numpadSize;
    private PadlockImpl padlock;

    public void solve(PadlockImpl padlock) {
        this.numpadSize = padlock.getNumpadSize();
        this.padlock = padlock;
        Integer[] solution = new Integer[numpadSize];
        Set<Integer> usedDigits = new HashSet<>();
        if (recurse(0, solution, usedDigits)) {
            System.out.println(Stream.of(solution).map(String::valueOf).collect(Collectors.joining()));
        }
    }


    private boolean recurse(int position, Integer[] solution, Set<Integer> usedDigits) {
        if (position == numpadSize) {
            return padlock.isPasscodeCorrect();
        }

        for (int digit = 0; digit < numpadSize; digit++) {
            if (!usedDigits.contains(digit)) {
                padlock.writeInputBuffer(position, digit);
                solution[position] = digit;
                usedDigits.add(digit);

                if (recurse(position + 1, solution, usedDigits)) {
                    return true;
                }
                usedDigits.remove(digit);
            }
        }
        return false;
    }

}
