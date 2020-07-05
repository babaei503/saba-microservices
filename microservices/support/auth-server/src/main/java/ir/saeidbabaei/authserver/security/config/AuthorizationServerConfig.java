package ir.saeidbabaei.authserver.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import ir.saeidbabaei.authserver.security.services.UserDetailsServiceImpl;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

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
	  JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	  return converter;	  
   }

   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	  clients.jdbc(dataSource);
   }

   @Bean
   public JdbcTokenStore tokenStore() {
	  return new JdbcTokenStore(dataSource);
   }
   

}