package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.CursoDTO;

public interface CursoService {
    void criarCurso(CursoDTO cursoDTO);
    void associarProfessorAoCurso(Long idCurso, Long idProfessor);
    List<CursoDTO> listarTodosOsCursos();
    CursoDTO buscarCursoPorId(Long idCurso);
    List<CursoDTO> buscarCursos();
    void adicionar(CursoDTO cursoDTO);
    void adicionarProfessor(Long idCurso, Long idProfessor);
}