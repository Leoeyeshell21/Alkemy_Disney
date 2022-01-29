package com.Alkemy.Disney.Auth.Entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Email
    private String username;
    @Size(min = 8)
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialNonExpired;
    private Boolean enable;

    public Usuario(){
        accountNonExpired = true;
        accountNonLocked = true;
        credentialNonExpired = true;
        enable = true;
    }

    public Long getId(){ return id;}

    public void setId(Long id){this.id = id;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    public String getUsername(){return username;}

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

}
