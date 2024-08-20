package com.cleverthis.interview;

import com.cleverthis.interview.padlock.PadlockImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a placeholder class showing a simple boilerplate.
 * This class is not required, so you can replace with your own architecture.
 */
public class Solution {

    private PadlockImpl padlock;

    public void solve(PadlockImpl padlock) {
        this.padlock = padlock;
        String cracked = crack();
        if (cracked == null) {
            throw new RuntimeException("Failed");
        }

        System.out.println("CRACKED ::::: " + cracked);
    }

    private String crack() {
        int numpadSize = padlock.getNumpadSize();
        List<String> permutations = generatePermutations(numpadSize);
        for (String code : permutations) {
            for (int i = 0; i < numpadSize; i++) {
                padlock.writeInputBuffer(i, Integer.parseInt(code.substring(i, i+1)));
            }
            if (padlock.isPasscodeCorrect()) {
                return code;
            }
        }
        return null;
    }

    private List<String> generatePermutations(int numberOfButtons) {
        List<String> permutations = new ArrayList<>();
        permute("", "0123456789".substring(0, numberOfButtons), permutations);
        return permutations;
    }

    private void permute(String prefix, String str, List<String> result) {
        int n = str.length();
        if (n == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), result);
            }
        }
    }

}
