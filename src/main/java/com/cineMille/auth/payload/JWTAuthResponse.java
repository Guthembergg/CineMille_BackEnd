package com.cineMille.auth.payload;

import java.util.List;
import java.util.Set;

import com.cineMille.auth.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
	private String username;
    private String accessToken;
    private String tokenType = "Bearer";
    private Set<Role> roles;
}
