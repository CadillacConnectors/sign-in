package me.joshuajacobson.signinapp.libs.datatypes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import me.joshuajacobson.signinapp.libs.Hasher;

/**
 * Created by joshua on 1/8/17.
 */

public class User {

    private String firstname, lastname, id, pin_hashed, permissions;

    public User(String firstname, String lastname, String id, String pin, String permissions) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        this.pin_hashed = Hasher.hash(pin);
        this.permissions = permissions;
    }

    public User(String id) {

    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPin_hashed() {
        return pin_hashed;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public String getId() {
        return id;
    }

    public boolean validatePin(String pin) {
        return pin_hashed.equals(Hasher.hash(pin));
    }

    public String getPermissions() {
        return permissions;
    }



}
