package com.example.air_quality_check.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/* Jwt response which gives the details of the users based on auth type */
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities,String firstname,String lastname,String email) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}