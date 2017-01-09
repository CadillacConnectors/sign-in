package me.joshuajacobson.signinapp.libs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by joshua on 1/8/17.
 */

public final class Hasher {
    public static String hash(String pin) {
        try {
            return new String(MessageDigest.getInstance("MD5").digest(pin.getBytes("UTF-8")), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("This device does not support UTF-8 Encoding");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("This device || java version || android version does not support MD5 hashing");
            e.printStackTrace();
        }
        return null;
    }
}
