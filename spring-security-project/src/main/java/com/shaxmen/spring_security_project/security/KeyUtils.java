package com.shaxmen.spring_security_project.security;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {

  private KeyUtils() {
  }

  // Keys shouldn't be storing locally in our source code but we need to use Vault, AWS, Secrets manager that's why I did store the private key !!!
  public static PrivateKey loadPrivateKey(final String filePath) throws Exception {
    final String key = readKeyFromResource(filePath).replace("-----BEGIN PRIVATE KEY-----", "")
        .replace("-----END PRIVATE KEY-----", "")
        .replaceAll("\\s+", "");

    final byte[] decoded = Base64.getDecoder().decode(key);
    final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
    return KeyFactory.getInstance("RSA").generatePrivate(spec);
  }

  public static PublicKey loadPublicKey(final String filePath) throws Exception {
    final String key = readKeyFromResource(filePath).replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replaceAll("\\s+", "");

    final byte[] decoded = Base64.getDecoder().decode(key);
    final X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
    return KeyFactory.getInstance("RSA").generatePublic(spec);
  }

  private static String readKeyFromResource(String filePath) throws Exception {
    try (final InputStream inputStream = KeyUtils.class.getClassLoader()
        .getResourceAsStream(filePath)) {
      if (inputStream == null) {
        throw new IllegalArgumentException("Resource not found: " + filePath);
      }
      return new String(inputStream.readAllBytes());
    }
  }

}
