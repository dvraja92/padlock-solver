package com.cleverthis.interview.padlock;

class Utils {
    /**
     * Ensure we will wait a given amount of time even if there are interruptions.
     *
     * @param millis The time you want to sleep, measure in millisecond.
     */
    public static void ensureSleep(long millis) {
        long endTime = System.currentTimeMillis() + millis;
        while (endTime > System.currentTimeMillis()) {
            try {
                //noinspection BusyWait
                Thread.sleep(endTime - System.currentTimeMillis());
            } catch (InterruptedException e) {
                // do nothing when interrupted, will re-sleep in next loop
            } catch (IllegalArgumentException e) {
                break;
            }
        }
    }
}
