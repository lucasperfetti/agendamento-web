package com.agenda.agendamento.service;

import com.agenda.agendamento.model.Usuario;
import com.agenda.agendamento.repository.UsuarioRepository;
import com.agenda.agendamento.dto.UsuarioRequest;
import com.agenda.agendamento.dto.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioResponse criarUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(encoder.encode(request.getSenha()));
        repository.save(usuario);

        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public List<UsuarioResponse> listarTodos() {
        return repository.findAll().stream()
                .map(u -> new UsuarioResponse(u.getId(), u.getNome(), u.getEmail()))
                .collect(Collectors.toList());
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
