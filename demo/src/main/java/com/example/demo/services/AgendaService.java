package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.AgendaDTO;
import com.example.demo.dtos.CadastrarAgendaDTO;
import com.example.demo.dtos.ProfessorDTO;

public interface AgendaService {
    void criarNovaAgenda(CadastrarAgendaDTO agendaDTO);
    List<AgendaDTO> listarAgendasPorProfessor(Long professorId);
    void atualizarResumoDeAgenda(Long agendaId, String resumo);
    AgendaDTO obterAgendaDetalhada(Long agendaId);
    String excluirAgendaPorIdentificador(Long agendaId);
    List<AgendaDTO> listarTodasAsAgendas();
    boolean verificarDisponibilidadeProfessor(AgendaDTO agendaDTO);
    List<AgendaDTO> buscarTodasAgendas();
    void adicionarResumo(Long idAgenda, String resumo);
    List<AgendaDTO> visualizarAgendaDeProfessor(Long idProf);
    void cadastrarAgenda(CadastrarAgendaDTO agendaDTO);
}