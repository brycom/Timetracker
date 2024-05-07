package com.TimetrackerBackend.TimetrackerBackend.security.Utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtility {

    public static KeyPair generateKey() {

        KeyPair keyPair;

        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            keyPair = generator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate");
        }

        return keyPair;
    }

}
