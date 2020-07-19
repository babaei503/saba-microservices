package ir.saeidbabaei.authserver.security.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.jwk.JWKSet;

@RestController
class JwkSetEndpoint {
	
    @Autowired
    private JWKSet jwkSet;

	@GetMapping("/.well-known/jwks.json")
	@ResponseBody
	public Map<String, Object> getKey() {	    	    
        return this.jwkSet.toJSONObject();
	}
}