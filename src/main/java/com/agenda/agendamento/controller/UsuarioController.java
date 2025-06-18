package com.agenda.agendamento.controller;

import com.agenda.agendamento.dto.UsuarioRequest;
import com.agenda.agendamento.dto.UsuarioResponse;
import com.agenda.agendamento.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public UsuarioResponse cadastrar(@RequestBody @Valid UsuarioRequest request) {
        return service.criarUsuario(request);
    }

    @GetMapping
    public List<UsuarioResponse> listarTodos() {
        return service.listarTodos();
    }
}
