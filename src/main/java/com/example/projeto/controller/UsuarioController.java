package com.example.projeto.controller;

import com.example.projeto.dto.UsuarioDTO;
import com.example.projeto.dto.UsuarioResponseDTO;
import com.example.projeto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.listarTodosUsuarios());
    }

    @PostMapping
    public ResponseEntity<Map<Object, String>> salvar(@Valid @RequestBody UsuarioDTO dto) {
        usuarioService.salvarUsuario(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Usuário cadastrado com sucesso."));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Map<Object, String>> atualizar(@PathVariable String email,
                                                         @Valid @RequestBody UsuarioDTO dto) {
        usuarioService.atualizarUsuarioPorEmail(email, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Usuário atualizado com sucesso."));
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<Object, String>> excluir(@PathVariable String email) {
        usuarioService.excluirUsuarioPorEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Usuário excluído com sucesso."));
    }
}
