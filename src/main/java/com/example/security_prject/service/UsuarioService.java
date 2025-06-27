package com.example.security_prject.service;

import com.example.security_prject.dto.UsuarioDTO;
import com.example.security_prject.dto.UsuarioResponseDTO;
import com.example.security_prject.model.Usuario;
import com.example.security_prject.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Usuario salavarUsuario(UsuarioDTO dto){
        usuarioRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {throw new IllegalArgumentException("E-mail já cadastrado");});

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseDTO> listarTodos(){
        return usuarioRepository
                .findAll()
                .stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail()))
                .toList();
    }


}
