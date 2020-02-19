package com.wxfjava.struggle.utils;

import android.util.Log;

public class L {

    public static final String TAG = "[TS-FH]";
    private static final int CALLER_LOCATION = 4;
    private static final int MIN_STACK_TRACE_LENGTH = 5;
    private static StringAlign stringAlign = new StringAlign();

    public static void i(String msg) {
        String message = getCaller(msg);
        Log.i(TAG, message);
    }

    public static void v(String msg) {
        String message = getCaller(msg);
        Log.v(TAG, message);
    }

    public static void e(Throwable t) {
        Log.e(TAG, t.toString());
        StackTraceElement[] traces = t.getStackTrace();
        for (int i = 0; i < traces.length && i < 10; i++) {
            Log.e(TAG, "\tat " + traces[i].toString());
        }
    }

    private static String getCaller(String msg) {
        StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
        if (stackTrace.length < MIN_STACK_TRACE_LENGTH) {
            return msg;
        }

        StackTraceElement caller = stackTrace[CALLER_LOCATION];

        return String.format("[%s]%s(%s:%d)", stringAlign.format(caller.getMethodName()), msg,
                caller.getFileName(),
                caller.getLineNumber());
    }
}
