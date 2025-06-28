package com.example.projeto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Deve ser um e-mail válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public @NotBlank(message = "O nome é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O e-mail é obrigatório.") @Email(message = "Deve ser um e-mail válido.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O e-mail é obrigatório.") @Email(message = "Deve ser um e-mail válido.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "A senha é obrigatória.") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "A senha é obrigatória.") String senha) {
        this.senha = senha;
    }
}
