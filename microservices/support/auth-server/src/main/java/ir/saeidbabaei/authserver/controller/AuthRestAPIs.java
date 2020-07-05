package ir.saeidbabaei.authserver.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ir.saeidbabaei.authserver.message.request.SignUpForm;
import ir.saeidbabaei.authserver.message.response.ResponseMessage;
import ir.saeidbabaei.authserver.model.Role;
import ir.saeidbabaei.authserver.model.RoleName;
import ir.saeidbabaei.authserver.model.User;
import ir.saeidbabaei.authserver.repository.RoleRepository;
import ir.saeidbabaei.authserver.repository.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/oauth")
public class AuthRestAPIs {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), false);

		Set<String> strRoles = new HashSet<>();
		Set<Role> roles = new HashSet<>();

		if (signUpRequest.getRole()==null)
		{
			strRoles.add("USER");
		}
		else
		{
			strRoles = signUpRequest.getRole();
		}
		
		strRoles.forEach(role -> {
			switch (role) {
			case "ADMIN":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				break;			
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});
			
		user.setRoles(roles);
		
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
	
	@PutMapping("/approve-user/{username}")
	public ResponseEntity<?> registerUser(@PathVariable String username) {
		
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		
		user.setEnabled(true);
		
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }
    
    //for test auth-server
    @GetMapping("/callback")
    public String callback() {
        return "Successfull Response";
    }
}