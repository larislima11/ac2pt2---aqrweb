package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.CursoDTO;
import com.example.demo.dtos.ProfessorDTO;

public interface ProfessorService {
    void criarProfessor(ProfessorDTO professorDTO);
    List<ProfessorDTO> listarProfessoresPorCurso(Long idCurso);
    void adicionarEspecializacao(Long idProfessor, Long idCurso);
    List<ProfessorDTO> listarTodosOsProfessores();
    void adicionar(ProfessorDTO professorDTO);
    List<ProfessorDTO> buscarTodosProfessores();
    List<ProfessorDTO> professoresPorEspecialidade(Long idCurso);
}