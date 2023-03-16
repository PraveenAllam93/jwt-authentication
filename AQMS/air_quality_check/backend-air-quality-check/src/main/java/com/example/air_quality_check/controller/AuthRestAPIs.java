package com.example.air_quality_check.controller;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.air_quality_check.message.request.LoginForm;
import com.example.air_quality_check.message.request.SignUpForm;
import com.example.air_quality_check.message.response.JwtResponse;
import com.example.air_quality_check.message.response.ResponseMessage;
import com.example.air_quality_check.model.UserModel.Role;
import com.example.air_quality_check.model.UserModel.RoleName;
import com.example.air_quality_check.model.UserModel.User;
import com.example.air_quality_check.repository.RoleRepository;
import com.example.air_quality_check.repository.UserRepository;
import com.example.air_quality_check.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/aqms")
public class AuthRestAPIs {

	@Autowired	
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	/* Post Mapping request which takes the request body in the login form and helps to login the user */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println(jwt);
		User data = userRepository.findByUsername(userDetails.getUsername()).get();
		
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), data.getFirstname(), data.getLastname(), data.getEmail()));
	}

	/* Post Mapping request which takes the request body in the signup form and helps to signup the user */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser( @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getFirstname(), signUpRequest.getLastname(), 
								signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>(); 
		 
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(userRole);			
	
		
		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User "+ signUpRequest.getFirstname() + " " + signUpRequest.getLastname()+ " is registered successfully!"), HttpStatus.OK);
	}
}