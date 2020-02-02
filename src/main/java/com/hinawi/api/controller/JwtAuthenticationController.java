package com.hinawi.api.controller;

import java.util.Objects;

import com.hinawi.api.config.JwtTokenUtil;
import com.hinawi.api.domains.Users;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.dto.JwtRequest;
import com.hinawi.api.dto.JwtResponse;
import com.hinawi.api.dto.UserDto;
import com.hinawi.api.services.impl.JwtUserDetailsServiceImpl;
import com.hinawi.api.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/")
public class JwtAuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    //http://localhost:8091/api/authenticate
   // {"email":"shadi@hinawi.ae","password":"password"}

    @RequestMapping(value = "/authenticate1", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value= Constants.AUTHENTICATE, method= RequestMethod.POST)
    public ApiResponse<UserDto> loginUser(@RequestBody Users users) {
        // UserDto userDto=UserConverter.entityToDto(users);
        try {
            authenticate(users.getEmail(), users.getPassword());

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(users.getEmail());

           final Users dbUsers= userDetailsService.loginUsers(users);

           final String token = jwtTokenUtil.generateToken(dbUsers);

           // Users dbUser = userService.loginUsers(users);
            if (userDetails == null) {
                return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "User not found !!.", userDetails);
            } else if (userDetails.getUsername()!=null) {


                UserDto userDto=new UserDto();
                userDto.setUserName(dbUsers.getUserName());
                userDto.setEmail(users.getEmail());
                userDto.setUserId(dbUsers.getUserId());
                userDto.setToken(token);
                GrantedAuthority simpleGrantedAuthority= userDetails.getAuthorities().stream().findFirst().orElse(null);
                if(simpleGrantedAuthority!=null)
                userDto.setRole(simpleGrantedAuthority.getAuthority());
                else
                    userDto.setRole("");

                return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", userDto, true);
            }
            else
                return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "User not found !!.", userDetails,false);
        }
        catch (UsernameNotFoundException ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null,false);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Invalid username or password", null,false);
        }
    }

}
