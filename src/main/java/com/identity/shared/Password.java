package com.identity.shared;

import com.identity.exeptions.exceptions.VeryWeakPassword;
import com.identity.utils.PassBasedEnc;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Password {
    private String password;
    private String salt;
    private String encryptedPassword;

    public Password(String password) throws VeryWeakPassword {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#.$%^&-+=()])(?=\\S+$).{6,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);

        if (m.matches()) {
            this.salt = PassBasedEnc.getSaltValue(30);
            this.password = password;
            encode(password, this.salt);
            log.info("Encoded password");
        } else {
            throw new VeryWeakPassword();
        }
    }

    public Password(String salt, String encryptedPassword) {
        this.salt = salt;
        this.encryptedPassword = encryptedPassword;
    }

    public boolean verify(String password) {
        return PassBasedEnc.verifyUserPassword(password, this.encryptedPassword, this.salt);
    }

    public String getSalt() {
        return salt;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    private void encode(String password, String salt) {
        this.encryptedPassword = PassBasedEnc.generateSecurePassword(password, salt);
    }
}