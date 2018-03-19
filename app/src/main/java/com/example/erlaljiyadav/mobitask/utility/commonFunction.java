package com.example.erlaljiyadav.mobitask.utility;

import java.util.regex.Pattern;

/**
 * Created by lalji on 9/4/2017.
 */

public class commonFunction {
    public commonFunction() {

    }

    public static boolean isValidMail(String email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

    }

    public static boolean isValideEmailPattern(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern) && email.length() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isValidMobile(String phone) {

        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 6 || phone.length() > 13) {
                check = false;
                // Toast.makeText(LoginActivity.this, "Mobile Digit is not Valid", Toast.LENGTH_LONG).show();
            } else {
                check = true;
            }
        } else {
            check = false;
        }

        return check;
    }


}
