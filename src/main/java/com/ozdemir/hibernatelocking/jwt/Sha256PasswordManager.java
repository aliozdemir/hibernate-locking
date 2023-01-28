package com.ozdemir.hibernatelocking.jwt;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Sha256PasswordManager implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.sha256Hex(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        final String encodedRawPassword = DigestUtils.sha256Hex(rawPassword.toString());
        return encodedPassword.equals(encodedRawPassword);
    }
}
