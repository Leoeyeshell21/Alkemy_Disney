package com.Alkemy.Disney.Auth.Service;

import com.Alkemy.Disney.Auth.DTO.UsuarioDTO;
import com.Alkemy.Disney.Auth.Entity.Usuario;
import com.Alkemy.Disney.Auth.Repository.UserRepository;
import com.Alkemy.Disney.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuarioEntity = userRepository.findByUsername(username);
        if(usuarioEntity == null){
            throw new UsernameNotFoundException("Username or Password Not Found");
        }
        return new User(usuarioEntity.getUsername(), usuarioEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UsuarioDTO usuarioDTO){
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setUsername(usuarioDTO.getUsername());
        usuarioEntity.setPassword(usuarioDTO.getPassword());
        usuarioEntity = userRepository.save(usuarioEntity);
        if(usuarioEntity != null){
            emailService.sendWelcomeEmailTo(usuarioEntity.getUsername());
        }
        return usuarioEntity != null;
    }
}
