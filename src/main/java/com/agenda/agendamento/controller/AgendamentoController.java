package com.agenda.agendamento.controller;

import com.agenda.agendamento.dto.AgendamentoRequest;
import com.agenda.agendamento.dto.AgendamentoResponse;
import com.agenda.agendamento.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public AgendamentoResponse agendar(@RequestBody @Valid AgendamentoRequest request) {
        return service.criarAgendamento(request);
    }

    @GetMapping
    public List<AgendamentoResponse> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public AgendamentoResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AgendamentoResponse atualizar(@PathVariable Long id, @RequestBody @Valid AgendamentoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}