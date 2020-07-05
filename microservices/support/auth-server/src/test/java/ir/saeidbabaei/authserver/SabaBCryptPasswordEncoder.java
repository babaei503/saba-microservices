package ir.saeidbabaei.authserver;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SabaBCryptPasswordEncoder {

  @Test
  void bcryptPasswordEncoder() {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    /*
     * Users: admin/admin123 user/user123
     * 
     */

    System.out.println("Admin's Bcrypt encrypted password = " + encoder.encode("admin123"));
    System.out.println("User's Bcrypt encrypted password = " + encoder.encode("user123"));

    /*
     * Users: Client: saba-product-api-service/secret123
     * 
     */
    System.out.println("Client's(saba-product-api-service) Bcrypt encrypted password = " + encoder.encode("secret123"));
  }
}