package com.example.security_prject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O email é obrigatorio")
    @Email(message = "O email deve ser valido")
    private String email;

    @NotBlank(message = "Senha é obrigatorio")
    @Size(min = 6, message = "A senha precisa ter o 6 caracteres")
    private String senha;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public @NotBlank(message = "O nome é obrigatorio") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatorio") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O email é obrigatorio") @Email(message = "O email deve ser valido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O email é obrigatorio") @Email(message = "O email deve ser valido") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Senha é obrigatorio") @Size(min = 6, message = "A senha precisa ter o 6 caracteres") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "Senha é obrigatorio") @Size(min = 6, message = "A senha precisa ter o 6 caracteres") String senha) {
        this.senha = senha;
    }
}
