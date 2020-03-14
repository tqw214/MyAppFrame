package com.viger.myappframe.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class PatternUtils {

    public static boolean checkEmail(String email) {
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

}
