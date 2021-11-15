package com.ssafy.heypapa.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.heypapa.entity.User;

public class PapaUserDetails implements UserDetails {

	@Autowired
	User user;
	
	boolean accountNonExpired;
	boolean accountNonLocked;
	boolean credentailNonExpired;
	boolean enabled = false;
	List<GrantedAuthority> roles = new ArrayList<>();
	
	public PapaUserDetails(User user) {
		super();
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setAuthorities(List<GrantedAuthority> roles) {
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getNickname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentailNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
