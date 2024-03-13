package com.cleverthis.interview.padlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.cleverthis.interview.padlock.Utils.ensureSleep;

/**
 * This is a logical representation of a physical padlock with a numpad.
 * <br/>
 * The padlock has the following features:
 * <ul>
 *     <li>All the keys/buttons on the numpad is used exactly once.
 *     Which means codes like 1234 and 1432 is valid, but codes like
 *     112 or 1332 is invalid.</li>
 *     <li>Writing to the passcode input buffer of the padlock is an
 *     expensive operation, since the hardware need to figure out the
 *     memory address every time you write. Thus an write operation
 *     can take seconds to finish.</li>
 *     <li>Ask padlock if a passcode is correct is fast.</li>
 *     <li>If the padlock reject your input, the input buffer remain
 *     unchanged. There is NO reset on failed attempts.</li>
 * </ul>
 * <br/>
 * After create, the input buffer is empty, you have to initialize.
 */
public class PadlockImpl {
    private final boolean debug;
    private final int numpadSize;
    private final Integer[] inputBuffer;
    private final Integer[] correctPasscode;

    /**
     * Create a padlock instance.
     *
     * @param numpadSize The number of buttons on the numpad of this lock.
     */
    public PadlockImpl(int numpadSize) {
        this(numpadSize, false);
    }

    /**
     * Create a padlock instance.
     *
     * @param numpadSize The number of buttons on the numpad of this lock.
     * @param debug      Will skip sleep if is true
     */
    PadlockImpl(int numpadSize, boolean debug) {
        this.debug = debug;
        if (numpadSize < 1) throw new IllegalArgumentException("numpadSize must be a positive number");
        this.numpadSize = numpadSize;
        this.inputBuffer = new Integer[numpadSize];
        List<Integer> answer = new ArrayList<>(numpadSize);
        for (int i = 0; i < numpadSize; i++)
            answer.add(i);
        for (int i = 0; i < numpadSize / 2; i++) {
            Collections.shuffle(answer);
        }
        this.correctPasscode = answer.toArray(new Integer[0]);
    }

    public int getNumpadSize() {
        return numpadSize;
    }

    /**
     * Write a digit into padlock's input buffer. This is a very expensive operation.
     *
     * @param address  The digits you want to write. Range: [0, numpadSize)
     * @param keyIndex The key/button index you want to put here. Range: [0, numpadSize)
     * @return The old value, null if not initialized.
     */
    public synchronized Integer writeInputBuffer(int address, int keyIndex) {
        if (!debug) ensureSleep(1000);
        if (keyIndex < 0 || keyIndex >= numpadSize)
            throw new IllegalArgumentException(
                    "keyIndex out of range. Keypad size: " + numpadSize + ", keyIndex: " + keyIndex);
        Integer oldValue = inputBuffer[address];
        inputBuffer[address] = keyIndex;
        return oldValue;
    }

    /**
     * Check if the input buffer contains a correct passcode.
     *
     * @return true if the passcode is correct; false if passcode is wrong.
     * @throws IllegalStateException if the input buffer is not a valid passcode
     */
    public synchronized boolean isPasscodeCorrect() {
        // first check if the input is legal
        boolean[] uniqueTestArr = new boolean[numpadSize];
        for (Integer i : inputBuffer) {
            if (i == null) throw new IllegalStateException(
                    "Passcode invalid: contain uninitialized value. " + Arrays.toString(inputBuffer));
            if (uniqueTestArr[i]) throw new IllegalStateException(
                    "Passcode invalid: contain duplicated value. " + Arrays.toString(inputBuffer));
            uniqueTestArr[i] = true;
        }
        // if no exception, means:
        //     every digit is unique, and every digit is initialized
        // aka this is a valid code
        // now compare with our answer
        return Arrays.equals(correctPasscode, inputBuffer);
    }
}