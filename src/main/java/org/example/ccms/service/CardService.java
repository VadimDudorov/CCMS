package org.example.ccms.service;

import lombok.RequiredArgsConstructor;
import org.example.ccms.exception.InternalExceptionCcms;
import org.example.ccms.exception.ValidationExceptionCcms;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CardService {

    @Value("${secret.key}")
    private String secretKey;


    public String hash(String number) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return Base64.getEncoder().encodeToString(messageDigest.digest(number.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new InternalExceptionCcms("Ошибка хеширования");
        }
    }

    public String cipherEncrypt(String number) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(number.getBytes()));

        } catch (NoSuchAlgorithmException e) {
            throw new InternalExceptionCcms("Ошибка шифрования NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            throw new InternalExceptionCcms("Ошибка шифрования NoSuchPaddingException");
        } catch (InvalidKeyException e) {
            throw new InternalExceptionCcms("Ошибка шифрования InvalidKeyException");
        } catch (IllegalBlockSizeException e) {
            throw new InternalExceptionCcms("Ошибка шифрования IllegalBlockSizeException");
        } catch (BadPaddingException e) {
            throw new InternalExceptionCcms("Ошибка шифрования BadPaddingException");
        }
    }

    public String cipherDecrypt(String number) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(number.getBytes()));

        } catch (NoSuchAlgorithmException e) {
            throw new InternalExceptionCcms("Ошибка шифрования NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            throw new InternalExceptionCcms("Ошибка шифрования NoSuchPaddingException");
        } catch (InvalidKeyException e) {
            throw new InternalExceptionCcms("Ошибка шифрования InvalidKeyException");
        } catch (IllegalBlockSizeException e) {
            throw new InternalExceptionCcms("Ошибка шифрования IllegalBlockSizeException");
        } catch (BadPaddingException e) {
            throw new InternalExceptionCcms("Ошибка шифрования BadPaddingException");
        }
    }

    public String maskedNumber(String number) {

        if (number.length() < 4) {
            throw new ValidationExceptionCcms("Номер карты слишком короткий");
        }
        return "*".repeat(number.length() - 4) + number.substring(number.length() - 4);
    }
}
