package com.Alkemy.Disney.Auth.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UsuarioDTO {

    @Email(message = "Username must be n email")
    private String username;
    @Size(min = 8)
    private String password;
}
