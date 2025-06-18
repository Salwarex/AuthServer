package ru.trifonov.hasning;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Argon2 {
    private static Argon2PasswordEncoder encoder;

    public Argon2(int iterations, int memory, int parallelism, int hashLength, int saltLength){
        encoder = new Argon2PasswordEncoder(
                saltLength,
                hashLength,
                parallelism,
                memory,
                iterations
        );
    }

    public String hash(String str){
        return encoder.encode(str);
    }
    public boolean isMatch(String str, String hash){
        return encoder.matches(str, hash);
    }


}
