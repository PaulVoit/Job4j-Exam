package ru.jo4j.backgroundthread;

import android.util.Log;

public class TestRunnable implements Runnable {
    private int times;
    private static final String TAG = "BackgroundThread";
    public TestRunnable(int times) {
        this.times = times;
    }

    @Override
    public void run() {
        int count = 0;
        while (count != 10) {
            Log.d(TAG, "startThread" + count);
            count++;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
