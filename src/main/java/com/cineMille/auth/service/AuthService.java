package com.cineMille.auth.service;

import com.cineMille.auth.payload.LoginDto;
import com.cineMille.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
