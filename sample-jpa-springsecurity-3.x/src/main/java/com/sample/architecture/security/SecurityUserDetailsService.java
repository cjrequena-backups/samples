package com.sample.architecture.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sample.model.dao.IRightDAO;
import com.sample.model.dao.IUserDAO;
import com.sample.model.jpa.Right;
import com.sample.model.jpa.Role;
import com.sample.model.jpa.User;

public class SecurityUserDetailsService implements UserDetailsService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IRightDAO rightDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		Collection<GrantedAuthority> grantedAuthorities = null;

		try {
			user = this.userDAO.findByEmail(email);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Invalid User");
		}

		if (user == null) {
			throw new UsernameNotFoundException("Invalid User");
		}

		grantedAuthorities = getGrantedAuthority(user);

		// Create the UserDetails object for a specified user with
		// their grantedAuthorities List.
		final UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked(), grantedAuthorities);

		return userDetails;
	}

	private Collection<GrantedAuthority> getGrantedAuthority(User user) {

		// get the list of rights for a specified user from db.
		Collection<Right> rights;
		Collection<Role> roles;
		try {
			rights = rightDAO.findRightsByUser(user);
			roles = user.getRoles();

			// create the list for the spring grantedRights
			final ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(rights.size() + roles.size());

			// now create for all rights a GrantedAuthority entry
			// and fill the GrantedAuthority List with these authorities.
			for (final Right right : rights) {
				grantedAuthorities.add(new SimpleGrantedAuthority(right.getRightName()));
			}

			// now create for all roles a GrantedAuthority entry
			// and fill the GrantedAuthority List with these authorities.
			for (final Role role : roles) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}

			return grantedAuthorities;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
