package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.AgendaDTO;
import com.example.demo.dtos.CadastrarAgendaDTO;
import com.example.demo.models.Agenda;
import com.example.demo.models.Curso;
import com.example.demo.models.Professor;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfessorRepository;

@Service
public class AgendaServiceImp implements AgendaService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional
    public void criarNovaAgenda(CadastrarAgendaDTO agendaDTO) {
        Professor professor = professorRepository.findById(agendaDTO.getIdProfessor())
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Curso curso = cursoRepository.findById(agendaDTO.getIdCurso())
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Agenda agenda = Agenda.builder()
            .dataInicio(agendaDTO.getDataInicio())
            .dataFim(agendaDTO.getDataFim())
            .horarioInicio(agendaDTO.getHorarioInicio())
            .horarioFim(agendaDTO.getHorarioFim())
            .cidade(agendaDTO.getCidade())
            .estado(agendaDTO.getEstado())
            .cep(agendaDTO.getCep())
            .professor(professor)
            .curso(curso)
            .build();

        if (!verificarDisponibilidadeProfessor(new AgendaDTO(agenda.getId(), agenda.getDataInicio(), agenda.getDataFim(),
                agenda.getHorarioInicio(), agenda.getHorarioFim(), agenda.getCidade(), agenda.getEstado(),
                agenda.getCep(), agenda.getProfessor(), agenda.getCurso(), agenda.getResumo()))) {
            throw new IllegalArgumentException("Professor já possui agenda nesse período");
        }
        
        agendaRepository.save(agenda);
    }

    @Override
    public List<AgendaDTO> listarAgendasPorProfessor(Long professorId) {
        professorRepository.findById(professorId)
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        List<Agenda> agendas = agendaRepository.findByProfessorId(professorId);
        
        return agendas.stream()
            .map(this::convertToAgendaDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void atualizarResumoDeAgenda(Long agendaId, String resumo) {
        Agenda agenda = agendaRepository.findById(agendaId)
            .orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));

        agenda.setResumo(resumo);
        agendaRepository.save(agenda);
    }

    @Override
    public AgendaDTO obterAgendaDetalhada(Long agendaId) {
        Agenda agenda = agendaRepository.findById(agendaId)
            .orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));

        return convertToAgendaDTO(agenda);
    }

    @Override
    public String excluirAgendaPorIdentificador(Long agendaId) {
        if (!agendaRepository.existsById(agendaId)) {
            throw new IllegalArgumentException("Agenda não encontrada");
        }

        agendaRepository.deleteById(agendaId);
        return "Agenda removida com sucesso";
    }

    @Override
    public List<AgendaDTO> listarTodasAsAgendas() {
        List<Agenda> agendas = agendaRepository.findAll();

        return agendas.stream()
            .map(this::convertToAgendaDTO)
            .collect(Collectors.toList());
    }

    @Override
    public boolean verificarDisponibilidadeProfessor(AgendaDTO agendaDTO) {
        List<Agenda> agendas = agendaRepository
            .findByProfessorIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
                agendaDTO.getProfessor().getId(),
                agendaDTO.getDataInicio(),
                agendaDTO.getDataFim());

        return agendas.isEmpty();
    }

    private AgendaDTO convertToAgendaDTO(Agenda agenda) {
        return new AgendaDTO(
            agenda.getId(),
            agenda.getDataInicio(),
            agenda.getDataFim(),
            agenda.getHorarioInicio(),
            agenda.getHorarioFim(),
            agenda.getCidade(),
            agenda.getEstado(),
            agenda.getCep(),
            agenda.getProfessor(),
            agenda.getCurso(),
            agenda.getResumo());
    }

    @Override
    public List<AgendaDTO> buscarTodasAgendas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodasAgendas'");
    }

    @Override
    public void adicionarResumo(Long idAgenda, String resumo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarResumo'");
    }

    @Override
    public List<AgendaDTO> visualizarAgendaDeProfessor(Long idProf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarAgendaDeProfessor'");
    }

    @Override
    public void cadastrarAgenda(CadastrarAgendaDTO agendaDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrarAgenda'");
    }
}