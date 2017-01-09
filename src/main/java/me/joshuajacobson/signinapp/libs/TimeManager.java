package me.joshuajacobson.signinapp.libs;

import java.util.Date;

/**
 * Created by joshua on 1/8/17.
 */

public final class TimeManager {
    public static String getTime() {
        Long time = System.currentTimeMillis() / 1000L;
        return time.toString();
    }
}
