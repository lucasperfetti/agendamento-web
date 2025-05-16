package com.agenda.agendamento.service;

import com.agenda.agendamento.dto.AgendamentoRequest;
import com.agenda.agendamento.dto.AgendamentoResponse;
import com.agenda.agendamento.model.Agendamento;
import com.agenda.agendamento.model.Usuario;
import com.agenda.agendamento.repository.AgendamentoRepository;
import com.agenda.agendamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AgendamentoResponse criarAgendamento(AgendamentoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Agendamento agendamento = new Agendamento();
        agendamento.setDescricao(request.getDescricao());
        agendamento.setDataHora(request.getDataHora());
        agendamento.setUsuario(usuario);

        agendamento = agendamentoRepository.save(agendamento);

        return mapToResponse(agendamento);
    }

    public List<AgendamentoResponse> listarTodos() {
        return agendamentoRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public AgendamentoResponse buscarPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return mapToResponse(agendamento);
    }

    public AgendamentoResponse atualizar(Long id, AgendamentoRequest request) {
        Agendamento agendamento = agendamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        agendamento.setDescricao(request.getDescricao());
        agendamento.setDataHora(request.getDataHora());
        agendamento.setUsuario(usuario);

        agendamento = agendamentoRepository.save(agendamento);
        return mapToResponse(agendamento);
    }

    public void deletar(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new RuntimeException("Agendamento não encontrado");
        }
        agendamentoRepository.deleteById(id);
    }

    private AgendamentoResponse mapToResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
            agendamento.getId(),
            agendamento.getDescricao(),
            agendamento.getDataHora(),
            agendamento.getUsuario().getNome()
        );
    }
}
