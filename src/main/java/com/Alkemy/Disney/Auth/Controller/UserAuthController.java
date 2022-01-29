package com.Alkemy.Disney.Auth.Controller;

import com.Alkemy.Disney.Auth.DTO.AuthenticationRequest;
import com.Alkemy.Disney.Auth.DTO.AuthenticationResponse;
import com.Alkemy.Disney.Auth.DTO.UsuarioDTO;
import com.Alkemy.Disney.Auth.Service.JwtUtils;
import com.Alkemy.Disney.Auth.Service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/singup")
    public ResponseEntity<AuthenticationResponse> singUp(@Validated @RequestBody UsuarioDTO usuarioDTO) throws Exception{
        userDetailsCustomService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/singin")
    public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        UserDetails userDetails;
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
                    );
            userDetails = (UserDetails) auth.getPrincipal();
        }catch(BadCredentialsException e){
            throw new Exception("Incorrect username or pasword" , e);
        }
        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
