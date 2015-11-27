package ua.nure.sigma.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ua.nure.sigma.dao.UserDao;
import ua.nure.sigma.db_entities.UserRole;
import ua.nure.sigma.exceptions.AccountException;

public class CustomUserDetailsService implements UserDetailsService {
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(final String email) 
               throws UsernameNotFoundException {

		ua.nure.sigma.db_entities.User user = userDao.getUserForAuth(email);
		if (user == null) {
			throw new AccountException("No such user!");
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());

		return buildUserForAuthentication(user, authorities);
		

	}

	private User buildUserForAuthentication(ua.nure.sigma.db_entities.User user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getEmail(), 
			user.getPassword(), true, 
                        true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
