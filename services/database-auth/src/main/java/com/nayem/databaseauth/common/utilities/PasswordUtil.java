package com.nayem.databaseauth.common.utilities;

import com.nayem.databaseauth.common.exceptions.NullPasswordException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordUtil {
    public static BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static String encryptPassword(String password) throws NullPasswordException {
        if (password == null) throw ExceptionUtil.getNullPasswordException();
        return getBCryptPasswordEncoder().encode(password);
    }

    public static boolean matches(String existingEncodedPassword, String rawPassword) throws NullPasswordException {
        if (existingEncodedPassword == null || rawPassword == null) throw ExceptionUtil.getNullPasswordException();
        return getBCryptPasswordEncoder().matches(rawPassword, existingEncodedPassword);
    }
}
