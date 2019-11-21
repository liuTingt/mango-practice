package com.louis.mango.admin.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @Description 
 *	安全用户模型
 * @author lt
 *
 */
public class JwtUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private String salt;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public JwtUserDetails(String username, String password, String salt,
			Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.authorities = authorities;
	}

	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSalt() {
		return salt;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

}