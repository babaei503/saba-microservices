package ir.saeidbabaei.authserver.security.config;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

import ir.saeidbabaei.authserver.security.services.UserDetailsServiceImpl;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

   @Value("${saba.appconfig.KEY_STORE_FILE}")
   private String KEY_STORE_FILE;
   
   @Value("${saba.appconfig.KEY_STORE_PASSWORD}")
   private String KEY_STORE_PASSWORD;
   
   @Value("${saba.appconfig.KEY_ALIAS}")
   private String KEY_ALIAS;
   
   @Value("${saba.appconfig.JWK_KID}")
   private String JWK_KID;
	   
   @Autowired
   private DataSource dataSource;
   
   @Autowired
   private AuthenticationManager authenticationManager;
 
   @Autowired
   UserDetailsServiceImpl userDetailsService;
   
   @Autowired
   PasswordEncoder passwordEncoder;
   
   @Autowired
   ClientDetailsService clientDetailsService;
   
   @Autowired
   KeyPair keyPair;
   
   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	   endpoints.authenticationManager(this.authenticationManager)
	  			.tokenStore(tokenStore())
	    		.accessTokenConverter(accessTokenConverter())
	    		.authorizationCodeServices(authorizationCodeServices())
	    		.setClientDetailsService(clientDetailsService);  
   }

   @Override
   public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	  oauthServer.checkTokenAccess("permitAll()")
	  			 .checkTokenAccess("isAuthenticated()")
	  			 .passwordEncoder(passwordEncoder);
   }
   
   @Bean
   protected AuthorizationCodeServices authorizationCodeServices() {
	   return new JdbcAuthorizationCodeServices(dataSource);
   }

   @Bean
   public JwtAccessTokenConverter accessTokenConverter() {	   
       Map<String, String> customHeaders = Collections.singletonMap("kid", JWK_KID);
       return new JwtCustomHeadersAccessTokenConverter(customHeaders, keyPair());       
   }

   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	  clients.jdbc(dataSource);
   }

   @Bean
   public JdbcTokenStore tokenStore() {
	  return new JdbcTokenStore(dataSource);
   }
 
   @Bean
   public KeyPair keyPair() {
       ClassPathResource ksFile = new ClassPathResource(KEY_STORE_FILE);
       KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, KEY_STORE_PASSWORD.toCharArray());
       return ksFactory.getKeyPair(KEY_ALIAS);
   }
   
   @Bean
   public JWKSet jwkSet() {
       RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
    		   .keyUse(KeyUse.SIGNATURE)
    		   .algorithm(JWSAlgorithm.RS256)
    		   .keyID(JWK_KID);
       return new JWKSet(builder.build());
   }   

}