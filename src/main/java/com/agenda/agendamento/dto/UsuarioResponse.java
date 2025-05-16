package com.agenda.agendamento.dto;

public class UsuarioResponse {
    private Long id;
    private String nome;
    private String email;

    public UsuarioResponse(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}