package com.cleverthis.interview;

import com.cleverthis.interview.padlock.PadlockImpl;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a simple placeholder to show how unit test works.
 * You can replace it with your own test.
 */
class SolutionTest {
    private void solve(PadlockImpl padlock) {
        new Solution().solve(padlock);
    }

    @Test
    void verify(){
        Random random = new Random();
        PadlockImpl padlock = new PadlockImpl(random.nextInt(1, 8));

        long startTime = System.currentTimeMillis();
        solve(padlock);
        long endTime = System.currentTimeMillis();
        assertTrue(padlock.isPasscodeCorrect());
        System.out.println("Time usage: " + (endTime - startTime) + "ms");
    }
}