package com.authservice.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.authservice.proxyentity.user.Applicant;
import com.authservice.proxyentity.user.Recruiter;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {

		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl getApplicant(Applicant applicant) {
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(applicant.getRole()));
		return new UserDetailsImpl(applicant.getUsername(), applicant.getPassword(), authorities);
	}

	public static UserDetailsImpl getRecruiter(Recruiter recruiter) {
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(recruiter.getRole()));
		return new UserDetailsImpl(recruiter.getUsername(), recruiter.getPassword(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}