package com.cleverthis.interview;

import com.cleverthis.interview.padlock.PadlockImpl;
import com.cleverthis.interview.padlock.cracker.ConcretePadlockSolution;

/**
 * This is a placeholder class showing a simple boilerplate.
 * This class is not required, so you can replace with your own architecture.
 */
public class Solution {
    public void solve(PadlockImpl padlock) {
        new ConcretePadlockSolution(padlock).solve();
    }
}
