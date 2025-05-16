package com.agenda.agendamento.dto;

import java.time.LocalDateTime;

public class AgendamentoResponse {

    private Long id;
    private String descricao;
    private LocalDateTime dataHora;
    private String nomeUsuario;

    // Construtor
    public AgendamentoResponse(Long id, String descricao, LocalDateTime dataHora, String nomeUsuario) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.nomeUsuario = nomeUsuario;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}