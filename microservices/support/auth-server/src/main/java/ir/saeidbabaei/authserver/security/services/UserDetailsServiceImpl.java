package ir.saeidbabaei.authserver.security.services;

import ir.saeidbabaei.authserver.exception.UserNotActivatedException;
import ir.saeidbabaei.authserver.model.User;
import ir.saeidbabaei.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		
		if (!user.isEnabled())
		{
			throw new UserNotActivatedException("User " + user.getUsername() + " is not activated");
		}

		return UserPrinciple.build(user);
	}
}