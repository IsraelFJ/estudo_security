package com.example.projeto.service;

import com.example.projeto.dto.UsuarioDTO;
import com.example.projeto.dto.UsuarioResponseDTO;
import com.example.projeto.enums.Role;
import com.example.projeto.model.Usuario;
import com.example.projeto.repository.UsuarioRepository;
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

    public Usuario salvarUsuario(UsuarioDTO dto) {
        usuarioRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> { throw new IllegalArgumentException("E-mail já cadastrado."); });

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha())); // Criptografando a senha.
        usuario.setRole(Role.ROLE_USER); // Usuário comum por padrão

        return usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return usuarioRepository
            .findAll()
            .stream()
            .map(usuario -> new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail()))
            .toList();
    }

    public Usuario atualizarUsuarioPorEmail(String email, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public void excluirUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));

        usuarioRepository.delete(usuario);
    }

}
