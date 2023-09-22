package org.oao.eticket.common;

import org.oao.eticket.exception.UnexpectedException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenUtils {

  public static String signatureOf(final String jwt) {
    final MessageDigest sha256;
    try {
      sha256 = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      throw new UnexpectedException(e);
    }

    final var jwtSignature = jwt.substring(jwt.lastIndexOf('.') + 1);
    sha256.digest(jwtSignature.getBytes(StandardCharsets.UTF_8));

    return sha256.toString();
  }
}
